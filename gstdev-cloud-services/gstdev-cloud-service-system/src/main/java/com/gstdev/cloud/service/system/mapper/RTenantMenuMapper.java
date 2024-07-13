// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by gstdev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.service.system.mapper;

import com.gstdev.cloud.data.core.mapper.BasePOJOMapper;
import com.gstdev.cloud.service.system.domain.base.rTenantMenu.RTenantMenuDto;
import com.gstdev.cloud.service.system.domain.base.rTenantMenu.RTenantMenuInsertInput;
import com.gstdev.cloud.service.system.domain.base.rTenantMenu.RTenantMenuUpdateInput;
import com.gstdev.cloud.service.system.domain.base.rTenantMenu.RTenantMenuVo;
import com.gstdev.cloud.service.system.domain.entity.SysMenu;
import com.gstdev.cloud.service.system.domain.entity.SysTenantMenu;
import com.gstdev.cloud.service.system.domain.pojo.rTenantMenu.RoleManageTenantMenuTreeVO;
import com.gstdev.cloud.service.system.domain.pojo.rTenantMenu.TenantMenuMenuTreeDto;
import com.gstdev.cloud.service.system.util.TreeUtils;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.Comparator;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
public interface RTenantMenuMapper extends BasePOJOMapper<SysTenantMenu, RTenantMenuDto, RTenantMenuVo, RTenantMenuInsertInput, RTenantMenuUpdateInput> {
    List<RoleManageTenantMenuTreeVO> toRoleManageRTenantMenuTreeVO(List<SysMenu> sysRole);

    default List<RoleManageTenantMenuTreeVO> toRoleManageRTenantMenuTreeVOToTree(List<SysMenu> sysRole) {
        List<RoleManageTenantMenuTreeVO> roleManageRoleDetaiToListVo = toRoleManageRTenantMenuTreeVO(sysRole);
        return TreeUtils.buildTree(
                roleManageRoleDetaiToListVo,
                RoleManageTenantMenuTreeVO::getId,
                RoleManageTenantMenuTreeVO::getParentId,
                Comparator.comparingInt((RoleManageTenantMenuTreeVO item) ->
                        item.getSort() != null ? item.getSort() : Integer.MAX_VALUE)
        );
    }
    List<TenantMenuMenuTreeDto> toTenantMenuMenuTreeDto(List<SysTenantMenu> sysMenus);

    default List<TenantMenuMenuTreeDto> toTenantMenuMenuTreeDtoToTree(List<SysTenantMenu> sysRole) {
        List<TenantMenuMenuTreeDto> tenantMenuMenuTreeDto = toTenantMenuMenuTreeDto(sysRole);
        return TreeUtils.buildTree(
                tenantMenuMenuTreeDto,
                TenantMenuMenuTreeDto::getId,
                TenantMenuMenuTreeDto::getParentId,
                Comparator.comparingInt((TenantMenuMenuTreeDto item) ->
                        item.getSort() != null ? item.getSort() : Integer.MAX_VALUE)
        );
    }

}

