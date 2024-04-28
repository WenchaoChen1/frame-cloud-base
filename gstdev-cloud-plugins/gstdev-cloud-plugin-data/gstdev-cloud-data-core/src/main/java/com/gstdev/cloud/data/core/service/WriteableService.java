package com.gstdev.cloud.data.core.service;

import com.gstdev.cloud.base.definition.domain.base.Entity;

import java.io.Serializable;
import java.util.List;

/**
 * <p>Description: 可读、可写的Service基础接口 </p>
 *
 * @author : cc
 * @date : 2021/7/7 16:47
 */
public interface WriteableService<E extends Entity, ID extends Serializable> extends ReadableService<E, ID> {

    /**
     * 删除数据
     *
     * @param entity 数据对应实体
     */
    default void delete(E entity) {
        getRepository().delete(entity);
    }

    /**
     * 批量全部删除
     */
    default void deleteAllInBatch() {
        getRepository().deleteAllInBatch();
    }

    /**
     * 删除指定多个数据
     *
     * @param entities 数据对应实体集合
     */
    default void deleteAll(Iterable<E> entities) {
        getRepository().deleteAll(entities);
    }

    /**
     * 删除全部数据
     */
    default void deleteAll() {
        getRepository().deleteAll();
    }

    /**
     * 根据ID删除数据
     *
     * @param id 数据对应ID
     */
    default void deleteById(ID id) {
        getRepository().deleteById(id);
    }

    /**
     * 保存或更新数据
     *
     * @param domain 数据对应实体
     * @return 已保存数据
     */
    default E save(E domain) {
        return getRepository().save(domain);
    }

    /**
     * 批量保存或更新数据
     *
     * @param entities 实体集合
     * @return 已经保存的实体集合
     */
    default <S extends E> List<S> saveAll(Iterable<S> entities) {
        return getRepository().saveAll(entities);
    }

    /**
     * 保存或者更新
     *
     * @param entity 实体
     * @return 保存后实体
     */
    default E saveAndFlush(E entity) {
        return getRepository().saveAndFlush(entity);
    }

    /**
     * 批量保存或者更新
     *
     * @param entities 实体列表
     * @return 保存或更新后的实体
     */
    default List<E> saveAllAndFlush(List<E> entities) {
        return getRepository().saveAllAndFlush(entities);
    }

    /**
     * 刷新实体状态
     */
    default void flush() {
        getRepository().flush();
    }
}
