// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.minio.converter.domain;

import com.gstdev.cloud.plugin.storage.minio.utils.ConverterUtils;
import com.gstdev.cloud.plugin.storage.specification.arguments.object.ListObjectsV2Arguments;
import com.gstdev.cloud.plugin.storage.specification.domain.object.ListObjectsV2Domain;
import com.gstdev.cloud.plugin.storage.specification.domain.object.ObjectDomain;
import io.minio.Result;
import io.minio.messages.Item;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.core.convert.converter.Converter;

import java.util.List;

/**
 * <p>Description: Iterable<Result<Item>> V2 转 BucketDomain 转换器 </p>
 *
 * @author : cc
 * @date : 2023/8/10 10:55
 */
public class IterableResultItemV2ToDomainConverter implements Converter<Iterable<Result<Item>>, ListObjectsV2Domain> {

    private final String bucketName;

    private String prefix;
    private ListObjectsV2Arguments listObjectsV2Arguments;

    public IterableResultItemV2ToDomainConverter(String bucketName) {
        this.bucketName = bucketName;
    }

    public IterableResultItemV2ToDomainConverter(String bucketName, String prefix) {
        this.bucketName = bucketName;
        this.prefix = prefix;
    }

    public IterableResultItemV2ToDomainConverter(ListObjectsV2Arguments listObjectsV2Arguments) {
        this.listObjectsV2Arguments = listObjectsV2Arguments;
        this.bucketName = listObjectsV2Arguments.getBucketName();
        this.prefix = listObjectsV2Arguments.getPrefix();
    }

    @Override
    public ListObjectsV2Domain convert(Iterable<Result<Item>> source) {

        List<ObjectDomain> objectDomains = ConverterUtils.toDomains(source, new ResultItemToDomainConverter(this.bucketName));

        ListObjectsV2Domain domain = new ListObjectsV2Domain();
        domain.setBucketName(this.bucketName);
        domain.setPrefix(this.prefix);

        if (ObjectUtils.isNotEmpty(listObjectsV2Arguments)) {
            domain.setMarker(listObjectsV2Arguments.getMarker());
            domain.setDelimiter(listObjectsV2Arguments.getDelimiter());
            domain.setMaxKeys(listObjectsV2Arguments.getMaxKeys());
            domain.setEncodingType(listObjectsV2Arguments.getEncodingType());
            domain.setBucketName(listObjectsV2Arguments.getBucketName());
        }

        domain.setSummaries(objectDomains);

        return domain;
    }
}
