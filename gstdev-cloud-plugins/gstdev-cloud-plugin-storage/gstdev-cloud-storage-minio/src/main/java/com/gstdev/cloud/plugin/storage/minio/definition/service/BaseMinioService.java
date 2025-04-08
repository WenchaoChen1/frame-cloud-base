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
import io.minio.MinioClient;

/**
 * <p>Description: Minio 基础服务 </p>
 *
 * @author : cc
 * @date : 2021/11/8 11:14
 */
public abstract class BaseMinioService extends BaseOssService<MinioClient> {

    public BaseMinioService(AbstractObjectPool<MinioClient> ossClientObjectPool) {
        super(ossClientObjectPool);
    }
}
