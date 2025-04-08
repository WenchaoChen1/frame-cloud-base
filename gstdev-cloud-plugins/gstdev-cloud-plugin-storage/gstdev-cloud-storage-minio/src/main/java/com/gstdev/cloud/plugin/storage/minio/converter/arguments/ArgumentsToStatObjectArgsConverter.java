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
import com.gstdev.cloud.plugin.storage.specification.arguments.object.GetObjectMetadataArguments;
import io.minio.StatObjectArgs;

/**
 * <p>Description: 统一定义 GetObjectMetadataArguments 转 Minio StatObjectArgs 转换器 </p>
 *
 * @author : cc
 * @date : 2023/8/17 15:41
 */
public class ArgumentsToStatObjectArgsConverter extends ArgumentsToObjectConditionalReadConverter<GetObjectMetadataArguments, StatObjectArgs, StatObjectArgs.Builder> {
    @Override
    public StatObjectArgs.Builder getBuilder() {
        return StatObjectArgs.builder();
    }
}
