// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by gstdev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.service.system.domain.pojo.sysBusinessPermission;

import com.gstdev.cloud.data.core.enums.DataItemStatus;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class BusinessPermissionManageDetailVo {

    private String businessPermissionId;
    private String parentId;
    private String tenantId;
    private String name;
    private String code;
    private DataItemStatus status;
    private Integer sort;
    private String description;

}
