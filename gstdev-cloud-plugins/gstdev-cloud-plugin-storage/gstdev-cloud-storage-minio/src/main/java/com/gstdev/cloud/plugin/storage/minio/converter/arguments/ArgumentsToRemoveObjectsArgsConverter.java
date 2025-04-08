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
import com.gstdev.cloud.plugin.storage.specification.arguments.object.DeleteObjectsArguments;
import io.minio.RemoveObjectsArgs;
import io.minio.messages.DeleteObject;
import org.apache.commons.lang3.ObjectUtils;

import java.util.List;

/**
 * <p>Description: 统一定义 DeletedObjectArguments 转 Minio RemoveObjectArgs 转换器 </p>
 *
 * @author : cc
 * @date : 2023/7/28 18:21
 */
public class ArgumentsToRemoveObjectsArgsConverter extends ArgumentsToBucketConverter<DeleteObjectsArguments, RemoveObjectsArgs, RemoveObjectsArgs.Builder> {

    @Override
    public void prepare(DeleteObjectsArguments arguments, RemoveObjectsArgs.Builder builder) {
        if (ObjectUtils.isNotEmpty(arguments.getBypassGovernanceMode())) {
            builder.bypassGovernanceMode(arguments.getBypassGovernanceMode());
        }

        List<DeleteObject> deleteObjects = arguments.getObjects().stream().map(item -> new DeleteObject(item.getObjectName(), item.getVersionId())).toList();
        builder.objects(deleteObjects);

        super.prepare(arguments, builder);
    }

    @Override
    public RemoveObjectsArgs.Builder getBuilder() {
        return RemoveObjectsArgs.builder();
    }
}
