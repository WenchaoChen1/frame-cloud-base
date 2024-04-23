package com.gstdev.cloud.rest.core.controller;


import com.gstdev.cloud.base.definition.domain.base.pojo.*;
import com.gstdev.cloud.data.core.entity.BaseTreeEntityINT;
import com.gstdev.cloud.data.core.mapper.BaseTreeVoMapper;
import com.gstdev.cloud.data.core.service.BaseTreeService;

import java.io.Serializable;

/**
 * @param <S>   xxxService
 * @param <D>   xxxDto
 * @param <II>  xxxInsertInput
 * @param <UI>  xxxUpdateInput
 * @param <PQC> xxxPageQueryCriteria
 * @param <FQC> xxxFindAllByQueryCriteria
 */
public abstract class BaseTreeController<E extends BaseTreeEntityINT<ID>
    , ID extends Serializable
    , S extends BaseTreeService<E, ID, D, II, UI, PQC, FQC>
    , M extends BaseTreeVoMapper<V, D>
    , V extends BaseTreeVoInterface & BaseVoInterface
    , D extends BaseTreeDtoInterface<D,ID> & BaseDtoInterface<ID>
    , II extends BaseTreeInsertInputInterface & BaseInsertInputInterface
    , UI extends BaseTreeUpdateInputInterface & BaseUpdateInputInterface
    , PQC extends BaseTreePageQueryCriteriaInterface & BasePageQueryCriteriaInterface
    , FQC extends BaseTreeFindAllByQueryCriteriaInterface & BaseFindAllByQueryCriteriaInterface> extends BasePOJOController<E, ID, S, M, V, D, II, UI, PQC, FQC> implements TreeController<E, ID, S, M, V, D, II, UI, PQC, FQC> {


    public BaseTreeController(S service, M mapper) {
        super(service, mapper);
    }
}
