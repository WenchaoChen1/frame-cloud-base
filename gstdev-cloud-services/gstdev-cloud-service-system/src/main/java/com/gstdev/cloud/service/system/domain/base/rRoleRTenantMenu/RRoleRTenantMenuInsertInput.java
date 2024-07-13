// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by gstdev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.service.system.domain.base.rRoleRTenantMenu;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Deprecated
@Getter
@Setter
public class RRoleRTenantMenuInsertInput implements Serializable {

    List<String> rTenantMenuIds;
    private String id;
    private Integer checked;
    private String tenantId;
    private String menuId;
    private String roleId;


}

