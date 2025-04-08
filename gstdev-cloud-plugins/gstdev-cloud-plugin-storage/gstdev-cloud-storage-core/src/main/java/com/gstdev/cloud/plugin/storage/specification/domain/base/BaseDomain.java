// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.specification.domain.base;

import com.gstdev.cloud.plugin.storage.specification.core.domain.OssDomain;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

import java.util.Map;
import java.util.TreeMap;

/**
 * <p>Description: 共性属性 </p>
 *
 * @author : cc
 * @date : 2023/6/11 15:45
 */
public abstract class BaseDomain implements OssDomain {

    @NotBlank(message = "存储桶名称不能为空")
    @Schema(name = "存储桶名称")
    private String bucketName;

    @Schema(name = "存储区域")
    private String region;

    @NotBlank(message = "对象名称不能为空")
    @Schema(name = "对象名称")
    private String objectName;

    @Schema(name = "用户自定义头信息")
    private Map<String, Object> header = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public Map<String, Object> getHeader() {
        return header;
    }

    public void setHeader(Map<String, Object> header) {
        this.header = header;
    }
}
