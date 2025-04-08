// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.minio.converter;

import com.gstdev.cloud.plugin.storage.specification.domain.base.ObjectWriteDomain;
import io.minio.ObjectWriteResponse;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.core.convert.converter.Converter;

/**
 * <p>Description: ObjectWriteResponse 转 Entity 转换器 </p>
 *
 * @author : cc
 * @date : 2023/6/1 22:04
 */
public class ResponseToObjectWriteDomainConverter implements Converter<ObjectWriteResponse, ObjectWriteDomain> {
    @Override
    public ObjectWriteDomain convert(ObjectWriteResponse response) {
        if (ObjectUtils.isNotEmpty(response)) {
            ObjectWriteDomain domain = new ObjectWriteDomain();
            domain.setEtag(response.etag());
            domain.setVersionId(response.versionId());
            domain.setBucketName(response.bucket());
            domain.setRegion(response.region());
            domain.setObjectName(response.object());
            return domain;
        }

        return null;
    }
}
