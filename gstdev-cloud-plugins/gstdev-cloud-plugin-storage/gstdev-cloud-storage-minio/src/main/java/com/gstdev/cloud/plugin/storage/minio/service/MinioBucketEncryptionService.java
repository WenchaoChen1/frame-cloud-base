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
import io.minio.DeleteBucketEncryptionArgs;
import io.minio.GetBucketEncryptionArgs;
import io.minio.MinioClient;
import io.minio.SetBucketEncryptionArgs;
import io.minio.errors.*;
import io.minio.messages.SseConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.ConnectException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * <p>Description: Bucket 加密服务 </p>
 *
 * @author : cc
 * @date : 2022/6/30 15:31
 */
@Service
public class MinioBucketEncryptionService extends BaseMinioService {

    private static final Logger log = LoggerFactory.getLogger(MinioBucketEncryptionService.class);

    public MinioBucketEncryptionService(MinioClientObjectPool minioClientObjectPool) {
        super(minioClientObjectPool);
    }

    /**
     * 获取 Bucket 加密配置
     *
     * @param bucketName 存储桶名称
     * @return 自定义 SseConfiguration 枚举 {@link SseConfiguration}
     */
    public SseConfiguration getBucketEncryption(String bucketName) {
        return getBucketEncryption(bucketName, null);
    }

    /**
     * 获取 Bucket 加密配置
     *
     * @param bucketName 存储桶名称
     * @param region     区域
     * @return 自定义 SseConfiguration 枚举 {@link SseConfiguration}
     */
    public SseConfiguration getBucketEncryption(String bucketName, String region) {
        return getBucketEncryption(GetBucketEncryptionArgs.builder().bucket(bucketName).region(region).build());
    }

    /**
     * 获取 Bucket 加密配置
     *
     * @param getBucketEncryptionArgs {@link GetBucketEncryptionArgs}
     */
    public SseConfiguration getBucketEncryption(GetBucketEncryptionArgs getBucketEncryptionArgs) {
        String function = "getBucketEncryption";
        MinioClient minioClient = getClient();

        try {
            return minioClient.getBucketEncryption(getBucketEncryptionArgs);
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
        } finally {
            close(minioClient);
        }
    }

    /**
     * 设置 Bucket 加密
     *
     * @param bucketName 存储桶名称
     * @param config     加密配置 {@link SseConfiguration}
     */
    public void setBucketEncryption(String bucketName, SseConfiguration config) {
        setBucketEncryption(bucketName, null, config);
    }

    /**
     * 设置 Bucket 加密
     *
     * @param bucketName 存储桶名称
     * @param region     区域
     * @param config     加密配置 {@link SseConfiguration}
     */
    public void setBucketEncryption(String bucketName, String region, SseConfiguration config) {
        setBucketEncryption(SetBucketEncryptionArgs.builder().bucket(bucketName).region(region).config(config).build());
    }


    /**
     * 设置 Bucket 加密
     *
     * @param setBucketEncryptionArgs {@link SetBucketEncryptionArgs}
     */
    public void setBucketEncryption(SetBucketEncryptionArgs setBucketEncryptionArgs) {
        String function = "setBucketEncryption";
        MinioClient minioClient = getClient();

        try {
            minioClient.setBucketEncryption(setBucketEncryptionArgs);
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
        } finally {
            close(minioClient);
        }
    }

    /**
     * 删除 Bucket 加密配置
     *
     * @param bucketName 存储桶名称
     */
    public void deleteBucketEncryption(String bucketName) {
        deleteBucketEncryption(bucketName, null);
    }

    /**
     * 删除 Bucket 加密配置
     *
     * @param bucketName 存储桶名称
     * @param region     区域
     */
    public void deleteBucketEncryption(String bucketName, String region) {
        deleteBucketEncryption(DeleteBucketEncryptionArgs.builder().bucket(bucketName).region(region).build());
    }

    /**
     * 删除 Bucket 加密配置
     *
     * @param deleteBucketEncryptionArgs {@link DeleteBucketEncryptionArgs}
     */
    public void deleteBucketEncryption(DeleteBucketEncryptionArgs deleteBucketEncryptionArgs) {
        String function = "deleteBucketEncryption";
        MinioClient minioClient = getClient();

        try {
            minioClient.deleteBucketEncryption(deleteBucketEncryptionArgs);
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
        } finally {
            close(minioClient);
        }
    }


}
