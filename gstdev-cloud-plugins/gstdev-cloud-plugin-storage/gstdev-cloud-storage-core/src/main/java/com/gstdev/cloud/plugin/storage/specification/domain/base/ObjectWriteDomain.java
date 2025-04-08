// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.specification.domain.base;

import com.google.common.base.MoreObjects;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * <p>Description: ObjectWriteDomain </p>
 *
 * @author : cc
 * @date : 2023/6/1 21:50
 */
public class ObjectWriteDomain extends BaseDomain {

    @Schema(name = "ETag 值")
    private String etag;

    @Schema(name = "版本ID")
    private String versionId;

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public String getVersionId() {
        return versionId;
    }

    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("etag", etag)
                .add("versionId", versionId)
                .toString();
    }
}
