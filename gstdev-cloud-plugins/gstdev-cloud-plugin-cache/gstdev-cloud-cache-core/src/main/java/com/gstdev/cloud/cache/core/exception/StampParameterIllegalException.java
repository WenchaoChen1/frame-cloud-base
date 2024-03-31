package com.gstdev.cloud.cache.core.exception;

import com.gstdev.cloud.cache.core.constants.CacheErrorCodes;
import com.gstdev.cloud.commons.ass.definition.domain.Feedback;
import com.gstdev.cloud.commons.ass.definition.exception.PlatformRuntimeException;

/**
 * <p>Description: 请求参数中缺少幂等Token错误 </p>
 *
 * @author : cc
 * @date : 2021/8/23 12:29
 */
public class StampParameterIllegalException extends PlatformRuntimeException {

    public StampParameterIllegalException() {
        super();
    }

    public StampParameterIllegalException(String message) {
        super(message);
    }

    public StampParameterIllegalException(String message, Throwable cause) {
        super(message, cause);
    }

    public StampParameterIllegalException(Throwable cause) {
        super(cause);
    }

    protected StampParameterIllegalException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public Feedback getFeedback() {
        return CacheErrorCodes.STAMP_PARAMETER_ILLEGAL;
    }
}
