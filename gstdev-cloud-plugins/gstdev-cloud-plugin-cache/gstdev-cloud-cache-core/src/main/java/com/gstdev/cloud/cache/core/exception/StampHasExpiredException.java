package com.gstdev.cloud.cache.core.exception;

import com.gstdev.cloud.cache.core.constants.CacheErrorCodes;
import com.gstdev.cloud.commons.ass.definition.domain.Feedback;
import com.gstdev.cloud.commons.ass.definition.exception.PlatformRuntimeException;

/**
 * <p>Description: Stamp签章 已过期错误 </p>
 *
 * @author : cc
 * @date : 2021/8/23 12:36
 */
public class StampHasExpiredException extends PlatformRuntimeException {

    public StampHasExpiredException() {
        super();
    }

    public StampHasExpiredException(String message) {
        super(message);
    }

    public StampHasExpiredException(String message, Throwable cause) {
        super(message, cause);
    }

    public StampHasExpiredException(Throwable cause) {
        super(cause);
    }

    protected StampHasExpiredException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public Feedback getFeedback() {
        return CacheErrorCodes.STAMP_HAS_EXPIRED;
    }
}
