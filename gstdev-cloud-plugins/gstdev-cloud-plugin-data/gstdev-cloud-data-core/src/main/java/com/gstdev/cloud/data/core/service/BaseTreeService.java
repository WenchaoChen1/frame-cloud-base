package com.gstdev.cloud.data.core.service;

import com.gstdev.cloud.base.definition.domain.Result;
import com.gstdev.cloud.base.definition.domain.base.pojo.BaseDtoInterface;
import com.gstdev.cloud.base.definition.domain.base.pojo.BaseTreeDtoInterface;
import com.gstdev.cloud.data.core.entity.BaseTreeEntityINT;
import org.springframework.data.jpa.domain.Specification;

import java.io.Serializable;
import java.util.List;

/**
 * @param <D>   xxxDto
 * @param <II>  xxxInsertInput
 * @param <UI>  xxxUpdateInput
 * @param <PQC> xxxPageQueryCriteria
 * @param <FQC> xxxFindAllByQueryCriteria
 */
public interface BaseTreeService<E extends BaseTreeEntityINT<ID>
        , ID extends Serializable
        , D extends BaseTreeDtoInterface<D, ID> & BaseDtoInterface<ID>
        > extends BaseDtoServiceDefault<E, ID, D> {

    List<D> findItselfAndSubsetsToDto(ID id);

    List<D> findSubsetsToDto(ID id);

    D findByIdToTreeToDto(ID id);

    Result<D> findByIdToTreeToResult(ID id);

    List<D> findByParentIdToTreeToDto(ID parentId);

    Result<List<D>> findByParentIdToTreeToResult(ID parentId);

    List<D> findByParentIdToDto(ID parentId);

    Result<List<D>> findByParentIdToResult(ID parentId);

    List<D> findAllByQueryCriteriaToDtoToTree(Specification<E> specification);

}
