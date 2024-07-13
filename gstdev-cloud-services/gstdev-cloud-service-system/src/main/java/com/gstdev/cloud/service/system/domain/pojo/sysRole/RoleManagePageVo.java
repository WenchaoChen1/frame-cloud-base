// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by gstdev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.service.system.domain.pojo.sysRole;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gstdev.cloud.data.core.enums.DataItemStatus;
import com.gstdev.cloud.data.core.pojo.BaseTreeVo;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
public class RoleManagePageVo extends BaseTreeVo {

    private String roleId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdDate;
    private String createdUser;
    private String createdAccount;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedDate;
    private String updatedUser;
    private String updatedAccount;
    private String code;
    private String description;
    private String parentId;
    private String roleName;
    private Integer sort;
    private DataItemStatus status;
    private String tenantId;

    public void setRoleId(String roleId) {
        this.roleId = roleId;
        super.setId(roleId);
    }
}

