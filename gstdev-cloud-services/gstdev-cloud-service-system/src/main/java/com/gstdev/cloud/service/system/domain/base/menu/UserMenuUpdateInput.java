// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by gstdev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.service.system.domain.base.menu;

import com.gstdev.cloud.data.core.pojo.BaseTreeUpdateInput;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class UserMenuUpdateInput extends BaseTreeUpdateInput {

    private String userId;

    private List<String> checkedMenuId;

    private String AccountType;
}

