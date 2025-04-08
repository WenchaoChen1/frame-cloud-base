// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.minio.converter.sse;

import com.gstdev.cloud.plugin.storage.minio.enums.SseConfigurationEnums;
import com.google.common.base.Enums;
import io.minio.messages.SseConfiguration;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.core.convert.converter.Converter;

/**
 * <p>Description: Minio SseConfiguration 转 SseConfigurationEnums 转换器 </p>
 *
 * @author : cc
 * @date : 2023/6/12 6:33
 */
public class SseConfigurationToEnumConverter implements Converter<SseConfiguration, SseConfigurationEnums> {
    @Override
    public SseConfigurationEnums convert(SseConfiguration configuration) {
        if (ObjectUtils.isNotEmpty(configuration) && ObjectUtils.isNotEmpty(configuration.rule())) {
            return Enums.getIfPresent(SseConfigurationEnums.class, configuration.rule().sseAlgorithm().name()).or(SseConfigurationEnums.AES256);
        }

        return SseConfigurationEnums.DISABLED;
    }
}
