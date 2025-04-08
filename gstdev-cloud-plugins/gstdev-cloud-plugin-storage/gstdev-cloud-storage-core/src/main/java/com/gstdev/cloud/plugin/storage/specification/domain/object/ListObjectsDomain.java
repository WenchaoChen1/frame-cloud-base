// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.specification.domain.object;

import com.gstdev.cloud.plugin.storage.specification.arguments.object.ListObjectsArguments;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

/**
 * <p>Description: 对象结果 </p>
 *
 * @author : cc
 * @date : 2023/8/9 16:36
 */
@Schema(name = "对象结果")
public class ListObjectsDomain extends ListObjectsArguments {

    @Schema(name = "对象列表")
    private List<ObjectDomain> summaries;

    /**
     * 用于请求下一页结果的标记-仅当isTruncated成员指示此对象列表被截断时才会填充
     */
    @Schema(name = "请求下一页结果的标记", description = "仅当isTruncated成员指示此对象列表被截断时才会填充")
    private String nextMarker;

    /**
     * 指示这是否是一个完整的列表，或者调用者是否需要向AmazonS3发出额外请求以查看S3 bucket的完整对象列表
     */
    @Schema(name = "否是一个完整的列表")
    private Boolean isTruncated;

    public List<ObjectDomain> getSummaries() {
        return summaries;
    }

    public void setSummaries(List<ObjectDomain> summaries) {
        this.summaries = summaries;
    }

    public String getNextMarker() {
        return nextMarker;
    }

    public void setNextMarker(String nextMarker) {
        this.nextMarker = nextMarker;
    }

    public Boolean getTruncated() {
        return isTruncated;
    }

    public void setTruncated(Boolean truncated) {
        isTruncated = truncated;
    }
}
