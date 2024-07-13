// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by gstdev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.service.system.domain.vo.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gstdev.cloud.data.core.enums.DataItemStatus;
import com.gstdev.cloud.data.core.pojo.BaseInsertInput;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class UserInsertInput extends BaseInsertInput implements Serializable {

    List<String> departIds;
    List<String> roleIds;
    private String username;
    private String email;
    private String phoneNumber;
    private String password;
    private String nickname;
    private String avatar;
    private Integer gender = 0;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date lastLoginTime;
    private String accountName;
    private String tenantId;
//    private SysAccountType type = SysAccountType.USER;
    private String firstName;
    private String lastName;
    private String icon;

    private DataItemStatus status;
}

