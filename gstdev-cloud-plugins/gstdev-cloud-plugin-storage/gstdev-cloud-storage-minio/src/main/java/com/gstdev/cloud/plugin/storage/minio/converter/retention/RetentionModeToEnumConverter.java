// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.minio.converter.retention;

import com.gstdev.cloud.plugin.storage.minio.enums.RetentionModeEnums;
import io.minio.messages.RetentionMode;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.core.convert.converter.Converter;

/**
 * <p>Description: Minio RetentionMode 转 RetentionModeEnums 转换器 </p>
 *
 * @author : cc
 * @date : 2023/6/11 16:04
 */
public class RetentionModeToEnumConverter implements Converter<RetentionMode, RetentionModeEnums> {

    @Override
    public RetentionModeEnums convert(RetentionMode retentionMode) {

        if (ObjectUtils.isNotEmpty(retentionMode)) {
            String retentionModeName = retentionMode.name();
            return RetentionModeEnums.valueOf(retentionModeName);
        }

        return null;
    }
}
