// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.service.system.mapper;

import com.gstdev.cloud.data.core.mapper.BaseTreeMapper;
import com.gstdev.cloud.service.system.util.TreeUtils;
import com.gstdev.cloud.service.system.domain.base.tenant.TenantDto;
import com.gstdev.cloud.service.system.domain.base.tenant.TenantInsertInput;
import com.gstdev.cloud.service.system.domain.base.tenant.TenantUpdateInput;
import com.gstdev.cloud.service.system.domain.base.tenant.TenantVo;
import com.gstdev.cloud.service.system.domain.entity.SysTenant;
import com.gstdev.cloud.service.system.domain.pojo.sysTenant.*;
import org.mapstruct.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.Comparator;
import java.util.List;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
public interface SysTenantMapper extends BaseTreeMapper<SysTenant, TenantDto, TenantVo, TenantInsertInput, TenantUpdateInput> {
    //    TenantInsertInput toTenantInsertInput(TenantLoginInferiorInsertInput tenantLoginInferiorInsertInput);
//
//    TenantUpdateInput toTenantInsertUpdate(TenantLoginInferiorUpdateInput tenantLoginInferiorUpdateInput);
//
    SysTenant toEntity(InsertTenantManageIO insertTenantManageIO);

    TenantManageDetailVo toTenantManageDetailVo(SysTenant sysTenant);

    List<TenantManagePageVo> toTenantManagePageVo(List<SysTenant> sysTenant);

    default Page<TenantManagePageVo> toTenantManagePageVo(Page<SysTenant> page) {
        List<TenantManagePageVo> responses = this.toTenantManagePageVo(page.getContent());
        return new PageImpl(responses, page.getPageable(), page.getTotalElements());
    }


    void copy(UpdateTenantManageIO updateTenantManageIO, @MappingTarget SysTenant sysTenant);


    List<TenantManageTreeVo> toTenantManageTreeVo(List<SysTenant> sysTenant);

    default List<TenantManageTreeVo> toTenantManageTreeVoToTree(List<SysTenant> sysTenant) {
        List<TenantManageTreeVo> roleManageTenantDetaiToListVo = toTenantManageTreeVo(sysTenant);
        return TreeUtils.buildTree(
                roleManageTenantDetaiToListVo,
                TenantManageTreeVo::getId,
                TenantManageTreeVo::getParentId
        );
    }

    List<RoleManageTenantDetaiToListVo> toRoleManageTenantDetaiToListVo(List<SysTenant> sysTenant);

    default List<RoleManageTenantDetaiToListVo> toRoleManageTenantDetaiToListVoToTree(List<SysTenant> sysTenant) {
        List<RoleManageTenantDetaiToListVo> roleManageTenantDetaiToListVo = toRoleManageTenantDetaiToListVo(sysTenant);
        return TreeUtils.buildTree(
                roleManageTenantDetaiToListVo,
                RoleManageTenantDetaiToListVo::getId,
                RoleManageTenantDetaiToListVo::getParentId,
                Comparator.comparingInt((RoleManageTenantDetaiToListVo item) ->
                        item.getSort() != null ? item.getSort() : Integer.MAX_VALUE)
        );
    }

    List<AccountManageTenantDetaiToListVo> toAccountManageTenantDetaiToListVo(List<SysTenant> sysTenant);

    default List<AccountManageTenantDetaiToListVo> toAccountManageTenantDetaiToListVoToTree(List<SysTenant> sysTenant) {
        List<AccountManageTenantDetaiToListVo> roleManageTenantDetaiToListVo = toAccountManageTenantDetaiToListVo(sysTenant);
        return TreeUtils.buildTree(
                roleManageTenantDetaiToListVo,
                AccountManageTenantDetaiToListVo::getId,
                AccountManageTenantDetaiToListVo::getParentId,
                Comparator.comparingInt((AccountManageTenantDetaiToListVo item) ->
                        item.getSort() != null ? item.getSort() : Integer.MAX_VALUE)
        );
    }

    List<UserManageTenantDetaiToListVo> toUserManageTenantDetaiToListVo(List<SysTenant> sysTenant);

    default List<UserManageTenantDetaiToListVo> toUserManageTenantDetaiToListVoToTree(List<SysTenant> sysTenant) {
        List<UserManageTenantDetaiToListVo> roleManageTenantDetaiToListVo = toUserManageTenantDetaiToListVo(sysTenant);
        return TreeUtils.buildTree(
                roleManageTenantDetaiToListVo,
                UserManageTenantDetaiToListVo::getId,
                UserManageTenantDetaiToListVo::getParentId,
                Comparator.comparingInt((UserManageTenantDetaiToListVo item) ->
                        item.getSort() != null ? item.getSort() : Integer.MAX_VALUE)
        );
    }

    List<BusinessPermissionManageTenantDetaiToListVo> toBusinessPermissionManageTenantDetaiToListVo(List<SysTenant> sysTenant);

    default List<BusinessPermissionManageTenantDetaiToListVo> toBusinessPermissionManageTenantDetaiToListVoToTree(List<SysTenant> sysTenant) {
        List<BusinessPermissionManageTenantDetaiToListVo> roleManageTenantDetaiToListVo = toBusinessPermissionManageTenantDetaiToListVo(sysTenant);
        return TreeUtils.buildTree(
                roleManageTenantDetaiToListVo,
                BusinessPermissionManageTenantDetaiToListVo::getId,
                BusinessPermissionManageTenantDetaiToListVo::getParentId,
                Comparator.comparingInt((BusinessPermissionManageTenantDetaiToListVo item) ->
                        item.getSort() != null ? item.getSort() : Integer.MAX_VALUE)
        );
    }
}

