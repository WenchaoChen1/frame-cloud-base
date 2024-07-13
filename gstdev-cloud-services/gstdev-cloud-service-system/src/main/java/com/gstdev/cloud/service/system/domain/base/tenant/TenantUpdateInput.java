// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.service.system.domain.base.tenant;

import com.gstdev.cloud.data.core.enums.DataItemStatus;
import com.gstdev.cloud.data.core.pojo.BaseTreeUpdateInput;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class TenantUpdateInput extends BaseTreeUpdateInput {

    private String id;
    private String parentId;
    private String tenantName;
    private String description;
    private DataItemStatus status;
    private String tenantCode;
    private Integer type;

//    //-----------------自定义-----------
//
//    private String companyName;
//    private String website;
//    private String addressLine1;
//    private String addressLine2;
//    private String city;
//    private String state;
//    private String country;
//    private String zipCode;
//    private String firstName;
//    private String lastName;
//    private String emailAddress;
//    private String phoneNumber;
//    private String logo;
}

