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
import com.gstdev.cloud.plugin.storage.minio.converter.retention.RetentionToDomainConverter;
import com.gstdev.cloud.plugin.storage.minio.definition.pool.MinioClientObjectPool;
import com.gstdev.cloud.plugin.storage.minio.definition.service.BaseMinioService;
import com.gstdev.cloud.plugin.storage.minio.domain.RetentionDomain;
import io.minio.GetObjectRetentionArgs;
import io.minio.MinioClient;
import io.minio.SetObjectRetentionArgs;
import io.minio.errors.*;
import io.minio.messages.Retention;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.ConnectException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * <p>Description: Minio 对象保留配置 </p>
 *
 * @author : cc
 * @date : 2022/6/30 21:08
 */
@Service
public class MinioObjectRetentionService extends BaseMinioService {

    private static final Logger log = LoggerFactory.getLogger(MinioObjectRetentionService.class);
    private final Converter<Retention, RetentionDomain> toDo;

    public MinioObjectRetentionService(MinioClientObjectPool minioClientObjectPool) {
        super(minioClientObjectPool);
        this.toDo = new RetentionToDomainConverter();
    }

    /**
     * 获取对象的保留配置
     *
     * @param bucketName 存储桶名称
     * @param objectName 对象名称
     * @return 自定义保留域对象
     */
    public RetentionDomain getObjectRetention(String bucketName, String objectName) {
        return getObjectRetention(bucketName, null, objectName);
    }

    /**
     * 获取对象的保留配置
     *
     * @param bucketName 存储桶名称
     * @param region     区域
     * @param objectName 对象名称
     * @return 自定义保留域对象
     */
    public RetentionDomain getObjectRetention(String bucketName, String region, String objectName) {
        return getObjectRetention(bucketName, region, objectName, null);
    }

    /**
     * 获取对象的保留配置
     *
     * @param bucketName 存储桶名称
     * @param region     区域
     * @param objectName 对象名称
     * @param versionId  版本ID
     * @return 自定义保留域对象
     */
    public RetentionDomain getObjectRetention(String bucketName, String region, String objectName, String versionId) {
        return getObjectRetention(GetObjectRetentionArgs.builder().bucket(bucketName).region(region).object(objectName).versionId(versionId).build());
    }

    /**
     * 获取对象的保留配置
     *
     * @param getObjectRetentionArgs {@link GetObjectRetentionArgs}
     * @return {@link RetentionDomain}
     */
    public RetentionDomain getObjectRetention(GetObjectRetentionArgs getObjectRetentionArgs) {
        String function = "getObjectRetention";
        MinioClient minioClient = getClient();

        try {
            Retention retention = minioClient.getObjectRetention(getObjectRetentionArgs);
            return toDo.convert(retention);
        } catch (ErrorResponseException e) {
            log.error("[Herodotus] |- Minio catch ErrorResponseException in [{}].", function, e);
            throw new OssErrorResponseException(e.getMessage());
        } catch (InsufficientDataException e) {
            log.error("[Herodotus] |- Minio catch InsufficientDataException in [{}].", function, e);
            throw new OssInsufficientDataException(e.getMessage());
        } catch (InternalException e) {
            log.error("[Herodotus] |- Minio catch InternalException in [{}].", function, e);
            throw new OssInternalException(e.getMessage());
        } catch (InvalidKeyException e) {
            log.error("[Herodotus] |- Minio catch InvalidKeyException in [{}].", function, e);
            throw new OssInvalidKeyException(e.getMessage());
        } catch (InvalidResponseException e) {
            log.error("[Herodotus] |- Minio catch InvalidResponseException in [{}].", function, e);
            throw new OssInvalidResponseException(e.getMessage());
        } catch (IOException e) {
            log.error("[Herodotus] |- Minio catch IOException in [{}].", function, e);
            if (e instanceof ConnectException) {
                throw new OssConnectException(e.getMessage());
            } else {
                throw new OssIOException(e.getMessage());
            }
        } catch (NoSuchAlgorithmException e) {
            log.error("[Herodotus] |- Minio catch NoSuchAlgorithmException in [{}].", function, e);
            throw new OssNoSuchAlgorithmException(e.getMessage());
        } catch (ServerException e) {
            log.error("[Herodotus] |- Minio catch ServerException in [{}].", function, e);
            throw new OssServerException(e.getMessage());
        } catch (XmlParserException e) {
            log.error("[Herodotus] |- Minio catch XmlParserException in [{}].", function, e);
            throw new OssXmlParserException(e.getMessage());
        } finally {
            close(minioClient);
        }
    }

