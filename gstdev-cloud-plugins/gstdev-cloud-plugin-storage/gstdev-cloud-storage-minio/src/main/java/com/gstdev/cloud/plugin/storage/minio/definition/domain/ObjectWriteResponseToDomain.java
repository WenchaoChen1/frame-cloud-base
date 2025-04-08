// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.minio.definition.domain;

import com.gstdev.cloud.plugin.storage.specification.domain.base.ObjectWriteDomain;
import io.minio.ObjectWriteResponse;

/**
 * <p>Description: Minio ObjectWriteResponse 转 统一定义 ObjectWriteDomain 转换器 </p>
 *
 * @author : cc
 * @date : 2023/8/16 12:20
 */
public abstract class ObjectWriteResponseToDomain<T extends ObjectWriteDomain> extends GenericResponseToDomainConverter<ObjectWriteResponse, T> {

    @Override
    public void prepare(ObjectWriteResponse response, T domain) {
        domain.setEtag(response.etag());
        domain.setVersionId(response.versionId());
        super.prepare(response, domain);
    }
}
