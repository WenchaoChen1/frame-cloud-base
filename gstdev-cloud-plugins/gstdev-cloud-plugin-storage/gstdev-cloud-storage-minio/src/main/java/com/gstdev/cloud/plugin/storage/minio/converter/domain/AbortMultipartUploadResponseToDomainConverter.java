// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.minio.converter.domain;

import com.gstdev.cloud.plugin.storage.specification.domain.multipart.AbortMultipartUploadDomain;
import io.minio.AbortMultipartUploadResponse;
import org.springframework.core.convert.converter.Converter;

/**
 * <p>Description: Minio AbortMultipartUploadResponse 转 AbortMultipartUploadDomain 转换器 </p>
 *
 * @author : cc
 * @date : 2023/8/14 17:13
 */
public class AbortMultipartUploadResponseToDomainConverter implements Converter<AbortMultipartUploadResponse, AbortMultipartUploadDomain> {
    @Override
    public AbortMultipartUploadDomain convert(AbortMultipartUploadResponse source) {

        AbortMultipartUploadDomain domain = new AbortMultipartUploadDomain();
        domain.setUploadId(source.uploadId());
        domain.setBucketName(source.bucket());
        domain.setRegion(source.region());
        domain.setObjectName(source.object());
        return domain;
    }
}
