// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.specification.domain.object;

import com.gstdev.cloud.plugin.storage.specification.domain.base.ObjectWriteDomain;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

/**
 * <p>Description: 下载对象返回结果域对象 </p>
 *
 * @author : cc
 * @date : 2023/8/16 23:10
 */
public class ObjectMetadataDomain extends ObjectWriteDomain {

    @Schema(name = "用户自定义 Metadata")
    private Map<String, String> userMetadata = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

    @Schema(name = "内容大小")
    private long contentLength;

    @Schema(name = "contentType")
    private String contentType;

    @Schema(name = "最后修改时间")
    private Date LastModified;

    public Map<String, String> getUserMetadata() {
        return userMetadata;
    }

    public void setUserMetadata(Map<String, String> userMetadata) {
        this.userMetadata = userMetadata;
    }

    public long getContentLength() {
        return contentLength;
    }

    public void setContentLength(long contentLength) {
        this.contentLength = contentLength;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Date getLastModified() {
        return LastModified;
    }

    public void setLastModified(Date lastModified) {
        LastModified = lastModified;
    }
}
