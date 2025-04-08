// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.minio.domain;

import com.gstdev.cloud.base.definition.domain.base.Entity;
import com.gstdev.cloud.plugin.storage.minio.enums.ServerSideEncryptionEnums;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;

/**
 * <p>Description: 服务端加密域对象 </p>
 *
 * @author : cc
 * @date : 2023/6/9 12:39
 */
public class ServerSideEncryptionDomain implements Entity {

    @Schema(name = "服务端加密方式类型", description = "1:SSE_KMS, 2:SSE_S3, 3: 自定义")
    private ServerSideEncryptionEnums type;

    @Schema(name = "自定义服务端加密方式加密Key", description = "Minio 默认仅支持 256 位 AES")
    private String customerKey;

    @Schema(name = "KMS加密MasterKeyId", description = "可选参数，主要用于AWS_KMS加密算法")
    private String keyId;

    @Schema(name = "KMS加密context", description = "可选参数，主要用于AWS_KMS加密算法")
    private Map<String, String> context;

    public ServerSideEncryptionEnums getType() {
        return type;
    }

    public void setType(ServerSideEncryptionEnums type) {
        this.type = type;
    }

    public String getCustomerKey() {
        return customerKey;
    }

    public void setCustomerKey(String customerKey) {
        this.customerKey = customerKey;
    }

    public String getKeyId() {
        return keyId;
    }

    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }

    public Map<String, String> getContext() {
        return context;
    }

    public void setContext(Map<String, String> context) {
        this.context = context;
    }
}
