package com.gstdev.cloud.rest.core.controller;


import com.gstdev.cloud.data.core.entity.BaseTreeEntityINT;
import com.gstdev.cloud.data.core.mapper.BaseTreeVoMapper;
import com.gstdev.cloud.data.core.pojo.*;
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
public abstract class BaseTreeController<E extends BaseTreeEntityINT
    , ID extends Serializable
    , S extends BaseTreeService<E, ID, D, II, UI, PQC, FQC>
    , M extends BaseTreeVoMapper<V, D>
    , V extends BaseTreeVo
    , D extends BaseTreeDto
    , II extends BaseTreeInsertInput,
    UI extends BaseTreeUpdateInput
    , PQC extends BaseTreePageQueryCriteria
    , FQC extends BaseTreeFindAllByQueryCriteria> extends BasePOJOController<E, ID, S, M, V, D, II, UI, PQC, FQC> implements TreeController<E, ID, S, M, V, D, II, UI, PQC, FQC> {


    public BaseTreeController(S service, M mapper) {
        super(service, mapper);
    }

    public BaseTreeController(S service) {
        super(service);
    }
}
