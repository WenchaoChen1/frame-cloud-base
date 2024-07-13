// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by gstdev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.service.system.domain.pojo.sysAccount;

import com.gstdev.cloud.data.core.enums.DataItemStatus;
import com.gstdev.cloud.service.system.domain.enums.SysAccountType;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AccountManageDetailVo {

    private String accountId;
    private SysAccountType type;
    private String name;
    private String identity;
    private String tenantId;
    private DataItemStatus status;
    private SysUser user;

    public String getUserId() {
        if (user == null) {
            return null;
        }
        return user.getUserId();
    }

    @Getter
    @Setter
    public static class SysUser {
        private String userId;
    }

}
