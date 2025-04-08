// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.minio.converter.retention;

import com.google.common.base.Enums;
import com.gstdev.cloud.plugin.storage.minio.enums.RetentionModeEnums;
import io.minio.messages.RetentionMode;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.core.convert.converter.Converter;

/**
 * <p>Description: RetentionModeEnums 转 RetentionMode 转换器 </p>
 *
 * @author : cc
 * @date : 2023/6/11 18:43
 */
public class EnumToRetentionModeConverter implements Converter<RetentionModeEnums, RetentionMode> {
    @Override
    public RetentionMode convert(RetentionModeEnums enums) {
        if (ObjectUtils.isNotEmpty(enums)) {
            return Enums.getIfPresent(RetentionMode.class, enums.name()).orNull();
        }

        return null;
    }
}
