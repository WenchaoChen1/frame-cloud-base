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
import com.gstdev.cloud.service.system.domain.base.user.UserDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class UserMenuDto extends BaseTreeUpdateInput {

    private UserDto userDto;

    private List<MenuDto> menuDtoList;
}

