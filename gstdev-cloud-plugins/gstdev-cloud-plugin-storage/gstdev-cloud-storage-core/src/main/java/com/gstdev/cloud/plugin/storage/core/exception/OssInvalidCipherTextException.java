// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.core.exception;

import com.gstdev.cloud.base.definition.domain.Feedback;
import com.gstdev.cloud.base.definition.exception.PlatformRuntimeException;
import com.gstdev.cloud.plugin.storage.core.constants.OssErrorCodes;
/**
 * <p>Description: Minio 无效密码文本错误 </p>
 *
 * @author : cc
 * @date : 2023/6/25 11:02
 */
public class OssInvalidCipherTextException extends PlatformRuntimeException {

    public OssInvalidCipherTextException() {
        super();
    }

    public OssInvalidCipherTextException(String message) {
        super(message);
    }

    public OssInvalidCipherTextException(String message, Throwable cause) {
        super(message, cause);
    }

    public OssInvalidCipherTextException(Throwable cause) {
        super(cause);
    }

    protected OssInvalidCipherTextException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public Feedback getFeedback() {
        return OssErrorCodes.OSS_INVALID_CIPHER_TEXT;
    }
}
