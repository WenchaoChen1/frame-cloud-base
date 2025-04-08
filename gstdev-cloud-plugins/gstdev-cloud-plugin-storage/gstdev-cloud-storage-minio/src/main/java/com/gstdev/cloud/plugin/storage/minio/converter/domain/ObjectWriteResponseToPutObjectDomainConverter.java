// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.minio.converter.domain;

import com.gstdev.cloud.plugin.storage.minio.definition.domain.ObjectWriteResponseToDomain;
import com.gstdev.cloud.plugin.storage.specification.domain.object.PutObjectDomain;
import io.minio.ObjectWriteResponse;

/**
 * <p>Description: Minio ObjectWriteResponse 转 PutObjectDomain 转换器 </p>
 *
 * @author : cc
 * @date : 2023/8/14 17:11
 */
public class ObjectWriteResponseToPutObjectDomainConverter extends ObjectWriteResponseToDomain<PutObjectDomain> {
    @Override
    public PutObjectDomain getInstance(ObjectWriteResponse source) {
        return new PutObjectDomain();
    }
}
