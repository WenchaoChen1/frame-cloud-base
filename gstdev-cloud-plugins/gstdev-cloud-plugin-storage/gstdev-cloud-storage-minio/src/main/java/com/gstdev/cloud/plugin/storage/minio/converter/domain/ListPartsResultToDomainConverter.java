// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.minio.converter.domain;

import com.gstdev.cloud.plugin.storage.specification.arguments.multipart.ListPartsArguments;
import com.gstdev.cloud.plugin.storage.specification.domain.base.OwnerDomain;
import com.gstdev.cloud.plugin.storage.specification.domain.multipart.ListPartsDomain;
import com.gstdev.cloud.plugin.storage.specification.domain.multipart.PartSummaryDomain;
import io.minio.messages.Initiator;
import io.minio.messages.ListPartsResult;
import io.minio.messages.Owner;
import io.minio.messages.Part;
import org.springframework.core.convert.converter.Converter;

import java.util.List;

/**
 * <p>Description: ListPartsResult 转 PartSummaryDomain </p>
 *
 * @author : cc
 * @date : 2023/8/13 23:42
 */
public class ListPartsResultToDomainConverter implements Converter<ListPartsResult, ListPartsDomain> {

    private final Converter<Owner, OwnerDomain> owner = new OwnerToDomainConverter();
    private final Converter<Initiator, OwnerDomain> initiator = new InitiatorToDomainConverter();
    private final Converter<List<Part>, List<PartSummaryDomain>> parts = new PartToDomainConverter();

    private final ListPartsArguments arguments;

    public ListPartsResultToDomainConverter(ListPartsArguments arguments) {
        this.arguments = arguments;
    }

    @Override
    public ListPartsDomain convert(ListPartsResult source) {

        ListPartsDomain domain = new ListPartsDomain();
        domain.setOwner(owner.convert(source.owner()));
        domain.setInitiator(initiator.convert(source.initiator()));
        domain.setStorageClass(source.storageClass());
        domain.setMaxParts(source.maxParts());
        domain.setPartNumberMarker(source.partNumberMarker());
        domain.setNextPartNumberMarker(source.nextPartNumberMarker());
        domain.setTruncated(source.isTruncated());
        domain.setParts(parts.convert(source.partList()));
        domain.setUploadId(arguments.getUploadId());
        domain.setBucketName(source.bucketName());
        domain.setObjectName(source.objectName());

        return domain;
    }
}
