package com.gstdev.cloud.data.core.mapper;

import com.gstdev.cloud.base.definition.domain.Result;
import org.mapstruct.Named;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

public interface BaseVoMapper<D, V> {

    @Named("toVo")
    V toVo(D var1);

    @Named("toListVo")
    List<V> toVo(List<D> var1);

    @Named("toPageVo")
    default Page<V> toVo(Page<D> page) {
        List<V> responses = this.toVo(page.getContent());
        new PageImpl<V>(responses, page.getPageable(), page.getTotalElements());
        return new PageImpl<V>(responses, page.getPageable(), page.getTotalElements());
    }

    @Named("toResultVo")
    default Result<V> toVo(Result<D> result) {
        D data = result.getData();
        V v = toVo(data);
        Result<V> of = Result.success(result.getMessage(), result.getCode(), v);
        return of;
    }

    @Named("toListResultVo")
    default Result<List<V>> toAllVo(Result<List<D>> result) {
        List<D> data = result.getData();
        List<V> v = toVo(data);
        Result<List<V>> of = Result.success(result.getMessage(), result.getCode(), v);
        return of;
    }

}
