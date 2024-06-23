package com.gstdev.cloud.service.identity.controller;

import com.gstdev.cloud.base.definition.domain.Result;
import com.gstdev.cloud.data.core.utils.BasePage;
import com.gstdev.cloud.data.core.utils.QueryUtils;
import com.gstdev.cloud.oauth2.data.jpa.entity.FrameAuthorization;
import com.gstdev.cloud.oauth2.data.jpa.service.FrameAuthorizationService;
import com.gstdev.cloud.rest.core.controller.Controller;
import com.gstdev.cloud.service.identity.domain.pojo.authorization.AuthorizationManageQO;
import com.gstdev.cloud.service.identity.mapper.Oauth2AuthorizationMapper;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>Description: OAuth2 认证管理接口 </p>
 *
 * @author : cc
 * @date : 2022/3/1 18:52
 */
@RestController
@RequestMapping("/v1/authorize/authorization")
//@Tags({
//    @Tag(name = "OAuth2 认证服务接口"),
//    @Tag(name = "OAuth2 认证管理接口")
//})
public class OAuth2AuthorizationController implements Controller<FrameAuthorization, String> {

    @Resource
    private FrameAuthorizationService service;

    @Resource
    private Oauth2AuthorizationMapper oauth2AuthorizationMapper;

//    public OAuth2AuthorizationController(FrameAuthorizationService service) {
//        super(service);
//    }

    @Override
    public FrameAuthorizationService getService() {
        return service;
    }


    // ********************************* authorization Manage *****************************************
    @GetMapping("/get-authorization-manage-page")
    @Operation(summary = "get-authorization-manage-page")
    public Result<Map<String, Object>> getAuthorizationManagePage(AuthorizationManageQO authorizationManageQO, BasePage basePage) {
        return result(oauth2AuthorizationMapper.toAuthorizationManagePageVO(getService().findByPage((root, criteriaQuery, criteriaBuilder) -> QueryUtils.getPredicate(root, authorizationManageQO, criteriaBuilder), basePage)));
    }

        @Operation(summary = "删除一条数据")
    @DeleteMapping("/delete-authorization-manage/{id}")
    public Result deleteAuthorizationManage(@PathVariable String id) {
        Result<String> result = result(String.valueOf(id));
        getService().deleteById(id);
        return result;
    }

        @Operation(summary = "删除多条数据")
    @DeleteMapping("/delete-all-authorization-manage")
    public Result deleteAllAuthorizationManage(List<String> id) {
        Result<String> result = result(String.valueOf(id));
        getService().deleteAllById(id);
        return result;
    }
//    @AccessLimited
//    @Operation(summary = "分页查询数据", description = "通过pageNumber和pageSize获取分页数据",
//        responses = {
//            @ApiResponse(description = "单位列表", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Map.class))),
//            @ApiResponse(responseCode = "204", description = "查询成功，未查到数据"),
//            @ApiResponse(responseCode = "500", description = "查询失败")
//        })
//    @Parameters({
//        @Parameter(name = "pager", required = true, in = ParameterIn.QUERY, description = "分页Bo对象", schema = @Schema(implementation = BasePage.class))
//    })
//    @GetMapping
//    public Result<Page<Oauth2AuthorizationVo>> findByPageToResult(@Validated BasePage page) {
//        return Result.success(oauth2AuthorizationVoMapper.entityToVo(getService().findByPage(page)));
//    }
//
//
//    @Idempotent
//    @Operation(summary = "删除数据", description = "根据实体ID删除数据，以及相关联的关联数据",
//        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(mediaType = "application/json")),
//        responses = {@ApiResponse(description = "操作消息", content = @Content(mediaType = "application/json"))})
//    @Parameters({
//        @Parameter(name = "id", required = true, in = ParameterIn.PATH, description = "实体ID，@Id注解对应的实体属性")
//    })
//    @DeleteMapping("/{id}")
//    @Override
//    public Result<String> delete(@PathVariable String id) {
//        return Controller.super.delete(id);
//    }
}
