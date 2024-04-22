package com.gstdev.cloud.data.core.mapper;

import com.gstdev.cloud.base.definition.domain.Result;
import com.gstdev.cloud.data.core.pojo.BaseDto;
import com.gstdev.cloud.data.core.pojo.BaseVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

public interface BaseVoMapper<V, D> {

  V toVo(D var1);

  List<V> toVo(List<D> var1);

  default Page<V> toVo(Page<D> page) {
    List<V> responses = this.toVo(page.getContent());
    return new PageImpl(responses, page.getPageable(), page.getTotalElements());
  }

  default Result<V> toVo(Result<D> result) {
    D data = result.getData();
    V v = toVo(data);
    Result<V> of = Result.success(result.getMessage(), result.getCode(), v);
    return of;
  }

  default Result<List<V>> toAllVo(Result<List<D>> result) {
    List<D> data = result.getData();
    List<V> v = toVo(data);
    Result<List<V>> of = Result.success(result.getMessage(), result.getCode(), v);
    return of;
  }

}
