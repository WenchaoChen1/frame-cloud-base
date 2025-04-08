// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.core.service;


import com.gstdev.cloud.base.definition.support.AbstractObjectPool;

/**
 * <p>Description: 对象存储 Service 抽象定义 </p>
 *
 * @author : cc
 * @date : 2023/7/23 13:50
 */
public abstract class BaseOssService<T> {

    private final AbstractObjectPool<T> objectPool;

    public BaseOssService(AbstractObjectPool<T> objectPool) {
        this.objectPool = objectPool;
    }

    protected T getClient() {
        return objectPool.get();
    }

    protected void close(T client) {
        objectPool.close(client);
    }
}
