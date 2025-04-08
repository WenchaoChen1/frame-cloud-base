// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.minio.converter.domain;

import com.gstdev.cloud.plugin.storage.specification.domain.multipart.UploadPartDomain;
import io.minio.UploadPartResponse;
import org.springframework.core.convert.converter.Converter;

/**
 * <p>Description: Minio InitiateMultipartUploadResult 转 InitiateMultipartUploadDomain 转换器 </p>
 *
 * @author : cc
 * @date : 2023/8/14 16:46
 */
public class UploadPartResponseToDomainConverter implements Converter<UploadPartResponse, UploadPartDomain> {

    @Override
    public UploadPartDomain convert(UploadPartResponse source) {
        UploadPartDomain domain = new UploadPartDomain();
        domain.setPartNumber(source.partNumber());
        domain.setEtag(source.etag());
        return domain;
    }
}
