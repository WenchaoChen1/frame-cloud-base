// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.minio.converter.retention;

import com.gstdev.cloud.plugin.storage.minio.domain.ObjectLockConfigurationDomain;
import com.gstdev.cloud.plugin.storage.minio.enums.RetentionModeEnums;
import com.gstdev.cloud.plugin.storage.minio.enums.RetentionUnitEnums;
import io.minio.messages.ObjectLockConfiguration;
import io.minio.messages.RetentionDuration;
import io.minio.messages.RetentionMode;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.core.convert.converter.Converter;

/**
 * <p>Description: Minio ObjectLockConfiguration 转 ObjectLockConfigurationDomain 转换器 </p>
 *
 * @author : cc
 * @date : 2023/6/5 21:32
 */
public class ObjectLockConfigurationToDomainConverter implements Converter<ObjectLockConfiguration, ObjectLockConfigurationDomain> {

    @Override
    public ObjectLockConfigurationDomain convert(ObjectLockConfiguration objectLockConfiguration) {

        if (ObjectUtils.isNotEmpty(objectLockConfiguration)) {
            RetentionMode mode = objectLockConfiguration.mode();
            RetentionDuration duration = objectLockConfiguration.duration();

            if (ObjectUtils.isNotEmpty(mode) && ObjectUtils.isNotEmpty(duration)) {
                ObjectLockConfigurationDomain configurationDo = new ObjectLockConfigurationDomain();
                configurationDo.setMode(RetentionModeEnums.valueOf(mode.name()));
                configurationDo.setUnit(RetentionUnitEnums.valueOf(duration.unit().name()));
                configurationDo.setValidity(duration.duration());
                return configurationDo;
            }
        }

        return null;
    }
}
