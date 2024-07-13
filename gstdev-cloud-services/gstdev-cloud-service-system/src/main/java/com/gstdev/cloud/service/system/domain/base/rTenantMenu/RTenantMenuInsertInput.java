// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by gstdev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.service.system.domain.base.rTenantMenu;

import com.gstdev.cloud.data.core.pojo.BaseInsertInput;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class RTenantMenuInsertInput extends BaseInsertInput implements Serializable {


    private Integer checked;
    private Integer status;
    private String tenantId;
    private String menuId;
    private List<String> menuIds;

}

