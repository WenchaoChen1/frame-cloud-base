// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.minio.properties;

import com.gstdev.cloud.plugin.storage.core.constants.OssConstants;
import com.gstdev.cloud.plugin.storage.core.properties.AbstractOssProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p>Description: Minio 配置参数 </p>
 *
 * @author : cc
 * @date : 2021/11/8 10:31
 */
@ConfigurationProperties(prefix = OssConstants.PROPERTY_OSS_MINIO)
public class MinioProperties extends AbstractOssProperties {

}
