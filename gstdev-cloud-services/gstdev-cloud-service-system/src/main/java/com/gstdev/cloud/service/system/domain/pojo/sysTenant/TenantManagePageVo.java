// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by gstdev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.service.system.domain.pojo.sysTenant;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gstdev.cloud.data.core.enums.DataItemStatus;
import com.gstdev.cloud.data.core.pojo.BaseTreeVo;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
public class TenantManagePageVo extends BaseTreeVo {

    private String id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdDate;
    private String createdUser;
    private String createdAccount;
    private Date updatedDate;
    private String updatedUser;
    private String updatedAccount;
    private String parentId;
    private String tenantCode;
    private String tenantName;
    private String description;
    private DataItemStatus status;
    private Integer type;
}

