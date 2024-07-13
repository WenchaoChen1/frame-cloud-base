// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by gstdev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.service.system.mapper;

import com.gstdev.cloud.service.common.autoconfigure.currentLoginInformation.CurrentLoginInformation;
import com.gstdev.cloud.service.system.domain.entity.SysMenu;
import com.gstdev.cloud.service.system.domain.pojo.sysSecurity.MenuRoutesDto;
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
public interface SysSecurityMapper {


    List<CurrentLoginInformation.Routes> toRoutes(List<MenuRoutesDto> menuRoutesDtos);

    List<MenuRoutesDto> toMenuRoutesDto(List<SysMenu> sysMenus);

    default List<MenuRoutesDto> toMenuRoutesDtoToTree(List<SysMenu> sysMenus) {
        List<MenuRoutesDto> menuRoutesDto = toMenuRoutesDto(sysMenus);
        return TreeUtils.buildTree(
                menuRoutesDto,
                MenuRoutesDto::getId,
                MenuRoutesDto::getParentId,
                Comparator.comparingInt((MenuRoutesDto item) ->
                        item.getSort() != null ? item.getSort() : Integer.MAX_VALUE)
        );
    }

}

