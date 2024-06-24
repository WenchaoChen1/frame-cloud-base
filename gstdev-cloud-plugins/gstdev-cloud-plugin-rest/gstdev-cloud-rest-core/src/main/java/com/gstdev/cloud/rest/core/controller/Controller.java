package com.gstdev.cloud.rest.core.controller;

import com.gstdev.cloud.base.definition.domain.Result;
import com.gstdev.cloud.base.definition.domain.base.Entity;
import com.gstdev.cloud.data.core.service.BaseService;
import com.gstdev.cloud.data.core.utils.BasePage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * <p> Description : Controller基础定义 </p>
 * <p>
 * 这里只在方法上做了泛型，主要是考虑到返回的结果数据可以是各种类型，而不一定受限于某一种类型。
 *
 * @author : cc
 * @date : 2020/4/29 18:56
 */
public interface Controller<E extends Entity, ID extends Serializable> extends ResultController {

    /**
     * 获取Service
     *
     * @return Service
     */
    BaseService<E, ID> getService();
    //----------------------------------------------代码--------------------------------

    /**
     * 根据ID查询数据
     *
     * @param id 数据ID
     * @return 与ID对应的数据，如果不存在则返回空
     */
    default Result<E> findById(ID id) {
        E domain = getService().findById(id);
        return result(domain);
    }

    /**
     * 数据是否存在
     *
     * @param id 数据ID
     * @return true 存在，false 不存在
     */
    default Result<Boolean> existsById(ID id) {
        boolean b = getService().existsById(id);
        return result(b);
    }

    /**
     * 查询数量
     *
     * @return 数据数量
     */
    default Result<Long> count() {
        long count = getService().count();
        return result(count);
    }

    /**
     * 查询数量
     *
     * @param specification {@link Specification}
     * @return 数据数量
     */
    default Result<Long> count(Specification<E> specification) {
        long count = getService().count(specification);
        return result(count);
    }

    /**
     * 查询全部数据
     *
     * @return 全部数据列表
     */
    default Result<List<E>> findAll() {
        List<E> domains = getService().findAll();
        return result(domains);
    }

    /**
     * 查询全部数据
     *
     * @param sort {@link Sort}
     * @return 已排序的全部数据列表
     */
    default Result<List<E>> findAll(Sort sort) {
        List<E> domains = getService().findAll(sort);
        return result(domains);
    }

    /**
     * 查询全部数据
     *
     * @param specification {@link Specification}
     * @return 全部数据列表
     */
    default Result<List<E>> findAll(Specification<E> specification) {
        List<E> domains = getService().findAll(specification);
        return result(domains);
    }

    /**
     * 查询全部数据
     *
     * @param specification {@link Specification}
     * @param sort          {@link Sort}
     * @return 全部数据列表
     */
    default Result<List<E>> findAll(Specification<E> specification, Sort sort) {
        List<E> domains = getService().findAll(specification, sort);
        return result(domains);
    }

    /**
     * 查询分页数据
     *
     * @param pageable 分页
     * @return 分页数据
     */
    default Result<Map<String, Object>> findByPage(Pageable pageable) {
        Page<E> pages = getService().findByPage(pageable);
        return result(pages);
    }

    /**
     * 查询分页数据
     *
     * @param page 当前页码, 起始页码 0
     * @return 分页数据
     */
    default Result<Map<String, Object>> findByPage(BasePage page) {
        Page<E> pages = getService().findByPage(page);
        return result(pages);
    }

    /**
     * 查询分页数据
     *
     * @param pageNumber 当前页码，起始页码 0
     * @param pageSize   每页显示数据条数
     * @return {@link Result}
     */
    default Result<Map<String, Object>> findByPage(Integer pageNumber, Integer pageSize) {
        Page<E> pages = getService().findByPage(pageNumber, pageSize);
        return result(pages);
    }

    /**
     * 查询分页数据
     *
     * @param pageNumber 当前页码, 起始页码 0
     * @param pageSize   每页显示的数据条数
     * @param direction  {@link org.springframework.data.domain.Sort.Direction}
     * @return 分页数据
     */
    default Result<Map<String, Object>> findByPage(Integer pageNumber, Integer pageSize, Sort.Direction direction) {
        Page<E> pages = getService().findByPage(pageNumber, pageSize, direction);
        return result(pages);
    }

    /**
     * 查询分页数据
     *
     * @param pageNumber 当前页码, 起始页码 0
     * @param pageSize   每页显示的数据条数
     * @param sort       排序的属性名称
     * @return 分页数据
     */
    default Result<Map<String, Object>> findByPage(Integer pageNumber, Integer pageSize, Sort sort) {
        Page<E> pages = getService().findByPage(pageNumber, pageSize, sort);
        return result(pages);
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
    default Result<Map<String, Object>> findByPage(Integer pageNumber, Integer pageSize, Sort.Direction direction, String... properties) {
        Page<E> pages = getService().findByPage(pageNumber, pageSize, direction, properties);
        return result(pages);
    }

    /**
     * 查询分页数据
     *
     * @param specification {@link Specification}
     * @param pageNumber    当前页码, 起始页码 0
     * @param pageSize      每页显示的数据条数
     * @return 分页数据
     */
    default Result<Map<String, Object>> findByPage(Specification<E> specification, Integer pageNumber, Integer pageSize) {
        Page<E> pages = getService().findByPage(specification, pageNumber, pageSize);
        return result(pages);
    }


    /**
     * 查询分页数据
     *
     * @param specification {@link Specification}
     * @param pageable      {@link Pageable}
     * @return 分页数据
     */
    default Result<Map<String, Object>> findByPage(Specification<E> specification, Pageable pageable) {
        Page<E> pages = getService().findByPage(specification, pageable);
        return result(pages);
    }

    /**
     * 保存或更新实体
     *
     * @param domain 实体参数
     * @return 用Result包装的实体
     */

    default Result<E> saveOrUpdate(E domain) {
        E savedDomain = getService().saveAndFlush(domain);
        return result(savedDomain);
    }

    /**
     * 删除数据
     *
     * @param id 实体ID
     * @return 用Result包装的信息
     */
    default Result<String> delete(ID id) {
        Result<String> result = result(String.valueOf(id));
        getService().deleteById(id);
        return result;
    }
}
