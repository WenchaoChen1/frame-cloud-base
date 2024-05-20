package com.gstdev.cloud.rest.core.controller;

import com.gstdev.cloud.base.definition.domain.Result;
import com.gstdev.cloud.base.definition.domain.base.pojo.*;
import com.gstdev.cloud.data.core.entity.BaseTreeEntityINT;
import com.gstdev.cloud.data.core.mapper.BasePOJOMapper;
import com.gstdev.cloud.data.core.mapper.BaseTreeVoMapper;
import com.gstdev.cloud.data.core.service.BaseDtoService;
import com.gstdev.cloud.data.core.service.BaseTreeService;
import com.gstdev.cloud.data.core.utils.QueryUtils;

import java.io.Serializable;
import java.util.List;

public interface TreeController<E extends BaseTreeEntityINT<ID>
    , ID extends Serializable
//    , S extends BaseTreeService<E, ID, D, II, UI, PQC, FQC>
//    , M extends BasePOJOMapper<E, D,V, II, UI>
    , V extends BaseTreeVoInterface & BaseVoInterface
    , D extends BaseTreeDtoInterface<D, ID> & BaseDtoInterface<ID>
    , II extends BaseTreeInsertInputInterface & BaseInsertInputInterface
    , UI extends BaseTreeUpdateInputInterface & BaseUpdateInputInterface
    , PQC extends BaseTreePageQueryCriteriaInterface & BasePageQueryCriteriaInterface
    , FQC extends BaseTreeFindAllByQueryCriteriaInterface & BaseFindAllByQueryCriteriaInterface> extends POJOController<E, ID, V, D, II, UI, PQC, FQC> {


    BaseTreeService<E, ID, D> getService();

    BasePOJOMapper<E, D, V, II, UI> getMapper();

    default Result<V> findByIdToTreeToResult(ID id) {
        return result(getMapper().toVo(getService().findByIdToTreeToDto(id)));
    }

    default Result<List<V>> findByParentIdIdToTreeToResult(ID parentId) {
        return result(getMapper().toVo(getService().findByParentIdToTreeToDto(parentId)));
    }

    default Result<List<V>> findByParentIdIdToResult(ID parentId) {
        return result(getMapper().toVo(getService().findByParentIdToDto(parentId)));
    }

    default Result<List<V>> findAllByQueryCriteriaToResultToTree(FQC queryCriteria) {
        return result(getMapper().toVo(getService().findAllByQueryCriteriaToDtoToTree((root, criteriaQuery, criteriaBuilder) -> QueryUtils.getPredicate(root, queryCriteria, criteriaBuilder))));
    }


}
