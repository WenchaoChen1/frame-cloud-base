package com.gstdev.cloud.cache.core.exception;

import com.gstdev.cloud.cache.core.constants.CacheErrorCodes;
import com.gstdev.cloud.base.definition.domain.Feedback;
import com.gstdev.cloud.base.definition.exception.PlatformRuntimeException;

/**
 * <p>Description: Stamp签章校验错误 </p>
 *
 * @author : cc
 * @date : 2021/8/23 12:32
 */
public class StampMismatchException extends PlatformRuntimeException {

    public StampMismatchException() {
        super();
    }

    public StampMismatchException(String message) {
        super(message);
    }

    public StampMismatchException(String message, Throwable cause) {
        super(message, cause);
    }

    public StampMismatchException(Throwable cause) {
        super(cause);
    }

    protected StampMismatchException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public Feedback getFeedback() {
        return CacheErrorCodes.STAMP_MISMATCH;
    }
}
