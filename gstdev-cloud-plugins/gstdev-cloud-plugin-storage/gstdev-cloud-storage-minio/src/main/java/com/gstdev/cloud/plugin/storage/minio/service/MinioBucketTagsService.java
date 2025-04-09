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
import io.minio.DeleteBucketTagsArgs;
import io.minio.GetBucketTagsArgs;
import io.minio.MinioClient;
import io.minio.SetBucketTagsArgs;
import io.minio.errors.*;
import io.minio.messages.Tags;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.ConnectException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * <p>Description: Bucket 标签服务 </p>
 * 当为桶添加标签时，该桶上所有请求产生的计费话单里都会带上这些标签，从而可以针对话单报表做分类筛选，进行更详细的成本分析。例如：某个应用程序在运行过程会往桶里上传数据，我们可以用应用名称作为标签，设置到被使用的桶上。在分析话单时，就可以通过应用名称的标签来分析此应用的成本
 *
 * @author : cc
 * @date : 2022/6/30 15:58
 */
@Service
public class MinioBucketTagsService extends BaseMinioService {

    private static final Logger log = LoggerFactory.getLogger(MinioBucketPolicyService.class);

    public MinioBucketTagsService(MinioClientObjectPool minioClientObjectPool) {
        super(minioClientObjectPool);
    }

    /**
     * 获取 Bucket 标签配置
     *
     * @param bucketName 存储桶名称
     * @return {@link Tags}
     */
    public Tags getBucketTags(String bucketName) {
        return getBucketTags(bucketName, null);
    }

    /**
     * 获取 Bucket 标签配置
     *
     * @param bucketName 存储桶名称
     * @param region     区域
     * @return {@link Tags}
     */
    public Tags getBucketTags(String bucketName, String region) {
        return getBucketTags(GetBucketTagsArgs.builder().bucket(bucketName).region(region).build());
    }

    /**
     * 获取 Bucket 标签配置
     *
     * @param getBucketTagsArgs {@link GetBucketTagsArgs}
     * @return {@link Tags}
     */
    public Tags getBucketTags(GetBucketTagsArgs getBucketTagsArgs) {
        String function = "getBucketTags";
        MinioClient minioClient = getClient();

        try {
            return minioClient.getBucketTags(getBucketTagsArgs);
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
     * 设置 Bucket 标签
     *
     * @param bucketName 存储桶名称
     * @param tags       标签
     */
    public void setBucketTags(String bucketName, Tags tags) {
        setBucketTags(bucketName, null, tags);
    }

    /**
     * 设置 Bucket 标签
     *
     * @param bucketName 存储桶名称
     * @param region     区域
     * @param tags       标签
     */
    public void setBucketTags(String bucketName, String region, Tags tags) {
        setBucketTags(SetBucketTagsArgs.builder().bucket(bucketName).region(region).tags(tags).build());
    }

    /**
     * 设置 Bucket 标签
     *
     * @param setBucketTagsArgs {@link SetBucketTagsArgs}
     */
    public void setBucketTags(SetBucketTagsArgs setBucketTagsArgs) {
        String function = "setBucketTags";
        MinioClient minioClient = getClient();

        try {
            minioClient.setBucketTags(setBucketTagsArgs);
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
     * 删除 Bucket 标签配置
     *
     * @param bucketName 存储桶名称
     */
    public void deleteBucketTags(String bucketName) {
        deleteBucketTags(bucketName, null);
    }

    /**
     * 删除 Bucket 标签配置
     *
     * @param bucketName 存储桶名称
     * @param region     区域
     */
    public void deleteBucketTags(String bucketName, String region) {
        deleteBucketTags(DeleteBucketTagsArgs.builder().bucket(bucketName).region(region).build());
    }

    /**
     * 删除 Bucket 标签
     *
     * @param deleteBucketTagsArgs {@link DeleteBucketTagsArgs}
     */
    public void deleteBucketTags(DeleteBucketTagsArgs deleteBucketTagsArgs) {
        String function = "deleteBucketTags";
        MinioClient minioClient = getClient();

        try {
            minioClient.deleteBucketTags(deleteBucketTagsArgs);
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
