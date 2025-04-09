// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.core.constants;


import com.gstdev.cloud.base.definition.constants.BaseConstants;

/**
 * <p>Description: 对象存储统一常量 </p>
 *
 * @author : cc
 * @date : 2023/7/23 12:37
 */
public interface OssConstants extends BaseConstants {

    String PROPERTY_OSS_MINIO = PROPERTY_PREFIX_STORAGE + ".minio";
    String PROPERTY_OSS_S3 = PROPERTY_PREFIX_STORAGE + ".s3";
    String PROPERTY_OSS_ALIYUN = PROPERTY_PREFIX_STORAGE + ".aliyun";

    String ITEM_OSS_DIALECT = PROPERTY_PREFIX_STORAGE + ".dialect";
}
