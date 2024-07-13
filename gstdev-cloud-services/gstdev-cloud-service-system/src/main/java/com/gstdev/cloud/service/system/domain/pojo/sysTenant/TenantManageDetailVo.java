// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by gstdev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.service.system.domain.pojo.sysTenant;

import com.gstdev.cloud.data.core.enums.DataItemStatus;
import com.gstdev.cloud.service.system.domain.enums.SysTenantPermissionType;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;


@Getter
@Setter
public class TenantManageDetailVo {

    private String id;
    private String parentId;
    private String tenantCode;
    private String tenantName;
    private String description;
    private DataItemStatus status;
    private Integer type;
    private Set<SysTenantPermissionType> tenantPermissionTypes;
}
