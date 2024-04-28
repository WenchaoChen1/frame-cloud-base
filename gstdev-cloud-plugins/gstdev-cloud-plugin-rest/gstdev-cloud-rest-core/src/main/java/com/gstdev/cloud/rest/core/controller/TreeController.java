package com.gstdev.cloud.rest.core.controller;

import com.gstdev.cloud.base.definition.domain.Result;
import com.gstdev.cloud.base.definition.domain.base.pojo.*;
import com.gstdev.cloud.data.core.entity.BaseTreeEntityINT;
import com.gstdev.cloud.data.core.mapper.BaseTreeVoMapper;
import com.gstdev.cloud.data.core.service.BaseTreeService;

import java.io.Serializable;
import java.util.List;

public interface TreeController<E extends BaseTreeEntityINT<ID>
    , ID extends Serializable
    , S extends BaseTreeService<E, ID, D, II, UI, PQC, FQC>
    , M extends BaseTreeVoMapper<V, D>
    , V extends BaseTreeVoInterface & BaseVoInterface
    , D extends BaseTreeDtoInterface<D, ID> & BaseDtoInterface<ID>
    , II extends BaseTreeInsertInputInterface & BaseInsertInputInterface
    , UI extends BaseTreeUpdateInputInterface & BaseUpdateInputInterface
    , PQC extends BaseTreePageQueryCriteriaInterface & BasePageQueryCriteriaInterface
    , FQC extends BaseTreeFindAllByQueryCriteriaInterface & BaseFindAllByQueryCriteriaInterface> extends POJOController<E, ID, S, M, V, D, II, UI, PQC, FQC> {

    public default Result<V> findByIdToTreeToResult(ID id) {
        return getMapper().toVo(getService().findByIdToTreeToResult(id));
    }

    public default Result<List<V>> findByParentIdIdToTreeToResult(ID parentId) {
        return getMapper().toAllVo(getService().findByParentIdToTreeToResult(parentId));
    }

    public default Result<List<V>> findByParentIdIdToResult(ID parentId) {
        return getMapper().toAllVo(getService().findByParentIdToResult(parentId));
    }

    public default Result<List<V>> findAllByQueryCriteriaToResultToTree(FQC queryCriteria) {
        return getMapper().toAllVo(getService().findAllByQueryCriteriaToResultToTree(queryCriteria));
    }
}
