package com.gstdev.cloud.data.core.service;

import com.gstdev.cloud.base.definition.domain.Result;
import com.gstdev.cloud.base.core.utils.treeUtils.TreeFactory;
import com.gstdev.cloud.base.definition.domain.base.pojo.*;
import com.gstdev.cloud.data.core.entity.BaseTreeEntityINT;
import com.gstdev.cloud.data.core.mapper.BaseDtoMapper;
import com.gstdev.cloud.data.core.mapper.BaseTreeMapper;
import com.gstdev.cloud.data.core.repository.BaseRepository;
import com.gstdev.cloud.data.core.repository.BaseTreeRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @param <R> xxxRepository
 * @param <M> xxxMapper
 * @param <E> xxx  实体类
 * @param <D> xxxDto
 */
public abstract class BaseTreeServiceImpl<E extends BaseTreeEntityINT<ID>
    , ID extends Serializable
    , R extends BaseTreeRepository<E, ID>
    , M extends BaseDtoMapper<E, D>
    , D extends BaseTreeDtoInterface<D, ID> & BaseDtoInterface<ID>
    > extends BaseDtoServiceImpl<E, ID, R, M, D> implements BaseTreeServiceDefault<E, ID, D> {

    public BaseTreeServiceImpl(R repository, M mapper) {
        super(repository, mapper);
    }

    @Override
    public R getRepository() {
        return super.getRepository();
    }
}
