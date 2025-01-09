

package com.gstdev.cloud.access.core.exception;

import com.gstdev.cloud.access.core.constants.AccessErrorCodes;
import com.gstdev.cloud.base.definition.domain.Feedback;
import com.gstdev.cloud.base.definition.exception.PlatformRuntimeException;

/**
 * <p>Description: Access 配置错误 </p>
 *
 * @author : cc
 * @date : 2022/9/2 18:02
 */
public class AccessConfigErrorException extends PlatformRuntimeException {

    public AccessConfigErrorException() {
        super();
    }

    public AccessConfigErrorException(String message) {
        super(message);
    }

    public AccessConfigErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccessConfigErrorException(Throwable cause) {
        super(cause);
    }

    protected AccessConfigErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public Feedback getFeedback() {
        return AccessErrorCodes.ACCESS_CONFIG_ERROR;
    }
}
