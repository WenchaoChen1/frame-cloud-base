// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.minio.converter.arguments;

import com.gstdev.cloud.plugin.storage.minio.definition.arguments.ArgumentsToObjectReadConverter;
import com.gstdev.cloud.plugin.storage.specification.arguments.object.DownloadObjectArguments;
import io.minio.DownloadObjectArgs;

/**
 * <p>Description: 统一定义 GetObjectArguments 转 Minio GetObjectArgs 转换器 </p>
 *
 * @author : cc
 * @date : 2023/8/15 21:36
 */
public class ArgumentsToDownloadObjectArgsConverter extends ArgumentsToObjectReadConverter<DownloadObjectArguments, DownloadObjectArgs, DownloadObjectArgs.Builder> {

    @Override
    public void prepare(DownloadObjectArguments arguments, DownloadObjectArgs.Builder builder) {
        builder.filename(arguments.getFilename());
        builder.overwrite(arguments.getOverwrite());

        super.prepare(arguments, builder);
    }

    @Override
    public DownloadObjectArgs.Builder getBuilder() {
        return DownloadObjectArgs.builder();
    }
}
