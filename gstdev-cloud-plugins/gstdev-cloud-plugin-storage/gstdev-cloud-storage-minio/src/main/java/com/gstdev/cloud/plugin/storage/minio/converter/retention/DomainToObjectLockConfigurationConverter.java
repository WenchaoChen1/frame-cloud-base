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
import io.minio.messages.*;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.core.convert.converter.Converter;

/**
 * <p>Description: Minio Request 转 ObjectLockConfiguration 转换器 </p>
 *
 * @author : cc
 * @date : 2023/6/6 23:06
 */
public class DomainToObjectLockConfigurationConverter implements Converter<ObjectLockConfigurationDomain, ObjectLockConfiguration> {

    private final Converter<RetentionModeEnums, RetentionMode> toRetentionMode = new EnumToRetentionModeConverter();

    @Override
    public ObjectLockConfiguration convert(ObjectLockConfigurationDomain source) {

        if (isRetentionModeValid(source) && isRetentionDurationModeValid(source)) {
            RetentionMode mode = toRetentionMode.convert(source.getMode());
            RetentionDuration duration = getRetentionDuration(source.getUnit(), source.getValidity());
            return new ObjectLockConfiguration(mode, duration);
        }

        return null;
    }

    private boolean isRetentionModeValid(ObjectLockConfigurationDomain source) {
        RetentionModeEnums enums = source.getMode();
        return ObjectUtils.isNotEmpty(enums);
    }

    private boolean isRetentionDurationModeValid(ObjectLockConfigurationDomain source) {
        RetentionUnitEnums enums = source.getUnit();
        Integer duration = source.getValidity();
        return ObjectUtils.isNotEmpty(enums) && ObjectUtils.isNotEmpty(duration) && duration != 0;
    }

    private RetentionDuration getRetentionDuration(RetentionUnitEnums enums, Integer duration) {
        if (enums == RetentionUnitEnums.DAYS) {
            return new RetentionDurationDays(duration);
        } else {
            return new RetentionDurationYears(duration);
        }
    }
}
