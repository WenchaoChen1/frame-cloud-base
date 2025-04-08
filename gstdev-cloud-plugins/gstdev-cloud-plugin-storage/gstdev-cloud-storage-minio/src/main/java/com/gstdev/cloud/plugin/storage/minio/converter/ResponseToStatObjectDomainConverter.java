// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.minio.converter;

import com.gstdev.cloud.base.core.utils.type.DateTimeUtils;
import com.gstdev.cloud.plugin.storage.minio.converter.retention.RetentionModeToEnumConverter;
import com.gstdev.cloud.plugin.storage.minio.domain.StatObjectDomain;
import com.gstdev.cloud.plugin.storage.minio.enums.RetentionModeEnums;
import io.minio.StatObjectResponse;
import io.minio.messages.RetentionMode;
import org.springframework.core.convert.converter.Converter;

/**
 * <p>Description: Minio StatObjectResponse 转 StatObjectDomain 转换器 </p>
 *
 * @author : cc
 * @date : 2023/6/11 15:58
 */
public class ResponseToStatObjectDomainConverter implements Converter<StatObjectResponse, StatObjectDomain> {

    private final Converter<RetentionMode, RetentionModeEnums> toRetentionModeEnums;

    public ResponseToStatObjectDomainConverter() {
        this.toRetentionModeEnums = new RetentionModeToEnumConverter();
    }

    @Override
    public StatObjectDomain convert(StatObjectResponse response) {

        StatObjectDomain domain = new StatObjectDomain();
        domain.setEtag(response.etag());
        domain.setSize(response.size());
        domain.setLastModified(DateTimeUtils.zonedDateTimeToString(response.lastModified()));
        domain.setRetentionMode(toRetentionModeEnums.convert(response.retentionMode()));
        domain.setRetentionRetainUntilDate(DateTimeUtils.zonedDateTimeToString(response.retentionRetainUntilDate()));
        domain.setLegalHold(response.legalHold().status());
        domain.setDeleteMarker(response.deleteMarker());
        domain.setUserMetadata(response.userMetadata());
        domain.setBucketName(response.bucket());
        domain.setRegion(response.region());
        domain.setObjectName(response.object());

        return domain;
    }
}
