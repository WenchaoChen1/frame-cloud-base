package com.gstdev.cloud.oauth2.management.controller;

import com.gstdev.cloud.base.definition.domain.Result;
import com.gstdev.cloud.oauth2.data.jpa.entity.DefaultOauth2Authorization;
import com.gstdev.cloud.oauth2.data.jpa.service.DefaultOauth2AuthorizationService;
import com.gstdev.cloud.oauth2.management.mapper.vo.Oauth2AuthorizationVoMapper;
import com.gstdev.cloud.rest.core.annotation.AccessLimited;
import com.gstdev.cloud.rest.core.annotation.Idempotent;
import com.gstdev.cloud.rest.core.controller.Controller;
import com.gstdev.cloud.rest.core.definition.dto.Pager;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>Description: OAuth2 认证管理接口 </p>
 *
 * @author : cc
 * @date : 2022/3/1 18:52
 */
@RestController
@RequestMapping("/authorize/authorization")
@Tags({
    @Tag(name = "OAuth2 认证服务接口"),
    @Tag(name = "OAuth2 认证管理接口")
})
public class OAuth2AuthorizationController implements Controller<DefaultOauth2Authorization, String, DefaultOauth2AuthorizationService> {
//public class OAuth2AuthorizationController implements POJOController<DefaultOauth2Authorization
//    , String, HerodotusAuthorizationService
//    , Oauth2AuthorizationVoMapper
//    , Oauth2AuthorizationVo
//    , Oauth2AuthorizationDto
//    , Oauth2AuthorizationInsertInput
//    , Oauth2AuthorizationUpdateInput
//    , Oauth2AuthorizationPageQueryCriteria
//    , Oauth2AuthorizationFindAllByQueryCriteria> {


    @Resource
    private DefaultOauth2AuthorizationService service;

    @Resource
    private Oauth2AuthorizationVoMapper oauth2AuthorizationVoMapper;

    @Override
    public DefaultOauth2AuthorizationService getService() {
        return service;
    }



    @AccessLimited
    @Operation(summary = "分页查询数据", description = "通过pageNumber和pageSize获取分页数据",
        responses = {
            @ApiResponse(description = "单位列表", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Map.class))),
            @ApiResponse(responseCode = "204", description = "查询成功，未查到数据"),
            @ApiResponse(responseCode = "500", description = "查询失败")
        })
    @Parameters({
        @Parameter(name = "pager", required = true, in = ParameterIn.QUERY, description = "分页Bo对象", schema = @Schema(implementation = Pager.class))
    })
    @GetMapping
    public Result<Object> findByPage(@Validated Pager pager) {
        if (ArrayUtils.isNotEmpty(pager.getProperties())) {
            Sort.Direction direction = Sort.Direction.valueOf(pager.getDirection());
            Page<DefaultOauth2Authorization> pages = getService().findByPage(pager.getPageNumber(), pager.getPageSize(), direction, pager.getProperties());
            return Result.success(oauth2AuthorizationVoMapper.entityToVo(pages));
        } else {
            Page<DefaultOauth2Authorization> pages = getService().findByPage(pager.getPageNumber(), pager.getPageSize());
            return Result.success(oauth2AuthorizationVoMapper.entityToVo(pages));
        }
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
    public Result<String> delete(@PathVariable String id) {
        return Controller.super.delete(id);
    }
}
