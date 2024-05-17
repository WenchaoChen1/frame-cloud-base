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
    , S extends BaseService<E, ID>
    , D
    > extends Controller<E, ID, S> {

    /**
     * 获取Service
     *
     * @return Service
     */
    S getService();



}
