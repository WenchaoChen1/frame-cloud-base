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
import io.minio.MinioClient;

/**
 * <p>Description: Minio 基础 Client 池化工厂 </p>
 *
 * @author : cc
 * @date : 2022/7/3 20:21
 */
public class MinioClientPooledObjectFactory extends AbstractOssClientPooledObjectFactory<MinioClient> {

    private final MinioProperties minioProperties;

    public MinioClientPooledObjectFactory(MinioProperties minioProperties) {
        super(minioProperties);
        this.minioProperties = minioProperties;
    }

    @Override
    public MinioClient create() throws Exception {
        return MinioClient.builder()
                .endpoint(minioProperties.getEndpoint())
                .credentials(minioProperties.getAccessKey(), minioProperties.getSecretKey())
                .build();
    }
}
