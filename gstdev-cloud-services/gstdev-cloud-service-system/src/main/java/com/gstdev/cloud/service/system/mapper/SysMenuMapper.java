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
import com.gstdev.cloud.service.system.util.TreeUtils;
import com.gstdev.cloud.service.system.domain.base.menu.MenuDto;
import com.gstdev.cloud.service.system.domain.base.menu.MenuInsertInput;
import com.gstdev.cloud.service.system.domain.base.menu.MenuUpdateInput;
import com.gstdev.cloud.service.system.domain.base.menu.MenuVo;
import com.gstdev.cloud.service.system.domain.entity.SysMenu;
import com.gstdev.cloud.service.system.domain.pojo.sysMenu.*;
import org.mapstruct.*;

import java.util.Comparator;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
public interface SysMenuMapper extends BaseTreeMapper<SysMenu, MenuDto, MenuVo, MenuInsertInput, MenuUpdateInput> {
    void copy(UpdateMenuManageIO updateMenuManageIO, @MappingTarget SysMenu sysMenu);

    SysMenu toEntity(InsertMenuManageIO insertMenuManageIO);

    MenuManageDetailVo toMenuManageDetailVo(SysMenu byId);

    List<MenuManageTreeVo> toMenuManageTreeVo(List<SysMenu> byPage);


    default List<MenuManageTreeVo> toMenuManageTreeVoToTree(List<SysMenu> byPage) {
        List<MenuManageTreeVo> menuManageTreeVo = toMenuManageTreeVo(byPage);
        return TreeUtils.buildTree(
                menuManageTreeVo,
                MenuManageTreeVo::getId,
                MenuManageTreeVo::getParentId,
                Comparator.comparingInt((MenuManageTreeVo item) ->
                        item.getSort() != null ? item.getSort() : Integer.MAX_VALUE)
        );
    }

    List<AccountMenuPermissionsDto> toAccountMenuPermissionsDto(List<SysMenu> sysMenus);

    default List<AccountMenuPermissionsDto> toAccountMenuPermissionsDtoToTree(List<SysMenu> sysMenus) {
        List<AccountMenuPermissionsDto> accountMenuPermissionsDto = toAccountMenuPermissionsDto(sysMenus);
        return TreeUtils.buildTree(
                accountMenuPermissionsDto,
                AccountMenuPermissionsDto::getId,
                AccountMenuPermissionsDto::getParentId,
                Comparator.comparingInt((AccountMenuPermissionsDto item) ->
                        item.getSort() != null ? item.getSort() : Integer.MAX_VALUE)
        );
    }

}

