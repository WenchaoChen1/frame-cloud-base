// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.specification.domain.object;

import com.gstdev.cloud.plugin.storage.specification.arguments.object.ListObjectsV2Arguments;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

/**
 * <p>Description: V2对象列表结果 </p>
 *
 * @author : cc
 * @date : 2023/8/11 15:03
 */
public class ListObjectsV2Domain extends ListObjectsV2Arguments {

    @Schema(name = "对象列表")
    private List<ObjectDomain> summaries;

    /**
     * 指示这是否是一个完整的列表，或者调用者是否需要向AmazonS3发出额外请求以查看S3 bucket的完整对象列表
     */
    @Schema(name = "否是一个完整的列表")
    private boolean isTruncated;

    /**
     * KeyCount是此请求返回的密钥数。KeyCount将始终小于或等于MaxKeys字段
     */
    @Schema(name = "Key 数量")
    private int keyCount;

    /**
     * 当 isTruncated为 true 时，发送 NextContinuationToken，这意味着存储桶中可以列出更多对象。请求亚马逊
     * 可以通过提供此NextContinuationToken来继续下一个列表
     */
    @Schema(name = "下一个列表标记")
    private String nextContinuationToken;

    public boolean isTruncated() {
        return isTruncated;
    }

    public void setTruncated(boolean truncated) {
        isTruncated = truncated;
    }

    public int getKeyCount() {
        return keyCount;
    }

    public void setKeyCount(int keyCount) {
        this.keyCount = keyCount;
    }

    public String getNextContinuationToken() {
        return nextContinuationToken;
    }

    public void setNextContinuationToken(String nextContinuationToken) {
        this.nextContinuationToken = nextContinuationToken;
    }

    public List<ObjectDomain> getSummaries() {
        return summaries;
    }

    public void setSummaries(List<ObjectDomain> summaries) {
        this.summaries = summaries;
    }
}
