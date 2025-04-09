// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.minio.service;

import com.gstdev.cloud.plugin.storage.core.exception.*;
import com.gstdev.cloud.plugin.storage.minio.definition.pool.MinioClientObjectPool;
import com.gstdev.cloud.plugin.storage.minio.definition.service.BaseMinioService;
import com.gstdev.cloud.plugin.storage.minio.enums.PolicyEnums;
import com.google.common.base.Enums;
import io.minio.DeleteBucketPolicyArgs;
import io.minio.GetBucketPolicyArgs;
import io.minio.MinioClient;
import io.minio.SetBucketPolicyArgs;
import io.minio.errors.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.ConnectException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * <p>Description: Bucket 访问策略 </p>
 *
 * @author : cc
 * @date : 2022/6/30 15:44
 */
@Service
public class MinioBucketPolicyService extends BaseMinioService {

    private static final Logger log = LoggerFactory.getLogger(MinioBucketPolicyService.class);

    public MinioBucketPolicyService(MinioClientObjectPool minioClientObjectPool) {
        super(minioClientObjectPool);
    }

    /**
     * 获取 Bucket 访问策略配置
     *
     * @param bucketName 存储桶名称
     * @return 自定义策略枚举 {@link PolicyEnums}
     */
    public PolicyEnums getBucketPolicy(String bucketName) {
        return getBucketPolicy(bucketName, null);
    }

    /**
     * 获取 Bucket 访问策略配置
     *
     * @param bucketName 存储桶名称
     * @param region     区域
     * @return 自定义策略枚举 {@link PolicyEnums}
     */
    public PolicyEnums getBucketPolicy(String bucketName, String region) {
        return getBucketPolicy(GetBucketPolicyArgs.builder().bucket(bucketName).region(region).build());
    }

    /**
     * 获取 Bucket 访问策略配置
     *
     * @param getBucketPolicyArgs {@link GetBucketPolicyArgs}
     */
    public PolicyEnums getBucketPolicy(GetBucketPolicyArgs getBucketPolicyArgs) {
        String function = "getBucketPolicy";
        MinioClient minioClient = getClient();

        try {
            String policy = minioClient.getBucketPolicy(getBucketPolicyArgs);
            if (StringUtils.isNotBlank(policy)) {
                return Enums.getIfPresent(PolicyEnums.class, policy).or(PolicyEnums.PRIVATE);
            } else {
                return PolicyEnums.PRIVATE;
            }
        } catch (ErrorResponseException e) {
            log.error("[GstDev Cloud] |- Minio catch ErrorResponseException in [{}].", function, e);
            throw new OssErrorResponseException(e.getMessage());
        } catch (InsufficientDataException e) {
            log.error("[GstDev Cloud] |- Minio catch InsufficientDataException in [{}].", function, e);
            throw new OssInsufficientDataException(e.getMessage());
        } catch (InternalException e) {
            log.error("[GstDev Cloud] |- Minio catch InternalException in [{}].", function, e);
            throw new OssInternalException(e.getMessage());
        } catch (InvalidKeyException e) {
            log.error("[GstDev Cloud] |- Minio catch InvalidKeyException in [{}].", function, e);
            throw new OssInvalidKeyException(e.getMessage());
        } catch (InvalidResponseException e) {
            log.error("[GstDev Cloud] |- Minio catch InvalidResponseException in [{}].", function, e);
            throw new OssInvalidResponseException(e.getMessage());
        } catch (IOException e) {
            log.error("[GstDev Cloud] |- Minio catch IOException in [{}].", function, e);
            if (e instanceof ConnectException) {
                throw new OssConnectException(e.getMessage());
            } else {
                throw new OssIOException(e.getMessage());
            }
        } catch (NoSuchAlgorithmException e) {
            log.error("[GstDev Cloud] |- Minio catch NoSuchAlgorithmException in [{}].", function, e);
            throw new OssNoSuchAlgorithmException(e.getMessage());
        } catch (ServerException e) {
            log.error("[GstDev Cloud] |- Minio catch ServerException in [{}].", function, e);
            throw new OssServerException(e.getMessage());
        } catch (XmlParserException e) {
            log.error("[GstDev Cloud] |- Minio catch XmlParserException in [{}].", function, e);
            throw new OssXmlParserException(e.getMessage());
        } catch (BucketPolicyTooLargeException e) {
            log.error("[GstDev Cloud] |- Minio catch BucketPolicyTooLargeException in [{}].", function, e);
            throw new OssBucketPolicyTooLargeException(e.getMessage());
        } finally {
            close(minioClient);
        }
    }

    /**
     * 设置 Bucket 访问策略
     *
     * @param bucketName 存储桶名称
     * @param config     策略配置
     */
    public void setBucketPolicy(String bucketName, String config) {
        setBucketPolicy(bucketName, null, config);
    }

    /**
     * 设置 Bucket 访问策略
     *
     * @param bucketName 存储桶名称
     * @param region     区域
     * @param config     策略配置
     */
    public void setBucketPolicy(String bucketName, String region, String config) {
        setBucketPolicy(SetBucketPolicyArgs.builder().bucket(bucketName).region(region).config(config).build());
    }

