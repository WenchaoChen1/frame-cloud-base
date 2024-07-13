// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by gstdev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.service.system.domain.base.rRoleRTenantMenu;

import com.gstdev.cloud.data.core.pojo.BaseUpdateInput;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Deprecated
@Getter
@Setter
public class RRoleRTenantMenuUpdateInput extends BaseUpdateInput {

    private String id;
    private Date createdDate;
    private String createdUser;
    private String createdAccount;
    private Date updatedDate;
    private String updatedUser;
    private String updatedAccount;
    private Integer checked;
    private String tenantId;
    private String menuId;
    private String roleId;


}

