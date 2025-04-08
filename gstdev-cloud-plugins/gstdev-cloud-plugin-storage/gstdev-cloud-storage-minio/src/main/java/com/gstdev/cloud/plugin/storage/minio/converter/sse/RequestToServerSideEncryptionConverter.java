// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.minio.converter.sse;

import com.gstdev.cloud.plugin.storage.minio.domain.ServerSideEncryptionDomain;
import com.gstdev.cloud.plugin.storage.minio.enums.ServerSideEncryptionEnums;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.minio.ServerSideEncryption;
import io.minio.ServerSideEncryptionCustomerKey;
import io.minio.ServerSideEncryptionKms;
import io.minio.ServerSideEncryptionS3;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;

/**
 * <p>Description: Minio Request 转 ServerSideEncryption 转换器 </p>
 *
 * @author : cc
 * @date : 2023/6/9 12:45
 */
public class RequestToServerSideEncryptionConverter implements Converter<ServerSideEncryptionDomain, ServerSideEncryption> {

    private static final Logger log = LoggerFactory.getLogger(RequestToServerSideEncryptionConverter.class);
    private final Converter<String, ServerSideEncryptionCustomerKey> toCustomerKey = new RequestToServerSideEncryptionCustomerKeyConverter();

    @Override
    public ServerSideEncryption convert(ServerSideEncryptionDomain source) {

        if (ObjectUtils.isNotEmpty(source.getType())) {
            ServerSideEncryptionEnums type = source.getType();
            switch (type) {
                case CUSTOM -> {
                    if (StringUtils.isNotBlank(source.getCustomerKey())) {
                        return toCustomerKey.convert(source.getCustomerKey());
                    } else {
                        return null;
                    }
                }
                case AWS_KMS -> {
                    if (StringUtils.isNotBlank(source.getKeyId())) {
                        try {
                            return new ServerSideEncryptionKms(source.getKeyId(), source.getContext());
                        } catch (JsonProcessingException e) {
                            log.error("[Herodotus] |- Minio catch JsonProcessingException in RequestToServerSideEncryptionConverter.", e);
                            return null;
                        }
                    }
                }
                default -> {
                    return new ServerSideEncryptionS3();
                }
            }
        }

        return null;
    }
}
