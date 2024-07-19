package com.gstdev.cloud.message.core.exception;

import com.gstdev.cloud.base.definition.domain.Feedback;
import com.gstdev.cloud.base.definition.exception.PlatformRuntimeException;
import com.gstdev.cloud.message.core.constants.MessageErrorCodes;

/**
 * <p>Description: Web Socket Channel 错误 </p>
 *
 * @author : cc
 * @date : 2021/10/24 18:45
 */
public class IllegalChannelException extends PlatformRuntimeException {

    public IllegalChannelException() {
        super();
    }

    public IllegalChannelException(String message) {
        super(message);
    }

    public IllegalChannelException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalChannelException(Throwable cause) {
        super(cause);
    }

    protected IllegalChannelException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public Feedback getFeedback() {
        return MessageErrorCodes.ILLEGAL_CHANNEL;
    }
}
