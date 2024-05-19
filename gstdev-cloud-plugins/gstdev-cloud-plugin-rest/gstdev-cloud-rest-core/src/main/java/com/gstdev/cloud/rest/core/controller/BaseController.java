package com.gstdev.cloud.rest.core.controller;

import com.gstdev.cloud.base.definition.domain.Result;
import com.gstdev.cloud.base.definition.domain.base.Entity;
import com.gstdev.cloud.data.core.service.BaseService;
import com.gstdev.cloud.rest.core.annotation.AccessLimited;
import com.gstdev.cloud.rest.core.annotation.Idempotent;
import com.gstdev.cloud.data.core.utils.BasePage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.Map;

/**
 * <p> Description : 通用Controller </p>
 * <p>
 * 单独提取出一些公共方法，是为了解决某些支持feign的controller，requestMapping 不方便统一编写的问题。
 *
 * @author : cc
 * @date : 2020/4/30 11:00
 */
public abstract class BaseController<E extends Entity, ID extends Serializable, S extends BaseService<E, ID>> implements Controller<E, ID> {
    private S service;

    public BaseController(S service) {
        this.service = service;
    }

    /**
     * 获取Service
     *
     * @return Service
     */
    @Override
    public S getService() {
        return this.service;
    }


    @AccessLimited
    @Operation(summary = "分页查询数据", description = "通过pageNumber和pageSize获取分页数据",
        responses = {
            @ApiResponse(description = "单位列表", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Map.class))),
            @ApiResponse(responseCode = "204", description = "查询成功，未查到数据"),
            @ApiResponse(responseCode = "500", description = "查询失败")
        })
    @Parameters({
        @Parameter(name = "page", required = true, in = ParameterIn.QUERY, description = "分页Bo对象", schema = @Schema(implementation = BasePage.class))
    })
    @GetMapping
    public Result<Map<String, Object>> findByPage(@Validated BasePage page) {
        if (ArrayUtils.isNotEmpty(page.getProperties())) {
            Sort.Direction direction = Sort.Direction.valueOf(page.getDirection());
            return Controller.super.findByPage(page.getPageNumber(), page.getPageSize(), direction, page.getProperties());
        } else {
            return Controller.super.findByPage(page.getPageNumber(), page.getPageSize());
        }
    }

    @Idempotent
    @Operation(summary = "保存或更新数据", description = "接收JSON数据，转换为实体，进行保存或更新",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(mediaType = "application/json")),
        responses = {@ApiResponse(description = "已保存数据", content = @Content(mediaType = "application/json"))})
    @Parameters({
        @Parameter(name = "domain", required = true, description = "可转换为实体的json数据")
    })
    @PostMapping
    @Override
    public Result<E> saveOrUpdate(@RequestBody E domain) {
        return Controller.super.saveOrUpdate(domain);
    }

    @Idempotent
    @Operation(summary = "删除数据", description = "根据实体ID删除数据，以及相关联的关联数据",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(mediaType = "application/json")),
        responses = {@ApiResponse(description = "操作消息", content = @Content(mediaType = "application/json"))})
    @Parameters({
        @Parameter(name = "id", required = true, in = ParameterIn.PATH, description = "实体ID，@Id注解对应的实体属性")
    })
    @DeleteMapping("/{id}")
    @Override
    public Result<String> delete(@PathVariable ID id) {
        return Controller.super.delete(id);
    }


}
