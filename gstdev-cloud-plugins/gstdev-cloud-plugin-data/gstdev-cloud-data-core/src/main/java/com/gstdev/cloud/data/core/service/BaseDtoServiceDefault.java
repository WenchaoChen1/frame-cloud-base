package com.gstdev.cloud.data.core.service;

import com.gstdev.cloud.base.definition.domain.base.Entity;
import com.gstdev.cloud.data.core.mapper.BaseDtoMapper;
import com.gstdev.cloud.data.core.repository.BaseRepository;
import com.gstdev.cloud.data.core.utils.BasePage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

public interface BaseDtoServiceDefault<E extends Entity
        , ID extends Serializable
        , D
        > extends BaseDtoService<E, ID, D>, BaseServiceDefault<E, ID> {

    @Override
    BaseRepository<E, ID> getRepository();

    BaseDtoMapper<E, D> getMapper();

    @Override
    BaseDtoServiceDefault<E, ID, D> getService();

    /**
     * 根据ID查询数据
     *
     * @param id 数据ID
     * @return 与ID对应的数据，如果不存在则返回空
     */
    @Transactional(readOnly = true)
    @Override
    default D findByIdToDto(ID id) {
        return getMapper().toDto(getService().findById(id));
    }

    /**
     * 查询全部数据
     *
     * @return 全部数据列表
     */
    @Transactional(readOnly = true)
    @Override
    default List<D> findAllToDto() {
        return getMapper().toDto(getService().findAll());
    }

    /**
     * 查询全部数据
     *
     * @param sort {@link Sort}
     * @return 已排序的全部数据列表
     */
    @Transactional(readOnly = true)
    @Override
    default List<D> findAllToDto(Sort sort) {
        return getMapper().toDto(getService().findAll(sort));
    }

    /**
     * 查询全部数据
     *
     * @param specification {@link Specification}
     * @return 全部数据列表
     */
    @Transactional(readOnly = true)
    @Override
    default List<D> findAllToDto(Specification<E> specification) {
        return getMapper().toDto(getService().findAll(specification));
    }

    /**
     * 查询全部数据
     *
     * @param specification {@link Specification}
     * @param sort          {@link Sort}
     * @return 全部数据列表
     */
    @Transactional(readOnly = true)
    @Override
    default List<D> findAllToDto(Specification<E> specification, Sort sort) {
        return getMapper().toDto(getService().findAll(specification, sort));
    }

    /**
     * 查询分页数据
     *
     * @param pageable {@link Pageable}
     * @return 分页数据
     */
    @Transactional(readOnly = true)
    @Override
    default Page<D> findByPageToDto(Pageable pageable) {
        return getMapper().toDto(getService().findByPage(pageable));
    }

    /**
     * 查询分页数据
     *
     * @param page 页
     * @return 分页数据
     */
    @Transactional(readOnly = true)
    @Override
    default Page<D> findByPageToDto(BasePage page) {
        return getMapper().toDto(getService().findByPage(page));
    }

    /**
     * 查询分页数据
     *
     * @param pageNumber 当前页码, 起始页码 0
     * @param pageSize   每页显示的数据条数
     * @return 分页数据
     */
    @Transactional(readOnly = true)
    @Override
    default Page<D> findByPageToDto(int pageNumber, int pageSize) {
        return getMapper().toDto(getService().findByPage(pageNumber, pageSize));
    }

    /**
     * 查询分页数据
     *
     * @param pageNumber 当前页码, 起始页码 0
     * @param pageSize   每页显示的数据条数
     * @param sort       排序
     * @return 分页数据
     */
    @Transactional(readOnly = true)
    @Override
    default Page<D> findByPageToDto(int pageNumber, int pageSize, Sort sort) {
        return getMapper().toDto(getService().findByPage(pageNumber, pageSize, sort));
    }

    /**
     * 查询分页数据
     *
     * @param pageNumber 当前页码, 起始页码 0
     * @param pageSize   每页显示的数据条数
     * @param direction  {@link org.springframework.data.domain.Sort.Direction}
     * @param properties 排序的属性名称
     * @return 分页数据
     */
    @Transactional(readOnly = true)
    @Override
    default Page<D> findByPageToDto(int pageNumber, int pageSize, Sort.Direction direction, String... properties) {
        return getMapper().toDto(getService().findByPage(pageNumber, pageSize, direction, properties));
    }

    /**
     * 查询分页数据
     *
     * @param specification {@link Specification}
     * @param pageable      {@link Pageable}
     * @return 分页数据
     */
    @Transactional(readOnly = true)
    @Override
    default Page<D> findByPageToDto(Specification<E> specification, Pageable pageable) {
        return getMapper().toDto(getService().findByPage(specification, pageable));
    }

    /**
     * 查询分页数据
     *
     * @param specification {@link Specification}
     * @param pageable      {@link BasePage}
     * @return 分页数据
     */
    @Transactional(readOnly = true)
    @Override
    default Page<D> findByPageToDto(Specification<E> specification, BasePage pageable) {
        return getMapper().toDto(getService().findByPage(specification, pageable));
    }

    /**
     * 查询分页数据
     *
     * @param specification {@link Specification}
     * @param pageNumber    当前页码, 起始页码 0
     * @param pageSize      每页显示的数据条数
     * @return 分页数据
     */
    @Transactional(readOnly = true)
    @Override
    default Page<D> findByPageToDto(Specification<E> specification, int pageNumber, int pageSize) {
        return getMapper().toDto(getService().findByPage(specification, PageRequest.of(pageNumber, pageSize)));
    }

    /**
     * 查询分页数据
     *
     * @param pageNumber 当前页码, 起始页码 0
     * @param pageSize   每页显示的数据条数
     * @param direction  {@link org.springframework.data.domain.Sort.Direction}
     * @return 分页数据
     */
    @Transactional(readOnly = true)
    @Override
    default Page<D> findByPageToDto(int pageNumber, int pageSize, Sort.Direction direction) {
        return getMapper().toDto(getService().findByPage(pageNumber, pageSize, direction));
    }


    /**
     * 保存或更新数据
     *
     * @param domain 数据对应实体
     * @return 已保存数据
     */
    @Transactional
    @Override
    default D saveToDto(E domain) {
        return getMapper().toDto(getService().save(domain));
    }

    @Transactional
    @Override
    default D saveToDto(D domain) {
        return getService().saveToDto(getMapper().toEntity(domain));
    }

    //    /**
//     * 批量保存或更新数据
//     *
//     * @param entities 实体集合
//     * @return 已经保存的实体集合
//     */
//@Transactional
//    @Override
//    default <S extends E> List<S> saveAll(Iterable<S> entities) {
//        return getMapper().toDto(saveAll(entities));
//    }

    /**
     * 保存或者更新
     *
     * @param entity 实体
     * @return 保存后实体
     */
    @Transactional
    @Override
    default D saveAndFlushToDto(E entity) {
        return getMapper().toDto(getService().saveAndFlush(entity));
    }

    @Transactional
    @Override
    default D saveAndFlushToDto(D entity) {
        return getService().saveAndFlushToDto(getMapper().toEntity(entity));
    }

    /**
     * 批量保存或者更新
     *
     * @param entities 实体列表
     * @return 保存或更新后的实体
     */
    @Transactional
    @Override
    default List<D> saveAllAndFlushToDto(List<E> entities) {
        return getMapper().toDto(getService().saveAllAndFlush(entities));
    }

    @Transactional
    @Override
    default List<D> saveAllAndFlushDtoToDto(List<D> entities) {
        return getService().saveAllAndFlushToDto(getMapper().toEntity(entities));
    }


    @Transactional
    @Override
    default D insertToDto(E e) {
        return getMapper().toDto(getService().insert(e));
    }

    @Transactional
    @Override
    default D insertToDto(D e) {
        return getService().insertToDto(getMapper().toEntity(e));
    }

    @Transactional
    @Override
    default List<D> insertToDto(List<E> e) {
        return getMapper().toDto(getService().insert(e));
    }

    @Transactional
    @Override
    default List<D> insertDtoToDto(List<D> e) {
        return getService().insertToDto(getMapper().toEntity(e));
    }

    @Transactional
    @Override
    default D updateToDto(E e) {
        return getMapper().toDto(getService().update(e));
    }

    @Transactional
    @Override
    default D updateToDto(D e) {
        return getService().updateToDto(getMapper().toEntity(e));
    }

    @Transactional
    @Override
    default List<D> updateToDto(List<E> e) {
        return getMapper().toDto(getService().update(e));
    }

    @Transactional
    @Override
    default List<D> updateDtoToDto(List<D> e) {
        return getMapper().toDto(getService().update(getMapper().toEntity(e)));
    }

    @Transactional
    @Override
    default D insertAndUpdateToDto(E e) {
        return getMapper().toDto(getService().insertAndUpdate(e));
    }

    @Transactional
    @Override
    default D insertAndUpdateToDto(D e) {
        return getService().insertAndUpdateToDto(getMapper().toEntity(e));
    }

    @Transactional
    @Override
    default List<D> insertAndUpdateToDto(List<E> e) {
        return getMapper().toDto(getService().insertAndUpdate(e));
    }

    @Transactional
    @Override
    default List<D> insertAndUpdateDtoToDto(List<D> e) {
        return getMapper().toDto(getService().insertAndUpdate(getMapper().toEntity(e)));
    }
}
