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
 * <p>Description: 存储桶访问策略过大错误 </p>
 *
 * @author : cc
 * @date : 2023/6/7 21:36
 */
public class OssBucketPolicyTooLargeException extends PlatformRuntimeException {

    public OssBucketPolicyTooLargeException() {
        super();
    }

    public OssBucketPolicyTooLargeException(String message) {
        super(message);
    }

    public OssBucketPolicyTooLargeException(String message, Throwable cause) {
        super(message, cause);
    }

    public OssBucketPolicyTooLargeException(Throwable cause) {
        super(cause);
    }

    protected OssBucketPolicyTooLargeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public Feedback getFeedback() {
        return OssErrorCodes.OSS_BUCKET_POLICY_TOO_LARGE;
    }
}
