package com.gstdev.cloud.commons.exception;

import com.gstdev.cloud.commons.constant.ErrorCodes;
import com.gstdev.cloud.commons.domain.Feedback;

/**
 * <p>Description: 平台基础Exception </p>
 *
 * @author : cc
 * @date : 2019/12/18 15:31
 */
public class PlatformRuntimeException extends AbstractRuntimeException {

    public PlatformRuntimeException() {
        super();
    }

    public PlatformRuntimeException(String message) {
        super(message);
    }

    public PlatformRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public PlatformRuntimeException(Throwable cause) {
        super(cause);
    }

    protected PlatformRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public Feedback getFeedback() {
        return ErrorCodes.INTERNAL_SERVER_ERROR;
    }
}
