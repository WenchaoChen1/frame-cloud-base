

package com.gstdev.cloud.access.core.exception;

import com.gstdev.cloud.access.core.constants.AccessErrorCodes;
import com.gstdev.cloud.base.definition.domain.Feedback;
import com.gstdev.cloud.base.definition.exception.PlatformRuntimeException;

/**
 * <p>Description: 接入预操作失败错误 </p>
 *
 * @author : cc
 * @date : 2022/1/26 11:10
 */
public class AccessPreProcessFailedException extends PlatformRuntimeException {

    public AccessPreProcessFailedException() {
    }

    public AccessPreProcessFailedException(String message) {
        super(message);
    }

    public AccessPreProcessFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccessPreProcessFailedException(Throwable cause) {
        super(cause);
    }

    public AccessPreProcessFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public Feedback getFeedback() {
        return AccessErrorCodes.ACCESS_PRE_PROCESS_FAILED_EXCEPTION;
    }
}
