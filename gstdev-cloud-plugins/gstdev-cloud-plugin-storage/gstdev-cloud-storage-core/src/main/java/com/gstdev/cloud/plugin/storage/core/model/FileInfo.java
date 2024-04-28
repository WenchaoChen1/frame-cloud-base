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
public class FileInfo {

    @Schema(description = "附件集UUID")
    private String attachmentUuid;
    @Schema(description = "上传目录")
    private String directory;
    @Schema(description = "文件地址")
    private String fileUrl;
    @Schema(description = "文件类型")
    private String fileType;
    @Schema(description = "文件名称")
    private String fileName;
    @Schema(description = "文件大小")
    private Long fileSize;
    @Schema(description = "文件目录")
    private String bucketName;
    @Schema(description = "对象KEY")
    private String fileKey;
    @Schema(description = "租户Id")
    private Long tenantId;
    @Schema(description = "文件MD5")
    private String md5;
    @Schema(description = "存储编码")
    private String storageCode;
    @Schema(description = "来源类型")
    private String sourceType;
    @Schema(description = "服务器编码")
    private String serverCode;
}
