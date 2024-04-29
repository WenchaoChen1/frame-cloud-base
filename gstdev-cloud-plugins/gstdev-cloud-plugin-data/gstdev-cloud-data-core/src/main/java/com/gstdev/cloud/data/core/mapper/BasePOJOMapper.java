package com.gstdev.cloud.data.core.mapper;

import org.mapstruct.MappingTarget;

public interface BasePOJOMapper<E, D, II, UI> extends BaseDtoMapper<E, D> {
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
