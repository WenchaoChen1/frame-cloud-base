// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.minio.converter.domain;

import com.gstdev.cloud.plugin.storage.specification.domain.base.OwnerDomain;
import io.minio.messages.Initiator;
import org.springframework.core.convert.converter.Converter;

/**
 * <p>Description: Owner 转 OwnerDomain 转换器 </p>
 *
 * @author : cc
 * @date : 2023/8/13 23:09
 */
public class InitiatorToDomainConverter implements Converter<Initiator, OwnerDomain> {

    @Override
    public OwnerDomain convert(Initiator source) {

        OwnerDomain attribute = new OwnerDomain();
        attribute.setId(source.id());
        attribute.setDisplayName(source.displayName());
        return attribute;
    }
}
