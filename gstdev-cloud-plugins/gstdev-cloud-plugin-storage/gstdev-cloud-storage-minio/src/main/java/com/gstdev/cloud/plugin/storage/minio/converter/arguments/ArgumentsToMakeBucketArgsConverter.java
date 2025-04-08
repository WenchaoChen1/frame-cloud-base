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
import com.gstdev.cloud.plugin.storage.specification.arguments.bucket.CreateBucketArguments;
import io.minio.MakeBucketArgs;
import org.apache.commons.lang3.ObjectUtils;

/**
 * <p>Description: 统一定义 CreateBucketArguments 转 Minio MakeBucketArgs 转换器 </p>
 *
 * @author : cc
 * @date : 2023/7/28 18:21
 */
public class ArgumentsToMakeBucketArgsConverter extends ArgumentsToBucketConverter<CreateBucketArguments, MakeBucketArgs, MakeBucketArgs.Builder> {

    @Override
    public void prepare(CreateBucketArguments arguments, MakeBucketArgs.Builder builder) {
        if (ObjectUtils.isNotEmpty(arguments.getObjectLock())) {
            builder.objectLock(arguments.getObjectLock());
        }
        super.prepare(arguments, builder);
    }

    @Override
    public MakeBucketArgs.Builder getBuilder() {
        return MakeBucketArgs.builder();
    }
}
