package com.gstdev.cloud.data.core.service;

import com.gstdev.cloud.base.definition.constants.SymbolConstants;
import com.gstdev.cloud.base.definition.domain.base.Entity;
import com.gstdev.cloud.data.core.repository.BaseRepository;
import com.gstdev.cloud.data.core.utils.BasePage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public interface BaseServiceDefault<SE extends Entity, ID extends Serializable> extends BaseService<SE, ID> {
    default String like(String property) {
        return SymbolConstants.PERCENT + property + SymbolConstants.PERCENT;
    }

    /**
     * 获取Repository
     *
     * @return {@link BaseRepository}
     */
    BaseRepository<SE, ID> getRepository();

    BaseServiceDefault<SE, ID> getService();

    default SE toServiceEntity(SE entity) {
        return entity;
    }

    default List<SE> toServiceEntity(List<SE> entity) {
        return entity;
    }

    default Page<SE> toServiceEntity(Page<SE> entity) {
        return entity;
    }

    default SE toEntity(SE sEntity) {
        return sEntity;
    }

    default List<SE> toEntity(List<SE> sEntity) {
        return sEntity;
    }

    default Page<SE> toEntity(Page<SE> sEntity) {
        return sEntity;
    }

    default Iterable<SE> toEntity(Iterable<SE> sEntity) {
        return sEntity;
    }

    default Specification<SE> toEntity(Specification<SE> sEntity) {
        return sEntity;
    }


    /**
     * 根据ID查询数据
     *
     * @param id 数据ID
     * @return 与ID对应的数据，如果不存在则返回空
     */
    @Override
    @Transactional(readOnly = true)
    default SE findById(ID id) {
        return toServiceEntity(getRepository().findById(id).orElse(null));
    }

    /**
     * 根据ID查询数据
     *
     * @param id 数据ID
     * @return 与ID对应的数据，如果不存在则返回空
     */
    @Override
    @Transactional(readOnly = true)
    default List<SE> findAllById(Iterable<ID> id) {
        return toServiceEntity(getRepository().findAllById(id));
    }

    /**
     * 数据是否存在
     *
     * @param id 数据ID
     * @return true 存在，false 不存在
     */
    @Override
    @Transactional(readOnly = true)
    default boolean existsById(ID id) {
        return getRepository().existsById(id);
    }

    /**
     * 查询数量
     *
     * @return 数据数量
     */
    @Override
    @Transactional(readOnly = true)
    default long count() {
        return getRepository().count();
    }

    /**
     * 查询数量
     *
     * @param specification {@link Specification}
     * @return 数据数量
     */
    @Override
    @Transactional(readOnly = true)
    default long count(Specification<SE> specification) {
        return getRepository().count(toEntity(specification));
    }

    /**
     * 查询全部数据
     *
     * @return 全部数据列表
     */
    @Override
    @Transactional(readOnly = true)
    default List<SE> findAll() {
        return toServiceEntity(getRepository().findAll());
    }

    /**
     * 查询全部数据
     *
     * @param sort {@link Sort}
     * @return 已排序的全部数据列表
     */
    @Override
    @Transactional(readOnly = true)
    default List<SE> findAll(Sort sort) {
        return toServiceEntity(getRepository().findAll(sort));
    }

    /**
     * 查询全部数据
     *
     * @param specification {@link Specification}
     * @return 全部数据列表
     */
    @Override
    @Transactional(readOnly = true)
    default List<SE> findAll(Specification<SE> specification) {
        return toServiceEntity(getRepository().findAll(toEntity(specification)));
    }

    /**
     * 查询全部数据
     *
     * @param specification {@link Specification}
     * @param sort          {@link Sort}
     * @return 全部数据列表
     */
    @Override
    @Transactional(readOnly = true)
    default List<SE> findAll(Specification<SE> specification, Sort sort) {
        return toServiceEntity(getRepository().findAll(toEntity(specification), sort));
    }

    /**
     * 查询分页数据
     *
     * @param pageable {@link Pageable}
     * @return 分页数据
     */
    @Override
    @Transactional(readOnly = true)
    default Page<SE> findByPage(Pageable pageable) {
        return toServiceEntity(getRepository().findAll(pageable));
    }

    /**
     * 查询分页数据
     *
     * @param page 页
     * @return 分页数据
     */
    @Override
    @Transactional(readOnly = true)
    default Page<SE> findByPage(BasePage page) {
        if (page.getSort().isEmpty()) {
            return getService().findByPage(page.getPageNumber(), page.getPageSize());
        } else {
            return getService().findByPage(page.getPageNumber(), page.getPageSize(), page.getSort());
        }

    }

    /**
     * 查询分页数据
     *
     * @param pageNumber 当前页码, 起始页码 0
     * @param pageSize   每页显示的数据条数
     * @return 分页数据
     */
    @Override
    @Transactional(readOnly = true)
    default Page<SE> findByPage(int pageNumber, int pageSize) {
        return getService().findByPage(PageRequest.of(pageNumber, pageSize));
    }

    /**
     * 查询分页数据
     *
     * @param pageNumber 当前页码, 起始页码 0
     * @param pageSize   每页显示的数据条数
     * @param sort       排序
     * @return 分页数据
     */
    @Override
    @Transactional(readOnly = true)
    default Page<SE> findByPage(int pageNumber, int pageSize, Sort sort) {
        return getService().findByPage(PageRequest.of(pageNumber, pageSize, sort));
    }

    /**
     * 查询分页数据
     *
     * @param pageNumber 当前页码, 起始页码 0
     * @param pageSize   每页显示的数据条数
     * @param direction  {@link Sort.Direction}
     * @param properties 排序的属性名称
     * @return 分页数据
     */
    @Override
    @Transactional(readOnly = true)
    default Page<SE> findByPage(int pageNumber, int pageSize, Sort.Direction direction, String... properties) {
        return getService().findByPage(PageRequest.of(pageNumber, pageSize, direction, properties));
    }

    /**
     * 查询分页数据
     *
     * @param specification {@link Specification}
     * @param pageable      {@link Pageable}
     * @return 分页数据
     */
    @Override
    @Transactional(readOnly = true)
    default Page<SE> findByPage(Specification<SE> specification, Pageable pageable) {
        return toServiceEntity(getRepository().findAll(toEntity(specification), pageable));
    }

    /**
     * 查询分页数据
     *
     * @param specification {@link Specification}
     * @param pageable      {@link Pageable}
     * @return 分页数据
     */
    @Override
    @Transactional(readOnly = true)
    default Page<SE> findByPage(Specification<SE> specification, BasePage pageable) {
        PageRequest pageRequest = null;
        if (pageable.getSort().isEmpty()) {
            pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
        } else {
            pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort());
        }

        return toServiceEntity(getRepository().findAll(toEntity(specification), pageRequest));
    }

    /**
     * 查询分页数据
     *
     * @param specification {@link Specification}
     * @param pageNumber    当前页码, 起始页码 0
     * @param pageSize      每页显示的数据条数
     * @return 分页数据
     */
    @Override
    @Transactional(readOnly = true)
    default Page<SE> findByPage(Specification<SE> specification, int pageNumber, int pageSize) {
        return toServiceEntity(getRepository().findAll(toEntity(specification), PageRequest.of(pageNumber, pageSize)));
    }

    /**
     * 查询分页数据
     *
     * @param pageNumber 当前页码, 起始页码 0
     * @param pageSize   每页显示的数据条数
     * @param direction  {@link Sort.Direction}
     * @return 分页数据
     */
    @Override
    @Transactional(readOnly = true)
    default Page<SE> findByPage(int pageNumber, int pageSize, Sort.Direction direction) {
        return getService().findByPage(PageRequest.of(pageNumber, pageSize, direction));
    }


    /**
     * 删除数据
     *
     * @param entity 数据对应实体
     */
    @Override
    @Transactional
    default void delete(SE entity) {
        getRepository().delete(toEntity(entity));
    }

    /**
     * 批量全部删除
     */
    @Override
    @Transactional
    default void deleteAllInBatch() {
        getRepository().deleteAllInBatch();
    }

    /**
     * 删除指定多个数据
     *
     * @param entities 数据对应实体集合
     */
    @Override
    @Transactional
    default void deleteAll(Iterable<SE> entities) {
        getRepository().deleteAll(entities);
    }

    /**
     * 删除全部数据
     */
    @Override
    @Transactional
    default void deleteAll() {
        getRepository().deleteAll();
    }

    /**
     * 根据ID删除数据
     *
     * @param id 数据对应ID
     */
    @Override
    @Transactional
    default void deleteAllById(List<ID> id) {
        getRepository().deleteAllById(id);
    }

    /**
     * 根据ID删除数据
     *
     * @param id 数据对应ID
     */
    @Override
    @Transactional
    default void deleteById(ID id) {
        getRepository().deleteById(id);
    }

    /**
     * 保存或更新数据
     *
     * @param domain 数据对应实体
     * @return 已保存数据
     */
    @Override
    @Transactional
    default SE save(SE domain) {
        SE entity = toEntity(domain);
        return toServiceEntity(getRepository().save(entity));
    }

    /**
     * 批量保存或更新数据
     *
     * @param entities 实体集合
     * @return 已经保存的实体集合
     */
    @Override
    @Transactional
    default List<SE> saveAll(Iterable<SE> entities) {
        return toServiceEntity(getRepository().saveAll(toEntity(entities)));
    }

    /**
     * 保存或者更新
     *
     * @param entity 实体
     * @return 保存后实体
     */
    @Override
    @Transactional
    default SE saveAndFlush(SE entity) {
        return toServiceEntity(getRepository().saveAndFlush(toEntity(entity)));
    }

    /**
     * 批量保存或者更新
     *
     * @param entities 实体列表
     * @return 保存或更新后的实体
     */
    @Override
    @Transactional
    default List<SE> saveAllAndFlush(List<SE> entities) {
        return toServiceEntity(getRepository().saveAllAndFlush(toEntity(entities)));
    }


    @Override
    @Transactional
    default SE insert(SE var) {
        return toServiceEntity(getService().save(toEntity(var)));
    }

    @Override
    @Transactional
    default List<SE> insert(List<SE> e) {
        List<SE> es = new ArrayList<>();
        for (SE e1 : e) {
            es.add(getService().insert(e1));
        }
        return es;
    }

    @Override
    @Transactional
    default SE update(SE e) {
        return toServiceEntity(getService().save(toEntity(e)));
    }

    @Override
    @Transactional
    default List<SE> update(List<SE> e) {
        List<SE> es = new ArrayList<>();
        for (SE e1 : e) {
            es.add(getService().update(e1));
        }
        return es;
    }

    @Override
    @Transactional
    default SE insertAndUpdate(SE e) {
        return toServiceEntity(getService().save(toEntity(e)));
    }

    @Override
    @Transactional
    default List<SE> insertAndUpdate(List<SE> e) {
        List<SE> es = new ArrayList<>();
        for (SE e1 : e) {
            es.add(getService().insertAndUpdate(e1));
        }
        return es;
    }

    /**
     * 刷新实体状态
     */
    @Override
    @Transactional
    default void flush() {
        getRepository().flush();
    }
}
