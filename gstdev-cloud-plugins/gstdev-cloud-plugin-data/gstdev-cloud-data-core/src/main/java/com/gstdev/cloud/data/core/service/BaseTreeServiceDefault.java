package com.gstdev.cloud.data.core.service;

import com.gstdev.cloud.base.definition.domain.base.pojo.*;
import com.gstdev.cloud.data.core.entity.BaseTreeEntityINT;

import java.io.Serializable;

public interface BaseTreeServiceDefault <E extends BaseTreeEntityINT<ID>
    , ID extends Serializable
    , D extends BaseTreeDtoInterface<D, ID> & BaseDtoInterface<ID>
    , II extends BaseTreeInsertInputInterface & BaseInsertInputInterface
    , UI extends BaseTreeUpdateInputInterface & BaseUpdateInputInterface
    , PQC extends BaseTreePageQueryCriteriaInterface & BasePageQueryCriteriaInterface
    , FQC extends BaseTreeFindAllByQueryCriteriaInterface & BaseFindAllByQueryCriteriaInterface
    > extends BaseTreeService<E, ID, D, II, UI, PQC, FQC>,BasePOJOServiceDefault<E, ID, D, II, UI, PQC, FQC> {
}
