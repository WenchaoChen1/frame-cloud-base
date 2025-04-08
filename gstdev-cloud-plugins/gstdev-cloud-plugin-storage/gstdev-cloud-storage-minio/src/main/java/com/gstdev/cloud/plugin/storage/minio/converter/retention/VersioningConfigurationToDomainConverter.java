// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.minio.converter.retention;

import com.gstdev.cloud.plugin.storage.minio.domain.VersioningConfigurationDomain;
import io.minio.messages.VersioningConfiguration;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.core.convert.converter.Converter;

/**
 * <p>Description: Minio VersioningConfiguration 转 VersioningConfigurationDomain 转换器 </p>
 *
 * @author : cc
 * @date : 2023/6/30 20:44
 */
public class VersioningConfigurationToDomainConverter implements Converter<VersioningConfiguration, VersioningConfigurationDomain> {
    @Override
    public VersioningConfigurationDomain convert(VersioningConfiguration versioningConfiguration) {

        if (ObjectUtils.isNotEmpty(versioningConfiguration)) {
            VersioningConfigurationDomain domain = new VersioningConfigurationDomain();
            domain.setStatus(versioningConfiguration.status().name());
            domain.setMfaDelete(versioningConfiguration.isMfaDeleteEnabled());
            return domain;
        }

        return null;
    }
}
