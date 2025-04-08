// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.specification.arguments.object;

import com.gstdev.cloud.plugin.storage.specification.arguments.base.BucketArguments;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

/**
 * <p>Description: 批量删除对象请求参数实体 </p>
 *
 * @author : cc
 * @date : 2023/8/12 11:00
 */
@Schema(name = "批量删除对象请求参数实体", title = "批量删除对象请求参数实体")
public class DeleteObjectsArguments extends BucketArguments {

    @Schema(name = "使用治理模式进行删除", description = "Minio 专用参数")
    private Boolean bypassGovernanceMode;

    @NotEmpty(message = "删除对象不能为空")
    private List<DeletedObjectArguments> objects;

    private Boolean quiet = false;

    public Boolean getBypassGovernanceMode() {
        return bypassGovernanceMode;
    }

    public void setBypassGovernanceMode(Boolean bypassGovernanceMode) {
        this.bypassGovernanceMode = bypassGovernanceMode;
    }

    public List<DeletedObjectArguments> getObjects() {
        return objects;
    }

    public void setObjects(List<DeletedObjectArguments> objects) {
        this.objects = objects;
    }

    public Boolean getQuiet() {
        return quiet;
    }

    public void setQuiet(Boolean quiet) {
        this.quiet = quiet;
    }
}
