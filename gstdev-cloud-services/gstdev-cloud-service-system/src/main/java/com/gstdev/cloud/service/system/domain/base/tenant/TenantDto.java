// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.service.system.domain.base.tenant;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gstdev.cloud.data.core.enums.DataItemStatus;
import com.gstdev.cloud.data.core.pojo.BaseTreeDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;


@Getter
@Setter
public class TenantDto extends BaseTreeDto<TenantDto> implements Serializable {

    //  List<TenantDto> children;
//  private String id;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdDate;
    private String createdUser;
    private String createdAccount;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedDate;
    private String updatedUser;
    private String updatedAccount;
    private String tenantName;
    private String description;
    private DataItemStatus status;
    private String parentId;
    private String tenantCode;
    private Integer type;

    //-----------------自定义-----------
//  private String website;
//  private String addressLine1;
//  private String addressLine2;
//  private String city;
//  private String state;
//  private String country;
//  private String zipCode;
//  private String firstName;
//  private String lastName;
//  private String emailAddress;
//  private String phoneNumber;
//  private String logo;
//  private Integer users;
}

