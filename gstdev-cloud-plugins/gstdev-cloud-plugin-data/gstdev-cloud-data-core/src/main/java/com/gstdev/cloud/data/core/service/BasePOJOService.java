package com.gstdev.cloud.data.core.service;

import com.gstdev.cloud.base.definition.domain.Result;
import com.gstdev.cloud.base.definition.domain.base.pojo.*;
import com.gstdev.cloud.data.core.entity.BasePOJOEntityINT;
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
public interface BasePOJOService<E extends BasePOJOEntityINT<ID>
    , ID extends Serializable
    , D extends BaseDtoInterface<ID>
//    , II extends BaseInsertInputInterface
//    , UI extends BaseUpdateInputInterface
//    , PQC extends BasePageQueryCriteriaInterface
//    , FQC extends BaseFindAllByQueryCriteriaInterface> extends BaseDtoService<E, ID, D> {
    > extends BaseDtoService<E, ID, D> {

//    Result<D> insertToResult(II var1);
//
//    Result<List<D>> insertAllToResult(List<II> var1);
//
//    Result<D> updateToResult(UI varUpdateInput);
//
//    Result<List<D>> updateAllToResult(List<UI> varUpdateInput);
//
//    Result<D> deleteByIdToResult(ID id);
//
//    Result<List<D>> deleteAllByIdToResult(List<ID> ids);
//
//    Page<D> page(PQC queryCriteria, Pageable pageable);
//
////    D findByIdToDto(ID id);
//
//    Result<D> findByIdToResult(ID id);
//
//    List<D> findAllByQueryCriteriaToDto(FQC var1);
//
//    Result<List<D>> findAllByQueryCriteriaToResult(FQC var1);

}
