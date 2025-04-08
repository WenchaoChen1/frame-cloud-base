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
import io.minio.admin.UserInfo;
import org.apache.commons.collections4.MapUtils;
import org.springframework.core.convert.converter.Converter;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * <p>Description: UserInfo Map 转 List<UserDomain> 转换器 </p>
 *
 * @author : cc
 * @date : 2023/6/25 14:51
 */
public class UsersToDomainsConverter implements Converter<Map<String, UserInfo>, List<UserDomain>> {

    private final Converter<UserInfo, UserDomain> toDomain = new UserInfoToDomainConverter();

    @Override
    public List<UserDomain> convert(Map<String, UserInfo> source) {
        if (MapUtils.isNotEmpty(source)) {
            return source.entrySet().stream().map(entry -> {
                UserDomain domain = toDomain.convert(entry.getValue());
                domain.setAccessKey(entry.getKey());
                return domain;
            }).toList();
        }

        return Collections.emptyList();
    }
}
