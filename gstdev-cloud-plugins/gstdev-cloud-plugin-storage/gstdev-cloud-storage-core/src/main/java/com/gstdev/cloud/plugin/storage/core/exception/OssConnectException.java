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
 * <p>Description: Minio 服务器连接失败 </p>
 *
 * @author : cc
 * @date : 2023/6/6 12:47
 */
public class OssConnectException extends PlatformRuntimeException {

    public OssConnectException() {
        super();
    }

    public OssConnectException(String message) {
        super(message);
    }

    public OssConnectException(String message, Throwable cause) {
        super(message, cause);
    }

    public OssConnectException(Throwable cause) {
        super(cause);
    }

    protected OssConnectException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public Feedback getFeedback() {
        return OssErrorCodes.OSS_CONNECTION;
    }
}
