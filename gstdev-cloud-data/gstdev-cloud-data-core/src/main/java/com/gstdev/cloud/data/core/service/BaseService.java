package com.gstdev.cloud.data.core.service;

import com.gstdev.cloud.commons.ass.definition.constants.SymbolConstants;
import com.gstdev.cloud.commons.ass.definition.domain.base.Entity;

import java.io.Serializable;

/**
 * <p>Description: 通用核心 Service </p>
 *
 * @author : cc
 * @date : 2021/7/14 14:32
 */
public abstract class BaseService<E extends Entity, ID extends Serializable> implements WriteableService<E, ID> {

    protected String like(String property) {
        return SymbolConstants.PERCENT + property + SymbolConstants.PERCENT;
    }
}
