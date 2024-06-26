package com.gstdev.cloud.rest.core.controller;

import com.gstdev.cloud.base.definition.domain.base.pojo.*;
import com.gstdev.cloud.data.core.entity.BasePOJOEntityINT;
import com.gstdev.cloud.data.core.mapper.BasePOJOMapper;
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
public abstract class BasePOJOController<E extends BasePOJOEntityINT<ID>
        , ID extends Serializable
        , S extends BasePOJOService<E, ID, D>
        , M extends BasePOJOMapper<E, D, V, II, UI>
        , V extends BaseVoInterface
        , D extends BaseDtoInterface<ID>
        , II extends BaseInsertInputInterface
        , UI extends BaseUpdateInputInterface
        , PQC extends BasePageQueryCriteriaInterface
        , FQC extends BaseFindAllByQueryCriteriaInterface>
        implements POJOController<E, ID, V, D, II, UI, PQC, FQC> {
    //    public BasePOJOController(S service) {
//        super(service);
//    }
    private S service;

    private M mapper;

    public BasePOJOController(S service, M mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    /**
     * 获取Service
     *
     * @return Service
     */
    @Override
    public S getService() {
        return this.service;
    }

    public void setService(S service) {
        this.service = service;
    }

    @Override
    public M getMapper() {
        return mapper;
    }

    public void setMapper(M mapper) {
        this.mapper = mapper;
    }
}
