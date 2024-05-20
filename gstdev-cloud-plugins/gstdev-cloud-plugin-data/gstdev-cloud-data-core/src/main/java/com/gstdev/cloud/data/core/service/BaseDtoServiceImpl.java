package com.gstdev.cloud.data.core.service;

import com.gstdev.cloud.base.definition.domain.base.Entity;
import com.gstdev.cloud.data.core.mapper.BaseDtoMapper;
import com.gstdev.cloud.data.core.repository.BaseRepository;
import com.gstdev.cloud.data.core.utils.BasePage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * <p>Description: 通用核心 Service </p>
 *
 * @author : cc
 * @date : 2021/7/14 14:32
 */
@Transactional
public class BaseDtoServiceImpl<E extends Entity
    , ID extends Serializable
    , R extends BaseRepository<E, ID>
    , M extends BaseDtoMapper<E, D>
    , D
    > extends BaseServiceImpl<E, ID, R> implements BaseDtoServiceDefault<E, ID, D> {

    private M mapper;

    public BaseDtoServiceImpl(R repository, M mapper) {
        super(repository);
        this.mapper = mapper;
    }

    public M getMapper() {
        return mapper;
    }

    public void setMapper(M mapper) {
        this.mapper = mapper;
    }


}
