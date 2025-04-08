// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.specification.domain.multipart;

import com.gstdev.cloud.plugin.storage.specification.arguments.multipart.ListMultipartUploadsArguments;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Description: 分片上传列表返回结果域对象 </p>
 *
 * @author : cc
 * @date : 2023/8/13 20:46
 */
@Schema(name = "分片上传列表返回结果域对象", title = "分片上传列表返回结果域对象")
public class ListMultipartUploadsDomain extends ListMultipartUploadsArguments {

    private boolean isTruncated;

    private String nextKeyMarker;

    private String nextUploadIdMarker;

    private List<UploadDomain> multipartUploads = new ArrayList<>();

    private List<String> commonPrefixes = new ArrayList<>();

    public boolean isTruncated() {
        return isTruncated;
    }

    public void setTruncated(boolean truncated) {
        isTruncated = truncated;
    }

    public String getNextKeyMarker() {
        return nextKeyMarker;
    }

    public void setNextKeyMarker(String nextKeyMarker) {
        this.nextKeyMarker = nextKeyMarker;
    }

    public String getNextUploadIdMarker() {
        return nextUploadIdMarker;
    }

    public void setNextUploadIdMarker(String nextUploadIdMarker) {
        this.nextUploadIdMarker = nextUploadIdMarker;
    }

    public List<UploadDomain> getMultipartUploads() {
        return multipartUploads;
    }

    public void setMultipartUploads(List<UploadDomain> multipartUploads) {
        this.multipartUploads = multipartUploads;
    }

    public List<String> getCommonPrefixes() {
        return commonPrefixes;
    }

    public void setCommonPrefixes(List<String> commonPrefixes) {
        this.commonPrefixes = commonPrefixes;
    }
}
