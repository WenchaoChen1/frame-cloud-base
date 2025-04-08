// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.specification.arguments.base;

import com.gstdev.cloud.plugin.storage.specification.core.arguments.OssArguments;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;

/**
 * <p>Description: 请求参数对象基础定义 </p>
 *
 * @author : cc
 * @date : 2023/7/28 0:05
 */
public abstract class BaseArguments implements OssArguments {

    @Schema(name = "额外的请求头")
    private Map<String, String> extraHeaders;

    @Schema(name = "额外的Query参数")
    private Map<String, String> extraQueryParams;

    public Map<String, String> getExtraHeaders() {
        return extraHeaders;
    }

    public void setExtraHeaders(Map<String, String> extraHeaders) {
        this.extraHeaders = extraHeaders;
    }

    public Map<String, String> getExtraQueryParams() {
        return extraQueryParams;
    }

    public void setExtraQueryParams(Map<String, String> extraQueryParams) {
        this.extraQueryParams = extraQueryParams;
    }
}
