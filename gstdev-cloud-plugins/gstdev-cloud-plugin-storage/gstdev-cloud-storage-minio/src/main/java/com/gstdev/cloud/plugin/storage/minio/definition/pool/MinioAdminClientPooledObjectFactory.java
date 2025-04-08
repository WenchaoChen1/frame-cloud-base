// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.minio.definition.pool;

import com.gstdev.cloud.plugin.storage.core.client.AbstractOssClientPooledObjectFactory;
import com.gstdev.cloud.plugin.storage.minio.properties.MinioProperties;
import io.minio.admin.MinioAdminClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Description: Minio 基础 Admin Client 池化工厂 </p>
 *
 * @author : cc
 * @date : 2023/6/24 17:47
 */
public class MinioAdminClientPooledObjectFactory extends AbstractOssClientPooledObjectFactory<MinioAdminClient> {

    private static final Logger log = LoggerFactory.getLogger(MinioAdminClientPooledObjectFactory.class);

    private final MinioProperties minioProperties;

    public MinioAdminClientPooledObjectFactory(MinioProperties minioProperties) {
        super(minioProperties);
        this.minioProperties = minioProperties;
    }

    @Override
    public MinioAdminClient create() throws Exception {
        log.debug("[Herodotus] |- Minio admin client factory create object.");
        return MinioAdminClient.builder()
                .endpoint(minioProperties.getEndpoint())
                .credentials(minioProperties.getAccessKey(), minioProperties.getSecretKey())
                .build();
    }
}
