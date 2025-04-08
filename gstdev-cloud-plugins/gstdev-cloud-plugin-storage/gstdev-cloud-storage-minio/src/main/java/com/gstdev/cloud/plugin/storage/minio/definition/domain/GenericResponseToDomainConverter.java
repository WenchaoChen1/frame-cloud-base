// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.minio.definition.domain;

import com.gstdev.cloud.plugin.storage.specification.core.converter.OssConverter;
import com.gstdev.cloud.plugin.storage.specification.domain.base.BaseDomain;
import io.minio.GenericResponse;

/**
 * <p>Description:  Minio GenericResponse 转 统一定义 BaseDomain 转换器 </p>
 *
 * @author : cc
 * @date : 2023/8/16 11:51
 */
public abstract class GenericResponseToDomainConverter<S extends GenericResponse, T extends BaseDomain> implements OssConverter<S, T> {

    @Override
    public void prepare(S source, T instance) {
        instance.setBucketName(source.bucket());
        instance.setRegion(source.region());
        instance.setObjectName(source.object());
    }
}
