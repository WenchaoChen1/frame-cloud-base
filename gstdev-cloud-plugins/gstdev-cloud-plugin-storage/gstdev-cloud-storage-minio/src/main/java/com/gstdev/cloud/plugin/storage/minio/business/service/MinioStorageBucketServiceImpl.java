// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.minio.business.service;

import com.gstdev.cloud.base.definition.support.AbstractObjectPool;
import com.gstdev.cloud.plugin.storage.minio.converter.arguments.ArgumentsToMakeBucketArgsConverter;
import com.gstdev.cloud.plugin.storage.minio.converter.arguments.ArgumentsToRemoveBucketArgsConverter;
import com.gstdev.cloud.plugin.storage.minio.converter.domain.BucketToDomainConverter;
import com.gstdev.cloud.plugin.storage.minio.definition.service.BaseMinioService;
import com.gstdev.cloud.plugin.storage.minio.service.MinioBucketService;
import com.gstdev.cloud.plugin.storage.minio.utils.ConverterUtils;
import com.gstdev.cloud.plugin.storage.specification.arguments.bucket.CreateBucketArguments;
import com.gstdev.cloud.plugin.storage.specification.arguments.bucket.DeleteBucketArguments;
import com.gstdev.cloud.plugin.storage.specification.core.business.service.StorageBucketService;
import com.gstdev.cloud.plugin.storage.specification.domain.bucket.BucketDomain;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.RemoveBucketArgs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>Description: Minio Java OSS API 存储桶操作实现 </p>
 *
 * @author : cc
 * @date : 2023/7/24 19:13
 */
@Service
public class MinioStorageBucketServiceImpl extends BaseMinioService implements StorageBucketService {

    private static final Logger log = LoggerFactory.getLogger(MinioStorageBucketServiceImpl.class);

    private final MinioBucketService minioBucketService;

    public MinioStorageBucketServiceImpl(AbstractObjectPool<MinioClient> ossClientObjectPool, MinioBucketService minioBucketService) {
        super(ossClientObjectPool);
        this.minioBucketService = minioBucketService;
    }

    @Override
    public boolean doesBucketExist(String bucketName) {
        return minioBucketService.bucketExists(bucketName);
    }

    @Override
    public List<BucketDomain> listBuckets() {
        return ConverterUtils.toDomains(minioBucketService.listBuckets(), new BucketToDomainConverter());
    }

    @Override
    public BucketDomain createBucket(String bucketName) {
        minioBucketService.makeBucket(bucketName);
        return null;
    }

    @Override
    public BucketDomain createBucket(CreateBucketArguments arguments) {
        Converter<CreateBucketArguments, MakeBucketArgs> toArgs = new ArgumentsToMakeBucketArgsConverter();
        minioBucketService.makeBucket(toArgs.convert(arguments));
        return null;
    }

    @Override
    public void deleteBucket(String bucketName) {
        minioBucketService.removeBucket(bucketName);
    }

    @Override
    public void deleteBucket(DeleteBucketArguments arguments) {
        Converter<DeleteBucketArguments, RemoveBucketArgs> toArgs = new ArgumentsToRemoveBucketArgsConverter();
        minioBucketService.removeBucket(toArgs.convert(arguments));
    }


}
