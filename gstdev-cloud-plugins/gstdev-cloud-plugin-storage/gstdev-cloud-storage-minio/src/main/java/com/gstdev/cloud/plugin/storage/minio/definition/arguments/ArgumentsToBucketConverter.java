// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.minio.definition.arguments;

import com.gstdev.cloud.plugin.storage.specification.arguments.base.BucketArguments;
import io.minio.BucketArgs;
import org.apache.commons.lang3.StringUtils;

/**
 * <p>Description: 统一定义存储桶请求参数转换为 Minio 参数转换器 </p>
 *
 * @author : cc
 * @date : 2023/8/9 23:07
 */
public abstract class ArgumentsToBucketConverter<S extends BucketArguments, T extends BucketArgs, B extends BucketArgs.Builder<B, T>> extends ArgumentsToBaseConverter<S, T, B> {

    @Override
    public void prepare(S arguments, B builder) {

        builder.bucket(arguments.getBucketName());

        if (StringUtils.isNotBlank(arguments.getRegion())) {
            builder.region(arguments.getRegion());
        }

        super.prepare(arguments, builder);
    }
}
