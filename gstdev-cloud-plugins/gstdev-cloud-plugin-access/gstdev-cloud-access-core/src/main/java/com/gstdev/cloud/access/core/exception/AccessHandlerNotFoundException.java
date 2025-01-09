

package com.gstdev.cloud.access.core.exception;

import com.gstdev.cloud.access.core.constants.AccessErrorCodes;
import com.gstdev.cloud.base.definition.domain.Feedback;
import com.gstdev.cloud.base.definition.exception.PlatformRuntimeException;

/**
 * <p>Description: 接入处理器未找到错误 </p>
 *
 * @author : cc
 * @date : 2022/1/26 12:03
 */
public class AccessHandlerNotFoundException extends PlatformRuntimeException {

    public AccessHandlerNotFoundException() {
        super();
    }

    public AccessHandlerNotFoundException(String message) {
        super(message);
    }

    public AccessHandlerNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccessHandlerNotFoundException(Throwable cause) {
        super(cause);
    }

    public AccessHandlerNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public Feedback getFeedback() {
        return AccessErrorCodes.ACCESS_HANDLER_NOT_FOUND;
    }
}
