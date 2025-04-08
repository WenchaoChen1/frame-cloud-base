// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.minio.converter.domain;

import com.gstdev.cloud.plugin.storage.specification.arguments.multipart.ListMultipartUploadsArguments;
import com.gstdev.cloud.plugin.storage.specification.domain.multipart.ListMultipartUploadsDomain;
import com.gstdev.cloud.plugin.storage.specification.domain.multipart.UploadDomain;
import io.minio.messages.ListMultipartUploadsResult;
import io.minio.messages.Upload;
import org.springframework.core.convert.converter.Converter;

import java.util.List;

/**
 * <p>Description: ListMultipartUploadsResult 转 ListMultipartUploadsDomain 转换器 </p>
 *
 * @author : cc
 * @date : 2023/8/13 23:05
 */
public class ListMultipartUploadsResultToDomainConverter implements Converter<ListMultipartUploadsResult, ListMultipartUploadsDomain> {

    private final ListMultipartUploadsArguments listMultipartUploadsArguments;
    private final Converter<List<Upload>, List<UploadDomain>> toMultipartUpload = new UploadToDomainConverter();

    public ListMultipartUploadsResultToDomainConverter(ListMultipartUploadsArguments listMultipartUploadsArguments) {
        this.listMultipartUploadsArguments = listMultipartUploadsArguments;
    }

    @Override
    public ListMultipartUploadsDomain convert(ListMultipartUploadsResult source) {
        ListMultipartUploadsDomain domain = new ListMultipartUploadsDomain();
        domain.setTruncated(source.isTruncated());
        domain.setNextKeyMarker(source.nextKeyMarker());
        domain.setNextUploadIdMarker(source.nextUploadIdMarker());
        domain.setMultipartUploads(toMultipartUpload.convert(source.uploads()));
        domain.setDelimiter(listMultipartUploadsArguments.getDelimiter());
        domain.setPrefix(listMultipartUploadsArguments.getPrefix());
        domain.setMaxUploads(source.maxUploads());
        domain.setKeyMarker(source.keyMarker());
        domain.setUploadIdMarker(source.uploadIdMarker());
        domain.setEncodingType(source.encodingType());
        domain.setBucketName(source.bucketName());
        return domain;
    }
}
