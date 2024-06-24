package com.gstdev.cloud.service.identity.controller;

import com.gstdev.cloud.base.definition.domain.Result;
import com.gstdev.cloud.data.core.utils.BasePage;
import com.gstdev.cloud.data.core.utils.QueryUtils;
import com.gstdev.cloud.rest.core.controller.Controller;
import com.gstdev.cloud.service.identity.domain.pojo.scope.ScopeManageDetailVO;
import com.gstdev.cloud.service.identity.domain.pojo.scope.ScopeManageQO;
import com.gstdev.cloud.service.identity.domain.pojo.scope.InsertScopeManageIO;
import com.gstdev.cloud.service.identity.domain.pojo.scope.UpdateScopeManageIO;
import com.gstdev.cloud.service.identity.domain.dto.OAuth2PermissionDto;
import com.gstdev.cloud.service.identity.domain.pojo.scope.OAuth2ScopeIO;
import com.gstdev.cloud.service.identity.domain.entity.OAuth2Scope;
import com.gstdev.cloud.service.identity.domain.entity.OAuth2Permission;
import com.gstdev.cloud.service.identity.mapper.OAuth2ScopeMapper;
import com.gstdev.cloud.service.identity.service.OAuth2ScopeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p> Description : OauthScopesController </p>
 *
 * @author : cc
 * @date : 2020/3/25 17:10
 */
@RestController
@RequestMapping("/v1/authorize/scope")
public class OAuth2ScopeController implements Controller<OAuth2Scope, String> {

    @Resource
    private OAuth2ScopeMapper scopeMapper;
    @Resource
    private OAuth2ScopeService scopeService;

    @Override
    public OAuth2ScopeService getService() {
        return scopeService;
    }

    public OAuth2ScopeMapper getMapper() {
        return scopeMapper;
    }

    @GetMapping("/get-scope-manage-page")
    @Operation(summary = "get-scope-manage-page")
    public Result<Map<String, Object>> getScopeManagePage(ScopeManageQO scopeManageQO, BasePage basePage) {
        return result(getMapper().toScopeManagePageVO(getService().findByPage((root, criteriaQuery, criteriaBuilder) -> QueryUtils.getPredicate(root, scopeManageQO, criteriaBuilder), basePage)));
    }

    @GetMapping("/get-scope-manage-detail/{id}")
    @Operation(summary = "get-scope-manage-detail")
    public Result<ScopeManageDetailVO> getScopeManageDetail(@PathVariable String id) {
        return result(getMapper().toScopeManageDetailVO(getService().findById(id)));
    }

    @PostMapping("/insert-scope-manage")
    @Operation(summary = "insert-scope-manage")
    public Result insertScopeManage(@RequestBody @Validated InsertScopeManageIO insertScopeManageIO) {
        this.getService().insertAndUpdate(getMapper().toEntity(insertScopeManageIO));
        return result();
    }

    @PutMapping("/update-scope-manage")
    @Operation(summary = "update-scope-manage")
    public Result updateScopeManage(@RequestBody @Validated UpdateScopeManageIO updateScopeManageIO) {
        OAuth2Scope scope = this.getService().findById(updateScopeManageIO.getScopeId());
        getMapper().copy(updateScopeManageIO, scope);
        this.getService().insertAndUpdate(scope);
        return result();
    }


        @Operation(summary = "删除一条数据")
    @DeleteMapping("/delete-scope-manage/{id}")
    public Result deleteScopeManage(@PathVariable String id) {
        Result<String> result = result(String.valueOf(id));
        getService().deleteById(id);
        return result;
    }

        @Operation(summary = "删除多条数据")
    @DeleteMapping("/delete-all-scope-manage")
    public Result deleteAllScopeManage(List<String> id) {
        Result<String> result = result(String.valueOf(id));
        getService().deleteAllById(id);
        return result;
    }

    /*------------------------------------------以上是系统访问控制自定义代码--------------------------------------------*/


    @Tag(name = "Scope Manage")
    @PostMapping("/scope-manage-assigned-permission")
    @Operation(summary = "scope-manage-assigned-permission")
    public Result<OAuth2Scope> scopeManageAssignedPermission(@RequestBody OAuth2ScopeIO scope) {

        Set<OAuth2Permission> permissions = new HashSet<>();
        if (CollectionUtils.isNotEmpty(scope.getPermissions())) {
            permissions = scope.getPermissions().stream().map(this::toEntity).collect(Collectors.toSet());
        }

        OAuth2Scope result = getService().assigned(scope.getScopeId(), permissions);
        return result(result);
    }
    @Tag(name = "Scope Manage")
    @GetMapping("/get-scope-permission-id-by-scope-id/{id}")
    @Operation(summary = "get-scope-permission-id-by-scope-id")
    public Result<Set<String>> getScopePermissionIdByScopeId(@PathVariable String id) {
        return result(this.getService().getScopePermissionIdByScopeId(id));
    }
//
//    @AccessLimited
//    @Operation(summary = "获取全部范围", description = "获取全部范围", responses = {
//        @ApiResponse(description = "全部数据列表", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Result.class))),
//        @ApiResponse(responseCode = "204", description = "查询成功，未查到数据"),
//        @ApiResponse(responseCode = "500", description = "查询失败")
//    })
//    @GetMapping("/list")
//    public Result<List<OAuth2Scope>> findAll() {
//        List<OAuth2Scope> oAuth2Scopes = getService().findAll();
//        return result(oAuth2Scopes);
//    }
//
//    @AccessLimited
//    @Operation(summary = "根据范围代码查询应用范围", description = "根据范围代码查询应用范围",
//        responses = {
//            @ApiResponse(description = "查询到的应用范围", content = @Content(mediaType = "application/json", schema = @Schema(implementation = OAuth2Scope.class))),
//            @ApiResponse(responseCode = "204", description = "查询成功，未查到数据"),
//            @ApiResponse(responseCode = "500", description = "查询失败")
//        }
//    )
//    @GetMapping("/{scopeCode}")
//    public Result<OAuth2Scope> findByScopeCode(@PathVariable("scopeCode") String scopeCode) {
//        OAuth2Scope scope = getService().findByScopeCode(scopeCode);
//        return result(scope);
//    }
//    @AccessLimited
//    @Operation(summary = "根据id查询应用范围", description = "根据范围代码查询应用范围",
//        responses = {
//            @ApiResponse(description = "查询到的应用范围", content = @Content(mediaType = "application/json", schema = @Schema(implementation = OAuth2Scope.class))),
//            @ApiResponse(responseCode = "204", description = "查询成功，未查到数据"),
//            @ApiResponse(responseCode = "500", description = "查询失败")
//        }
//    )
//    @GetMapping("/get-by-id")
//    public Result<OAuth2Scope> findById(String id) {
//        OAuth2Scope scope = getService().findById(id);
//        return result(scope);
//    }
    private OAuth2Permission toEntity(OAuth2PermissionDto dto) {
        OAuth2Permission entity = new OAuth2Permission();
        entity.setPermissionId(dto.getPermissionId());
        entity.setPermissionCode(dto.getPermissionCode());
        entity.setPermissionName(dto.getPermissionName());
        return entity;
    }

}
