// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.minio.configuration;

import com.gstdev.cloud.plugin.storage.minio.definition.pool.*;
import com.gstdev.cloud.plugin.storage.minio.properties.MinioProperties;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>Description: Minio Client 配置 </p>
 *
 * @author : cc
 * @date : 2023/6/24 17:53
 */
@Configuration(proxyBeanMethods = false)
public class MinioClientConfiguration {

    private static final Logger log = LoggerFactory.getLogger(MinioClientConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.debug("[Herodotus] |- SDK [Minio Client] Auto Configure.");
    }

    @Bean
    @ConditionalOnMissingBean
    public MinioClientObjectPool minioClientPool(MinioProperties minioProperties) {
        MinioClientPooledObjectFactory factory = new MinioClientPooledObjectFactory(minioProperties);
        MinioClientObjectPool pool = new MinioClientObjectPool(factory);
        log.trace("[Herodotus] |- Bean [Minio Client Pool] Auto Configure.");
        return pool;
    }

    @Bean
    @ConditionalOnMissingBean
    public MinioAsyncClientObjectPool minioAsyncClientPool(MinioProperties minioProperties) {
        MinioAsyncClientPooledObjectFactory factory = new MinioAsyncClientPooledObjectFactory(minioProperties);
        MinioAsyncClientObjectPool pool = new MinioAsyncClientObjectPool(factory);
        log.trace("[Herodotus] |- Bean [Minio Async Client Pool] Auto Configure.");
        return pool;
    }

    @Bean
    @ConditionalOnMissingBean
    public MinioAdminClientObjectPool minioAdminClientPool(MinioProperties minioProperties) {
        MinioAdminClientPooledObjectFactory factory = new MinioAdminClientPooledObjectFactory(minioProperties);
        MinioAdminClientObjectPool pool = new MinioAdminClientObjectPool(factory);
        log.trace("[Herodotus] |- Bean [Minio Admin Client Pool] Auto Configure.");
        return pool;
    }
}
