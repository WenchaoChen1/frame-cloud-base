// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.minio.converter.arguments;

import com.gstdev.cloud.plugin.storage.minio.definition.arguments.ArgumentsToObjectConditionalReadConverter;
import com.gstdev.cloud.plugin.storage.specification.arguments.object.GetObjectArguments;
import io.minio.GetObjectArgs;

/**
 * <p>Description: 统一定义 GetObjectArguments 转 Minio GetObjectArgs 转换器 </p>
 *
 * @author : cc
 * @date : 2023/8/15 21:36
 */
public class ArgumentsToGetObjectArgsConverter extends ArgumentsToObjectConditionalReadConverter<GetObjectArguments, GetObjectArgs, GetObjectArgs.Builder> {

    @Override
    public GetObjectArgs.Builder getBuilder() {
        return GetObjectArgs.builder();
    }
}
