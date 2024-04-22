package com.gstdev.cloud.rest.core.controller;

import com.gstdev.cloud.data.core.entity.BasePOJOEntityINT;
import com.gstdev.cloud.data.core.mapper.BaseVoMapper;
import com.gstdev.cloud.data.core.pojo.*;
import com.gstdev.cloud.data.core.service.BasePOJOService;

import java.io.Serializable;

/**
 * @param <S>   xxxService
 * @param <D>   xxxDto
 * @param <II>  xxxInsertInput
 * @param <UI>  xxxUpdateInput
 * @param <PQC> xxxPageQueryCriteria
 * @param <FQC> xxxFindAllByQueryCriteria
 */
public abstract class BasePOJOController<E extends BasePOJOEntityINT
    , ID extends Serializable
    , S extends BasePOJOService<E, ID, D, II, UI, PQC, FQC>
    , M extends BaseVoMapper<V, D>
    , V extends BaseVo
    , D extends BaseDto
    , II extends BaseInsertInput
    , UI extends BaseUpdateInput
    , PQC extends BasePageQueryCriteria
    , FQC extends BaseFindAllByQueryCriteria> extends BaseController<E, ID, S>
    implements POJOController<E, ID, S, M, V, D, II, UI, PQC, FQC> {
    public BasePOJOController(S service) {
        super(service);
    }

//    protected S service;
//
//    private M mapper;
//
//    public BasePOJOController(S service, M mapper) {
//        this.service = service;
//        this.mapper = mapper;
//    }
//
//    public M getMapper() {
//        return mapper;
//    }
//
//    public void setMapper(M mapper) {
//        this.mapper = mapper;
//    }
//
//    public S getService() {
//        return this.service;
//    }
//
//    public void setService(S service) {
//        this.service = service;
//    }


}
