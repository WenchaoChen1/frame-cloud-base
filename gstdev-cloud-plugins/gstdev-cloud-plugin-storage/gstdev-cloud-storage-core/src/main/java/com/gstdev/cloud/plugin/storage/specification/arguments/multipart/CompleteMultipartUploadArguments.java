// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.specification.arguments.multipart;

import com.gstdev.cloud.plugin.storage.specification.arguments.base.BasePartArguments;
import com.gstdev.cloud.plugin.storage.specification.domain.multipart.PartSummaryDomain;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

/**
 * <p>Description: 完成分片上传请求参数实体 </p>
 *
 * @author : cc
 * @date : 2023/8/13 17:55
 */
@Schema(name = "完成分片上传请求参数实体", title = "完成分片上传请求参数实体")
public class CompleteMultipartUploadArguments extends BasePartArguments {

    @Schema(name = "分片列表不能为空")
    @NotEmpty(message = "分片列表不能为空")
    private List<PartSummaryDomain> parts;

    public List<PartSummaryDomain> getParts() {
        return parts;
    }

    public void setParts(List<PartSummaryDomain> parts) {
        this.parts = parts;
    }
}
