// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.minio.converter.arguments;

import com.gstdev.cloud.plugin.storage.minio.definition.arguments.ArgumentsToPutObjectBaseConverter;
import com.gstdev.cloud.plugin.storage.specification.arguments.object.UploadObjectArguments;
import io.minio.UploadObjectArgs;

import java.io.IOException;

/**
 * <p>Description: 统一定义 PutObjectArguments 转 Minio PutObjectArgs 转换器 </p>
 *
 * @author : cc
 * @date : 2023/8/16 11:29
 */
public class ArgumentsToUploadObjectArgsConverter extends ArgumentsToPutObjectBaseConverter<UploadObjectArguments, UploadObjectArgs, UploadObjectArgs.Builder> {

    @Override
    public void prepare(UploadObjectArguments arguments, UploadObjectArgs.Builder builder) {

        try {
            builder.filename(arguments.getFilename());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        builder.contentType(arguments.getContentType());

        super.prepare(arguments, builder);
    }

    @Override
    public UploadObjectArgs.Builder getBuilder() {
        return UploadObjectArgs.builder();
    }
}
