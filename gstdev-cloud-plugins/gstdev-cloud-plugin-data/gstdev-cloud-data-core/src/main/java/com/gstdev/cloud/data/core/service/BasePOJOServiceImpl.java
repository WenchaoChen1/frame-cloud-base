package com.gstdev.cloud.data.core.service;

import com.gstdev.cloud.base.definition.domain.Result;
import com.gstdev.cloud.base.definition.domain.base.pojo.*;
import com.gstdev.cloud.base.definition.exception.PlatformRuntimeException;
import com.gstdev.cloud.data.core.entity.BasePOJOEntityINT;
import com.gstdev.cloud.data.core.mapper.BaseDtoMapper;
import com.gstdev.cloud.data.core.mapper.BasePOJOMapper;
import com.gstdev.cloud.data.core.repository.BaseRepository;
import com.gstdev.cloud.data.core.utils.QueryUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    , M extends BasePOJOMapper<E, D, II, UI>
    , D extends BaseDtoInterface<ID>
    , II extends BaseInsertInputInterface
    , UI extends BaseUpdateInputInterface
    , PQC extends BasePageQueryCriteriaInterface
    , FQC extends BaseFindAllByQueryCriteriaInterface> extends BaseDtoServiceImpl<E, ID, R, M, D> implements BasePOJOServiceDefault<E, ID, D, II, UI, PQC, FQC> {

    private M mapper;

    public BasePOJOServiceImpl(R repository, M mapper) {
        super(repository, mapper);
        this.mapper = mapper;
    }

    public M getMapper() {
        return mapper;
    }

}
