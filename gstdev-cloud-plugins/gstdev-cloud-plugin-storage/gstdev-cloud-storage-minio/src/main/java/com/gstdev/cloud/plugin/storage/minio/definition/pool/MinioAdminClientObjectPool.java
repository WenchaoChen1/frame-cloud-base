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
import io.minio.admin.MinioAdminClient;

/**
 * <p>Description: Minio Admin Client 对象池 </p>
 *
 * @author : cc
 * @date : 2023/6/24 17:46
 */
public class MinioAdminClientObjectPool extends AbstractObjectPool<MinioAdminClient> {

    public MinioAdminClientObjectPool(AbstractOssClientPooledObjectFactory<MinioAdminClient> factory) {
        super(factory, factory.getOssProperties().getPool());
    }
}
