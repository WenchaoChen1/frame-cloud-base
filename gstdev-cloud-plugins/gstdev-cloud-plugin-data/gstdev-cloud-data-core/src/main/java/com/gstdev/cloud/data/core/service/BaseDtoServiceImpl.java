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

/**
 * <p>Description: 通用核心 Service </p>
 *
 * @author : cc
 * @date : 2021/7/14 14:32
 */
@Transactional
public  class BaseDtoServiceImpl<E extends Entity
    , ID extends Serializable
    , R extends BaseRepository<E, ID>
    , M extends BaseDtoMapper<E, D>
//    , D extends BaseDtoInterface<ID>
    , D
    > extends BaseServiceImpl<E, ID,R> implements BaseDtoServiceDefault<E,ID,D>{

    private M mapper;

    public BaseDtoServiceImpl(R repository, M mapper) {
        super(repository);
        this.mapper = mapper;
    }

    public M getMapper() {
        return mapper;
    }

    public void setMapper(M mapper) {
        this.mapper = mapper;
    }

    /**
     * 根据ID查询数据
     *
     * @param id 数据ID
     * @return 与ID对应的数据，如果不存在则返回空
     */
//@Override
    public D findByIdToDto(ID id) {
        return getMapper().toDto(findById(id));
    }

    /**
     * 查询全部数据
     *
     * @return 全部数据列表
     */
//@Override
    public List<D> findAllToDto() {
        return getMapper().toDto(findAll());
    }

    /**
     * 查询全部数据
     *
     * @param sort {@link Sort}
     * @return 已排序的全部数据列表
     */
//@Override
    public List<D> findAllToDto(Sort sort) {
        return getMapper().toDto(findAll(sort));
    }

    /**
     * 查询全部数据
     *
     * @param specification {@link Specification}
     * @return 全部数据列表
     */
//@Override
    public List<D> findAllToDto(Specification<E> specification) {
        return getMapper().toDto(findAll(specification));
    }
    /**
     * 查询全部数据
     *
     * @param specification {@link Specification}
     * @param sort          {@link Sort}
     * @return 全部数据列表
     */
//@Override
    public List<D> findAllToDto(Specification<E> specification, Sort sort) {
        return getMapper().toDto(findAll(specification, sort));
    }

    /**
     * 查询分页数据
     *
     * @param pageable {@link Pageable}
     * @return 分页数据
     */
//@Override
    public Page<D> findByPageToDto(Pageable pageable) {
        return getMapper().toDto(findByPage(pageable));
    }

    /**
     * 查询分页数据
     *
     * @param page 页
     * @return 分页数据
     */
//@Override
    public Page<D> findByPageToDto(BasePage page) {
        return getMapper().toDto(findByPage(page));
    }

    /**
     * 查询分页数据
     *
     * @param pageNumber 当前页码, 起始页码 0
     * @param pageSize   每页显示的数据条数
     * @return 分页数据
     */
//@Override
    public Page<D> findByPageToDto(int pageNumber, int pageSize) {
        return getMapper().toDto(findByPage(pageNumber, pageSize));
    }

    /**
     * 查询分页数据
     *
     * @param pageNumber 当前页码, 起始页码 0
     * @param pageSize   每页显示的数据条数
     * @param sort       排序
     * @return 分页数据
     */
//@Override
    public Page<D> findByPageToDto(int pageNumber, int pageSize, Sort sort) {
        return getMapper().toDto(findByPage(pageNumber, pageSize, sort));
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
//@Override
    public Page<D> findByPageToDto(int pageNumber, int pageSize, Sort.Direction direction, String... properties) {
        return getMapper().toDto(findByPage(pageNumber, pageSize, direction, properties));
    }

    /**
     * 查询分页数据
     *
     * @param specification {@link Specification}
     * @param pageable      {@link Pageable}
     * @return 分页数据
     */
//@Override
    public Page<D> findByPageToDto(Specification<E> specification, Pageable pageable) {
        return getMapper().toDto(findByPage(specification, pageable));
    }

    /**
     * 查询分页数据
     *
     * @param specification {@link Specification}
     * @param pageNumber    当前页码, 起始页码 0
     * @param pageSize      每页显示的数据条数
     * @return 分页数据
     */
//@Override
    public Page<D> findByPageToDto(Specification<E> specification, int pageNumber, int pageSize) {
        return getMapper().toDto(findByPage(specification, PageRequest.of(pageNumber, pageSize)));
    }

    /**
     * 查询分页数据
     *
     * @param pageNumber 当前页码, 起始页码 0
     * @param pageSize   每页显示的数据条数
     * @param direction  {@link org.springframework.data.domain.Sort.Direction}
     * @return 分页数据
     */
//@Override
    public Page<D> findByPageToDto(int pageNumber, int pageSize, Sort.Direction direction) {
        return getMapper().toDto(findByPage(pageNumber, pageSize, direction));
    }



    /**
     * 保存或更新数据
     *
     * @param domain 数据对应实体
     * @return 已保存数据
     */
//@Override
    public D saveToDto(E domain) {
        return getMapper().toDto(save(domain));
    }
//@Override
    public D saveToDto(D domain) {
        return saveToDto(getMapper().toEntity(domain));
    }

//    /**
//     * 批量保存或更新数据
//     *
//     * @param entities 实体集合
//     * @return 已经保存的实体集合
//     */
////@Override
//    public <S extends E> List<S> saveAll(Iterable<S> entities) {
//        return getMapper().toDto(saveAll(entities));
//    }

    /**
     * 保存或者更新
     *
     * @param entity 实体
     * @return 保存后实体
     */
//@Override
    public D saveAndFlushToDto(E entity) {
        return getMapper().toDto(saveAndFlush(entity));
    }
//@Override
    public D saveAndFlushToDto(D entity) {
        return saveAndFlushToDto(getMapper().toEntity(entity));
    }

    /**
     * 批量保存或者更新
     *
     * @param entities 实体列表
     * @return 保存或更新后的实体
     */
//@Override
    public List<D> saveAllAndFlushToDto(List<E> entities) {
        return getMapper().toDto(saveAllAndFlush(entities));
    }
//@Override
    public List<D> saveAllAndFlushDtoToDto(List<D> entities) {
        return saveAllAndFlushToDto(getMapper().toEntity(entities));
    }


//@Override
    public D insertToDto(E e) {
        return getMapper().toDto(insert(e));
    }

//@Override
    public D insertToDto(D e) {
        return insertToDto(getMapper().toEntity(e));
    }

//@Override
    public List<D> insertAllToDto(List<E> e) {
        return getMapper().toDto(insertAll(e));
    }

//@Override
    public List<D> insertAllDtoToDto(List<D> e) {
        return insertAllToDto(getMapper().toEntity(e));
    }

//@Override
    public D updateToDto(E e) {
        return getMapper().toDto(update(e));
    }
//@Override
    public D updateToDto(D e) {
        return updateToDto(getMapper().toEntity(e));
    }

//@Override
    public List<D> updateAllToDto(List<E> e) {
        return getMapper().toDto(updateAll(e));
    }

//@Override
    public List<D> updateAllDtoToDto(List<D> e) {
        return getMapper().toDto(updateAll(getMapper().toEntity(e)));
    }


}
