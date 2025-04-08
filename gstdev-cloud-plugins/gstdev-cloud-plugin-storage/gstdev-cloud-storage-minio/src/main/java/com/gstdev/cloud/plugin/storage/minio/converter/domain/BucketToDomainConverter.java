// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.minio.converter.domain;

import com.gstdev.cloud.plugin.storage.specification.domain.bucket.BucketDomain;
import io.minio.messages.Bucket;
import org.springframework.core.convert.converter.Converter;

import java.util.Date;
import java.util.Optional;

/**
 * <p>Description: Bucket 转 BucketDomain 转换器 </p>
 *
 * @author : cc
 * @date : 2023/5/30 10:11
 */
public class BucketToDomainConverter implements Converter<Bucket, BucketDomain> {

    @Override
    public BucketDomain convert(Bucket source) {

        Optional<Bucket> optional = Optional.ofNullable(source);
        return optional.map(bucket -> {
            BucketDomain domain = new BucketDomain();
            domain.setBucketName(bucket.name());
            Optional.ofNullable(bucket.creationDate()).ifPresent(zonedDateTime ->
                    domain.setCreationDate(new Date(zonedDateTime.toInstant().toEpochMilli()))
            );
            return domain;
        }).orElse(null);
    }
}
