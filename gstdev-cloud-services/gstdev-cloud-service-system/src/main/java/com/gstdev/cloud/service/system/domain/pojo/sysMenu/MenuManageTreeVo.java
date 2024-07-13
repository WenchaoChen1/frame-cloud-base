// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by gstdev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.service.system.domain.pojo.sysMenu;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gstdev.cloud.data.core.enums.DataItemStatus;
import com.gstdev.cloud.service.system.domain.enums.SysMenuLocation;
import com.gstdev.cloud.service.system.domain.enums.SysMenuType;
import com.gstdev.cloud.service.system.util.TreeNode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;


@Getter
@Setter
public class MenuManageTreeVo extends TreeNode<String, MenuManageTreeVo> {

    private String id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdDate;
    private String createdUser;
    private String createdAccount;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedDate;
    private String updatedUser;
    private String updatedAccount;

    private String name;
    private String menuName;
    private String code;
    private String parentId;
    private Integer sort;
    private DataItemStatus status;
    private String description;
    private SysMenuType type;
    private SysMenuLocation location;
    private String path;
    private String icon;
    private List<SysAttributesVO> attributes;

    @Getter
    @Setter
    public static class SysAttributesVO{
        private String attributeCode;
        private String requestMethod;
        private String serviceId;
        private String className;
        private String methodName;
        private String url;
    }
}

