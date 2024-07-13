// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by gstdev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.service.system.domain.base.user;

import com.gstdev.cloud.data.core.enums.DataItemStatus;
import com.gstdev.cloud.data.core.pojo.BaseUpdateInput;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserLoginInferiorUpdateInput extends BaseUpdateInput {

    private String id;
    @Schema(title = "用户名")
    private String username;
    @Schema(title = "EMAIL")
    private String email;
    @Schema(title = "手机号码")
    private String phoneNumber;
    @Schema(title = "密码", description = "BCryptPasswordEncoder")
    private String password;
    @Schema(title = "昵称")
    private String nickname;
    @Schema(title = "头像")
    private String avatar;
    @Schema(title = "性别")
    private Integer gender = 0;
    private Date lastLoginTime;
    private Integer deleted = 0;
    private String firstName;
    private String lastName;
    private String activateToken;
    private DataItemStatus status;
//
//    @Schema(title = "accountTypeConstants 不能为空", required = true)
//    private SysAccountType type = SysAccountType.USER;

    private String icon;
}

