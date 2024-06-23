package com.gstdev.cloud.data.core.service;

import com.gstdev.cloud.base.core.utils.treeUtils.TreeFactory;
import com.gstdev.cloud.base.definition.domain.Result;
import com.gstdev.cloud.base.definition.domain.base.pojo.*;
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

    @Transactional(readOnly = true)
    default List<E> findByParentId(ID parentId) {
        List<E> byId = getRepository().findByParentId(parentId);
        return byId;
    }

    @Override
    @Transactional(readOnly = true)
    default List<D> findByParentIdToDto(ID parentId) {
        List<D> byId = getMapper().toDto(findByParentId(parentId));
        return byId;
    }

    default List<E> findSubsets(ID id) {
        List<E> subsets = findByParentId(id);
        List<E> data = new ArrayList<>();
        if (ObjectUtils.isEmpty(subsets)) {
            return subsets;
        }
        for (E subset : subsets) {
            data.addAll(findSubsets((ID) subset.getId()));
        }
        return subsets;
    }

    @Override
    @Transactional(readOnly = true)
    default List<D> findSubsetsToDto(ID id) {
        return getMapper().toDto(findSubsets(id));
    }

    @Override
    @Transactional(readOnly = true)
    default List<D> findItselfAndSubsetsToDto(ID id) {
        List<D> subsetsToDto = findSubsetsToDto(id);
        subsetsToDto.add(findByIdToDto(id));
        return subsetsToDto;
    }

    @Override
    @Transactional(readOnly = true)
    default D findByIdToTreeToDto(ID id) {
        D byId = findByIdToDto(id);
        List<D> children = findChildren(byId);
        byId.setChildren(children);
        return byId;
    }

    @Override
    @Transactional(readOnly = true)
    default Result<D> findByIdToTreeToResult(ID id) {
        return Result.success(findByIdToTreeToDto(id));
    }

    @Transactional(readOnly = true)
    default List<D> findByParentIdToTreeToDto(ID parentId) {
        D byId = findByIdToTreeToDto(parentId);
        return byId.getChildren();
    }

    @Override
    @Transactional(readOnly = true)
    default Result<List<D>> findByParentIdToTreeToResult(ID parentId) {
        return Result.success(findByParentIdToTreeToDto(parentId));
    }

    @Override
    @Transactional(readOnly = true)
    default Result<List<D>> findByParentIdToResult(ID parentId) {
        return Result.success(findByParentIdToDto(parentId));
    }

    @Override
    @Transactional(readOnly = true)
    default List<D> findAllByQueryCriteriaToDtoToTree(Specification<E> specification) {
        List<D> allByQueryCriteriaToDto = findAllToDto(specification);
//    List<D> ds = new TreeFactory<String, D>().buildTree(allByQueryCriteriaToDto);
        List<D> ds = new TreeFactory().buildTree(allByQueryCriteriaToDto);
        return ds;
    }
//
//    //@Override
//    @Transactional()
//    default Result<List<D>> findAllByQueryCriteriaToResultToTree(FQC queryCriteria) {
//        return Result.success(findAllByQueryCriteriaToDtoToTree(queryCriteria));
//    }

    @Transactional(readOnly = true)
    default List<D> findChildren(D var) {
        List<D> byParentIdToDto = findByParentIdToDto(var.getId());
        if (!CollectionUtils.isEmpty(byParentIdToDto)) {
            byParentIdToDto.forEach(item -> {
                item.setChildren(findChildren(item));
            });
            return byParentIdToDto;
        }
        return null;
    }

}
