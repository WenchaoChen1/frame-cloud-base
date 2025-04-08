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
import io.minio.admin.MinioAdminClient;

/**
 * <p>Description: Minio Admin 基础服务 </p>
 *
 * @author : cc
 * @date : 2023/6/25 10:37
 */
public abstract class BaseMinioAdminService extends BaseOssService<MinioAdminClient> {

    public BaseMinioAdminService(AbstractObjectPool<MinioAdminClient> ossClientObjectPool) {
        super(ossClientObjectPool);
    }
}
