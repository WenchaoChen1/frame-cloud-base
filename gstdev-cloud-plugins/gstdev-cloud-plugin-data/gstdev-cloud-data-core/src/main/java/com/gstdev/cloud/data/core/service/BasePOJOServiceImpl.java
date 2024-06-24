package com.gstdev.cloud.data.core.service;

import com.gstdev.cloud.base.definition.domain.base.pojo.BaseDtoInterface;
import com.gstdev.cloud.data.core.entity.BasePOJOEntityINT;
import com.gstdev.cloud.data.core.mapper.BaseDtoMapper;
import com.gstdev.cloud.data.core.repository.BaseRepository;

import java.io.Serializable;

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
public class BasePOJOServiceImpl<E extends BasePOJOEntityINT<ID>
        , ID extends Serializable
        , R extends BaseRepository<E, ID>
        , M extends BaseDtoMapper<E, D>
        , D extends BaseDtoInterface<ID>
//    , II extends BaseInsertInputInterface
//    , UI extends BaseUpdateInputInterface
//    , PQC extends BasePageQueryCriteriaInterface
//    , FQC extends BaseFindAllByQueryCriteriaInterface> extends BaseDtoServiceImpl<E, ID, R, M, D> implements BasePOJOServiceDefault<E, ID, D, II, UI, PQC, FQC> {
        > extends BaseDtoServiceImpl<E, ID, R, M, D> implements BasePOJOServiceDefault<E, ID, D> {

    private M mapper;

    public BasePOJOServiceImpl(R repository, M mapper) {
        super(repository, mapper);
        this.mapper = mapper;
    }

    @Override
    public M getMapper() {
        return mapper;
    }

}
