

package com.gstdev.cloud.access.core.exception;

import com.gstdev.cloud.access.core.constants.AccessErrorCodes;
import com.gstdev.cloud.base.definition.domain.Feedback;
import com.gstdev.cloud.base.definition.exception.PlatformRuntimeException;

/**
 * <p>Description: 非法的外部访问参数类型错误 </p>
 *
 * @author : cc
 * @date : 2022/1/26 12:02
 */
public class IllegalAccessSourceException extends PlatformRuntimeException {

    public IllegalAccessSourceException() {
        super();
    }

    public IllegalAccessSourceException(String message) {
        super(message);
    }

    public IllegalAccessSourceException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalAccessSourceException(Throwable cause) {
        super(cause);
    }

    public IllegalAccessSourceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public Feedback getFeedback() {
        return AccessErrorCodes.ILLEGAL_ACCESS_SOURCE;
    }
}
