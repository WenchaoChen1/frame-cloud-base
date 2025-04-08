// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.minio.converter.retention;

import com.gstdev.cloud.base.core.utils.type.DateTimeUtils;
import com.gstdev.cloud.plugin.storage.minio.domain.RetentionDomain;
import com.gstdev.cloud.plugin.storage.minio.enums.RetentionModeEnums;
import io.minio.messages.Retention;
import io.minio.messages.RetentionMode;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.core.convert.converter.Converter;

import java.time.ZonedDateTime;

/**
 * <p>Description: Minio Request 转 Retention 转换器 </p>
 *
 * @author : cc
 * @date : 2023/6/8 23:01
 */
public class DomainToRetentionConverter implements Converter<RetentionDomain, Retention> {

    private final Converter<RetentionModeEnums, RetentionMode> toRetentionMode = new EnumToRetentionModeConverter();

    @Override
    public Retention convert(RetentionDomain retentionDomain) {
        RetentionMode mode = toRetentionMode.convert(retentionDomain.getMode());
        ZonedDateTime retainUntilDate = DateTimeUtils.stringToZonedDateTime(retentionDomain.getRetainUntilDate());
        if (ObjectUtils.isNotEmpty(mode) && ObjectUtils.isNotEmpty(retainUntilDate)) {
            return new Retention(mode, retainUntilDate);
        } else {
            return null;
        }
    }
}
