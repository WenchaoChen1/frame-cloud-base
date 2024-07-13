package com.gstdev.cloud.service.system.controller;

import com.gstdev.cloud.base.definition.domain.Result;
import com.gstdev.cloud.data.core.utils.QueryUtils;
import com.gstdev.cloud.rest.core.annotation.AccessLimited;
import com.gstdev.cloud.rest.core.annotation.Idempotent;
import com.gstdev.cloud.rest.core.controller.ResultController;
import com.gstdev.cloud.service.system.domain.base.SysPermission.SysPermissionVo;
import com.gstdev.cloud.service.system.domain.entity.SysPermission;
import com.gstdev.cloud.service.system.domain.pojo.sysPermission.InsertPermissionManageIO;
import com.gstdev.cloud.service.system.domain.pojo.sysPermission.PermissionManageDetailVo;
import com.gstdev.cloud.service.system.domain.pojo.sysPermission.PermissionManageQO;
import com.gstdev.cloud.service.system.domain.pojo.sysPermission.UpdatePermissionManageIO;
import com.gstdev.cloud.service.system.mapper.SysPermissionMapper;
import com.gstdev.cloud.service.system.service.SysPermissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>Description: SysPermissionController </p>
 */

@RestController
@RequestMapping("/v1/permission")
public class SysPermissionController implements ResultController {

    @Resource
    private SysPermissionService sysPermissionService;

    @Resource
    private SysPermissionMapper sysPermissionMapper;

    public SysPermissionService getService() {
        return sysPermissionService;
    }

    public SysPermissionMapper getMapper() {
        return sysPermissionMapper;
    }

    // ********************************* Permission Manage *****************************************
    @Tags({
            @Tag(name = "Permission Manage"),
            @Tag(name = "Scope Manage"),
            @Tag(name = "Attribute Manage")
    })
    @AccessLimited
    @PostMapping("/get-permission-manage-page")
    @Operation(summary = "get-permission-manage-page")
    public Result<Map<String, Object>> getPermissionManagePage(@RequestBody PermissionManageQO permissionManageQO) {
        Page<SysPermission> byPage = this.getService().findByPage((root, criteriaQuery, criteriaBuilder) -> QueryUtils.getPredicate(root, permissionManageQO, criteriaBuilder), permissionManageQO.getPage());
        return this.result(this.getMapper().toPermissionManagePageVo(byPage));
    }

    @Tag(name = "Permission Manage")
    @GetMapping("/get-permission-manage-detail/{id}")
    @Operation(summary = "get-permission-manage-detail")
    public Result<PermissionManageDetailVo> getPermissionManageDetail(@PathVariable String id) {
        return result(getMapper().toPermissionManageDetailVo(getService().findById(id)));
    }


    @Tag(name = "Permission Manage")
    @Idempotent
    @Operation(summary = "insert-permission-manage")
    @PostMapping("/insert-permission-manage")
    public Result<SysPermissionVo> insertAndUpdatePermissionManage(@RequestBody @Validated InsertPermissionManageIO insertPermissionManageIO) {
        this.getService().insertAndUpdate(getMapper().toEntity(insertPermissionManageIO));
        return Result.success();
    }

    @Tag(name = "Permission Manage")
    @PutMapping("/update-permission-manage")
    @Operation(summary = "update-permission-manage")
    public Result<SysPermissionVo> updatePermissionManage(@RequestBody @Validated UpdatePermissionManageIO updatePermissionManageIO) {
        SysPermission sysPermission = this.getService().findById(updatePermissionManageIO.getPermissionId());
        getMapper().copy(updatePermissionManageIO, sysPermission);
        this.getService().insertAndUpdate(sysPermission);
        return Result.success();
    }


    @Tag(name = "Permission Manage")
    @Operation(summary = "delete-permission-manage")
    @DeleteMapping("/delete-permission-manage/{id}")
    public Result<String> deletePermissionManage(@PathVariable String id) {
        this.getService().deleteById(id);
        return Result.success();
    }

    @Tag(name = "Permission Manage")
    @Operation(summary = "delete-all-permission-manage")
    @DeleteMapping("/delete-all-permission-manage")
    public Result<String> deleteAllPermissionManage(List<String> id) {
        this.getService().deleteAllById(id);
        return Result.success();
    }

    @Tag(name = "Permission Manage")
    @Operation(summary = "get-all-distinct-permission-type")
    @GetMapping("/get-all-distinct-permission-type")
    public Result<List<String>> getAllDistinctPermissionType() {
        return result(getService().findDistinctPermissionTypes());
    }
    @Tag(name = "Permission Manage")
    @GetMapping("/permissionInit")
    @Operation(summary = "permissionInit")
    public Result<PermissionManageDetailVo> permissionInit() {
        getService().permissionInit();
        return Result.success();
    }
    /*------------------------------------------以上是系统访问控制自定义代码--------------------------------------------*/

}
