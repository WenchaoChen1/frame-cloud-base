// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by gstdev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.service.system.domain.base.rTenantMenu;

import com.gstdev.cloud.data.core.pojo.BaseUpdateInput;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RTenantMenuUpdateInput extends BaseUpdateInput {

    private String id;
    private Integer checked;
    private Integer status;
    private String tenantId;
    private String menuId;


}

