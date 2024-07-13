package com.gstdev.cloud.service.system.controller;

import com.gstdev.cloud.base.definition.domain.Result;
import com.gstdev.cloud.data.core.utils.BasePage;
import com.gstdev.cloud.data.core.utils.QueryUtils;
import com.gstdev.cloud.rest.core.controller.ResultController;
import com.gstdev.cloud.service.system.domain.entity.SysRole;
import com.gstdev.cloud.service.system.domain.pojo.rTenantMenu.TenantMenuMenuTreeDto;
import com.gstdev.cloud.service.system.domain.pojo.sysBusinessPermission.TenantBusinessPermissionTreeDto;
import com.gstdev.cloud.service.system.domain.pojo.sysRole.*;
import com.gstdev.cloud.service.system.mapper.SysRoleMapper;
import com.gstdev.cloud.service.system.service.SysBusinessPermissionService;
import com.gstdev.cloud.service.system.service.SysRRoleTenantMenuService;
import com.gstdev.cloud.service.system.service.SysRoleService;
import com.gstdev.cloud.service.system.service.SysTenantMenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/v1/role")
public class SysRoleController implements ResultController {

    @Resource
    private SysRoleService roleService;
    @Resource
    private SysBusinessPermissionService sysBusinessPermissionService;

    @Resource
    private SysRoleMapper roleVoMapper;
    @Resource
    private SysTenantMenuService sysTenantMenuService;
    @Resource
    private SysRRoleTenantMenuService sysRRoleTenantMenuService;

    public SysRoleService getService() {
        return roleService;
    }

    public SysRoleMapper getMapper() {
        return roleVoMapper;
    }

    // ********************************* Role Manage *****************************************
    @Tag(name = "Role Manage")
    @GetMapping("/get-role-manage-page")
    @Operation(summary = "get-role-manage-page")
    public Result<Map<String, Object>> getRoleManagePage(RoleManagePageQO queryCriteria, BasePage basePage) {
        Page<SysRole> byPage = this.getService().findByPage((root, criteriaQuery, criteriaBuilder) -> QueryUtils.getPredicate(root, queryCriteria, criteriaBuilder), basePage);
        return this.result(this.getMapper().toRoleManagePageVo(byPage));
    }

    @Tag(name = "Role Manage")
    @GetMapping("/get-role-manage-tree")
    @Operation(summary = "get-role-manage-tree")
    public Result<List<RoleManageTreeVo>> getRoleManageTree(RoleManageTreeQO queryCriteria) {
        List<SysRole> byPage = this.getService().findAll((root, criteriaQuery, criteriaBuilder) -> QueryUtils.getPredicate(root, queryCriteria, criteriaBuilder));
        return this.result(this.getMapper().toRoleManageTreeVoToTree(byPage));
    }
    @Tag(name = "Role Manage")
    @Operation(summary = "get-role-manage-role-detail-to-list")
    @GetMapping("/get-role-manage-role-detail-to-list")
    public Result<List<RoleManageRoleDetaiToListVo>> getRoleManageTenantDetaiToListAll(RoleManageRoleDetaiToListQO queryCriteria) {
        List<SysRole> all = getService().findAll((root, criteriaQuery, criteriaBuilder) -> QueryUtils.getPredicate(root, queryCriteria, criteriaBuilder));
        return result(getMapper().toRoleManageRoleDetaiToListVoToTree(all));
    }

    @Tag(name = "Role Manage")
    @GetMapping("/get-role-manage-detail/{id}")
    @Operation(summary = "get-role-manage-detail")
    public Result<RoleManageDetailVo> getRoleManageDetail(@PathVariable String id) {
        return result(this.getMapper().toRoleManageDetailVo(getService().findById(id)));
    }

    @Tag(name = "Role Manage")
    @PostMapping("/insert-role-manage")
    @Operation(summary = "insert-role-manage")
    public Result<String> insertRoleManage(@RequestBody @Validated InsertRoleManageIO insertRoleManageIO) {
        this.getService().insertAndUpdate(roleVoMapper.toEntity(insertRoleManageIO));
        return Result.success();
    }

    @Tag(name = "Role Manage")
    @PutMapping("/update-role-manage")
    @Operation(summary = "update-role-manage")
    public Result<String> updateRoleManage(@RequestBody @Validated UpdateRoleManageIO updateRoleManageIO) {
        SysRole sysRole = this.getService().findById(updateRoleManageIO.getRoleId());
        roleVoMapper.copy(updateRoleManageIO, sysRole);
        this.getService().insertAndUpdate(sysRole);
        return Result.success();
    }

