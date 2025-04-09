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
import io.minio.admin.GroupInfo;
import io.minio.admin.MinioAdminClient;
import io.minio.admin.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.IOException;
import java.net.ConnectException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * <p>Description: Minio Group 管理 Service </p>
 *
 * @author : cc
 * @date : 2023/6/25 14:01
 */
@Service
public class MinioAdminGroupService extends BaseMinioAdminService {

    private static final Logger log = LoggerFactory.getLogger(MinioAdminGroupService.class);

    public MinioAdminGroupService(MinioAdminClientObjectPool minioAdminClientObjectPool) {
        super(minioAdminClientObjectPool);
    }

    /**
     * 获取所有MinIO组的列表
     *
     * @return 组列表
     */
    public List<String> listGroups() {
        String function = "listGroups";

        MinioAdminClient minioAdminClient = getClient();

        try {
            return minioAdminClient.listGroups();
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
     * 获取指定MinIO组的组信息
     *
     * @param group 组
     * @return 组信息
     */
    public GroupInfo getGroupInfo(String group) {
        String function = "getGroupInfo";

        MinioAdminClient minioAdminClient = getClient();

        try {
            return minioAdminClient.getGroupInfo(group);
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
     * 添加或者更新 Group
     *
     * @param group       用户组名称
     * @param groupStatus 用户组状态
     * @param members     组内成员
     */
    public void addUpdateGroup(@Nonnull String group, @Nullable Status groupStatus, @Nullable List<String> members) {
        String function = "addUpdateGroup";

        MinioAdminClient minioAdminClient = getClient();

        try {
            minioAdminClient.addUpdateGroup(group, groupStatus, members);
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
     * 移除组
     *
     * @param group 组名称
     */
    public void removeGroup(@Nonnull String group) {
        String function = "removeGroup";

        MinioAdminClient minioAdminClient = getClient();

        try {
            minioAdminClient.removeGroup(group);
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
