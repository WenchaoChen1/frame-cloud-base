// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.minio.converter.sse;

import com.gstdev.cloud.plugin.storage.core.exception.OssInvalidKeyException;
import com.gstdev.cloud.plugin.storage.core.exception.OssNoSuchAlgorithmException;
import io.minio.ServerSideEncryptionCustomerKey;
import org.apache.commons.lang3.StringUtils;
import org.dromara.hutool.crypto.KeyUtil;
import org.dromara.hutool.crypto.symmetric.SymmetricAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;

import javax.crypto.SecretKey;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * <p>Description: Minio Request 转 ServerSideEncryptionCustomerKey 转换器 </p>
 *
 * @author : cc
 * @date : 2023/6/9 12:21
 */
public class RequestToServerSideEncryptionCustomerKeyConverter implements Converter<String, ServerSideEncryptionCustomerKey> {

    private static final Logger log = LoggerFactory.getLogger(RequestToServerSideEncryptionCustomerKeyConverter.class);

    @Override
    public ServerSideEncryptionCustomerKey convert(String customerKey) {
        if (StringUtils.isNotBlank(customerKey)) {
            SecretKey secretKey = KeyUtil.generateKey(SymmetricAlgorithm.AES.getValue(), 256);

            try {
                return new ServerSideEncryptionCustomerKey(secretKey);
            } catch (InvalidKeyException e) {
                log.error("[Herodotus] |- Minio catch InvalidKeyException in ObjectReadRequest prepare.", e);
                throw new OssInvalidKeyException(e.getMessage());
            } catch (NoSuchAlgorithmException e) {
                log.error("[Herodotus] |- Minio catch NoSuchAlgorithmException in ObjectReadRequest prepare.", e);
                throw new OssNoSuchAlgorithmException(e.getMessage());
            }
        }

        return null;
    }
}
