// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by gstdev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.service.system.domain.base.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gstdev.cloud.data.core.enums.DataItemStatus;
import com.gstdev.cloud.data.core.pojo.BaseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;


@Getter
@Setter
public class UserDto extends BaseDto {


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
    private Integer gender;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date lastLoginTime;
    private Integer deleted;
    private String firstName;
    private String lastName;
    private String activateToken;
    private DataItemStatus status;

    private String AccountType;
    private String icon;

    private List<String> roleIdlist;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdDate;
    private String createdUser;
    private String createdAccount;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updatedDate;
    private String updatedUser;
    private String updatedAccount;
}

