// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.aliyun.config;

import com.gstdev.cloud.base.definition.properties.BaseProperties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "gstdev.cloud.storage.aliyun", ignoreUnknownFields = true)
public class AliyunStorageProperties extends BaseProperties {

    private String endpoint;

    private String accessKeyId;

    private String accessKeySecret;
}
