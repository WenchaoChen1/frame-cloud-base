// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.specification.arguments.object;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;

/**
 * <p>Description: 下载对象请求参数实体 </p>
 *
 * @author : cc
 * @date : 2023/8/16 16:03
 */
public class DownloadObjectArguments extends GetObjectArguments {

    @Schema(name = "文件名", description = "服务器端完整的文件名，包括绝对路径和名称")
    @NotEmpty(message = "文件名不能为空")
    private String filename;

    @Schema(name = "是否覆盖", description = "该参数仅用于Minio Download方法，替代DownloadObjectArgs，以便于与其它Dialect保持一致")
    private Boolean overwrite = false;

    public Boolean getOverwrite() {
        return overwrite;
    }

    public void setOverwrite(Boolean overwrite) {
        this.overwrite = overwrite;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
