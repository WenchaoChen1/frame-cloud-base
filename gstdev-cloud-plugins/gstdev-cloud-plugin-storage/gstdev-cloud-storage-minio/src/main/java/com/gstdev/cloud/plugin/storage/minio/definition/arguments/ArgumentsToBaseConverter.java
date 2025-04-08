// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.minio.definition.arguments;

import com.gstdev.cloud.plugin.storage.specification.arguments.base.BaseArguments;
import io.minio.BaseArgs;
import org.apache.commons.collections4.MapUtils;

/**
 * <p>Description: 基础的统一定义请求参数转换为 Minio 参数转换器 </p>
 *
 * @author : cc
 * @date : 2023/8/9 22:18
 */
public abstract class ArgumentsToBaseConverter<S extends BaseArguments, T extends BaseArgs, B extends BaseArgs.Builder<B, T>> implements ArgumentsConverter<S, T, B> {

    @Override
    public void prepare(S arguments, B builder) {
        if (MapUtils.isNotEmpty(arguments.getExtraHeaders())) {
            builder.extraHeaders(arguments.getExtraHeaders());
        }

        if (MapUtils.isNotEmpty(arguments.getExtraQueryParams())) {
            builder.extraQueryParams(arguments.getExtraQueryParams());
        }
    }
}
