// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.minio.service;

import com.gstdev.cloud.plugin.storage.core.exception.OssConnectException;
import com.gstdev.cloud.plugin.storage.core.exception.OssIOException;
import com.gstdev.cloud.plugin.storage.core.exception.OssInvalidKeyException;
import com.gstdev.cloud.plugin.storage.core.exception.OssNoSuchAlgorithmException;
import com.gstdev.cloud.plugin.storage.minio.definition.pool.MinioAdminClientObjectPool;
import com.gstdev.cloud.plugin.storage.minio.definition.service.BaseMinioAdminService;
import com.gstdev.cloud.plugin.storage.minio.domain.policy.PolicyDomain;
import io.minio.admin.MinioAdminClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.net.ConnectException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * <p>Description: Minio 屏蔽策略服务 </p>
 *
 * @author : cc
 * @date : 2023/6/25 10:55
 */
@Service
public class MinioAdminPolicyService extends BaseMinioAdminService {

    private static final Logger log = LoggerFactory.getLogger(MinioAdminPolicyService.class);

    public MinioAdminPolicyService(MinioAdminClientObjectPool minioAdminClientObjectPool) {
        super(minioAdminClientObjectPool);
    }

    /**
     * 获取屏蔽策略列表
     *
     * @return 屏蔽策略列表
     */
    public Map<String, String> listCannedPolicies() {
        String function = "listCannedPolicies";

        MinioAdminClient minioAdminClient = getClient();

        try {
            return minioAdminClient.listCannedPolicies();
        } catch (NoSuchAlgorithmException e) {
            log.error("[GstDev Cloud] |- Minio catch NoSuchAlgorithmException in [{}].", function, e);
            throw new OssNoSuchAlgorithmException(e.getMessage());
        } catch (InvalidKeyException e) {
            log.error("[GstDev Cloud] |- Minio catch InvalidKeyException in [{}].", function, e);
            throw new OssInvalidKeyException(e.getMessage());
        } catch (IOException e) {
            log.error("[GstDev Cloud] |- Minio catch IOException in [{}].", function, e);
            if (e instanceof ConnectException) {
                throw new OssConnectException(e.getMessage());
            } else {
                throw new OssIOException(e.getMessage());
            }
        } finally {
            close(minioAdminClient);
        }
    }

    /**
     * 创建屏蔽策略
     *
     * @param name   策略名称
     * @param policy 策略 {@link PolicyDomain}
     */
    public void addCannedPolicy(@Nonnull String name, @Nonnull String policy) {
        String function = "addCannedPolicy";

        MinioAdminClient minioAdminClient = getClient();

        try {
            minioAdminClient.addCannedPolicy(name, policy);
        } catch (NoSuchAlgorithmException e) {
            log.error("[GstDev Cloud] |- Minio catch NoSuchAlgorithmException in [{}].", function, e);
            throw new OssNoSuchAlgorithmException(e.getMessage());
        } catch (InvalidKeyException e) {
            log.error("[GstDev Cloud] |- Minio catch InvalidKeyException in [{}].", function, e);
            throw new OssInvalidKeyException(e.getMessage());
        } catch (IOException e) {
            log.error("[GstDev Cloud] |- Minio catch IOException in [{}].", function, e);
            if (e instanceof ConnectException) {
                throw new OssConnectException(e.getMessage());
            } else {
                throw new OssIOException(e.getMessage());
            }
        } finally {
            close(minioAdminClient);
        }
    }

    /**
     * 移除屏蔽策略
     *
     * @param name 策略名称
     */
    public void removeCannedPolicy(@Nonnull String name) {
        String function = "removeCannedPolicy";

        MinioAdminClient minioAdminClient = getClient();

        try {
            minioAdminClient.removeCannedPolicy(name);
        } catch (NoSuchAlgorithmException e) {
            log.error("[GstDev Cloud] |- Minio catch NoSuchAlgorithmException in [{}].", function, e);
            throw new OssNoSuchAlgorithmException(e.getMessage());
        } catch (InvalidKeyException e) {
            log.error("[GstDev Cloud] |- Minio catch InvalidKeyException in [{}].", function, e);
            throw new OssInvalidKeyException(e.getMessage());
        } catch (IOException e) {
            log.error("[GstDev Cloud] |- Minio catch IOException in [{}].", function, e);
            if (e instanceof ConnectException) {
                throw new OssConnectException(e.getMessage());
            } else {
                throw new OssIOException(e.getMessage());
            }
        } finally {
            close(minioAdminClient);
        }
    }

    /**
     * 设置屏蔽策略
     *
     * @param userOrGroupName 用户名或组名
     * @param isGroup         是否是组
     * @param policyName      策略名称
     */
    public void setPolicy(@Nonnull String userOrGroupName, boolean isGroup, @Nonnull String policyName) {
        String function = "setPolicy";

        MinioAdminClient minioAdminClient = getClient();

        try {
            minioAdminClient.setPolicy(userOrGroupName, isGroup, policyName);
        } catch (NoSuchAlgorithmException e) {
            log.error("[GstDev Cloud] |- Minio catch NoSuchAlgorithmException in [{}].", function, e);
            throw new OssNoSuchAlgorithmException(e.getMessage());
        } catch (InvalidKeyException e) {
            log.error("[GstDev Cloud] |- Minio catch InvalidKeyException in [{}].", function, e);
            throw new OssInvalidKeyException(e.getMessage());
        } catch (IOException e) {
            log.error("[GstDev Cloud] |- Minio catch IOException in [{}].", function, e);
            if (e instanceof ConnectException) {
                throw new OssConnectException(e.getMessage());
            } else {
                throw new OssIOException(e.getMessage());
            }
        } finally {
            close(minioAdminClient);
        }
    }
}
