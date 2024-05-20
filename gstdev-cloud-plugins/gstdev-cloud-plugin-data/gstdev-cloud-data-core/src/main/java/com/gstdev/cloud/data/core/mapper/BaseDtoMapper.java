// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.data.core.mapper;

import org.mapstruct.Named;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

public interface BaseDtoMapper<E, D> {
    @Named("toEntity")
    E toEntity(D dto);

    @Named("toDto")
    D toDto(E entity);

    @Named("toListEntity")
    List<E> toEntity(List<D> dtoList);

    @Named("toListDto")
    List<D> toDto(List<E> entityList);

    @Named("toPageEntity")
    default Page<E> toEntity(Page<D> page) {
        List<E> responses = toEntity(page.getContent());
        return new PageImpl<>(responses, page.getPageable(), page.getTotalElements());
    }

    @Named("toPageDto")
    default Page<D> toDto(Page<E> page) {
        List<D> responses = toDto(page.getContent());
        return new PageImpl<>(responses, page.getPageable(), page.getTotalElements());
    }
}
