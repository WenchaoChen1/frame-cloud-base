// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.core.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * description
 */
@Data
public class StoreConfig {

    @Schema(description = "存储类型")
    private Integer storageType;
    @Schema(description = "绑定域名")
    private String domain;
    @Schema(description = "EndPoint")
    private String endPoint;
    @Schema(description = "AccessKeyId")
    private String accessKeyId;
    @Schema(description = "AccessKeySecret")
    private String accessKeySecret;
    @Schema(description = "腾讯云AppId")
    private Integer appId;
    @Schema(description = "腾讯云COS所属地区")
    private String region;
    @Schema(description = "默认标识，0:不启用，1:启用")
    private Integer defaultFlag;
    @Schema(description = "租户ID")
    private Long tenantId;
    @Schema(description = "bucket权限控制")
    private String accessControl;
    @Schema(description = "bucket前缀")
    private String bucketPrefix;
    @Schema(description = "存储编码")
    private String storageCode;
    @Schema(description = "文件名前缀策略")
    private String prefixStrategy;
    @Schema(description = "自动创建bucket，0:不启用，1:启用")
    private Integer createBucketFlag;
}
