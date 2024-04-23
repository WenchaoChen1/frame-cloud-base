package com.gstdev.cloud.rest.core.controller;

import com.gstdev.cloud.base.definition.domain.base.pojo.*;
import com.gstdev.cloud.data.core.entity.BasePOJOEntityINT;
import com.gstdev.cloud.data.core.mapper.BaseVoMapper;
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
    , S extends BasePOJOService<E, ID, D, II, UI, PQC, FQC>
    , M extends BaseVoMapper<V, D>
    , V extends BaseVoInterface
    , D extends BaseDtoInterface<ID>
    , II extends BaseInsertInputInterface
    , UI extends BaseUpdateInputInterface
    , PQC extends BasePageQueryCriteriaInterface
    , FQC extends BaseFindAllByQueryCriteriaInterface> extends BaseController<E, ID, S>
    implements POJOController<E, ID, S, M, V, D, II, UI, PQC, FQC> {
    public BasePOJOController(S service) {
        super(service);
    }


    private M mapper;

    public BasePOJOController(S service, M mapper) {
        super(service);
        this.mapper = mapper;
    }

    public M getMapper() {
        return mapper;
    }

    public void setMapper(M mapper) {
        this.mapper = mapper;
    }

}
