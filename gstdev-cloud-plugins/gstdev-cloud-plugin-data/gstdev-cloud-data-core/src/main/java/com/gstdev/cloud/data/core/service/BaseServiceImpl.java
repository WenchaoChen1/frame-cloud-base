package com.gstdev.cloud.data.core.service;

import com.gstdev.cloud.base.definition.domain.base.Entity;
import com.gstdev.cloud.data.core.repository.BaseRepository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * <p>Description: 通用核心 Service </p>
 *
 * @author : cc
 * @date : 2021/7/14 14:32
 */
@Transactional
public class BaseServiceImpl<SE extends Entity, ID extends Serializable, S extends BaseRepository<SE, ID>> implements BaseService<SE, ID>, BaseServiceDefault<SE, ID> {

    private S baseRepository;

    public BaseServiceImpl(S baseRepository) {
        this.baseRepository = baseRepository;
    }

    /**
     * 获取Repository
     *
     * @return {@link BaseRepository}
     */
    @Override
    public S getRepository() {
        return baseRepository;
    }

    @Override
    public BaseServiceDefault<SE, ID> getService() {
        return this;
    }
}
