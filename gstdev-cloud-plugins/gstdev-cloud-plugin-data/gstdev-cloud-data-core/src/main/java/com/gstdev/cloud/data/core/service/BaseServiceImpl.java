package com.gstdev.cloud.data.core.service;

import com.gstdev.cloud.base.definition.domain.base.Entity;
import com.gstdev.cloud.data.core.repository.BaseRepository;
import com.gstdev.cloud.data.core.utils.BasePage;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Description: 通用核心 Service </p>
 *
 * @author : cc
 * @date : 2021/7/14 14:32
 */
@Transactional
public  class BaseServiceImpl<SE extends Entity, ID extends Serializable, S extends BaseRepository<SE, ID>> implements BaseService<SE, ID>, BaseServiceDefault<SE, ID> {

    private S baseRepository;

    public BaseServiceImpl(S baseRepository) {
        this.baseRepository = baseRepository;
    }

    /**
     * 获取Repository
     *
     * @return {@link BaseRepository}
     */
    public S getRepository() {
        return baseRepository;
    }
}
