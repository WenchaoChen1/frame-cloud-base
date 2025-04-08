// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.minio.definition.pool;


import com.gstdev.cloud.base.definition.support.AbstractObjectPool;
import com.gstdev.cloud.plugin.storage.core.client.AbstractOssClientPooledObjectFactory;
import io.minio.MinioClient;

/**
 * <p>Description: Minio 客户端连接池 </p>
 *
 * @author : cc
 * @date : 2021/11/8 10:54
 */
public class MinioClientObjectPool extends AbstractObjectPool<MinioClient> {

    public MinioClientObjectPool(AbstractOssClientPooledObjectFactory<MinioClient> factory) {
        super(factory, factory.getOssProperties().getPool());
    }
}
