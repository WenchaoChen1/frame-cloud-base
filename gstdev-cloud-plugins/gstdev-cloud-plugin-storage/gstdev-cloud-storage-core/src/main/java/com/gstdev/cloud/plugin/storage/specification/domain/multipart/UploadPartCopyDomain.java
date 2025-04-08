// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.specification.domain.multipart;

import com.google.common.base.MoreObjects;
import com.gstdev.cloud.plugin.storage.specification.domain.base.PartDomain;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * <p>Description: 分片上传拷贝返回结果域对象 </p>
 *
 * @author : cc
 * @date : 2023/8/13 16:32
 */
@Schema(name = "分片上传拷贝返回结果域对象", title = "分片上传拷贝返回结果域对象")
public class UploadPartCopyDomain extends PartDomain {

    @Schema(name = "分片上传ID")
    private String uploadId;

    public String getUploadId() {
        return uploadId;
    }

    public void setUploadId(String uploadId) {
        this.uploadId = uploadId;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("uploadId", uploadId)
                .toString();
    }
}
