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
import io.minio.DeleteBucketNotificationArgs;
import io.minio.GetBucketNotificationArgs;
import io.minio.MinioClient;
import io.minio.SetBucketNotificationArgs;
import io.minio.errors.*;
import io.minio.messages.NotificationConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.ConnectException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * <p>Description: Bucket 通知配置 </p>
 *
 * @author : cc
 * @date : 2022/6/30 15:42
 */
@Service
public class MinioBucketNotificationService extends BaseMinioService {

    private static final Logger log = LoggerFactory.getLogger(MinioBucketNotificationService.class);

    public MinioBucketNotificationService(MinioClientObjectPool minioClientObjectPool) {
        super(minioClientObjectPool);
    }

    /**
     * 设置 Bucket 通知
     *
     * @param bucketName                bucketName
     * @param notificationConfiguration {@link NotificationConfiguration}
     */
    public void setBucketNotification(String bucketName, NotificationConfiguration notificationConfiguration) {
        setBucketNotification(SetBucketNotificationArgs.builder().bucket(bucketName).config(notificationConfiguration).build());
    }

    /**
     * 设置 Bucket 通知
     *
     * @param bucketName                bucketName
     * @param region                    region
     * @param notificationConfiguration {@link NotificationConfiguration}
     */
    public void setBucketNotification(String bucketName, String region, NotificationConfiguration notificationConfiguration) {
        setBucketNotification(SetBucketNotificationArgs.builder().bucket(bucketName).region(region).config(notificationConfiguration).build());
    }


    /**
     * 设置 Bucket 通知
     *
     * @param setBucketNotificationArgs {@link SetBucketNotificationArgs}
     */
    public void setBucketNotification(SetBucketNotificationArgs setBucketNotificationArgs) {
        String function = "setBucketNotification";
        MinioClient minioClient = getClient();

        try {
            minioClient.setBucketNotification(setBucketNotificationArgs);
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
     * 获取 Bucket 通知配置
     *
     * @param bucketName bucketName
     * @return {@link  NotificationConfiguration}
     */
    public NotificationConfiguration getBucketNotification(String bucketName) {
        return getBucketNotification(GetBucketNotificationArgs.builder().bucket(bucketName).build());
    }

    /**
     * 获取 Bucket 通知配置
     *
     * @param bucketName bucketName
     * @param region     region
     * @return {@link  NotificationConfiguration}
     */
    public NotificationConfiguration getBucketNotification(String bucketName, String region) {
        return getBucketNotification(GetBucketNotificationArgs.builder().bucket(bucketName).region(region).build());
    }

    /**
     * 获取 Bucket 通知配置
     *
     * @param getBucketNotificationArgs {@link GetBucketNotificationArgs}
     * @return {@link  NotificationConfiguration}
     */
    public NotificationConfiguration getBucketNotification(GetBucketNotificationArgs getBucketNotificationArgs) {
        String function = "getBucketNotification";
        MinioClient minioClient = getClient();

        try {
            return minioClient.getBucketNotification(getBucketNotificationArgs);
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
     * 删除 Bucket 通知配置
     *
     * @param bucketName bucketName
     */
    public void deleteBucketNotification(String bucketName) {
        deleteBucketNotification(DeleteBucketNotificationArgs.builder().bucket(bucketName).build());
    }

    /**
     * 删除 Bucket 通知配置
     *
     * @param bucketName bucketName
     * @param region     region
     */
    public void deleteBucketNotification(String bucketName, String region) {
        deleteBucketNotification(DeleteBucketNotificationArgs.builder().bucket(bucketName).region(region).build());
    }

    /**
     * 删除 Bucket 通知配置
     *
     * @param deleteBucketNotificationArgs {@link DeleteBucketNotificationArgs}
     */
    public void deleteBucketNotification(DeleteBucketNotificationArgs deleteBucketNotificationArgs) {
        String function = "deleteBucketNotification";
        MinioClient minioClient = getClient();

        try {
            minioClient.deleteBucketNotification(deleteBucketNotificationArgs);
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
