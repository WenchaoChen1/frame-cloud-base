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
 * <p>Description: OssInvalidKeyException </p>
 *
 * @author : cc
 * @date : 2021/11/8 14:32
 */
public class OssInvalidKeyException extends PlatformRuntimeException {

    public OssInvalidKeyException() {
        super();
    }

    public OssInvalidKeyException(String message) {
        super(message);
    }

    public OssInvalidKeyException(String message, Throwable cause) {
        super(message, cause);
    }

    public OssInvalidKeyException(Throwable cause) {
        super(cause);
    }

    protected OssInvalidKeyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public Feedback getFeedback() {
        return OssErrorCodes.OSS_INVALID_KEY;
    }
}
