package com.gstdev.cloud.base.definition.domain.base.pojo;

import com.gstdev.cloud.base.definition.domain.base.Entity;

import java.io.Serializable;

public interface BaseDtoInterface<ID> extends Entity {
    ID getId();
}
