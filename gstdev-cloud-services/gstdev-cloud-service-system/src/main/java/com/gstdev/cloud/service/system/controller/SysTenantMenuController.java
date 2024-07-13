// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.service.system.controller;

import com.gstdev.cloud.base.definition.domain.Result;
import com.gstdev.cloud.data.core.utils.QueryUtils;
import com.gstdev.cloud.rest.core.controller.ResultController;
import com.gstdev.cloud.service.system.domain.base.rTenantMenu.RTenantMenuFindAllByQueryCriteria;
import com.gstdev.cloud.service.system.domain.entity.SysMenu;
import com.gstdev.cloud.service.system.domain.entity.SysTenantMenu;
import com.gstdev.cloud.service.system.domain.pojo.rTenantMenu.InsertTenantMenuIO;
import com.gstdev.cloud.service.system.mapper.RTenantMenuMapper;
import com.gstdev.cloud.service.system.service.SysTenantMenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotBlank;
import org.springframework.context.annotation.Lazy;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/v1/rTenantMenu")
public class SysTenantMenuController implements ResultController {

    @Resource
    @Lazy
    private SysTenantMenuService tenantMenuService;

    @Resource
    @Lazy
    private RTenantMenuMapper rTenantMenuMapper;

    public SysTenantMenuService getService() {
        return tenantMenuService;
    }

    public RTenantMenuMapper getMapper() {
        return rTenantMenuMapper;
    }

    @Tag(name = "Tenant Manage")
    @PostMapping("/insert-tenant-menu")
    @Operation(summary = "insert-tenant-menu")
    public Result<String> insertTenantMenu(@RequestBody @Validated InsertTenantMenuIO insertTenantMenuIO) {
        getService().insertTenantMenu(insertTenantMenuIO);
        return Result.success();
    }

    @Tag(name = "Tenant Manage")
    @GetMapping("/get-all-by-tenant-id")
    @Operation(summary = "get-all-by-tenant-id")
    public Result<List<String>> getAllByTenantId(@NotBlank @RequestParam("tenantId") String tenantId) {
        RTenantMenuFindAllByQueryCriteria rTenantMenuFindAllByQueryCriteria = new RTenantMenuFindAllByQueryCriteria();
        rTenantMenuFindAllByQueryCriteria.setTenantId(tenantId);
        List<SysTenantMenu> all = getService().findAll((root, criteriaQuery, criteriaBuilder) -> QueryUtils.getPredicate(root, rTenantMenuFindAllByQueryCriteria, criteriaBuilder));
        List<String> strings = all.stream()
                .map(SysTenantMenu::getMenu).map(SysMenu::getId)
                .toList();

        return Result.success(strings);
    }


//    /**
//     * 获取指定租户的所有菜单，返回树状结构.租户管理
//     *
//     * @param
//     * @return
//     */
//    @Tag(name = "Role Manage")
//    @GetMapping("/get-role-manage-tenant-menu-tree")
//    @Operation(summary = "get-role-manage-tenant-menu-tree")
//    public Result<List<RoleManageTenantMenuTreeVO>> getRoleManageTenantMenuTree(RoleManageTenantMenuTreeQO roleManageTenantMenuTreeQO, @NotBlank @RequestParam("tenantId") String tenantId) {
//        roleManageTenantMenuTreeQO.setTenantId(tenantId);
//        List<SysTenantMenu> all = getService().findAll((root, criteriaQuery, criteriaBuilder) -> QueryUtils.getPredicate(root, roleManageTenantMenuTreeQO, criteriaBuilder));
//        List<SysMenu> list = all.stream().map(SysTenantMenu::getMenu).toList();
//        List<RoleManageTenantMenuTreeVO> roleManageRTenantMenuTreeVOToTree = getMapper().toRoleManageRTenantMenuTreeVOToTree(list);
//        return result(roleManageRTenantMenuTreeVOToTree);
//    }
    /*------------------------------------------以上是系统访问控制自定义代码--------------------------------------------*/


}
