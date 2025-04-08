// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.minio.domain;

import com.google.common.base.MoreObjects;
import com.gstdev.cloud.plugin.storage.specification.domain.object.DeleteObjectDomain;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * <p>Description: Minio 错误信息基础实体 </p>
 *
 * @author : cc
 * @date : 2023/4/18 15:11
 */
public class DeleteErrorDomain extends DeleteObjectDomain {

    @Schema(name = "错误代码")
    private String code;
    @Schema(name = "错误信息")
    private String message;
    @Schema(name = "存储桶名称")
    private String bucketName;
    @Schema(name = "资源名称")
    private String resource;
    @Schema(name = "请求ID")
    private String requestId;
    @Schema(name = "主机ID")
    private String hostId;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getHostId() {
        return hostId;
    }

    public void setHostId(String hostId) {
        this.hostId = hostId;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("code", code)
                .add("message", message)
                .add("bucketName", bucketName)
                .add("resource", resource)
                .add("requestId", requestId)
                .add("hostId", hostId)
                .toString();
    }
}
