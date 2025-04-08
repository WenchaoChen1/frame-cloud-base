// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.minio.converter.domain;

import com.gstdev.cloud.plugin.storage.specification.domain.multipart.InitiateMultipartUploadDomain;
import io.minio.messages.InitiateMultipartUploadResult;
import org.springframework.core.convert.converter.Converter;

/**
 * <p>Description: Minio InitiateMultipartUploadResult 转 InitiateMultipartUploadDomain 转换器 </p>
 *
 * @author : cc
 * @date : 2023/8/14 16:42
 */
public class InitiateMultipartUploadResultToDomainConverter implements Converter<InitiateMultipartUploadResult, InitiateMultipartUploadDomain> {
    @Override
    public InitiateMultipartUploadDomain convert(InitiateMultipartUploadResult source) {

        InitiateMultipartUploadDomain domain = new InitiateMultipartUploadDomain();
        domain.setUploadId(source.uploadId());
        domain.setBucketName(source.bucketName());
        domain.setObjectName(source.objectName());
        return domain;
    }
}
