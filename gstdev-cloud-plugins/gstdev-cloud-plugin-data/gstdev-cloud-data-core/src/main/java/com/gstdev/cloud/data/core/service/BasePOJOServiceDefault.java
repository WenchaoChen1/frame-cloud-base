package com.gstdev.cloud.data.core.service;

import com.gstdev.cloud.base.definition.domain.Result;
import com.gstdev.cloud.base.definition.domain.base.pojo.*;
import com.gstdev.cloud.base.definition.exception.PlatformRuntimeException;
import com.gstdev.cloud.data.core.entity.BasePOJOEntityINT;
import com.gstdev.cloud.data.core.mapper.BaseDtoMapper;
import com.gstdev.cloud.data.core.mapper.BasePOJOMapper;
import com.gstdev.cloud.data.core.repository.BaseRepository;
import com.gstdev.cloud.data.core.utils.QueryUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public interface BasePOJOServiceDefault<E extends BasePOJOEntityINT<ID>
    , ID extends Serializable
    , D extends BaseDtoInterface<ID>
//    , II extends BaseInsertInputInterface
//    , UI extends BaseUpdateInputInterface
//    , PQC extends BasePageQueryCriteriaInterface
//    , FQC extends BaseFindAllByQueryCriteriaInterface> extends BasePOJOService<E, ID, D, II, UI, PQC, FQC>, BaseDtoServiceDefault<E, ID, D> {
    > extends BasePOJOService<E, ID, D>, BaseDtoServiceDefault<E, ID, D> {

//    BaseRepository<E, ID> getRepository();

//    BasePOJOMapper<E, D, II, UI> getMapper();
//
//    @Override
//    @Transactional
//    default Result<D> insertToResult(II varInsertUpdate) {
//        E e = toEntityInsert(varInsertUpdate);
//        return Result.success(insertToDto(e));
//    }
//
//    @Override
//    @Transactional
//    default Result<List<D>> insertAllToResult(List<II> varInsertInput) {
//        List<E> e = toEntityInsert(varInsertInput);
//        return Result.success(insertAllToDto(e));
//    }
//
//    @Override
//    @Transactional
//    default Result<D> updateToResult(UI varUpdateInput) {
//        return Result.success(updateToDto(toEntityUpdate(varUpdateInput)));
//    }
//
//    @Override
//    @Transactional
//    default Result<List<D>> updateAllToResult(List<UI> varUpdateInput) {
//        List<E> e = toEntityUpdate(varUpdateInput);
//        return Result.success(updateAllToDto(e));
//    }
//
//    @Override
//    @Transactional
//    default Result<D> deleteByIdToResult(ID id) {
//        if (ObjectUtils.isEmpty(id)) {
//            throw new PlatformRuntimeException("The primary key cannot be empty");
//        }
//        deleteById(id);
//        return Result.success(null);
//    }
//
//    @Override
//    @Transactional
//    default Result<List<D>> deleteAllByIdToResult(List<ID> ids) {
//        ids.forEach(id -> {
//            deleteById(id);
//        });
//        return Result.success(null);
//    }
//
//
//    @Override
//    default Page<D> page(PQC pageQueryCriteria, Pageable pageable) {
//        return getMapper().toDto(findByPage((root, criteriaQuery, criteriaBuilder) -> QueryUtils.getPredicate(root, pageQueryCriteria, criteriaBuilder), pageable));
//    }
//
//    @Override
//    default Result<D> findByIdToResult(ID id) {
//        return Result.success(findByIdToDto(id));
//    }
//
//    @Override
//    default List<D> findAllByQueryCriteriaToDto(FQC queryCriteria) {
//        List<E> all = findAll((root, criteriaQuery, criteriaBuilder) -> QueryUtils.getPredicate(root, queryCriteria, criteriaBuilder));
//        return getMapper().toDto(all);
//    }
//
//    @Override
//    default Result<List<D>> findAllByQueryCriteriaToResult(FQC queryCriteria) {
//        return Result.success(findAllByQueryCriteriaToDto(queryCriteria));
//    }
//
//    default E toEntityInsert(II var1) {
//        E e = getMapper().toEntityInsert(var1);
//        return e;
//    }
//
//    default List<E> toEntityInsert(List<II> var1) {
//        List<E> e = new ArrayList<>();
//        for (II ii : var1) {
//            e.add(toEntityInsert(ii));
//        }
//        return e;
//    }
//
//    default E toEntityUpdate(UI var1) {
//        ID id = null;
//        try {
//            Method method = var1.getClass().getMethod("getId", new Class[]{});
//            Object value = method.invoke(var1, new Object[]{});
//            id = (ID) value;
//        } catch (Exception e) {
//            throw new PlatformRuntimeException("The primary key cannot be empty");
//        }
//        if (id.equals("null")) {
//            throw new PlatformRuntimeException("The primary key cannot be empty");
//        }
//        E byId = findById(id);
////        Optional<E> byId = getRepository().findById(id);
////        if (byId.isEmpty()) {
////            throw new PlatformRuntimeException("The primary key cannot be empty");
////        }
////        E e = null;
////        if (byId.isPresent()) {
////            e = byId.get();
////        }
////        getMapper().copyUpdate(var1, byId.get());
//        getMapper().copyUpdate(var1, byId);
//        return byId;
//    }
//
//    default List<E> toEntityUpdate(List<UI> var1) {
//        List<E> e = new ArrayList<>();
//        for (UI ui : var1) {
//            e.add(toEntityUpdate(ui));
//        }
//        return e;
//    }
}
