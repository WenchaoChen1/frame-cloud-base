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
import com.gstdev.cloud.data.core.pojo.BaseTreeInsertInput;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;


@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class TenantInsertInput extends BaseTreeInsertInput {

    @Schema(title = "parentId 不能为空", required = true)
    @NotEmpty
    private String parentId;
    private String tenantCode;
    private String tenantName;
    private String description;
    private DataItemStatus status;
    private Integer type;

    //-----------------自定义-----------

//    private String companyName = "";
//    private String website = "";
//    private String addressLine1 = "";
//    private String addressLine2 = "";
//    private String city = "";
//    private String state = "";
//    private String country = "";
//    private String zipCode = "";
//    private String firstName = "";
//    private String lastName = "";
//    private String emailAddress = "";
//    private String phoneNumber = "";
//    private String logo = "";
}

