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
import io.minio.admin.MinioAdminClient;
import io.minio.admin.messages.DataUsageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.ConnectException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * <p>Description: Minio 管理服务 </p>
 *
 * @author : cc
 * @date : 2023/6/25 10:45
 */
@Service
public class MinioAdminService extends BaseMinioAdminService {

    private static final Logger log = LoggerFactory.getLogger(MinioAdminService.class);

    public MinioAdminService(MinioAdminClientObjectPool minioAdminClientObjectPool) {
        super(minioAdminClientObjectPool);
    }

    /**
     * 获取服务器/群集数据使用情况信息
     *
     * @return {@link DataUsageInfo}
     */
    public DataUsageInfo getDataUsageInfo() {
        String function = "getDataUsageInfo";

        MinioAdminClient minioAdminClient = getClient();

        try {
            return minioAdminClient.getDataUsageInfo();
        } catch (NoSuchAlgorithmException e) {
            log.error("[Herodotus] |- Minio catch NoSuchAlgorithmException in [{}].", function, e);
            throw new OssNoSuchAlgorithmException(e.getMessage());
        } catch (InvalidKeyException e) {
            log.error("[Herodotus] |- Minio catch InvalidKeyException in [{}].", function, e);
            throw new OssInvalidKeyException(e.getMessage());
        } catch (IOException e) {
            log.error("[Herodotus] |- Minio catch IOException in [{}].", function, e);
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
