package com.gstdev.cloud.data.core.service;

import com.gstdev.cloud.commons.ass.definition.domain.Result;
import com.gstdev.cloud.data.core.entity.BasePOJOEntityINT;
import com.gstdev.cloud.data.core.pojo.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

/**
 * @param <D>   xxxDto
 * @param <II>  xxxInsertInput
 * @param <UI>  xxxUpdateInput
 * @param <PQC> xxxPageQueryCriteria
 * @param <FQC> xxxFindAllByQueryCriteria
 */
public interface BasePOJOService<E extends BasePOJOEntityINT
    , ID extends Serializable
    , D extends BaseDto
    , II extends BaseInsertInput
    , UI extends BaseUpdateInput
    , PQC extends BasePageQueryCriteria
    , FQC extends BaseFindAllByQueryCriteria> extends BaseService<E, ID> {

    Result<D> insertToResult(II var1);

    Result<List<D>> insertAllToResult(List<II> var1);

    Result<D> updateToResult(UI varUpdateInput);

    Result<List<D>> updateAllToResult(List<UI> varUpdateInput);

    Result<D> deleteByIdToResult(ID id);

    Result<List<D>> deleteAllByIdToResult(List<ID> ids);

    Page<D> page(PQC queryCriteria, Pageable pageable);

    D findByIdToDto(ID id);

    Result<D> findByIdToResult(ID id);

    List<D> findAllByQueryCriteriaToDto(FQC var1);

    Result<List<D>> findAllByQueryCriteriaToResult(FQC var1);

}
