package com.gstdev.cloud.data.core.service;

import com.gstdev.cloud.base.core.utils.treeUtils.TreeFactory;
import com.gstdev.cloud.base.definition.domain.Result;
import com.gstdev.cloud.base.definition.domain.base.pojo.BaseDtoInterface;
import com.gstdev.cloud.base.definition.domain.base.pojo.BaseTreeDtoInterface;
import com.gstdev.cloud.data.core.entity.BaseTreeEntityINT;
import com.gstdev.cloud.data.core.repository.BaseTreeRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public interface BaseTreeServiceDefault<E extends BaseTreeEntityINT<ID>
        , ID extends Serializable
        , D extends BaseTreeDtoInterface<D, ID> & BaseDtoInterface<ID>
        > extends BaseDtoServiceDefault<E, ID, D>, BaseTreeService<E, ID, D> {
    @Override
    BaseTreeRepository<E, ID> getRepository();
    @Override
    BaseTreeServiceDefault<E, ID,D> getService();
    @Transactional(readOnly = true)
    default List<E> findByParentId(ID parentId) {
        return getRepository().findByParentId(parentId);
    }

    @Override
    @Transactional(readOnly = true)
    default List<D> findByParentIdToDto(ID parentId) {
        return getMapper().toDto(getService().findByParentId(parentId));
    }
    @Transactional(readOnly = true)
    default List<E> findSubsets(ID id) {
        List<E> subsets = getService().findByParentId(id);
        List<E> data = new ArrayList<>();
        if (ObjectUtils.isEmpty(subsets)) {
            return subsets;
        }
        for (E subset : subsets) {
            data.addAll(getService().findSubsets(subset.getId()));
        }
        return subsets;
    }

    @Override
    @Transactional(readOnly = true)
    default List<D> findSubsetsToDto(ID id) {
        return getMapper().toDto(getService().findSubsets(id));
    }

    @Override
    @Transactional(readOnly = true)
    default List<D> findItselfAndSubsetsToDto(ID id) {
        List<D> subsetsToDto = getService().findSubsetsToDto(id);
        subsetsToDto.add(getService().findByIdToDto(id));
        return subsetsToDto;
    }

    @Override
    @Transactional(readOnly = true)
    default D findByIdToTreeToDto(ID id) {
        D byId = getService().findByIdToDto(id);
        List<D> children = getService().findChildren(byId);
        byId.setChildren(children);
        return byId;
    }

    @Override
    @Transactional(readOnly = true)
    default Result<D> findByIdToTreeToResult(ID id) {
        return Result.success(getService().findByIdToTreeToDto(id));
    }

    @Override
    @Transactional(readOnly = true)
    default List<D> findByParentIdToTreeToDto(ID parentId) {
        D byId = getService().findByIdToTreeToDto(parentId);
        return byId.getChildren();
    }

    @Override
    @Transactional(readOnly = true)
    default Result<List<D>> findByParentIdToTreeToResult(ID parentId) {
        return Result.success(getService().findByParentIdToTreeToDto(parentId));
    }

    @Override
    @Transactional(readOnly = true)
    default Result<List<D>> findByParentIdToResult(ID parentId) {
        return Result.success(getService().findByParentIdToDto(parentId));
    }

    @Override
    @Transactional(readOnly = true)
    default List<D> findAllByQueryCriteriaToDtoToTree(Specification<E> specification) {
        List<D> allByQueryCriteriaToDto = getService().findAllToDto(specification);
        return new TreeFactory().buildTree(allByQueryCriteriaToDto);
    }


    @Transactional(readOnly = true)
    default List<D> findChildren(D var) {
        List<D> byParentIdToDto = getService().findByParentIdToDto(var.getId());
        if (!CollectionUtils.isEmpty(byParentIdToDto)) {
            byParentIdToDto.forEach(item -> item.setChildren(getService().findChildren(item)));
            return byParentIdToDto;
        }
        return new ArrayList<>();
    }

}
