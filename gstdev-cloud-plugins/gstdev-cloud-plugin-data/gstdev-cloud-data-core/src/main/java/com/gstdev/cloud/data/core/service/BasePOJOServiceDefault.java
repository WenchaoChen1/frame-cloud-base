package com.gstdev.cloud.data.core.service;

import com.gstdev.cloud.base.definition.domain.base.pojo.BaseDtoInterface;
import com.gstdev.cloud.data.core.entity.BasePOJOEntityINT;

import java.io.Serializable;

public interface BasePOJOServiceDefault<E extends BasePOJOEntityINT<ID>
        , ID extends Serializable
        , D extends BaseDtoInterface<ID>
        > extends BasePOJOService<E, ID, D>, BaseDtoServiceDefault<E, ID, D> {


}
