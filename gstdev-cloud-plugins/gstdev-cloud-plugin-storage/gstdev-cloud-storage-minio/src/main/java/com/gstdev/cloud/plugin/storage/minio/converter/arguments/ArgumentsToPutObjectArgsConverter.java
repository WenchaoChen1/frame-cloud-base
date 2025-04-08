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
import com.gstdev.cloud.plugin.storage.specification.arguments.object.PutObjectArguments;
import io.minio.PutObjectArgs;
import org.apache.commons.lang3.StringUtils;

/**
 * <p>Description: 统一定义 PutObjectArguments 转 Minio PutObjectArgs 转换器 </p>
 *
 * @author : cc
 * @date : 2023/8/16 11:29
 */
public class ArgumentsToPutObjectArgsConverter extends ArgumentsToPutObjectBaseConverter<PutObjectArguments, PutObjectArgs, PutObjectArgs.Builder> {

    @Override
    public void prepare(PutObjectArguments arguments, PutObjectArgs.Builder builder) {
        builder.stream(arguments.getInputStream(), arguments.getObjectSize(), arguments.getPartSize());

        if (StringUtils.isNotBlank(arguments.getContentType())) {
            builder.contentType(arguments.getContentType());
        }

        super.prepare(arguments, builder);
    }

    @Override
    public PutObjectArgs.Builder getBuilder() {
        return PutObjectArgs.builder();
    }
}
