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
import com.gstdev.cloud.plugin.storage.specification.arguments.base.BucketArguments;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

/**
 * <p>Description: 对象列表请求参数实体 </p>
 *
 * @author : cc
 * @date : 2023/8/9 15:54
 */
@Schema(name = "对象列表请求参数实体", title = "对象列表请求参数实体")
public class ListObjectsArguments extends BucketArguments {

    @Schema(name = "前缀")
    private String prefix;

    @Schema(name = "关键字", description = "ListObjectV2 版本中对应的名称为 startMarker, 这里为了方便统一使用 marker")
    private String marker;

    @Schema(name = "分隔符", description = "如果recursive为true，那么默认值为'', 否则默认值为'/'")
    private String delimiter = "";

    @Min(value = 1, message = "maxKeys 值不能小于 1")
    @Max(value = 1000, message = "maxKeys 值不能大于 1000")
    @Schema(name = "最大关键字数量", description = "关键字数量必须大于1，同时小于等于1000, 默认值 1000")
    private Integer maxKeys = 1000;

    @Schema(name = "encodingType")
    private String encodingType;

    @Schema(name = "是否递归", description = "该属性仅在 Minio 环境下使用")
    private Boolean recursive = false;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getMarker() {
        return marker;
    }

    public void setMarker(String marker) {
        this.marker = marker;
    }

    public String getDelimiter() {
        return delimiter;
    }

    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }

    public Integer getMaxKeys() {
        return maxKeys;
    }

    public void setMaxKeys(Integer maxKeys) {
        this.maxKeys = maxKeys;
    }

    public String getEncodingType() {
        return encodingType;
    }

    public void setEncodingType(String encodingType) {
        this.encodingType = encodingType;
    }

    public Boolean getRecursive() {
        return recursive;
    }

    public void setRecursive(Boolean recursive) {
        this.recursive = recursive;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("prefix", prefix)
                .add("marker", marker)
                .add("delimiter", delimiter)
                .add("maxKeys", maxKeys)
                .add("encodingType", encodingType)
                .add("recursive", recursive)
                .toString();
    }
}
