package com.gstdev.cloud.data.core.mapper;

import com.gstdev.cloud.base.definition.domain.Result;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

public interface BasePOJOMapper<E, D, V, II, UI> extends BaseDtoMapper<E, D>, BaseVoMapper<D, V> {
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
    @Named("toVo")
    V toVo(D var1);
    @Named("toListVo")
    List<V> toVo(List<D> var1);

}
