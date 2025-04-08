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

import java.util.Map;

/**
 * <p>Description: 基础 Object Write 请求参数实体 </p>
 *
 * @author : cc
 * @date : 2023/8/15 15:10
 */
public abstract class ObjectWriteArguments extends ObjectArguments {

    @Schema(name = "请求头信息")
    private Map<String, String> requestHeaders;

    @Schema(name = "对象元数据")
    private Map<String, String> metadata;

    public Map<String, String> getRequestHeaders() {
        return requestHeaders;
    }

    public void setRequestHeaders(Map<String, String> requestHeaders) {
        this.requestHeaders = requestHeaders;
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, String> metadata) {
        this.metadata = metadata;
    }
}
