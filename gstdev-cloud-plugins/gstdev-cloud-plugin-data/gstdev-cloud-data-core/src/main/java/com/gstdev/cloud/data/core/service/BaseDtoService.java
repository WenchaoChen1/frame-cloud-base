package com.gstdev.cloud.data.core.service;

import com.gstdev.cloud.base.definition.domain.base.Entity;
import com.gstdev.cloud.base.definition.domain.base.pojo.BaseDtoInterface;
import com.gstdev.cloud.data.core.utils.BasePage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.io.Serializable;
import java.util.List;

public interface BaseDtoService<E extends Entity
    , ID extends Serializable
    , D extends BaseDtoInterface<ID>> extends BaseService<E, ID> {
    /**
     * 根据ID查询数据
     *
     * @param id 数据ID
     * @return 与ID对应的数据，如果不存在则返回空
     */
    D findByIdToDto(ID id);

//    /**
//     * 查询数量
//     *
//     * @param specification {@link Specification}
//     * @return 数据数量
//     */
//    long count(Specification<D> specification);

    /**
     * 查询全部数据
     *
     * @return 全部数据列表
     */
    List<D> findAllToDto();

    /**
     * 查询全部数据
     *
     * @param sort {@link Sort}
     * @return 已排序的全部数据列表
     */
    List<D> findAllToDto(Sort sort);

    /**
     * 查询全部数据
     *
     * @param specification {@link Specification}
     * @return 全部数据列表
     */
    List<D> findAllToDto(Specification<E> specification);
//    List<D> findAllToDto(Specification<D> specification);

    /**
     * 查询全部数据
     *
     * @param specification {@link Specification}
     * @param sort          {@link Sort}
     * @return 全部数据列表
     */
    List<D> findAllToDto(Specification<E> specification, Sort sort);
//    List<D> findAllToDto(Specification<D> specification, Sort sort);

    /**
     * 查询分页数据
     *
     * @param pageable {@link Pageable}
     * @return 分页数据
     */
    Page<D> findByPageToDto(Pageable pageable);

    /**
     * 查询分页数据
     *
     * @param page {@link BasePage}
     * @return 分页数据
     */
    Page<D> findByPageToDto(BasePage page);

    /**
     * 查询分页数据
     *
     * @param pageNumber 当前页码, 起始页码 0
     * @param pageSize   每页显示的数据条数
     * @return 分页数据
     */
    Page<D> findByPageToDto(int pageNumber, int pageSize);

    /**
     * 查询分页数据
     *
     * @param pageNumber 当前页码, 起始页码 0
     * @param pageSize   每页显示的数据条数
     * @param sort       排序
     * @return 分页数据
     */
    Page<D> findByPageToDto(int pageNumber, int pageSize, Sort sort);

    /**
     * 查询分页数据
     *
     * @param pageNumber 当前页码, 起始页码 0
     * @param pageSize   每页显示的数据条数
     * @param direction  {@link Sort.Direction}
     * @param properties 排序的属性名称
     * @return 分页数据
     */
    Page<D> findByPageToDto(int pageNumber, int pageSize, Sort.Direction direction, String... properties);

    /**
     * 查询分页数据
     *
     * @param specification {@link Specification}
     * @param pageable      {@link Pageable}
     * @return 分页数据
     */
    Page<D> findByPageToDto(Specification<E> specification, Pageable pageable);
//    Page<D> findByPageToDto(Specification<D> specification, Pageable pageable);

    /**
     * 查询分页数据
     *
     * @param specification {@link Specification}
     * @param pageNumber    当前页码, 起始页码 0
     * @param pageSize      每页显示的数据条数
     * @return 分页数据
     */
    Page<D> findByPageToDto(Specification<E> specification, int pageNumber, int pageSize);
//    Page<D> findByPageToDto(Specification<D> specification, int pageNumber, int pageSize);

    /**
     * 查询分页数据
     *
     * @param pageNumber 当前页码, 起始页码 0
     * @param pageSize   每页显示的数据条数
     * @param direction  {@link Sort.Direction}
     * @return 分页数据
     */
    Page<D> findByPageToDto(int pageNumber, int pageSize, Sort.Direction direction);

    /**
     * 删除数据
     *
     * @param entity 数据对应实体
     */
//    void delete(D entity);

    /**
     * 删除指定多个数据
     *
     * @param entities 数据对应实体集合
     */
//    void deleteAll(Iterable<D> entities);


    /**
     * 保存或更新数据
     *
     * @param domain 数据对应实体
     * @return 已保存数据
     */
    D saveToDto(E domain);

    D saveToDto(D domain);

    /**
     * 批量保存或更新数据
     *
     * @param entities 实体集合
     * @return 已经保存的实体集合
     */
//    <S> List<S> saveAllToDto(Iterable<E> entities);
//    <S> List<S> saveAllDtoToDto(Iterable<D> entities);

    /**
     * 保存或者更新
     *
     * @param entity 实体
     * @return 保存后实体
     */
    D saveAndFlushToDto(E entity);
    D saveAndFlushToDto(D entity);

    /**
     * 批量保存或者更新
     *
     * @param entities 实体列表
     * @return 保存或更新后的实体
     */
    List<D> saveAllAndFlushToDto(List<E> entities);

    List<D> saveAllAndFlushDtoToDto(List<D> entities);

    D insertToDto(E var);

    D insertToDto(D var);

    List<D> insertAllToDto(List<E> e);

    List<D> insertAllDtoToDto(List<D> d);

    D updateToDto(E e);

    D updateToDto(D e);

    List<D> updateAllToDto(List<E> e);

    List<D> updateAllDtoToDto(List<D> d);

}
