// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.minio.definition.arguments;

import com.gstdev.cloud.plugin.storage.specification.arguments.base.ObjectReadArguments;
import io.minio.ObjectReadArgs;

/**
 * <p>Description: 统一定义对象读取请求参数转换为 Minio 参数转换器 </p>
 *
 * @author : cc
 * @date : 2023/8/15 21:38
 */
public abstract class ArgumentsToObjectReadConverter<S extends ObjectReadArguments, T extends ObjectReadArgs, B extends ObjectReadArgs.Builder<B, T>> extends ArgumentsToObjectVersionConverter<S, T, B> {

}
