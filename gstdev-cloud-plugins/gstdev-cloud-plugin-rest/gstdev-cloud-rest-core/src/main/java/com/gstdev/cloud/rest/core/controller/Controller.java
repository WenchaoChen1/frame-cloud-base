package com.gstdev.cloud.rest.core.controller;

import com.gstdev.cloud.base.definition.constants.DefaultConstants;
import com.gstdev.cloud.base.definition.domain.Result;
import com.gstdev.cloud.base.definition.domain.base.Entity;
import com.gstdev.cloud.data.core.service.BaseService;
import com.gstdev.cloud.data.core.utils.BasePage;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.dromara.hutool.core.tree.MapTree;
import org.dromara.hutool.core.tree.TreeNode;
import org.dromara.hutool.core.tree.TreeUtil;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p> Description : Controller基础定义 </p>
 * <p>
 * 这里只在方法上做了泛型，主要是考虑到返回的结果数据可以是各种类型，而不一定受限于某一种类型。
 *
 * @author : cc
 * @date : 2020/4/29 18:56
 */
public interface Controller<E extends Entity, ID extends Serializable> {

    /**
     * 获取Service
     *
     * @return Service
     */
    BaseService<E, ID> getService();

    /**
     * 数据实体转换为统一响应实体
     *
     * @return {@link Result} Entity
     */
    default  Result result() {
       return Result.success();

    }
    /**
     * 数据实体转换为统一响应实体
     *
     * @param domain 数据实体
     * @param <OE>   {@link Entity} 子类型
     * @return {@link Result} Entity
     */
    default <OE> Result<OE> result(OE domain) {
        if (ObjectUtils.isNotEmpty(domain)) {
            return Result.content(domain);
        } else {
            return Result.empty();
        }
    }

    /**
     * 数据列表转换为统一响应实体
     *
     * @param domains 数据实体 List
     * @param <OE>    {@link Entity} 子类型
     * @return {@link Result} List
     */
    default <OE> Result<List<OE>> result(List<OE> domains) {

        if (null == domains) {
            return Result.failure("Failed to query data!");
        }

        if (CollectionUtils.isNotEmpty(domains)) {
            return Result.success("Query data successfully!", domains);
        } else {
            return Result.empty("No data found!");
        }
    }

    /**
     * 数组转换为统一响应实体
     *
     * @param domains 数组
     * @param <T>     数组类型
     * @return {@link Result} List
     */
    default <T> Result<T[]> result(T[] domains) {
        if (ArrayUtils.isNotEmpty(domains)) {
            return Result.success("Query data successfully!", domains);
        } else {
            return Result.empty("No data found!");
        }
    }

    /**
     * 数据分页对象转换为统一响应实体
     *
     * @param pages 分页查询结果 {@link Page}
     * @param <OE>  {@link Entity} 子类型
     * @return {@link Result} Map
     */
    default <OE> Result<Map<String, Object>> result(Page<OE> pages) {
        if (null == pages) {
            return Result.failure("Failed to query data!");
        }

        if (CollectionUtils.isNotEmpty(pages.getContent())) {
            return Result.success("Query data successfully!", getPageInfoMap(pages));
        } else {
            return Result.empty("No data found!");
        }
    }

    /**
     * 数据 Map 转换为统一响应实体
     *
     * @param map 数据 Map
     * @return {@link Result} Map
     */
    default Result<Map<String, Object>> result(Map<String, Object> map) {

        if (null == map) {
            return Result.failure("Failed to query data!");
        }

        if (MapUtils.isNotEmpty(map)) {
            return Result.success("Query data successfully!", map);
        } else {
            return Result.empty("No data found!");
        }
    }

    /**
     * 数据操作结果转换为统一响应实体
     *
     * @param parameter 数据ID
     * @return {@link Result} String
     */
    default Result<String> result(String parameter) {
        if (ObjectUtils.isNotEmpty(parameter)) {
            return Result.success("operate successfully!", parameter);
        } else {
            return Result.failure("operation failure!", parameter);
        }
    }

    /**
     * 数据操作结果转换为统一响应实体
     *
     * @param status 操作状态
     * @return {@link Result} String
     */
    default Result<Boolean> result(boolean status) {
        if (status) {
            return Result.success("operate successfully!", true);
        } else {
            return Result.failure("operation failure!", false);
        }
    }

    default <OE> Result<List<MapTree<String>>> result(List<OE> domains, Converter<OE, TreeNode<String>> toTreeNode) {
        if (ObjectUtils.isNotEmpty(domains)) {
            List<TreeNode<String>> treeNodes = domains.stream().map(toTreeNode::convert).collect(Collectors.toList());
            return Result.success("Query data successfully", TreeUtil.build(treeNodes, DefaultConstants.TREE_ROOT_ID));
        } else {
            return Result.empty("No data found!");
        }
    }

    /**
     * Page 对象转换为 Map
     *
     * @param pages 分页查询结果 {@link Page}
     * @param <OE>  {@link Entity} 子类型
     * @return Map
     */
    default <OE> Map<String, Object> getPageInfoMap(Page<OE> pages) {
        return getPageInfoMap(pages.getContent(), pages.getTotalPages(), pages.getTotalElements());
    }

    /**
     * Page 对象转换为 Map
     *
     * @param content       数据实体 List
     * @param totalPages    分页总页数
     * @param totalElements 总的数据条目
     * @param <OE>          {@link Entity} 子类型
     * @return Map
     */
    default <OE> Map<String, Object> getPageInfoMap(List<OE> content, int totalPages, long totalElements) {
        Map<String, Object> result = new HashMap<>(8);
        result.put("content", content);
        result.put("totalPages", totalPages);
        result.put("totalElements", totalElements);
        return result;
    }


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
