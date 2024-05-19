//package com.gstdev.cloud.data.core.service;
//
//import com.gstdev.cloud.base.definition.constants.SymbolConstants;
//import com.gstdev.cloud.base.definition.domain.base.Entity;
//import com.gstdev.cloud.data.core.repository.BaseRepository;
//import com.gstdev.cloud.data.core.utils.BasePage;
//import org.apache.commons.lang3.ArrayUtils;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.jpa.domain.Specification;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * <p>Description: 通用核心 Service </p>
// *
// * @author : cc
// * @date : 2021/7/14 14:32
// */
//@Transactional
//public abstract class AAAbBaseServiceImpl<E extends Entity, ID extends Serializable> implements BaseService<E, ID> {
//
//    protected String like(String property) {
//        return SymbolConstants.PERCENT + property + SymbolConstants.PERCENT;
//    }
//    protected BaseRepository baseRepository;
////    public BaseServiceImpl(BaseRepository baseRepository) {
////        this.baseRepository = baseRepository;
////    }
//
//    /**
//     * 获取Repository
//     *
//     * @return {@link BaseRepository}
//     */
//    public BaseRepository<E, ID> getRepository() {
//        return baseRepository;
//    }
//
//    /**
//     * 根据ID查询数据
//     *
//     * @param id 数据ID
//     * @return 与ID对应的数据，如果不存在则返回空
//     */
//    @Override
//    public E findById(ID id) {
//        return getRepository().findById(id).orElse(null);
//    }
//
//    /**
//     * 数据是否存在
//     *
//     * @param id 数据ID
//     * @return true 存在，false 不存在
//     */
//    @Override
//    public boolean existsById(ID id) {
//        return getRepository().existsById(id);
//    }
//
//    /**
//     * 查询数量
//     *
//     * @return 数据数量
//     */
//    @Override
//    public long count() {
//        return getRepository().count();
//    }
//
//    /**
//     * 查询数量
//     *
//     * @param specification {@link Specification}
//     * @return 数据数量
//     */
//    @Override
//    public long count(Specification<E> specification) {
//        return getRepository().count(specification);
//    }
//
//    /**
//     * 查询全部数据
//     *
//     * @return 全部数据列表
//     */
//    @Override
//    public List<E> findAll() {
//        return getRepository().findAll();
//    }
//
//    /**
//     * 查询全部数据
//     *
//     * @param sort {@link Sort}
//     * @return 已排序的全部数据列表
//     */
//    @Override
//    public List<E> findAll(Sort sort) {
//        return getRepository().findAll(sort);
//    }
//
//    /**
//     * 查询全部数据
//     *
//     * @param specification {@link Specification}
//     * @return 全部数据列表
//     */
//    @Override
//    public List<E> findAll(Specification<E> specification) {
//        return getRepository().findAll(specification);
//    }
//
//    /**
//     * 查询全部数据
//     *
//     * @param specification {@link Specification}
//     * @param sort          {@link Sort}
//     * @return 全部数据列表
//     */
//    @Override
//    public List<E> findAll(Specification<E> specification, Sort sort) {
//        return getRepository().findAll(specification, sort);
//    }
//
//    /**
//     * 查询分页数据
//     *
//     * @param pageable {@link Pageable}
//     * @return 分页数据
//     */
//    @Override
//    public Page<E> findByPage(Pageable pageable) {
//        return getRepository().findAll(pageable);
//    }
//
//    /**
//     * 查询分页数据
//     *
//     * @param page 页
//     * @return 分页数据
//     */
//    @Override
//    public Page<E> findByPage(BasePage page) {
//        if (ArrayUtils.isNotEmpty(page.getProperties())) {
//            Sort.Direction direction = Sort.Direction.valueOf(page.getDirection());
//            return findByPage(page.getPageNumber(), page.getPageSize(), direction, page.getProperties());
//        } else {
//            return findByPage(page.getPageNumber(), page.getPageSize());
//        }
//    }
//
//    /**
//     * 查询分页数据
//     *
//     * @param pageNumber 当前页码, 起始页码 0
//     * @param pageSize   每页显示的数据条数
//     * @return 分页数据
//     */
//    @Override
//    public Page<E> findByPage(int pageNumber, int pageSize) {
//        return findByPage(PageRequest.of(pageNumber, pageSize));
//    }
//
//    /**
//     * 查询分页数据
//     *
//     * @param pageNumber 当前页码, 起始页码 0
//     * @param pageSize   每页显示的数据条数
//     * @param sort       排序
//     * @return 分页数据
//     */
//    @Override
//    public Page<E> findByPage(int pageNumber, int pageSize, Sort sort) {
//        return findByPage(PageRequest.of(pageNumber, pageSize, sort));
//    }
//
//    /**
//     * 查询分页数据
//     *
//     * @param pageNumber 当前页码, 起始页码 0
//     * @param pageSize   每页显示的数据条数
//     * @param direction  {@link Sort.Direction}
//     * @param properties 排序的属性名称
//     * @return 分页数据
//     */
//    @Override
//    public Page<E> findByPage(int pageNumber, int pageSize, Sort.Direction direction, String... properties) {
//        return findByPage(PageRequest.of(pageNumber, pageSize, direction, properties));
//    }
//
//    /**
//     * 查询分页数据
//     *
//     * @param specification {@link Specification}
//     * @param pageable      {@link Pageable}
//     * @return 分页数据
//     */
//    @Override
//    public Page<E> findByPage(Specification<E> specification, Pageable pageable) {
//        return getRepository().findAll(specification, pageable);
//    }
//
//    /**
//     * 查询分页数据
//     *
//     * @param specification {@link Specification}
//     * @param pageNumber    当前页码, 起始页码 0
//     * @param pageSize      每页显示的数据条数
//     * @return 分页数据
//     */
//    @Override
//    public Page<E> findByPage(Specification<E> specification, int pageNumber, int pageSize) {
//        return getRepository().findAll(specification, PageRequest.of(pageNumber, pageSize));
//    }
//
//    /**
//     * 查询分页数据
//     *
//     * @param pageNumber 当前页码, 起始页码 0
//     * @param pageSize   每页显示的数据条数
//     * @param direction  {@link Sort.Direction}
//     * @return 分页数据
//     */
//    @Override
//    public Page<E> findByPage(int pageNumber, int pageSize, Sort.Direction direction) {
//        return findByPage(PageRequest.of(pageNumber, pageSize, direction));
//    }
//
//
//    /**
//     * 删除数据
//     *
//     * @param entity 数据对应实体
//     */
//    @Override
//    public void delete(E entity) {
//        getRepository().delete(entity);
//    }
//
//    /**
//     * 批量全部删除
//     */
//    @Override
//    public void deleteAllInBatch() {
//        getRepository().deleteAllInBatch();
//    }
//
//    /**
//     * 删除指定多个数据
//     *
//     * @param entities 数据对应实体集合
//     */
//    @Override
//    public void deleteAll(Iterable<E> entities) {
//        getRepository().deleteAll(entities);
//    }
//
//    /**
//     * 删除全部数据
//     */
//    @Override
//    public void deleteAll() {
//        getRepository().deleteAll();
//    }
//
//    /**
//     * 根据ID删除数据
//     *
//     * @param id 数据对应ID
//     */
//    @Override
//    public void deleteById(ID id) {
//        getRepository().deleteById(id);
//    }
//
//    /**
//     * 保存或更新数据
//     *
//     * @param domain 数据对应实体
//     * @return 已保存数据
//     */
//    @Override
//    public E save(E domain) {
//        return getRepository().save(domain);
//    }
//
//    /**
//     * 批量保存或更新数据
//     *
//     * @param entities 实体集合
//     * @return 已经保存的实体集合
//     */
//    @Override
//    public List<E> saveAll(Iterable<E> entities) {
//        return getRepository().saveAll(entities);
//    }
//
//    /**
//     * 保存或者更新
//     *
//     * @param entity 实体
//     * @return 保存后实体
//     */
//    @Override
//    public E saveAndFlush(E entity) {
//        return getRepository().saveAndFlush(entity);
//    }
//
//    /**
//     * 批量保存或者更新
//     *
//     * @param entities 实体列表
//     * @return 保存或更新后的实体
//     */
//    @Override
//    public List<E> saveAllAndFlush(List<E> entities) {
//        return getRepository().saveAllAndFlush(entities);
//    }
//
//
//    @Override
//    public E insert(E var) {
//        return save(var);
//    }
//
//    @Override
//    public List<E> insertAll(List<E> e) {
//        List<E> es = new ArrayList<>();
//        for (E e1 : e) {
//            es.add(insert(e1));
//        }
//        return es;
//    }
//
//    @Override
//    public E update(E e) {
//        return save(e);
//    }
//
//    @Override
//    public List<E> updateAll(List<E> e) {
//        List<E> es = new ArrayList<>();
//        for (E e1 : e) {
//            es.add(update(e1));
//        }
//        return es;
//    }
//
//    /**
//     * 刷新实体状态
//     */
//    @Override
//    public void flush() {
//        getRepository().flush();
//    }
//}
