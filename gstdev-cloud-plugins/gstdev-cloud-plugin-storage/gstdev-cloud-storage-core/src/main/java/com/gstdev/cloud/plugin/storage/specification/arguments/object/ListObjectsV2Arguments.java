// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.specification.arguments.object;

import com.google.common.base.MoreObjects;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * <p>Description: 对象列表V2请求参数实体 </p>
 *
 * @author : cc
 * @date : 2023/8/11 14:24
 */
@Schema(name = "对象列表V2请求参数实体", title = "对象列表V2请求参数实体")
public class ListObjectsV2Arguments extends ListObjectsArguments {

    @Schema(name = "允许从特定点继续列表", description = "ContinuationToken在截断的列表结果中提供")
    private String continuationToken;

    @Schema(name = "是否包括所有者字段", description = "默认情况下，ListObjectsV2结果中不存在所有者字段。如果此标志设置为true，则将包括所有者字段。")
    private Boolean fetchOwner = false;

    @Schema(name = "版本ID标记", description = "仅在 Minio GetObjectVersions情况下使用")
    private String versionIdMarker;

    public String getContinuationToken() {
        return continuationToken;
    }

    public void setContinuationToken(String continuationToken) {
        this.continuationToken = continuationToken;
    }

    public Boolean getFetchOwner() {
        return fetchOwner;
    }

    public void setFetchOwner(Boolean fetchOwner) {
        this.fetchOwner = fetchOwner;
    }

    public String getVersionIdMarker() {
        return versionIdMarker;
    }

    public void setVersionIdMarker(String versionIdMarker) {
        this.versionIdMarker = versionIdMarker;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("continuationToken", continuationToken)
                .add("fetchOwner", fetchOwner)
                .add("versionIdMarker", versionIdMarker)
                .toString();
    }
}
