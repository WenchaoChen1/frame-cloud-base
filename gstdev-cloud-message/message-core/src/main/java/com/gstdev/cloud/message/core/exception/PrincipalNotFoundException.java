package com.gstdev.cloud.message.core.exception;

import com.gstdev.cloud.commons.ass.definition.domain.Feedback;
import com.gstdev.cloud.commons.ass.definition.exception.PlatformRuntimeException;
import com.gstdev.cloud.message.core.constants.MessageErrorCodes;

/**
 * <p>Description: 无法找到 Principal 错误 </p>
 *
 * @author : cc
 * @date : 2021/10/24 18:45
 */
public class PrincipalNotFoundException extends PlatformRuntimeException {

    public PrincipalNotFoundException() {
        super();
    }

    public PrincipalNotFoundException(String message) {
        super(message);
    }

    public PrincipalNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public PrincipalNotFoundException(Throwable cause) {
        super(cause);
    }

    protected PrincipalNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public Feedback getFeedback() {
        return MessageErrorCodes.PRINCIPAL_NOT_FOUND;
    }
}
