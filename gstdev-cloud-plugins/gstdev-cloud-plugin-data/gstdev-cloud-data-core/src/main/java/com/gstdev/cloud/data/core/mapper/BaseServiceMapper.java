package com.gstdev.cloud.data.core.mapper;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface BaseServiceMapper<E, SE> {
    SE toServiceEntity(E entity);

    List<SE> toServiceEntity(List<E> entity);

    Page<SE> toServiceEntity(Page<E> entity);

    E toEntity(SE sEntity);

    List<E> toEntity(List<SE> sEntity);

    Page<E> toEntity(Page<SE> sEntity);

    Iterable<E> toEntity(Iterable<SE> sEntity);

    Specification<E> toEntity(Specification<SE> sEntity);
}
