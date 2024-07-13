// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by gstdev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.service.system.domain.pojo.sysAttribute;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gstdev.cloud.data.core.enums.DataItemStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;


@Getter
@Setter
public class AttributeManagePageVo {

    private String attributeId;
    private String attributeCode;
    private String requestMethod;
    private String serviceId;
    private String className;
    private String methodName;
    private String url;
    private String webExpression;
    private String description;
    private DataItemStatus status;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedDate;

    private Set<AttributeManageSysPermissionPageVo> permissions;

    @Getter
    @Setter
    public static class AttributeManageSysPermissionPageVo {
        private String permissionCode;
        private String permissionName;
        private String permissionType;
        private DataItemStatus status;
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private Date createdDate;
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private Date updatedDate;
    }
}

