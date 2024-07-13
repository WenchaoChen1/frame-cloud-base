// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by gstdev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.service.system.domain.pojo.sysMenu;

import com.gstdev.cloud.data.core.enums.DataItemStatus;
import com.gstdev.cloud.service.system.domain.enums.SysMenuLocation;
import com.gstdev.cloud.service.system.domain.enums.SysMenuType;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class MenuManageDetailVo {

    private String id;
    private String code;
    private String parentId;
    private Integer sort;
    private DataItemStatus status;
    private String description;
    private String name;
    private String menuName;
    private SysMenuType type;
    private SysMenuLocation location;
    private String path;
    private String icon;
}
