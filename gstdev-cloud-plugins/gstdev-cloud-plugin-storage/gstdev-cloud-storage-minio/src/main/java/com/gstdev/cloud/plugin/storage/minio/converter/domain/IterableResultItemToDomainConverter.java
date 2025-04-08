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
import com.gstdev.cloud.plugin.storage.specification.arguments.object.ListObjectsArguments;
import com.gstdev.cloud.plugin.storage.specification.domain.object.ListObjectsDomain;
import com.gstdev.cloud.plugin.storage.specification.domain.object.ObjectDomain;
import io.minio.Result;
import io.minio.messages.Item;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.core.convert.converter.Converter;

import java.util.List;

/**
 * <p>Description: Iterable<Result<Item>> 转 BucketDomain 转换器 </p>
 *
 * @author : cc
 * @date : 2023/8/10 10:55
 */
public class IterableResultItemToDomainConverter implements Converter<Iterable<Result<Item>>, ListObjectsDomain> {

    private final String bucketName;

    private String prefix;
    private ListObjectsArguments listObjectsArguments;

    public IterableResultItemToDomainConverter(String bucketName) {
        this.bucketName = bucketName;
    }

    public IterableResultItemToDomainConverter(String bucketName, String prefix) {
        this.bucketName = bucketName;
        this.prefix = prefix;
    }

    public IterableResultItemToDomainConverter(ListObjectsArguments listObjectsArguments) {
        this.listObjectsArguments = listObjectsArguments;
        this.bucketName = listObjectsArguments.getBucketName();
        this.prefix = listObjectsArguments.getPrefix();
    }

    @Override
    public ListObjectsDomain convert(Iterable<Result<Item>> source) {

        List<ObjectDomain> objectDomains = ConverterUtils.toDomains(source, new ResultItemToDomainConverter(this.bucketName));

        ListObjectsDomain domain = new ListObjectsDomain();
        domain.setBucketName(this.bucketName);
        domain.setPrefix(this.prefix);

        if (ObjectUtils.isNotEmpty(listObjectsArguments)) {
            domain.setMarker(listObjectsArguments.getMarker());
            domain.setDelimiter(listObjectsArguments.getDelimiter());
            domain.setMaxKeys(listObjectsArguments.getMaxKeys());
            domain.setEncodingType(listObjectsArguments.getEncodingType());
            domain.setBucketName(listObjectsArguments.getBucketName());
        }

        domain.setSummaries(objectDomains);

        return domain;
    }
}
