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
 * <p>Description: OssExecutionException </p>
 *
 * @author : cc
 * @date : 2022/7/4 11:08
 */
public class OssExecutionException extends PlatformRuntimeException {

    public OssExecutionException() {
        super();
    }

    public OssExecutionException(String message) {
        super(message);
    }

    public OssExecutionException(String message, Throwable cause) {
        super(message, cause);
    }

    public OssExecutionException(Throwable cause) {
        super(cause);
    }

    protected OssExecutionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public Feedback getFeedback() {
        return OssErrorCodes.OSS_EXECUTION;
    }
}
