// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.minio.converter.arguments;

import com.gstdev.cloud.plugin.storage.minio.definition.arguments.ArgumentsToBucketConverter;
import com.gstdev.cloud.plugin.storage.specification.arguments.bucket.DeleteBucketArguments;
import io.minio.RemoveBucketArgs;

/**
 * <p>Description: 统一定义 DeleteBucketArguments 转 Minio RemoveBucketArgs 转换器 </p>
 *
 * @author : cc
 * @date : 2023/7/28 18:21
 */
public class ArgumentsToRemoveBucketArgsConverter extends ArgumentsToBucketConverter<DeleteBucketArguments, RemoveBucketArgs, RemoveBucketArgs.Builder> {

    @Override
    public RemoveBucketArgs.Builder getBuilder() {
        return RemoveBucketArgs.builder();
    }
}
