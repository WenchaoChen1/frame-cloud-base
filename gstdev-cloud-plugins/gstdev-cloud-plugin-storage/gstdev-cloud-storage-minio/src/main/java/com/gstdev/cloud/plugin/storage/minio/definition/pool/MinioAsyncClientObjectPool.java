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

/**
 * <p>Description: Minio 异步 Client 对象池 </p>
 *
 * @author : cc
 * @date : 2022/7/3 20:29
 */
public class MinioAsyncClientObjectPool extends AbstractObjectPool<MinioAsyncClient> {

    public MinioAsyncClientObjectPool(AbstractOssClientPooledObjectFactory<MinioAsyncClient> factory) {
        super(factory, factory.getOssProperties().getPool());
    }
}
