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
import com.gstdev.cloud.data.core.utils.BasePage;
import com.gstdev.cloud.data.core.utils.QueryUtils;
import com.gstdev.cloud.rest.core.controller.ResultController;
import com.gstdev.cloud.service.system.util.TreeUtils;
import com.gstdev.cloud.service.system.domain.entity.SysTenant;
import com.gstdev.cloud.service.system.domain.pojo.sysTenant.*;
import com.gstdev.cloud.service.system.mapper.SysTenantMapper;
import com.gstdev.cloud.service.system.service.SysTenantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/v1/tenant")
public class SysTenantController implements ResultController {


    @Resource
    private SysTenantService tenantService;

    @Resource
    private SysTenantMapper tenantVoMapper;

    public SysTenantService getService() {
        return tenantService;
    }

    public SysTenantMapper getMapper() {
        return tenantVoMapper;
    }

    // ********************************* Tenant Manage *****************************************

    @Tag(name = "Tenant Manage")
    @GetMapping("/get-tenant-manage-page")
    @Operation(summary = "get-tenant-manage-page")
    public Result<Map<String, Object>> getTenantManagePage(TenantManageQO tenantManageQO, BasePage basePage) {
        Page<SysTenant> byPage = this.getService().findByPage((root, criteriaQuery, criteriaBuilder) -> QueryUtils.getPredicate(root, tenantManageQO, criteriaBuilder), basePage);
        return this.result(this.getMapper().toTenantManagePageVo(byPage));
    }

    /**
     * 获取当前租户以及当前租户的所有子租户，返回树状结构
     *
     * @param tenantFindAllByQueryCriteria
     * @return
     */
    @Tag(name = "Tenant Manage")
    @GetMapping("/get-tenant-manage-tree")
    @Operation(summary = "get-tenant-manage-tree")
    public Result getTenantManageTree(TenantManageTreeQO tenantFindAllByQueryCriteria) {

        List<SysTenant> all = this.getService().findAll((root, criteriaQuery, criteriaBuilder) -> QueryUtils.getPredicate(root, tenantFindAllByQueryCriteria, criteriaBuilder));
        List<TenantManageTreeVo> tenantManageTreeVoToTree = this.getMapper().toTenantManageTreeVoToTree(all);
        if (ObjectUtils.isEmpty(tenantFindAllByQueryCriteria.getTenantId())) {
            return result(tenantManageTreeVoToTree);
        }
        TenantManageTreeVo nodeById = TreeUtils.findNodeById(tenantManageTreeVoToTree, tenantFindAllByQueryCriteria.getTenantId());
        return this.result(nodeById);
    }

    @Tag(name = "Tenant Manage")
    @GetMapping("/get-tenant-manage-detail/{id}")
    @Operation(summary = "get-tenant-manage-detail")
    public Result<TenantManageDetailVo> getTenantManageDetail(@PathVariable String id) {
        return result(this.getMapper().toTenantManageDetailVo(getService().findById(id)));
    }

    @Tag(name = "Tenant Manage")
    @PostMapping("/insert-tenant-manage")
    @Operation(summary = "insert-tenant-manage")
    public Result<String> insertTenantManage(@RequestBody @Validated InsertTenantManageIO insertTenantManageIO) {
        this.getService().insertAndUpdate(tenantVoMapper.toEntity(insertTenantManageIO));
        return Result.success();
    }

    @Tag(name = "Tenant Manage")
    @PutMapping("/update-tenant-manage")
    @Operation(summary = "update-tenant-manage")
    public Result<String> updateTenantManage(@RequestBody @Validated UpdateTenantManageIO updateTenantManageIO) {
        SysTenant sysTenant = this.getService().findById(updateTenantManageIO.getId());
        tenantVoMapper.copy(updateTenantManageIO, sysTenant);
        this.getService().insertAndUpdate(sysTenant);
        return Result.success();
    }

    @Tag(name = "Tenant Manage")
    @Operation(summary = "delete-tenant-manage")
    @DeleteMapping("/delete-tenant-manage/{id}")
    public Result<String> deleteTenantManage(@PathVariable String id) {
        getService().deleteById(id);
        return Result.success();
    }

    @Tag(name = "Tenant Manage")
    @Operation(summary = "delete-all-tenant-manage")
    @DeleteMapping("/delete-all-tenant-manage")
    public Result<String> deleteAllTenantManage(List<String> id) {
        getService().deleteAllById(id);
        return Result.success();
    }


    // ********************************* other Manage *****************************************


    @Tag(name = "Role Manage")
    @GetMapping("/get-role-manage-tenant-detail-to-list")
    @Operation(summary = "get-role-manage-tenant-detail-to-list")
    public Result<List<RoleManageTenantDetaiToListVo>> getRoleManageTenantDetaiToListAll(RoleManageTenantDetaiToListQO queryCriteria) {
        List<SysTenant> all = getService().findAll((root, criteriaQuery, criteriaBuilder) -> QueryUtils.getPredicate(root, queryCriteria, criteriaBuilder));
        return result(getMapper().toRoleManageTenantDetaiToListVoToTree(all));
    }

    @Tag(name = "User Manage")
    @GetMapping("/get-user-manage-tenant-detail-to-list")
    @Operation(summary = "get-account-manage-tenant-detail-to-list")
    public Result<List<UserManageTenantDetaiToListVo>> getUserManageTenantDetaiToListAll(UserManageTenantDetaiToListQO queryCriteria) {
        List<SysTenant> all = getService().findAll((root, criteriaQuery, criteriaBuilder) -> QueryUtils.getPredicate(root, queryCriteria, criteriaBuilder));
        return result(getMapper().toUserManageTenantDetaiToListVoToTree(all));
    }

    @Tag(name = "Account Manage")
    @GetMapping("/get-account-manage-tenant-detail-to-list")
    @Operation(summary = "get-account-manage-tenant-detail-to-list")
    public Result<List<AccountManageTenantDetaiToListVo>> getAccountManageTenantDetaiToListAll(AccountManageTenantDetaiToListQO queryCriteria) {
        List<SysTenant> all = getService().findAll((root, criteriaQuery, criteriaBuilder) -> QueryUtils.getPredicate(root, queryCriteria, criteriaBuilder));
        return result(getMapper().toAccountManageTenantDetaiToListVoToTree(all));
    }

    @Tag(name = "Business Permission Manage")
    @GetMapping("/get-business-permission-manage-tenant-detail-to-list")
    @Operation(summary = "get-business-permission-manage-tenant-detail-to-list")
    public Result<List<BusinessPermissionManageTenantDetaiToListVo>> getBusinessPermissionManageTenantDetaiToListAll(BusinessPermissionManageTenantDetaiToListQO queryCriteria) {
        List<SysTenant> all = getService().findAll((root, criteriaQuery, criteriaBuilder) -> QueryUtils.getPredicate(root, queryCriteria, criteriaBuilder));
        return result(getMapper().toBusinessPermissionManageTenantDetaiToListVoToTree(all));
    }
//  @GetMapping("/get-all-tenant-to-tree")
//  @Operation(summary = "获取当前当前租户的所有子租户，返回树状结构")
//  public Result<List<TenantVo>> findAllByQueryCriteriaToTree() {
//    return findByParentIdIdToTreeToResult(redisCurrentLoginInformation.getCurrentLoginTenantId());
//  }

    /*------------------------------------------ 以上是系统访问控制 --------------------------------------------*/
}

