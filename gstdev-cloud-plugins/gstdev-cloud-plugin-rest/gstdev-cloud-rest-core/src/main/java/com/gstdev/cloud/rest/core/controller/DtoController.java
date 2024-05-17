package com.gstdev.cloud.rest.core.controller;

import com.gstdev.cloud.base.definition.constants.DefaultConstants;
import com.gstdev.cloud.base.definition.domain.Result;
import com.gstdev.cloud.base.definition.domain.base.Entity;
import com.gstdev.cloud.data.core.service.BaseDtoService;
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
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

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
public interface DtoController<E extends Entity
    , ID extends Serializable
    , S extends BaseDtoService<E, ID,D>
    , D
    > extends Controller<E, ID, S> {

    /**
     * 获取Service
     *
     * @return Service
     */
    S getService();

    /**
     * 查询分页数据
     *
     * @param pageable 分页
     * @return 分页数据
     */
    default Result<Map<String, Object>> findByPageToDto(Pageable pageable) {
        Page<D> pages = getService().findByPageToDto(pageable);
        return result(pages);
    }

    /**
     * 查询分页数据
     *
     * @param page 当前页码, 起始页码 0
     * @return 分页数据
     */
    default Result<Map<String, Object>> findByPageToDto(BasePage page) {
        Page<D> pages = getService().findByPageToDto(page);
        return result(pages);
    }

    /**
     * 查询分页数据
     *
     * @param pageNumber 当前页码，起始页码 0
     * @param pageSize   每页显示数据条数
     * @return {@link Result}
     */
    default Result<Map<String, Object>> findByPageToDto(Integer pageNumber, Integer pageSize) {
        Page<D> pages = getService().findByPageToDto(pageNumber, pageSize);
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
    default Result<Map<String, Object>> findByPageToDto(Integer pageNumber, Integer pageSize, Sort.Direction direction, String... properties) {
        Page<D> pages = getService().findByPageToDto(pageNumber, pageSize, direction, properties);
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
    default Result<Map<String, Object>> findByPageToDto(Integer pageNumber, Integer pageSize, Sort sort) {
        Page<D> pages = getService().findByPageToDto(pageNumber, pageSize, sort);
        return result(pages);
    }


    default Result<List<D>> findAllToDto() {
        List<D> domains = getService().findAllToDto();
        return result(domains);
    }

    default Result<D> findByIdToDto(ID id) {
        D domain = getService().findByIdToDto(id);
        return result(domain);
    }

}
