// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.specification.constants;

/**
 * <p>Description: TODO </p>
 *
 * @author : cc
 * @date : 2023/8/17 14:14
 */
public interface OssConstants {

    // allowed maximum object size is 5TiB.
    /**
     * 允许的对象大小，最大为 5T
     */
    long MAX_OBJECT_SIZE = 5L * 1024 * 1024 * 1024 * 1024;
    // allowed minimum part size is 5MiB in multipart upload.
    /**
     * 分片上传中，允许的分片大小最小为 5M
     */
    int MIN_MULTIPART_SIZE = 5 * 1024 * 1024;
    /**
     * 分片上传中，允许的分片大小最大为 5G
     */
    long MAX_PART_SIZE = 5L * 1024 * 1024 * 1024;
    /**
     * 分片上传中，允许的最大分片数量为 1000
     */
    int MAX_MULTIPART_COUNT = 10000;
}
