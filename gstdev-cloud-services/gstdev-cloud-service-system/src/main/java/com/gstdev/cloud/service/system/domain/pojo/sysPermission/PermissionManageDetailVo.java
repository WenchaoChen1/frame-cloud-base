// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by gstdev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.service.system.domain.pojo.sysPermission;

import com.gstdev.cloud.data.core.enums.DataItemStatus;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PermissionManageDetailVo {

    private String permissionId;
    private String permissionCode;
    private String permissionName;
    private String permissionType;
    private DataItemStatus status;

}
