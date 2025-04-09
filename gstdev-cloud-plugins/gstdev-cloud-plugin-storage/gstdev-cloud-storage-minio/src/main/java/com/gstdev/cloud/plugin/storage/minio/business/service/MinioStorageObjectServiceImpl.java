// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.minio.business.service;

import com.gstdev.cloud.plugin.storage.minio.converter.arguments.*;
import com.gstdev.cloud.plugin.storage.minio.converter.domain.*;
import com.gstdev.cloud.plugin.storage.minio.service.MinioObjectService;
import com.gstdev.cloud.plugin.storage.minio.utils.ConverterUtils;
import com.gstdev.cloud.plugin.storage.specification.arguments.object.*;
import com.gstdev.cloud.plugin.storage.specification.core.business.service.StorageObjectService;
import com.gstdev.cloud.plugin.storage.specification.domain.base.ObjectWriteDomain;
import com.gstdev.cloud.plugin.storage.specification.domain.object.*;
import io.minio.*;
import io.minio.messages.DeleteError;
import io.minio.messages.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>Description: Minio Java OSS API 对象操作实现 </p>
 *
 * @author : cc
 * @date : 2023/8/9 16:50
 */
@Service
public class MinioStorageObjectServiceImpl implements StorageObjectService {

    private static final Logger log = LoggerFactory.getLogger(MinioStorageObjectServiceImpl.class);

    private final MinioObjectService minioObjectService;

    public MinioStorageObjectServiceImpl(MinioObjectService minioObjectService) {
        this.minioObjectService = minioObjectService;
    }

    @Override
    public ListObjectsDomain listObjects(ListObjectsArguments arguments) {
        Converter<ListObjectsArguments, ListObjectsArgs> toArgs = new ArgumentsToListObjectsArgsConverter();
        Iterable<Result<Item>> iterable = minioObjectService.listObjects(toArgs.convert(arguments));
        Converter<Iterable<Result<Item>>, ListObjectsDomain> toDomain = new IterableResultItemToDomainConverter(arguments);
        return toDomain.convert(iterable);
    }

    @Override
    public ListObjectsV2Domain listObjectsV2(ListObjectsV2Arguments arguments) {
        Converter<ListObjectsV2Arguments, ListObjectsArgs> toArgs = new ArgumentsToListObjectsV2ArgsConverter();
        Iterable<Result<Item>> iterable = minioObjectService.listObjects(toArgs.convert(arguments));
        Converter<Iterable<Result<Item>>, ListObjectsV2Domain> toDomain = new IterableResultItemV2ToDomainConverter(arguments);
        return toDomain.convert(iterable);
    }

    @Override
    public void deleteObject(DeleteObjectArguments arguments) {
        Converter<DeleteObjectArguments, RemoveObjectArgs> toArgs = new ArgumentsToRemoveObjectArgsConverter();
        minioObjectService.removeObject(toArgs.convert(arguments));
    }

    @Override
    public List<DeleteObjectDomain> deleteObjects(DeleteObjectsArguments arguments) {
        Converter<DeleteObjectsArguments, RemoveObjectsArgs> toArgs = new ArgumentsToRemoveObjectsArgsConverter();
        Iterable<Result<DeleteError>> deletesErrors = minioObjectService.removeObjects(toArgs.convert(arguments));
        return ConverterUtils.toDomains(deletesErrors, new ResultDeleteErrorToDomainConverter());
    }

    @Override
    public ObjectMetadataDomain getObjectMetadata(GetObjectMetadataArguments arguments) {

        Converter<GetObjectMetadataArguments, StatObjectArgs> toRequest = new ArgumentsToStatObjectArgsConverter();
        Converter<StatObjectResponse, ObjectMetadataDomain> toDomain = new StatObjectResponseToDomainConverter();

        StatObjectResponse response = minioObjectService.statObject(toRequest.convert(arguments));
        return toDomain.convert(response);
    }

    @Override
    public GetObjectDomain getObject(GetObjectArguments arguments) {

        Converter<GetObjectArguments, GetObjectArgs> toRequest = new ArgumentsToGetObjectArgsConverter();
        Converter<GetObjectResponse, GetObjectDomain> toDomain = new GetObjectResponseToDomainConverter();

        GetObjectResponse response = minioObjectService.getObject(toRequest.convert(arguments));
        return toDomain.convert(response);
    }

    @Override
    public PutObjectDomain putObject(PutObjectArguments arguments) {

        Converter<PutObjectArguments, PutObjectArgs> toRequest = new ArgumentsToPutObjectArgsConverter();
        Converter<ObjectWriteResponse, PutObjectDomain> toDomain = new ObjectWriteResponseToPutObjectDomainConverter();

        ObjectWriteResponse response = minioObjectService.putObject(toRequest.convert(arguments));
        return toDomain.convert(response);
    }

    @Override
    public String generatePresignedUrl(GeneratePresignedUrlArguments arguments) {

        Converter<GeneratePresignedUrlArguments, GetPresignedObjectUrlArgs> toRequest = new ArgumentsToGetPreSignedObjectUrlConverter();
        return minioObjectService.getPreSignedObjectUrl(toRequest.convert(arguments));
    }

    @Override
    public ObjectMetadataDomain download(DownloadObjectArguments arguments) {
        Converter<DownloadObjectArguments, DownloadObjectArgs> toRequest = new ArgumentsToDownloadObjectArgsConverter();
        minioObjectService.downloadObject(toRequest.convert(arguments));
        return new ObjectMetadataDomain();
    }

    @Override
    public ObjectWriteDomain upload(UploadObjectArguments arguments) {

        Converter<UploadObjectArguments, UploadObjectArgs> toRequest = new ArgumentsToUploadObjectArgsConverter();
        Converter<ObjectWriteResponse, UploadObjectDomain> toDomain = new ObjectWriteResponseToUploadObjectDomainConverter();

        ObjectWriteResponse response = minioObjectService.uploadObject(toRequest.convert(arguments));
        return toDomain.convert(response);
    }
}
