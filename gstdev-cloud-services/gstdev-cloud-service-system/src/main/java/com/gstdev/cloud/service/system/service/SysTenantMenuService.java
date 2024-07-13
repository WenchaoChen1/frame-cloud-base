// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.service.system.service;

import com.gstdev.cloud.data.core.service.BaseService;
import com.gstdev.cloud.service.system.domain.entity.SysTenantMenu;
import com.gstdev.cloud.service.system.domain.pojo.rTenantMenu.InsertTenantMenuIO;
import com.gstdev.cloud.service.system.domain.pojo.rTenantMenu.TenantMenuMenuTreeDto;

import java.util.List;
import java.util.Set;

public interface SysTenantMenuService extends BaseService<SysTenantMenu, String> {
    void insertTenantMenu(InsertTenantMenuIO insertTenantMenuIO);

    List<SysTenantMenu> findAllByTenantId(String tenantId);

    /**
     *
     * @param tenantId
     * @return
     */
    List<TenantMenuMenuTreeDto> getAllTenantMenuMenuTree(String tenantId);

    List<String> getAllTenantMenuIdByTenantMenuIdIn(Set<String> teantMenuIds);
    List<String> getAllTenantMenuIdByTenantIdIn(Set<String> teantMenuIds);

    List<SysTenantMenu> findAllById(Set<String> tenantMenuIds);

    //////////////////////////////////////////自定义代码//////////////////////////////////////////////////////////////
}