    /**
     * 设置 Bucket 访问策略
     *
     * @param setBucketPolicyArgs {@link SetBucketPolicyArgs}
     */
    public void setBucketPolicy(SetBucketPolicyArgs setBucketPolicyArgs) {
        String function = "setBucketPolicy";
        MinioClient minioClient = getClient();

        try {
            minioClient.setBucketPolicy(setBucketPolicyArgs);
        } catch (ErrorResponseException e) {
            log.error("[GstDev Cloud] |- Minio catch ErrorResponseException in [{}].", function, e);
            throw new OssErrorResponseException(e.getMessage());
        } catch (InsufficientDataException e) {
            log.error("[GstDev Cloud] |- Minio catch InsufficientDataException in [{}].", function, e);
            throw new OssInsufficientDataException(e.getMessage());
        } catch (InternalException e) {
            log.error("[GstDev Cloud] |- Minio catch InternalException in [{}].", function, e);
            throw new OssInternalException(e.getMessage());
        } catch (InvalidKeyException e) {
            log.error("[GstDev Cloud] |- Minio catch InvalidKeyException in [{}].", function, e);
            throw new OssInvalidKeyException(e.getMessage());
        } catch (InvalidResponseException e) {
            log.error("[GstDev Cloud] |- Minio catch InvalidResponseException in [{}].", function, e);
            throw new OssInvalidResponseException(e.getMessage());
        } catch (IOException e) {
            log.error("[GstDev Cloud] |- Minio catch IOException in [{}].", function, e);
            if (e instanceof ConnectException) {
                throw new OssConnectException(e.getMessage());
            } else {
                throw new OssIOException(e.getMessage());
            }
        } catch (NoSuchAlgorithmException e) {
            log.error("[GstDev Cloud] |- Minio catch NoSuchAlgorithmException in [{}].", function, e);
            throw new OssNoSuchAlgorithmException(e.getMessage());
        } catch (ServerException e) {
            log.error("[GstDev Cloud] |- Minio catch ServerException in [{}].", function, e);
            throw new OssServerException(e.getMessage());
        } catch (XmlParserException e) {
            log.error("[GstDev Cloud] |- Minio catch XmlParserException in createBucket.", e);
            throw new OssXmlParserException(e.getMessage());
        } finally {
            close(minioClient);
        }
    }

    /**
     * 删除 Bucket 访问策略
     *
     * @param bucketName 存储桶名称
     */
    public void deleteBucketPolicy(String bucketName) {
        deleteBucketPolicy(bucketName, null);
    }

    /**
     * 删除 Bucket 访问策略
     *
     * @param bucketName 存储桶名称
     * @param region     区域
     */
    public void deleteBucketPolicy(String bucketName, String region) {
        deleteBucketPolicy(DeleteBucketPolicyArgs.builder().bucket(bucketName).region(region).build());
    }

    /**
     * 删除 Bucket 访问策略
     *
     * @param deleteBucketPolicyArgs {@link DeleteBucketPolicyArgs}
     */
    public void deleteBucketPolicy(DeleteBucketPolicyArgs deleteBucketPolicyArgs) {
        String function = "deleteBucketPolicy";
        MinioClient minioClient = getClient();

        try {
            minioClient.deleteBucketPolicy(deleteBucketPolicyArgs);
        } catch (ErrorResponseException e) {
            log.error("[GstDev Cloud] |- Minio catch ErrorResponseException in [{}].", function, e);
            throw new OssErrorResponseException(e.getMessage());
        } catch (InsufficientDataException e) {
            log.error("[GstDev Cloud] |- Minio catch InsufficientDataException in [{}].", function, e);
            throw new OssInsufficientDataException(e.getMessage());
        } catch (InternalException e) {
            log.error("[GstDev Cloud] |- Minio catch InternalException in [{}].", function, e);
            throw new OssInternalException(e.getMessage());
        } catch (InvalidKeyException e) {
            log.error("[GstDev Cloud] |- Minio catch InvalidKeyException in [{}].", function, e);
            throw new OssInvalidKeyException(e.getMessage());
        } catch (InvalidResponseException e) {
            log.error("[GstDev Cloud] |- Minio catch InvalidResponseException in [{}].", function, e);
            throw new OssInvalidResponseException(e.getMessage());
        } catch (IOException e) {
            log.error("[GstDev Cloud] |- Minio catch IOException in [{}].", function, e);
            if (e instanceof ConnectException) {
                throw new OssConnectException(e.getMessage());
            } else {
                throw new OssIOException(e.getMessage());
            }
        } catch (NoSuchAlgorithmException e) {
            log.error("[GstDev Cloud] |- Minio catch NoSuchAlgorithmException in [{}].", function, e);
            throw new OssNoSuchAlgorithmException(e.getMessage());
        } catch (ServerException e) {
            log.error("[GstDev Cloud] |- Minio catch ServerException in [{}].", function, e);
            throw new OssServerException(e.getMessage());
        } catch (XmlParserException e) {
            log.error("[GstDev Cloud] |- Minio catch XmlParserException in createBucket.", e);
            throw new OssXmlParserException(e.getMessage());
        } finally {
            close(minioClient);
        }
    }
}
