// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.minio.converter.domain;

import com.gstdev.cloud.plugin.storage.specification.domain.object.GetObjectDomain;
import io.minio.GetObjectResponse;
import org.springframework.core.convert.converter.Converter;

/**
 * <p>Description: Minio GetObjectResponse 转 GetObjectDomain 转换器 </p>
 *
 * @author : cc
 * @date : 2023/8/15 22:04
 */
public class GetObjectResponseToDomainConverter implements Converter<GetObjectResponse, GetObjectDomain> {
    @Override
    public GetObjectDomain convert(GetObjectResponse source) {

        GetObjectDomain domain = new GetObjectDomain();
        domain.setObjectContent(source);
        domain.setBucketName(source.bucket());
        domain.setRegion(source.region());
        domain.setObjectName(source.object());

        return domain;
    }
}
