// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.minio.converter;

import com.gstdev.cloud.plugin.storage.minio.domain.GroupDomain;
import io.minio.admin.GroupInfo;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.core.convert.converter.Converter;

/**
 * <p>Description: GroupInfo 转 GroupDomain 转换器 </p>
 *
 * @author : cc
 * @date : 2023/6/25 15:32
 */
public class GroupInfoToDomainConverter implements Converter<GroupInfo, GroupDomain> {
    @Override
    public GroupDomain convert(GroupInfo groupInfo) {

        GroupDomain domain = new GroupDomain();

        if (ObjectUtils.isNotEmpty(groupInfo)) {
            domain.setName(groupInfo.name());
            domain.setStatus(groupInfo.status());
            domain.setMembers(groupInfo.members());
            domain.setPolicy(groupInfo.policy());
        }

        return domain;
    }
}
