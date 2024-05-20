package com.gstdev.cloud.data.core.service;

import com.gstdev.cloud.data.core.utils.BasePage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.io.Serializable;
import java.util.List;

public interface BaseService<E extends Serializable, ID extends Serializable> {
    /**
     * 根据ID查询数据
     *
     * @param id 数据ID
     * @return 与ID对应的数据，如果不存在则返回空
     */
    E findById(ID id);

    /**
     * 数据是否存在
     *
     * @param id 数据ID
     * @return true 存在，false 不存在
     */
    boolean existsById(ID id);

    /**
     * 查询数量
     *
     * @return 数据数量
     */
    long count();

    /**
     * 查询数量
     *
     * @param specification {@link Specification}
     * @return 数据数量
     */
    long count(Specification<E> specification);

    /**
     * 查询全部数据
     *
     * @return 全部数据列表
     */
    List<E> findAll();

    /**
     * 查询全部数据
     *
     * @param sort {@link Sort}
     * @return 已排序的全部数据列表
     */
    List<E> findAll(Sort sort);

    /**
     * 查询全部数据
     *
     * @param specification {@link Specification}
     * @return 全部数据列表
     */
    List<E> findAll(Specification<E> specification);

    /**
     * 查询全部数据
     *
     * @param specification {@link Specification}
     * @param sort          {@link Sort}
     * @return 全部数据列表
     */
    List<E> findAll(Specification<E> specification, Sort sort);

    /**
     * 查询分页数据
     *
     * @param pageable {@link Pageable}
     * @return 分页数据
     */
    Page<E> findByPage(Pageable pageable);

    /**
     * 查询分页数据
     *
     * @param page {@link BasePage}
     * @return 分页数据
     */
    Page<E> findByPage(BasePage page);

    /**
     * 查询分页数据
     *
     * @param pageNumber 当前页码, 起始页码 0
     * @param pageSize   每页显示的数据条数
     * @return 分页数据
     */
    Page<E> findByPage(int pageNumber, int pageSize);

    /**
     * 查询分页数据
     *
     * @param pageNumber 当前页码, 起始页码 0
     * @param pageSize   每页显示的数据条数
     * @param direction  {@link org.springframework.data.domain.Sort.Direction}
     * @return 分页数据
     */
    Page<E> findByPage(int pageNumber, int pageSize, Sort.Direction direction);

    /**
     * 查询分页数据
     *
     * @param pageNumber 当前页码, 起始页码 0
     * @param pageSize   每页显示的数据条数
     * @param sort       排序
     * @return 分页数据
     */
    Page<E> findByPage(int pageNumber, int pageSize, Sort sort);

    /**
     * 查询分页数据
     *
     * @param pageNumber 当前页码, 起始页码 0
     * @param pageSize   每页显示的数据条数
     * @param direction  {@link org.springframework.data.domain.Sort.Direction}
     * @param properties 排序的属性名称
     * @return 分页数据
     */
    Page<E> findByPage(int pageNumber, int pageSize, Sort.Direction direction, String... properties);

    /**
     * 查询分页数据
     *
     * @param specification {@link Specification}
     * @param pageNumber    当前页码, 起始页码 0
     * @param pageSize      每页显示的数据条数
     * @return 分页数据
     */
    Page<E> findByPage(Specification<E> specification, int pageNumber, int pageSize);


    /**
     * 查询分页数据
     *
     * @param specification {@link Specification}
     * @param pageable      {@link Pageable}
     * @return 分页数据
     */
    Page<E> findByPage(Specification<E> specification, Pageable pageable);


    /**
     * 删除数据
     *
     * @param entity 数据对应实体
     */
    void delete(E entity);

    /**
     * 批量全部删除
     */
    void deleteAllInBatch();

    /**
     * 删除指定多个数据
     *
     * @param entities 数据对应实体集合
     */
    void deleteAll(Iterable<E> entities);

    /**
     * 删除全部数据
     */
    void deleteAll();

    /**
     * 根据ID删除数据
     *
     * @param id 数据对应ID
     */
    void deleteAllById(List<ID> id);

    /**
     * 根据ID删除数据
     *
     * @param id 数据对应ID
     */
    void deleteById(ID id);

    /**
     * 保存或更新数据
     *
     * @param domain 数据对应实体
     * @return 已保存数据
     */
    E save(E domain);

    /**
     * 批量保存或更新数据
     *
     * @param entities 实体集合
     * @return 已经保存的实体集合
     */
    List<E> saveAll(Iterable<E> entities);

    /**
     * 保存或者更新
     *
     * @param entity 实体
     * @return 保存后实体
     */
    E saveAndFlush(E entity);

    /**
     * 批量保存或者更新
     *
     * @param entities 实体列表
     * @return 保存或更新后的实体
     */
    List<E> saveAllAndFlush(List<E> entities);

    E insert(E var);

    List<E> insertAll(List<E> e);

    E update(E e);

    List<E> updateAll(List<E> e);

    /**
     * 刷新实体状态
     */
    void flush();
}
