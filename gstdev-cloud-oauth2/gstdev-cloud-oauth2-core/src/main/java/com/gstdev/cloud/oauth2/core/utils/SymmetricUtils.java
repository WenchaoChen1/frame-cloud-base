// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================
package com.gstdev.cloud.oauth2.core.utils;

import com.gstdev.cloud.commons.ass.definition.constants.SymbolConstants;
import com.gstdev.cloud.oauth2.core.exception.IllegalSymmetricKeyException;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.dromara.hutool.core.util.RandomUtil;
import org.dromara.hutool.crypto.SecureUtil;
import org.dromara.hutool.crypto.symmetric.AES;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p> Description : 基于Hutool的Aes加解密工具 </p>
 *
 * @author : cc
 * @date : 2020/1/28 17:25
 */
public class SymmetricUtils {

    private static final Logger log = LoggerFactory.getLogger(SymmetricUtils.class);

    private static String encryptedRealSecretKey(String symmetricKey) {
        String realSecretKey = RandomUtil.randomString(16);
        log.trace("[GstDev Cloud] |- Generate Random Secret Key is : [{}]", realSecretKey);

        AES ase = SecureUtil.aes(symmetricKey.getBytes());
        String encryptedRealSecretKey = ase.encryptHex(realSecretKey);
        log.trace("[GstDev Cloud] |- Generate Encrypt Hex Secret Key is : [{}]", encryptedRealSecretKey);

        return encryptedRealSecretKey;
    }

    public static String getEncryptedSymmetricKey() {
        String symmetricKey = RandomUtil.randomString(16);
        String realSecretKey = encryptedRealSecretKey(symmetricKey);
        log.trace("[GstDev Cloud] |- Generate Symmetric Key is : [{}]", realSecretKey);

        return symmetricKey +
                SymbolConstants.FORWARD_SLASH +
                realSecretKey;
    }

    public static byte[] getDecryptedSymmetricKey(String key) {
        if (!StringUtils.contains(key, SymbolConstants.FORWARD_SLASH)) {
            throw new IllegalSymmetricKeyException("Parameter Illegal!");
        }

        String[] keys = StringUtils.split(key, SymbolConstants.FORWARD_SLASH);
        String symmetricKey = keys[0];
        String realSecretKey = keys[1];

        AES ase = SecureUtil.aes(symmetricKey.getBytes());
        return ase.decrypt(realSecretKey);
    }

    public static String decrypt(String content, byte[] key) {
        if (ArrayUtils.isNotEmpty(key)) {
            AES ase = SecureUtil.aes(key);
            return ase.decryptStr(content);
        }

        return "";
    }
}
