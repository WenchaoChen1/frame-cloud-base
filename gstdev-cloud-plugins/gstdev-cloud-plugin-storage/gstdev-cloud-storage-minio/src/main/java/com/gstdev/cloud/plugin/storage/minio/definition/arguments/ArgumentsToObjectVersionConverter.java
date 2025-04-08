// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.minio.definition.arguments;

import com.gstdev.cloud.plugin.storage.specification.arguments.base.ObjectVersionArguments;
import io.minio.ObjectVersionArgs;
import org.apache.commons.lang3.StringUtils;

/**
 * <p>Description: 统一定义对象版本请求参数转换为 Minio 参数转换器 </p>
 *
 * @author : cc
 * @date : 2023/8/9 23:07
 */
public abstract class ArgumentsToObjectVersionConverter<S extends ObjectVersionArguments, T extends ObjectVersionArgs, B extends ObjectVersionArgs.Builder<B, T>> extends ArgumentsToObjectConverter<S, T, B> {
    @Override
    public void prepare(S arguments, B builder) {
        if (StringUtils.isNotBlank(arguments.getVersionId())) {
            builder.versionId(arguments.getVersionId());
        }
        super.prepare(arguments, builder);
    }
}
