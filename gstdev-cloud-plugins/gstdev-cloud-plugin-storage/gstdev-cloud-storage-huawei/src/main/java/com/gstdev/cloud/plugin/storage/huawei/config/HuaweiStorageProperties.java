// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.huawei.config;

import com.gstdev.cloud.base.definition.properties.BaseProperties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "gstdev.cloud.storage.huawei", ignoreUnknownFields = true)
public class HuaweiStorageProperties extends BaseProperties {

    private String endpoint;

    private String accessKey;

    private String secretKey;
}
