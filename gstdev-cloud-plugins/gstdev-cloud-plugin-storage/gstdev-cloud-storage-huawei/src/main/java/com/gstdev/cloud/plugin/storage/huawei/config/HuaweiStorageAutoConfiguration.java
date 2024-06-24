// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.huawei.config;

import com.gstdev.cloud.plugin.storage.core.service.StorageService;
import com.gstdev.cloud.plugin.storage.huawei.service.HuaweiStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(HuaweiStorageProperties.class)
@ConditionalOnProperty(prefix = "gstdev.cloud.storage.huawei", name = {"endpoint", "accessKey", "secretKey"})
public class HuaweiStorageAutoConfiguration {

    @Autowired
    private HuaweiStorageProperties properties;

    @Bean
    @ConditionalOnMissingBean
    public StorageService storageService() {
        return new HuaweiStorageService(properties.getEndpoint(), properties.getAccessKey(), properties.getSecretKey());
    }
}
