// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by gstdev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.service.system.domain.base.account;

import com.gstdev.cloud.data.core.pojo.BaseInsertInput;
import com.gstdev.cloud.service.system.domain.enums.SysAccountType;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class AccountInsertInput extends BaseInsertInput implements Serializable {

    List<String> departIds;
    List<String> roleIds;
    private String tenantId;
    private String userId;
    private String name;
    private SysAccountType type;
}

