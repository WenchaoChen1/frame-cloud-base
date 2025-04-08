// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.minio.converter.domain;

import com.gstdev.cloud.plugin.storage.specification.domain.multipart.UploadPartCopyDomain;
import io.minio.UploadPartCopyResponse;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.core.convert.converter.Converter;

import java.util.Date;

/**
 * <p>Description: Minio UploadPartCopyResponse 转 UploadPartCopyDomain 转换器 </p>
 *
 * @author : cc
 * @date : 2023/8/14 16:52
 */
public class UploadPartCopyResponseToDomainConverter implements Converter<UploadPartCopyResponse, UploadPartCopyDomain> {
    @Override
    public UploadPartCopyDomain convert(UploadPartCopyResponse source) {

        UploadPartCopyDomain domain = new UploadPartCopyDomain();
        domain.setUploadId(source.uploadId());
        domain.setPartNumber(source.partNumber());

        if (ObjectUtils.isNotEmpty(source.result())) {
            domain.setEtag(source.result().etag());
            domain.setLastModifiedDate(Date.from(source.result().lastModified().toInstant()));
        }

        return domain;
    }
}
