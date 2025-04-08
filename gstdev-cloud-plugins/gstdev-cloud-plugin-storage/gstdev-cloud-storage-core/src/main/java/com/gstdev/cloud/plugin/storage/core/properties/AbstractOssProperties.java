// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.core.properties;

import com.google.common.base.MoreObjects;
import com.gstdev.cloud.base.definition.domain.Pool;

/**
 * <p>Description: 对象存储通用属性提取抽象类 </p>
 *
 * @author : cc
 * @date : 2023/7/14 16:28
 */
public abstract class AbstractOssProperties {

    /**
     * Oss Server endpoint
     */
    private String endpoint;

    /**
     * Oss Server accessKey
     */
    private String accessKey;

    /**
     * Oss Server secretKey
     */
    private String secretKey;

    /**
     * 自定义 OSS 对象池参数配置
     */
    private Pool pool = new Pool();

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public Pool getPool() {
        return pool;
    }

    public void setPool(Pool pool) {
        this.pool = pool;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("endpoint", endpoint)
                .add("accessKey", accessKey)
                .add("secretKey", secretKey)
                .add("pool", pool)
                .toString();
    }
}
