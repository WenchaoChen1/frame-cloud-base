// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by gstdev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.service.system.mapper;

import com.gstdev.cloud.data.core.mapper.BaseTreeMapper;
import com.gstdev.cloud.service.system.domain.pojo.rTenantMenu.TenantMenuMenuTreeDto;
import com.gstdev.cloud.service.system.domain.pojo.sysBusinessPermission.TenantBusinessPermissionTreeDto;
import com.gstdev.cloud.service.system.util.TreeUtils;
import com.gstdev.cloud.service.system.domain.base.role.RoleDto;
import com.gstdev.cloud.service.system.domain.base.role.RoleInsertInput;
import com.gstdev.cloud.service.system.domain.base.role.RoleUpdateInput;
import com.gstdev.cloud.service.system.domain.base.role.RoleVo;
import com.gstdev.cloud.service.system.domain.entity.SysRole;
import com.gstdev.cloud.service.system.domain.pojo.sysRole.*;
import org.mapstruct.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.Comparator;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
public interface SysRoleMapper extends BaseTreeMapper<SysRole, RoleDto, RoleVo, RoleInsertInput, RoleUpdateInput> {
    SysRole toEntity(InsertRoleManageIO insertRoleManageIO);

    RoleManageDetailVo toRoleManageDetailVo(SysRole sysRole);

    List<RoleManageRoleDetaiToListVo> toRoleManageRoleDetaiToListVo(List<SysRole> sysRole);

    default List<RoleManageRoleDetaiToListVo> toRoleManageRoleDetaiToListVoToTree(List<SysRole> sysRole) {
        List<RoleManageRoleDetaiToListVo> roleManageRoleDetaiToListVo = toRoleManageRoleDetaiToListVo(sysRole);
        return TreeUtils.buildTree(
                roleManageRoleDetaiToListVo,
                RoleManageRoleDetaiToListVo::getId,
                RoleManageRoleDetaiToListVo::getParentId,
                Comparator.comparingInt((RoleManageRoleDetaiToListVo item) ->
                        item.getSort() != null ? item.getSort() : Integer.MAX_VALUE)
        );
    }

    List<RoleManagePageVo> toRoleManagePageVo(List<SysRole> sysRole);

    default Page<RoleManagePageVo> toRoleManagePageVo(Page<SysRole> page) {
        List<RoleManagePageVo> responses = this.toRoleManagePageVo(page.getContent());
        return new PageImpl(responses, page.getPageable(), page.getTotalElements());
    }

    void copy(UpdateRoleManageIO updateRoleManageIO, @MappingTarget SysRole sysRole);


    List<RoleManageTreeVo> toRoleManageTreeVo(List<SysRole> sysRole);

    default List<RoleManageTreeVo> toRoleManageTreeVoToTree(List<SysRole> sysRole) {
        List<RoleManageTreeVo> roleManageTreeVo = toRoleManageTreeVo(sysRole);
        return TreeUtils.buildTree(
                roleManageTreeVo,
                RoleManageTreeVo::getId,
                RoleManageTreeVo::getParentId,
                Comparator.comparingInt((RoleManageTreeVo item) ->
                        item.getSort() != null ? item.getSort() : Integer.MAX_VALUE)
        );
    }

    List<RoleManageBusinessPermissionTreeVo> toRoleManageBusinessPermissionTreeVo(List<TenantBusinessPermissionTreeDto> allTenantMenuMenuTree);

    List<TenantRoleTreeDto> toTenantRoleTreeDto(List<SysRole> sysRoles);

    default List<TenantRoleTreeDto> toRoleManageTreeDtoToTree(List<SysRole> sysRole) {
        List<TenantRoleTreeDto> roleManageTreeDto = toTenantRoleTreeDto(sysRole);
        return TreeUtils.buildTree(
                roleManageTreeDto,
                TenantRoleTreeDto::getId,
                TenantRoleTreeDto::getParentId,
                Comparator.comparingInt((TenantRoleTreeDto item) ->
                        item.getSort() != null ? item.getSort() : Integer.MAX_VALUE)
        );
    }
    List<RoleManageTenantMenuTreeVo> toRoleManageTenantMenuTreeVo(List<TenantMenuMenuTreeDto> allTenantMenuMenuTree);
}