    /**
     * 添加对象的保留配置，存储桶需要设置为对象锁定模式，并且没有开启版本控制，否则会报错收蠕虫保护。
     *
     * @param bucketName 存储桶名称
     * @param objectName 对象名称
     * @param config     保留配置 {@link Retention}
     */
    public void setObjectRetention(String bucketName, String objectName, Retention config) {
        setObjectRetention(bucketName, objectName, config, false);
    }

    /**
     * 添加对象的保留配置，存储桶需要设置为对象锁定模式，并且没有开启版本控制，否则会报错收蠕虫保护。
     *
     * @param bucketName           存储桶名称
     * @param objectName           对象名称
     * @param config               保留配置 {@link Retention}
     * @param bypassGovernanceMode 使用 Governance 模式
     */
    public void setObjectRetention(String bucketName, String objectName, Retention config, boolean bypassGovernanceMode) {
        setObjectRetention(bucketName, null, objectName, config, bypassGovernanceMode);
    }

    /**
     * 添加对象的保留配置，存储桶需要设置为对象锁定模式，并且没有开启版本控制，否则会报错收蠕虫保护。
     *
     * @param bucketName           存储桶名称
     * @param region               区域
     * @param objectName           对象名称
     * @param config               保留配置 {@link Retention}
     * @param bypassGovernanceMode 使用 Governance 模式
     */
    public void setObjectRetention(String bucketName, String region, String objectName, Retention config, boolean bypassGovernanceMode) {
        setObjectRetention(bucketName, region, objectName, config, bypassGovernanceMode, null);
    }

    /**
     * 添加对象的保留配置，存储桶需要设置为对象锁定模式，并且没有开启版本控制，否则会报错收蠕虫保护。
     *
     * @param bucketName           存储桶名称
     * @param region               区域
     * @param objectName           对象名称
     * @param config               保留配置 {@link Retention}
     * @param bypassGovernanceMode 使用 Governance 模式
     * @param versionId            版本ID
     */
    public void setObjectRetention(String bucketName, String region, String objectName, Retention config, boolean bypassGovernanceMode, String versionId) {
        setObjectRetention(SetObjectRetentionArgs.builder()
                .bucket(bucketName)
                .region(region)
                .object(objectName)
                .config(config)
                .bypassGovernanceMode(bypassGovernanceMode)
                .versionId(versionId)
                .build());
    }

    /**
     * 添加对象的保留配置，存储桶需要设置为对象锁定模式，并且没有开启版本控制，否则会报错收蠕虫保护。
     *
     * @param setObjectRetentionArgs {@link SetObjectRetentionArgs}
     */
    public void setObjectRetention(SetObjectRetentionArgs setObjectRetentionArgs) {
        String function = "setObjectRetention";
        MinioClient minioClient = getClient();

        try {
            minioClient.setObjectRetention(setObjectRetentionArgs);
        } catch (ErrorResponseException e) {
            log.error("[Herodotus] |- Minio catch ErrorResponseException in [{}].", function, e);
            throw new OssErrorResponseException(e.getMessage());
        } catch (InsufficientDataException e) {
            log.error("[Herodotus] |- Minio catch InsufficientDataException in [{}].", function, e);
            throw new OssInsufficientDataException(e.getMessage());
        } catch (InternalException e) {
            log.error("[Herodotus] |- Minio catch InternalException in [{}].", function, e);
            throw new OssInternalException(e.getMessage());
        } catch (InvalidKeyException e) {
            log.error("[Herodotus] |- Minio catch InvalidKeyException in [{}].", function, e);
            throw new OssInvalidKeyException(e.getMessage());
        } catch (InvalidResponseException e) {
            log.error("[Herodotus] |- Minio catch InvalidResponseException in [{}].", function, e);
            throw new OssInvalidResponseException(e.getMessage());
        } catch (IOException e) {
            log.error("[Herodotus] |- Minio catch IOException in [{}].", function, e);
            if (e instanceof ConnectException) {
                throw new OssConnectException(e.getMessage());
            } else {
                throw new OssIOException(e.getMessage());
            }
        } catch (NoSuchAlgorithmException e) {
            log.error("[Herodotus] |- Minio catch NoSuchAlgorithmException in [{}].", function, e);
            throw new OssNoSuchAlgorithmException(e.getMessage());
        } catch (ServerException e) {
            log.error("[Herodotus] |- Minio catch ServerException in [{}].", function, e);
            throw new OssServerException(e.getMessage());
        } catch (XmlParserException e) {
            log.error("[Herodotus] |- Minio catch XmlParserException in [{}].", function, e);
            throw new OssXmlParserException(e.getMessage());
        } finally {
            close(minioClient);
        }
    }
}
