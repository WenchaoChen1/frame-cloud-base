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
import com.gstdev.cloud.plugin.storage.minio.enums.RetentionModeEnums;
import com.gstdev.cloud.plugin.storage.specification.domain.base.BaseDomain;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;

/**
 * <p>Description: 对象信息 </p>
 *
 * @author : cc
 * @date : 2023/6/11 15:54
 */
public class StatObjectDomain extends BaseDomain {
    @Schema(name = "ETag")
    private String etag;
    @Schema(name = "最后修改时间")
    private String lastModified;
    @Schema(name = "对象大小")
    private Long size;
    @Schema(name = "用户自定义元数据")
    private Map<String, String> userMetadata;
    @Schema(name = "保留模式")
    private RetentionModeEnums retentionMode;
    @Schema(name = "保留截止日期")
    private String retentionRetainUntilDate;
    @Schema(name = "是否合规持有")
    private Boolean legalHold;
    @Schema(name = "是否标记删除")
    private Boolean deleteMarker;

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getLastModified() {
        return lastModified;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    public RetentionModeEnums getRetentionMode() {
        return retentionMode;
    }

    public void setRetentionMode(RetentionModeEnums retentionMode) {
        this.retentionMode = retentionMode;
    }

    public String getRetentionRetainUntilDate() {
        return retentionRetainUntilDate;
    }

    public void setRetentionRetainUntilDate(String retentionRetainUntilDate) {
        this.retentionRetainUntilDate = retentionRetainUntilDate;
    }

    public Boolean getLegalHold() {
        return legalHold;
    }

    public void setLegalHold(Boolean legalHold) {
        this.legalHold = legalHold;
    }

    public Boolean getDeleteMarker() {
        return deleteMarker;
    }

    public void setDeleteMarker(Boolean deleteMarker) {
        this.deleteMarker = deleteMarker;
    }

    public Map<String, String> getUserMetadata() {
        return userMetadata;
    }

    public void setUserMetadata(Map<String, String> userMetadata) {
        this.userMetadata = userMetadata;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("etag", etag)
                .add("size", size)
                .add("lastModified", lastModified)
                .add("retentionMode", retentionMode)
                .add("retentionRetainUntilDate", retentionRetainUntilDate)
                .add("legalHold", legalHold)
                .add("deleteMarker", deleteMarker)
                .toString();
    }
}
