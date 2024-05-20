package com.gstdev.cloud.data.core.service;

import com.gstdev.cloud.base.definition.domain.base.pojo.*;
import com.gstdev.cloud.data.core.entity.BasePOJOEntityINT;

import java.io.Serializable;

public interface BasePOJOServiceDefault<E extends BasePOJOEntityINT<ID>
    , ID extends Serializable
    , D extends BaseDtoInterface<ID>
    , II extends BaseInsertInputInterface
    , UI extends BaseUpdateInputInterface
    , PQC extends BasePageQueryCriteriaInterface
    , FQC extends BaseFindAllByQueryCriteriaInterface> extends BasePOJOService<E, ID, D, II, UI, PQC, FQC>,BaseDtoServiceDefault<E,ID,D> {
}
