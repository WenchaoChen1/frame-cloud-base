// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.minio.definition.service;


import com.gstdev.cloud.base.definition.support.AbstractObjectPool;
import com.gstdev.cloud.plugin.storage.core.service.BaseOssService;
import com.gstdev.cloud.plugin.storage.minio.definition.pool.MinioAsyncClient;

/**
 * <p>Description: Minio 基础异步服务 </p>
 *
 * @author : cc
 * @date : 2022/7/3 20:42
 */
public abstract class BaseMinioAsyncService extends BaseOssService<MinioAsyncClient> {

    public BaseMinioAsyncService(AbstractObjectPool<MinioAsyncClient> ossClientObjectPool) {
        super(ossClientObjectPool);
    }
}