    @Tag(name = "Role Manage")
    @Operation(summary = "delete-role-manage")
    @DeleteMapping("/delete-role-manage/{id}")
    public Result<String> deleteRoleManage(@PathVariable String id) {
        getService().deleteById(id);
        return Result.success();
    }

    @Tag(name = "Role Manage")
    @Operation(summary = "delete-all-role-manage")
    @DeleteMapping("/delete-all-role-manage")
    public Result<String> deleteAllRoleManage(List<String> id) {
        getService().deleteAllById(id);
        return Result.success();
    }
//
//    // 角色关联菜单获取这个角色的menu id
//    @Tag(name = "Role Manage")
//    @GetMapping("/get-all-menu-id-by-role-id/{roleId}")
//    @Operation(summary = "get-all-menu-id-by-role-id")
//    public Result<List<String>> getAllMenuIdByRoleId(@PathVariable String roleId) {
//        return getService().getAllMenuIdByRoleId(roleId);
//    }
//
//    // 角色关联菜单
//    @Tag(name = "Role Manage")
//    @PostMapping("/insert-role-menu")
//    @Operation(summary = "insertSave")
//    public Result<String> insertRoleMenu(@RequestBody @Validated UpdateRoleAssignedTenantMenuIO insertRoleMenuIO) {
//        return getService().insertRoleMenu(insertRoleMenuIO);
//    }


    @Tag(name = "Role Manage Assigned Business Permission")
    @GetMapping("/get-role-manage-business-permission-tree/{tenantId}")
    @Operation(summary = "get-role-manage-business-permission-tree")
    public Result<List<RoleManageBusinessPermissionTreeVo>> getRoleManageBusinessPermissionTree(@PathVariable String tenantId) {
        List<TenantBusinessPermissionTreeDto> allTenantMenuMenuTree = sysBusinessPermissionService.getTenantBusinessPermissionTree(tenantId);
        return this.result(this.getMapper().toRoleManageBusinessPermissionTreeVo(allTenantMenuMenuTree));
    }


    @Tag(name = "Role Manage Assigned Business Permission")
    @GetMapping("/get-all-business-permission-id-by-role-id/{roleId}")
    @Operation(summary = "get-all-tenant-menu-id-by-role-id")
    public Result<Set<String>> getAllBusinessPermissionIdByRoleId(@PathVariable String roleId) {
        return result(getService().getAllBusinessPermissionIdByRoleId(roleId));
    }

    @Tag(name = "Role Manage Assigned Business Permission")
    @PostMapping("/update-role-assigned-business-permission")
    @Operation(summary = "update-role-assigned-business-permission")
    public Result<String> updateRoleAssignedBusinessPermission(@RequestBody @Validated UpdateRoleAssignedBusinessPermissionIO entityIo) {
         getService().updateRoleAssignedBusinessPermission(entityIo.getRoleId(), entityIo.getBusinessPermissionIds());
         return Result.success();
    }

    @Tag(name = "Role Manage Assigned Tenant Menu")
    @GetMapping("/get-role-manage-tenant-menu-tree/{tenantId}")
    @Operation(summary = "get-role-manage-tenant-menu-tree")
    public Result<List<RoleManageTenantMenuTreeVo>> getRoleManageTenantMenuTree(@PathVariable String tenantId) {
        List<TenantMenuMenuTreeDto> allTenantMenuMenuTree = sysTenantMenuService.getAllTenantMenuMenuTree(tenantId);
        return this.result(this.getMapper().toRoleManageTenantMenuTreeVo(allTenantMenuMenuTree));
    }


    @Tag(name = "Role Manage Assigned Tenant Menu")
    @GetMapping("/get-all-tenant-menu-id-by-role-id/{roleId}")
    @Operation(summary = "get-all-tenant-menu-id-by-role-id")
    public Result<Set<String>> getAllTenantMenuIdByRoleId(@PathVariable String roleId) {
        return result(sysRRoleTenantMenuService.getAllTenantMenuIdByRoleId(roleId));
    }

    @Tag(name = "Role Manage Assigned Tenant Menu")
    @PostMapping("/update-role-assigned-tenant-menu")
    @Operation(summary = "update-role-assigned-tenant-menu")
    public Result<String> updateRoleAssignedTenantMenu(@RequestBody @Validated UpdateRoleAssignedTenantMenuIO entityIo) {
        sysRRoleTenantMenuService.updateRoleAssignedTenantMenu(entityIo.getRoleId(), entityIo.getTenantMenuIds());
        return Result.success();
    }
    /*------------------------------------------ 以上是系统访问控制 --------------------------------------------*/


}

