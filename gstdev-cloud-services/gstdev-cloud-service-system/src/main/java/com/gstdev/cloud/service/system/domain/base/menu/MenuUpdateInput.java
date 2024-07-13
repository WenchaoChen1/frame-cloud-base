// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by gstdev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.service.system.domain.base.menu;

import com.gstdev.cloud.data.core.enums.DataItemStatus;
import com.gstdev.cloud.data.core.pojo.BaseTreeUpdateInput;
import com.gstdev.cloud.service.system.domain.enums.SysMenuLocation;
import com.gstdev.cloud.service.system.domain.enums.SysMenuType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuUpdateInput extends BaseTreeUpdateInput {

    private String id;
    private String code;
    private String description;
    private Integer hidden;
    private String icon;
    private String name;
    private String parentId;
    private String path;
    private String permission;
    private Integer sort;
    private DataItemStatus status;
    private Integer tenantEnable;
    private SysMenuType type;
    private String url;
    private SysMenuLocation location;

}

