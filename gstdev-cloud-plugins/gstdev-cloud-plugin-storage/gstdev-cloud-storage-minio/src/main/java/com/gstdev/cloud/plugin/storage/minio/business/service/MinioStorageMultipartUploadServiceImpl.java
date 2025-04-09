// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.minio.business.service;

import com.gstdev.cloud.plugin.storage.minio.converter.domain.*;
import com.gstdev.cloud.plugin.storage.minio.service.MinioMultipartUploadService;
import com.gstdev.cloud.plugin.storage.specification.arguments.multipart.*;
import com.gstdev.cloud.plugin.storage.specification.core.business.service.StorageMultipartUploadService;
import com.gstdev.cloud.plugin.storage.specification.domain.multipart.*;
import io.minio.*;
import io.minio.messages.InitiateMultipartUploadResult;
import io.minio.messages.ListMultipartUploadsResult;
import io.minio.messages.ListPartsResult;
import io.minio.messages.Part;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>Description: Minio Java OSS API 分片上传操作实现 </p>
 *
 * @author : cc
 * @date : 2023/8/13 21:11
 */
@Service
public class MinioStorageMultipartUploadServiceImpl implements StorageMultipartUploadService {

    private final MinioMultipartUploadService minioMultipartUploadService;

    public MinioStorageMultipartUploadServiceImpl(MinioMultipartUploadService minioMultipartUploadService) {
        this.minioMultipartUploadService = minioMultipartUploadService;
    }

    @Override
    public InitiateMultipartUploadDomain initiateMultipartUpload(InitiateMultipartUploadArguments arguments) {

        Converter<InitiateMultipartUploadResult, InitiateMultipartUploadDomain> toDomain = new InitiateMultipartUploadResultToDomainConverter();

        CreateMultipartUploadResponse response = minioMultipartUploadService.createMultipartUpload(
                arguments.getBucketName(),
                arguments.getRegion(),
                arguments.getObjectName(),
                arguments.getExtraHeaders(),
                arguments.getExtraQueryParams());

        return toDomain.convert(response.result());
    }

    @Override
    public UploadPartDomain uploadPart(UploadPartArguments arguments) {

        Converter<UploadPartResponse, UploadPartDomain> toDomain = new UploadPartResponseToDomainConverter();

        UploadPartResponse response = minioMultipartUploadService.uploadPart(
                arguments.getBucketName(),
                arguments.getRegion(),
                arguments.getObjectName(),
                arguments.getInputStream(),
                arguments.getPartSize(),
                arguments.getUploadId(),
                arguments.getPartNumber(),
                arguments.getExtraHeaders(),
                arguments.getExtraQueryParams());

        return toDomain.convert(response);
    }

    @Override
    public UploadPartCopyDomain uploadPartCopy(UploadPartCopyArguments arguments) {

        Converter<UploadPartCopyResponse, UploadPartCopyDomain> toDomain = new UploadPartCopyResponseToDomainConverter();

        UploadPartCopyResponse response = minioMultipartUploadService.uploadPartCopy(
                arguments.getBucketName(),
                arguments.getRegion(),
                arguments.getObjectName(),
                arguments.getUploadId(),
                arguments.getPartNumber(),
                arguments.getExtraHeaders(),
                arguments.getExtraQueryParams());

        return toDomain.convert(response);
    }

    @Override
    public CompleteMultipartUploadDomain completeMultipartUpload(CompleteMultipartUploadArguments arguments) {

        Converter<List<PartSummaryDomain>, Part[]> toPart = new DomainToPartConverter();
        Converter<ObjectWriteResponse, CompleteMultipartUploadDomain> toDomain = new ObjectWriteResponseToCompleteMultipartUploadDomainConverter();

        ObjectWriteResponse response = minioMultipartUploadService.completeMultipartUpload(
                arguments.getBucketName(),
                arguments.getRegion(),
                arguments.getObjectName(),
                arguments.getUploadId(),
                toPart.convert(arguments.getParts()),
                arguments.getExtraHeaders(),
                arguments.getExtraQueryParams());

        return toDomain.convert(response);
    }

    @Override
    public AbortMultipartUploadDomain abortMultipartUpload(AbortMultipartUploadArguments arguments) {

        Converter<AbortMultipartUploadResponse, AbortMultipartUploadDomain> toDomain = new AbortMultipartUploadResponseToDomainConverter();

        AbortMultipartUploadResponse response = minioMultipartUploadService.abortMultipartUpload(
                arguments.getBucketName(),
                arguments.getRegion(),
                arguments.getObjectName(),
                arguments.getUploadId(),
                arguments.getExtraHeaders(),
                arguments.getExtraQueryParams());

        return toDomain.convert(response);
    }

    @Override
    public ListPartsDomain listParts(ListPartsArguments arguments) {

        Converter<ListPartsResult, ListPartsDomain> toDomain = new ListPartsResultToDomainConverter(arguments);

        ListPartsResponse response = minioMultipartUploadService.listParts(
                arguments.getBucketName(),
                arguments.getRegion(),
                arguments.getObjectName(),
                arguments.getMaxParts(),
                arguments.getPartNumberMarker(),
                arguments.getUploadId(),
                arguments.getExtraHeaders(),
                arguments.getExtraQueryParams());

        return toDomain.convert(response.result());
    }

    @Override
    public ListMultipartUploadsDomain listMultipartUploads(ListMultipartUploadsArguments arguments) {

        Converter<ListMultipartUploadsResult, ListMultipartUploadsDomain> toDomain = new ListMultipartUploadsResultToDomainConverter(arguments);

        ListMultipartUploadsResponse response = minioMultipartUploadService.listMultipartUploads(
                arguments.getBucketName(),
                arguments.getRegion(),
                arguments.getDelimiter(),
                arguments.getEncodingType(),
                arguments.getKeyMarker(),
                arguments.getMaxUploads(),
                arguments.getPrefix(),
                arguments.getUploadIdMarker(),
                arguments.getExtraHeaders(),
                arguments.getExtraQueryParams());

        return toDomain.convert(response.result());
    }
}
