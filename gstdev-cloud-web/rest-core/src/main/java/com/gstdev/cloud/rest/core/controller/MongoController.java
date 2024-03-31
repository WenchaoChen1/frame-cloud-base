package com.gstdev.cloud.rest.core.controller;

import com.gstdev.cloud.commons.ass.definition.domain.Result;
import com.gstdev.cloud.data.core.entity.BaseMongoEntity;
import com.gstdev.cloud.data.core.service.MongoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * <p>Description: Spring Data Mongo 基础 controller </p>
 *
 * @author : cc
 * @date : 2023/2/26 20:21
 */
public interface MongoController<E extends BaseMongoEntity, ID extends Serializable> extends Controller {

    MongoService<E, ID> getMongoService();

    /**
     * 查询分页数据
     *
     * @param pageNumber 当前页码，起始页码 0
     * @param pageSize   每页显示数据条数
     * @return {@link Result}
     */
    default Result<Map<String, Object>> findByPage(Integer pageNumber, Integer pageSize) {
        Page<E> pages = getMongoService().findByPage(pageNumber, pageSize);
        return result(pages);
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
    default Result<Map<String, Object>> findByPage(Integer pageNumber, Integer pageSize, Sort.Direction direction, String... properties) {
        Page<E> pages = getMongoService().findByPage(pageNumber, pageSize, direction, properties);
        return result(pages);
    }

    default Result<List<E>> findAll() {
        List<E> domains = getMongoService().findAll();
        return result(domains);
    }

    default Result<E> findById(ID id) {
        E domain = getMongoService().findById(id);
        return result(domain);
    }

    default Result<E> saveOrUpdate(E domain) {
        E savedDomain = getMongoService().save(domain);
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
        getMongoService().deleteById(id);
        return result;
    }
}
