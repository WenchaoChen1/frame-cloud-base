package com.gstdev.cloud.rest.core.controller;

import com.gstdev.cloud.base.definition.domain.Result;
import com.gstdev.cloud.base.definition.domain.base.pojo.*;
import com.gstdev.cloud.base.definition.exception.PlatformRuntimeException;
import com.gstdev.cloud.data.core.entity.BasePOJOEntityINT;
import com.gstdev.cloud.data.core.mapper.BasePOJOMapper;
import com.gstdev.cloud.data.core.utils.QueryUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public interface POJOController<E extends BasePOJOEntityINT<ID>
        , ID extends Serializable
        , V extends BaseVoInterface
        , D extends BaseDtoInterface<ID>
        , II extends BaseInsertInputInterface
        , UI extends BaseUpdateInputInterface
        , PQC extends BasePageQueryCriteriaInterface
        , FQC extends BaseFindAllByQueryCriteriaInterface> extends VoController<E, ID, D, V> {

    @Override
    BasePOJOMapper<E, D, V, II, UI> getMapper();

    default Result<V> insertToResult(II var1) {
        return result(getMapper().toVo(getService().insertToDto(toEntityInsert(var1))));
    }

    default Result<List<V>> insertAllToResult(List<II> var1) {
        return result(getMapper().toVo(getService().insertToDto(toEntityInsert(var1))));
    }

    default Result<V> updateToResult(UI var1) {
        return result(getMapper().toVo(getService().updateToDto(toEntityUpdate(var1))));
    }

    default Result<List<V>> updateAllToResult(List<UI> var1) {
        return result(getMapper().toVo(getService().updateToDto(toEntityUpdate(var1))));
    }

    default Result<V> deleteByIdToResult(ID id) {
        if (ObjectUtils.isEmpty(id)) {
            throw new PlatformRuntimeException("The primary key cannot be empty");
        }
        getService().deleteById(id);
        return Result.success();
    }

    default Result<List<V>> deleteAllByIdToResult(List<ID> ids) {
        if (ObjectUtils.isEmpty(ids)) {
            throw new PlatformRuntimeException("The primary key cannot be empty");
        }
        getService().deleteAllById(ids);
        return Result.success();
    }

    default Result<Page<V>> pageToResult(PQC pageQueryCriteria, Pageable pageable) {
        return Result.success(getMapper().toVo(getService().findByPageToDto((root, criteriaQuery, criteriaBuilder) -> QueryUtils.getPredicate(root, pageQueryCriteria, criteriaBuilder), pageable)));
    }

    default Result<V> findByIdToResult(ID id) {
        return result(getMapper().toVo(getService().findByIdToDto(id)));
    }

    default Result<List<V>> findAllByQueryCriteriaToResult(FQC var1) {
        return result(getMapper().toVo(getService().findAllToDto((root, criteriaQuery, criteriaBuilder) -> QueryUtils.getPredicate(root, var1, criteriaBuilder))));
    }


    default E toEntityInsert(II var1) {
        E e = getMapper().toEntityInsert(var1);
        return e;
    }

    default List<E> toEntityInsert(List<II> var1) {
        List<E> e = new ArrayList<>();
        for (II ii : var1) {
            e.add(toEntityInsert(ii));
        }
        return e;
    }

    default E toEntityUpdate(UI var1) {
        ID id = null;
        try {
            Method method = var1.getClass().getMethod("getId", new Class[]{});
            Object value = method.invoke(var1, new Object[]{});
            id = (ID) value;
        } catch (Exception e) {
            throw new PlatformRuntimeException("The primary key cannot be empty");
        }
        if (id.equals("null")) {
            throw new PlatformRuntimeException("The primary key cannot be empty");
        }
        E byId = getService().findById(id);
        getMapper().copyUpdate(var1, byId);
        return byId;
    }

    default List<E> toEntityUpdate(List<UI> var1) {
        List<E> e = new ArrayList<>();
        for (UI ui : var1) {
            e.add(toEntityUpdate(ui));
        }
        return e;
    }

}
