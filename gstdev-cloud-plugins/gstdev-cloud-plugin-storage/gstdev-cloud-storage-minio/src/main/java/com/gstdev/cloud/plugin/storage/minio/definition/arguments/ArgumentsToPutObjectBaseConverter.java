// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.minio.definition.arguments;

import com.gstdev.cloud.plugin.storage.specification.arguments.base.PutObjectBaseArguments;
import io.minio.PutObjectBaseArgs;

/**
 * <p>Description: 统一定义对象写入基础请求参数转换为 Minio 参数转换器 </p>
 *
 * @author : cc
 * @date : 2023/8/16 11:42
 */
public abstract class ArgumentsToPutObjectBaseConverter<S extends PutObjectBaseArguments, T extends PutObjectBaseArgs, B extends PutObjectBaseArgs.Builder<B, T>> extends ArgumentsToObjectWriteConverter<S, T, B> {
}
