package com.gstdev.cloud.cache.core.exception;

import com.gstdev.cloud.cache.core.constants.CacheErrorCodes;
import com.gstdev.cloud.commons.domain.Feedback;
import com.gstdev.cloud.commons.exception.PlatformRuntimeException;

/**
 * <p>Description: Stamp签章删除失败Exception </p>
 *
 * @author : cc
 * @date : 2021/8/23 13:51
 */
public class StampDeleteFailedException extends PlatformRuntimeException {

    public StampDeleteFailedException() {
        super();
    }

    public StampDeleteFailedException(String message) {
        super(message);
    }

    public StampDeleteFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public StampDeleteFailedException(Throwable cause) {
        super(cause);
    }

    protected StampDeleteFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public Feedback getFeedback() {
        return CacheErrorCodes.STAMP_DELETE_FAILED;
    }
}
