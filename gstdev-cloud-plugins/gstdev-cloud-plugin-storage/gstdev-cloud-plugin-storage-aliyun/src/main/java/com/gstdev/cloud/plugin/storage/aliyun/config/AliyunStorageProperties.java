// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.aliyun.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "gstdev.cloud.storage.aliyun", ignoreUnknownFields = true)
public class AliyunStorageProperties {

  private String endpoint;

  private String accessKeyId;

  private String accessKeySecret;
}
