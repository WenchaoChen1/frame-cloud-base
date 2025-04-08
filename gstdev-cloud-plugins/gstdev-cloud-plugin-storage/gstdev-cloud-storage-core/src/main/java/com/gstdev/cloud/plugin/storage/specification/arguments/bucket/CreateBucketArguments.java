// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.specification.arguments.bucket;

import com.gstdev.cloud.plugin.storage.specification.arguments.base.BucketArguments;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * <p>Description: 创建存储桶请求参数实体 </p>
 *
 * @author : cc
 * @date : 2023/7/28 18:12
 */
@Schema(name = "创建存储桶请求参数实体", title = "创建存储桶请求参数实体")
public class CreateBucketArguments extends BucketArguments {

    @Schema(name = "开启对象锁定", description = "仅在Minio环境下使用")
    private Boolean objectLock;

    public Boolean getObjectLock() {
        return objectLock;
    }

    public void setObjectLock(Boolean objectLock) {
        this.objectLock = objectLock;
    }
}
