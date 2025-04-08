// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.core.client;

import com.gstdev.cloud.plugin.storage.core.properties.AbstractOssProperties;
import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

/**
 * <p>Description: 对象存储 Client 对象池对象工厂抽象定义 </p>
 *
 * @author : cc
 * @date : 2023/7/23 12:24
 */
public abstract class AbstractOssClientPooledObjectFactory<T> extends BasePooledObjectFactory<T> {

    private final AbstractOssProperties ossProperties;

    public AbstractOssClientPooledObjectFactory(AbstractOssProperties ossProperties) {
        this.ossProperties = ossProperties;
    }

    public AbstractOssProperties getOssProperties() {
        return ossProperties;
    }

    @Override
    public PooledObject<T> wrap(T obj) {
        return new DefaultPooledObject<>(obj);
    }
}
