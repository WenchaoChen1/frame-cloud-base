package com.gstdev.cloud.rest.core.controller;

import com.gstdev.cloud.base.definition.domain.Result;
import com.gstdev.cloud.base.definition.domain.base.pojo.*;
import com.gstdev.cloud.data.core.entity.BasePOJOEntityINT;
import com.gstdev.cloud.data.core.mapper.BaseVoMapper;
import com.gstdev.cloud.data.core.service.BasePOJOService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public interface POJOController<E extends BasePOJOEntityINT<ID>
    , ID extends Serializable
    , S extends BasePOJOService<E, ID, D, II, UI, PQC, FQC>
    , M extends BaseVoMapper<V, D>
    , V extends BaseVoInterface
    , D extends BaseDtoInterface<ID>
    , II extends BaseInsertInputInterface
    , UI extends BaseUpdateInputInterface
    , PQC extends BasePageQueryCriteriaInterface
    , FQC extends BaseFindAllByQueryCriteriaInterface> extends DtoController<E, ID, S, D> {
//
//    S getService();

    M getMapper();


    default Result<V> insertToResult(II var1) {
//    try {
        return getMapper().toVo(getService().insertToResult(var1));
//    } catch (Exception e) {
//      e.printStackTrace();
//      return Result.failure("Save failed");
//    }
    }

    default Result<List<V>> insertAllToResult(List<II> var1) {
        return getMapper().toAllVo(getService().insertAllToResult(var1));
    }

    default Result<V> updateToResult(UI var1) {
        return getMapper().toVo(getService().updateToResult(var1));
    }

    default Result<List<V>> updateAllToResult(List<UI> var1) {
        return getMapper().toAllVo(getService().updateAllToResult(var1));
    }


    default Result<V> deleteByIdToResult(ID id) {
        return getMapper().toVo(getService().deleteByIdToResult(id));
    }

    default Result<List<V>> deleteAllByIdToResult(List<ID> ids) {
        return getMapper().toAllVo(getService().deleteAllByIdToResult(ids));
    }

    default Result<Page<V>> pageToResult(PQC queryCriteria, Pageable pageable) {
        return Result.success(getMapper().toVo(getService().page(queryCriteria, pageable)));
    }

    default Result<V> findByIdToResult(ID id) {
        return getMapper().toVo(getService().findByIdToResult(id));
    }

    default Result<List<V>> findAllByQueryCriteriaToResult(FQC var1) {
        return getMapper().toAllVo(getService().findAllByQueryCriteriaToResult(var1));
    }


}
