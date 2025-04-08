// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.minio.converter.arguments;

import com.gstdev.cloud.plugin.storage.minio.definition.arguments.ArgumentsToBucketConverter;
import com.gstdev.cloud.plugin.storage.specification.arguments.object.ListObjectsArguments;
import io.minio.ListObjectsArgs;
import org.apache.commons.lang3.StringUtils;

/**
 * <p>Description: 统一定义 OssDomain 转 Minio ListObjectsArgs 转换器 </p>
 *
 * @author : cc
 * @date : 2023/8/9 22:14
 */
public class ArgumentsToListObjectsArgsConverter extends ArgumentsToBucketConverter<ListObjectsArguments, ListObjectsArgs, ListObjectsArgs.Builder> {

    @Override
    public void prepare(ListObjectsArguments arguments, ListObjectsArgs.Builder builder) {
        builder.delimiter(arguments.getDelimiter());
        builder.useUrlEncodingType(StringUtils.isNotBlank(arguments.getEncodingType()));
        builder.maxKeys(arguments.getMaxKeys());
        builder.prefix(arguments.getPrefix());
        builder.recursive(false);
        builder.useApiVersion1(true);

        if (StringUtils.isNotBlank(arguments.getMarker())) {
            builder.keyMarker(arguments.getMarker());
        }

        super.prepare(arguments, builder);
    }

    @Override
    public ListObjectsArgs.Builder getBuilder() {
        return ListObjectsArgs.builder();
    }
}
