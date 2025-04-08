// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.minio.definition.arguments;

import com.gstdev.cloud.plugin.storage.specification.arguments.base.ObjectArguments;
import io.minio.ObjectArgs;

/**
 * <p>Description: 统一定义对象请求参数转换为 Minio 参数转换器 </p>
 *
 * @author : cc
 * @date : 2023/8/9 23:07
 */
public abstract class ArgumentsToObjectConverter<S extends ObjectArguments, T extends ObjectArgs, B extends ObjectArgs.Builder<B, T>> extends ArgumentsToBucketConverter<S, T, B> {
    @Override
    public void prepare(S arguments, B builder) {
        builder.object(arguments.getObjectName());
        super.prepare(arguments, builder);
    }
}
