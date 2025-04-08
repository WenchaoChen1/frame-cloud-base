// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.specification.arguments.base;

import com.google.common.base.MoreObjects;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

/**
 * <p>Description: 基础的对象请求参数定义 </p>
 *
 * @author : cc
 * @date : 2022/7/2 21:51
 */
public abstract class ObjectArguments extends BucketArguments {

    @NotBlank(message = "对象名称不能为空")
    @Schema(name = "对象名称", requiredMode = Schema.RequiredMode.REQUIRED)
    private String objectName;

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("objectName", objectName)
                .toString();
    }
}
