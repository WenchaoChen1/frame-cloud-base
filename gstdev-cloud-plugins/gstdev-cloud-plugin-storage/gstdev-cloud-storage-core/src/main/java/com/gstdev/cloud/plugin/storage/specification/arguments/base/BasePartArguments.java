// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.specification.arguments.base;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

/**
 * <p>Description: 分片上传通用分片参数实体 </p>
 *
 * @author : cc
 * @date : 2023/8/13 16:54
 */
public abstract class BasePartArguments extends ObjectArguments {

    /**
     * 分片上传ID
     */
    @Schema(name = "分片上传ID")
    @NotBlank(message = "分片上传ID为空")
    private String uploadId;

    public String getUploadId() {
        return uploadId;
    }

    public void setUploadId(String uploadId) {
        this.uploadId = uploadId;
    }
}
