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
import com.gstdev.cloud.plugin.storage.specification.domain.multipart.UploadDomain;
import io.minio.messages.Initiator;
import io.minio.messages.Owner;
import io.minio.messages.Upload;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.core.convert.converter.Converter;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Description: Upload 转 UploadDomain 转换器 </p>
 *
 * @author : cc
 * @date : 2023/8/13 23:05
 */
public class UploadToDomainConverter implements Converter<List<Upload>, List<UploadDomain>> {

    private final Converter<Owner, OwnerDomain> owner = new OwnerToDomainConverter();
    private final Converter<Initiator, OwnerDomain> initiator = new InitiatorToDomainConverter();

    @Override
    public List<UploadDomain> convert(List<Upload> source) {
        if (CollectionUtils.isNotEmpty(source)) {
            return source.stream().map(this::convert).toList();
        }
        return new ArrayList<>();
    }

    private UploadDomain convert(Upload source) {
        UploadDomain domain = new UploadDomain();
        domain.setKey(source.objectName());
        domain.setUploadId(source.uploadId());
        domain.setOwner(owner.convert(source.owner()));
        domain.setInitiator(initiator.convert(source.initiator()));
        domain.setStorageClass(source.storageClass());
        domain.setInitiated(Date.from(source.initiated().toInstant()));
        return domain;
    }
}
