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

/**
 * <p>Description: Minio Retention 转 RetentionDomain 转换器 </p>
 *
 * @author : cc
 * @date : 2023/6/10 15:35
 */
public class RetentionToDomainConverter implements Converter<Retention, RetentionDomain> {

    private final Converter<RetentionMode, RetentionModeEnums> toEnums;

    public RetentionToDomainConverter() {
        this.toEnums = new RetentionModeToEnumConverter();
    }

    @Override
    public RetentionDomain convert(Retention retention) {

        RetentionDomain retentionDomain = new RetentionDomain();
        if (ObjectUtils.isNotEmpty(retention)) {
            retentionDomain.setMode(toEnums.convert(retention.mode()));
            if (ObjectUtils.isNotEmpty(retention.retainUntilDate())) {
                retentionDomain.setRetainUntilDate(DateTimeUtils.zonedDateTimeToString(retention.retainUntilDate()));
            }
            return retentionDomain;
        }

        return null;
    }
}
