// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.minio.configuration;

import com.gstdev.cloud.plugin.storage.minio.properties.MinioProperties;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * <p>Description: Minio Logic 模块配置 </p>
 *
 * @author : cc
 * @date : 2023/6/5 15:04
 */
@Configuration
@EnableConfigurationProperties(MinioProperties.class)
@Import({
        MinioClientConfiguration.class
})
@ComponentScan(basePackages = {
//        "com.gstdev.cloud.plugin.storage.minio.service",
//        "com.gstdev.cloud.plugin.storage.minio.repository",
})
public class OssDialectMinioConfiguration {

    private static final Logger log = LoggerFactory.getLogger(OssDialectMinioConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.debug("[Herodotus] |- SDK [Oss Dialect Minio] Auto Configure.");
    }
}
