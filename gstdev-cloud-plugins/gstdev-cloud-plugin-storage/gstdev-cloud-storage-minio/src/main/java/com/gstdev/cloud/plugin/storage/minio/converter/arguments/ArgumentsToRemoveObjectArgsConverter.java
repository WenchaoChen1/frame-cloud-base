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
import com.gstdev.cloud.plugin.storage.specification.arguments.object.DeleteObjectArguments;
import io.minio.RemoveObjectArgs;
import org.apache.commons.lang3.ObjectUtils;

/**
 * <p>Description: 统一定义 DeletedObjectArguments 转 Minio RemoveObjectArgs 转换器 </p>
 *
 * @author : cc
 * @date : 2023/7/28 18:21
 */
public class ArgumentsToRemoveObjectArgsConverter extends ArgumentsToObjectVersionConverter<DeleteObjectArguments, RemoveObjectArgs, RemoveObjectArgs.Builder> {

    @Override
    public void prepare(DeleteObjectArguments arguments, RemoveObjectArgs.Builder builder) {
        if (ObjectUtils.isNotEmpty(arguments.getBypassGovernanceMode())) {
            builder.bypassGovernanceMode(arguments.getBypassGovernanceMode());
        }
        super.prepare(arguments, builder);
    }

    @Override
    public RemoveObjectArgs.Builder getBuilder() {
        return RemoveObjectArgs.builder();
    }
}
