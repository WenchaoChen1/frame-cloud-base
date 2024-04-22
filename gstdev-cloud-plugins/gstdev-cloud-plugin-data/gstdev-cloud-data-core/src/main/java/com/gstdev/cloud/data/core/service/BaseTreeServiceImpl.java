package com.gstdev.cloud.data.core.service;

import com.gstdev.cloud.base.definition.domain.Result;
import com.gstdev.cloud.base.core.utils.treeUtils.TreeFactory;
import com.gstdev.cloud.data.core.entity.BaseTreeEntityINT;
import com.gstdev.cloud.data.core.mapper.BaseTreeMapper;
import com.gstdev.cloud.data.core.pojo.*;
import com.gstdev.cloud.data.core.repository.BaseTreeRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @param <R>   xxxRepository
 * @param <M>   xxxMapper
 * @param <E>   xxx  实体类
 * @param <D>   xxxDto
 * @param <II>  xxxInsertInput
 * @param <UI>  xxxUpdateInput
 * @param <PQC> xxxPageQueryCriteria
 * @param <FQC> xxxFindAllByQueryCriteria
 */
public abstract class BaseTreeServiceImpl<E extends BaseTreeEntityINT
    , ID extends Serializable
    , R extends BaseTreeRepository<E, ID>
    , M extends BaseTreeMapper<E, D, II, UI>
    , D extends BaseTreeDto
    , II extends BaseTreeInsertInput
    , UI extends BaseTreeUpdateInput
    , PQC extends BaseTreePageQueryCriteria
    , FQC extends BaseTreeFindAllByQueryCriteria>
    extends BasePOJOServiceImpl<E, ID, R, M, D, II, UI, PQC, FQC> implements BaseTreeService<E, ID, D, II, UI, PQC, FQC> {

    public BaseTreeServiceImpl(R repository, M mapper) {
        super(repository, mapper);
    }

    public List<E> findByParentId(ID parentId) {
        List<E> byId = getRepository().findByParentId(parentId);
        return byId;
    }

    public List<D> findByParentIdToDto(ID parentId) {
        List<D> byId = getMapper().toDto(findByParentId(parentId));
        return byId;
    }

    public List<E> findSubsets(ID id) {
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

    public List<D> findSubsetsToDto(ID id) {
        return getMapper().toDto(findSubsets(id));
    }

    public List<D> findItselfAndSubsetsToDto(ID id) {
        List<D> subsetsToDto = findSubsetsToDto(id);
        subsetsToDto.add(findByIdToDto(id));
        return subsetsToDto;
    }

    public D findByIdToTreeToDto(ID id) {
        D byId = findByIdToDto(id);
        List<D> children = findChildren(byId);
        byId.setChildren(children);
        return byId;
    }

    @Override
    @Transactional()
    public Result<D> findByIdToTreeToResult(ID id) {
        return Result.success(findByIdToTreeToDto(id));
    }

    public List<D> findByParentIdToTreeToDto(ID parentId) {
        D byId = findByIdToTreeToDto(parentId);
        return byId.getChildren();
    }

    @Override
    @Transactional()
    public Result<List<D>> findByParentIdToTreeToResult(ID parentId) {
        return Result.success(findByParentIdToTreeToDto(parentId));
    }

    @Override
    @Transactional()
    public Result<List<D>> findByParentIdToResult(ID parentId) {
        return Result.success(findByParentIdToDto(parentId));
    }

    public List<D> findAllByQueryCriteriaToDtoToTree(FQC queryCriteria) {
        List<D> allByQueryCriteriaToDto = findAllByQueryCriteriaToDto(queryCriteria);
//    List<D> ds = new TreeFactory<String, D>().buildTree(allByQueryCriteriaToDto);
        List<D> ds = new TreeFactory().buildTree(allByQueryCriteriaToDto);
        return ds;
    }

    @Override
    @Transactional()
    public Result<List<D>> findAllByQueryCriteriaToResultToTree(FQC queryCriteria) {
        return Result.success(findAllByQueryCriteriaToDtoToTree(queryCriteria));
    }


    public List<D> findChildren(D var) {
        List<D> byParentIdToDto = findByParentIdToDto((ID) var.getId());
        if (!CollectionUtils.isEmpty(byParentIdToDto)) {
            byParentIdToDto.forEach(item -> {
                item.setChildren(findChildren(item));
            });
            return byParentIdToDto;
        }
        return null;
    }


}
