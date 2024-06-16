package com.gstdev.cloud.data.core.converter;

import com.gstdev.cloud.data.core.mapper.BaseServiceMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class BaseServiceConverter<E, SE> implements BaseServiceMapper<E, SE> {
    public SE toServiceEntity(E entity) {
        return (SE) entity;
    }

    @Override
    public List<SE> toServiceEntity(List<E> entity) {
        return (List<SE>) entity;
    }

    @Override
    public Page<SE> toServiceEntity(Page<E> entity) {
        return (Page<SE>) entity;
    }

    @Override
    public E toEntity(SE sEntity) {
        return (E) sEntity;
    }

    @Override
    public List<E> toEntity(List<SE> sEntity) {
        return (List<E>) sEntity;
    }

    @Override
    public Page<E> toEntity(Page<SE> sEntity) {
        return (Page<E>) sEntity;
    }

    @Override
    public Iterable<E> toEntity(Iterable<SE> sEntity) {
        return (Iterable<E>) sEntity;
    }

    @Override
    public Specification<E> toEntity(Specification<SE> sEntity) {
        return (Specification<E>) sEntity;
    }
}
