package com.gstdev.cloud.data.core.service;

import com.gstdev.cloud.base.definition.domain.base.Entity;

import java.io.Serializable;

public interface BaseDtoServiceDefault<E extends Entity
    , ID extends Serializable
    , D
    > extends BaseDtoService<E, ID, D>, BaseServiceDefault<E, ID> {
}
