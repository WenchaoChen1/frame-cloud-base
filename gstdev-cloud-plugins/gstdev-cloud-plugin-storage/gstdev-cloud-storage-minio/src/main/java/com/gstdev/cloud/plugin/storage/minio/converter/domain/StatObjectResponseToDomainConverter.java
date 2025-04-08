// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.minio.converter.domain;

import com.gstdev.cloud.base.core.utils.type.DateTimeUtils;
import com.gstdev.cloud.plugin.storage.specification.domain.object.ObjectMetadataDomain;
import io.minio.StatObjectResponse;
import org.springframework.core.convert.converter.Converter;

/**
 * <p>Description: Minio StatObjectResponse 转 ObjectMetadataDomain 转换器 </p>
 *
 * @author : cc
 * @date : 2023/8/17 15:59
 */
public class StatObjectResponseToDomainConverter implements Converter<StatObjectResponse, ObjectMetadataDomain> {
    @Override
    public ObjectMetadataDomain convert(StatObjectResponse source) {

        ObjectMetadataDomain domain = new ObjectMetadataDomain();
        domain.setUserMetadata(source.userMetadata());
        domain.setContentLength(source.size());
        domain.setContentType(source.contentType());
        domain.setLastModified(DateTimeUtils.zonedDateTimeToDate(source.lastModified()));
        domain.setEtag(source.etag());
        domain.setVersionId(source.versionId());
        domain.setBucketName(source.bucket());
        domain.setRegion(source.region());
        domain.setObjectName(source.object());

        return domain;
    }
}
