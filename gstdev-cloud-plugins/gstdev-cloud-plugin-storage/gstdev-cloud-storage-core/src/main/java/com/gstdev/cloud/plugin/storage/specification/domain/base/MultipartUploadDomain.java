// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.specification.domain.base;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * <p>Description: 分片上传基础属性实体 </p>
 *
 * @author : cc
 * @date : 2023/8/13 17:44
 */
public class MultipartUploadDomain extends BaseDomain {

    /**
     * 上传ID
     */
    @Schema(name = "上传ID")
    private String uploadId;

    public String getUploadId() {
        return uploadId;
    }

    public void setUploadId(String uploadId) {
        this.uploadId = uploadId;
    }
}
