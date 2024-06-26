package com.gstdev.cloud.rest.core.exception;

import com.gstdev.cloud.base.definition.domain.Feedback;
import com.gstdev.cloud.base.definition.exception.PlatformRuntimeException;
import com.gstdev.cloud.rest.core.constants.RestErrorCodes;

/**
 * <p>Description: Session 不可用错误 </p>
 *
 * @author : cc
 * @date : 2021/10/4 16:46
 */
public class SessionInvalidException extends PlatformRuntimeException {

    public SessionInvalidException() {
        super();
    }

    public SessionInvalidException(String message) {
        super(message);
    }

    public SessionInvalidException(String message, Throwable cause) {
        super(message, cause);
    }

    public SessionInvalidException(Throwable cause) {
        super(cause);
    }

    public SessionInvalidException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public Feedback getFeedback() {
        return RestErrorCodes.SESSION_INVALID;
    }
}
