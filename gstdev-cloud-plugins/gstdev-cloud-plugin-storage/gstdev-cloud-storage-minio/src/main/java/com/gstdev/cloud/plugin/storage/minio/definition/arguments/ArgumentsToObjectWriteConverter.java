// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.minio.definition.arguments;

import com.gstdev.cloud.plugin.storage.specification.arguments.base.ObjectWriteArguments;
import io.minio.ObjectWriteArgs;
import org.apache.commons.collections4.MapUtils;

/**
 * <p>Description: 统一定义对象写入请求参数转换为 Minio 参数转换器 </p>
 *
 * @author : cc
 * @date : 2023/8/16 11:37
 */
public abstract class ArgumentsToObjectWriteConverter<S extends ObjectWriteArguments, T extends ObjectWriteArgs, B extends ObjectWriteArgs.Builder<B, T>> extends ArgumentsToObjectConverter<S, T, B> {

    @Override
    public void prepare(S arguments, B builder) {

        if (MapUtils.isNotEmpty(arguments.getRequestHeaders())) {
            builder.headers(arguments.getRequestHeaders());
        }

        if (MapUtils.isNotEmpty(arguments.getMetadata())) {
            builder.userMetadata(arguments.getMetadata());
        }

        super.prepare(arguments, builder);
    }
}
