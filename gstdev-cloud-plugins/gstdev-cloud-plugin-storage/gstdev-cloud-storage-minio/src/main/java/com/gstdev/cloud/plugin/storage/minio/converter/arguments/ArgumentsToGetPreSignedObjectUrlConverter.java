// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.minio.converter.arguments;

import com.gstdev.cloud.plugin.storage.minio.definition.arguments.ArgumentsToObjectVersionConverter;
import com.gstdev.cloud.plugin.storage.specification.arguments.object.GeneratePresignedUrlArguments;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.http.Method;

/**
 * <p>Description: 统一定义 GeneratePresignedUrlArguments 转 Minio GetPreSignedObjectUrlArgs 转换器 </p>
 *
 * @author : cc
 * @date : 2023/8/15 17:33
 */
public class ArgumentsToGetPreSignedObjectUrlConverter extends ArgumentsToObjectVersionConverter<GeneratePresignedUrlArguments, GetPresignedObjectUrlArgs, GetPresignedObjectUrlArgs.Builder> {

    @Override
    public void prepare(GeneratePresignedUrlArguments arguments, GetPresignedObjectUrlArgs.Builder builder) {

        builder.method(Method.valueOf(arguments.getMethod().name()));
        builder.expiry(Math.toIntExact(arguments.getExpiration().toSeconds()));
        builder.versionId(arguments.getVersionId());

        super.prepare(arguments, builder);
    }

    @Override
    public GetPresignedObjectUrlArgs.Builder getBuilder() {
        return GetPresignedObjectUrlArgs.builder();
    }
}
