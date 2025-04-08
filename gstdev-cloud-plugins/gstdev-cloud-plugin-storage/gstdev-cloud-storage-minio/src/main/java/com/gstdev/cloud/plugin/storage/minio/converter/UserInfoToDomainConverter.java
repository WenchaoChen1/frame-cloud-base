// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.minio.converter;

import com.gstdev.cloud.plugin.storage.minio.domain.UserDomain;
import io.minio.admin.Status;
import io.minio.admin.UserInfo;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.core.convert.converter.Converter;

/**
 * <p>Description: UserInfo 转 UserDomain 转换器 </p>
 *
 * @author : cc
 * @date : 2023/6/25 14:20
 */
public class UserInfoToDomainConverter implements Converter<UserInfo, UserDomain> {
    @Override
    public UserDomain convert(UserInfo userInfo) {

        UserDomain domain = new UserDomain();

        if (ObjectUtils.isNotEmpty(userInfo)) {
            domain.setSecretKey(userInfo.secretKey());
            domain.setPolicyName(userInfo.policyName());
            domain.setMemberOf(userInfo.memberOf());
            domain.setStatus(Status.fromString(userInfo.status().name()));
        }

        return domain;
    }
}
