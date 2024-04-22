package com.gstdev.cloud.data.core.mapper;

import com.gstdev.cloud.base.definition.domain.base.Entity;
import com.gstdev.cloud.data.core.pojo.BaseDto;
import com.gstdev.cloud.data.core.pojo.BaseInsertInput;
import com.gstdev.cloud.data.core.pojo.BaseUpdateInput;
import org.mapstruct.MappingTarget;

public interface BaseMapper<E extends Entity, D extends BaseDto, II extends BaseInsertInput, UI extends BaseUpdateInput> extends AbstractMapper<D, E> {
  /**
   * 单条新增
   *
   * @param var1
   * @return
   */
  E toEntityInsert(II var1);

  /**
   * 单条更新
   *
   * @param var1
   * @return
   */
  E toEntityUpdate(UI var1);

  void copyUpdate(UI var1, @MappingTarget E var2);

}
