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

/**
 * <p>Description: 扩展的 Minio 异步 Client 池化工厂 </p>
 *
 * @author : cc
 * @date : 2022/7/3 20:25
 */
public class MinioAsyncClientPooledObjectFactory extends AbstractOssClientPooledObjectFactory<MinioAsyncClient> {

    private final MinioProperties minioProperties;

    public MinioAsyncClientPooledObjectFactory(MinioProperties minioProperties) {
        super(minioProperties);
        this.minioProperties = minioProperties;
    }

    @Override
    public MinioAsyncClient create() throws Exception {
        io.minio.MinioAsyncClient minioAsyncClient = io.minio.MinioAsyncClient.builder()
                .endpoint(minioProperties.getEndpoint())
                .credentials(minioProperties.getAccessKey(), minioProperties.getSecretKey())
                .build();
        return new MinioAsyncClient(minioAsyncClient);
    }
}
