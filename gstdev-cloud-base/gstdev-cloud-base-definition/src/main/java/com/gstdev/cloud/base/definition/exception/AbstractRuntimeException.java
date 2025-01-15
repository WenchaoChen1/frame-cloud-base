package com.gstdev.cloud.base.definition.exception;


import com.gstdev.cloud.base.definition.domain.Result;

/**
 * <p>Description: 自定义错误基础类 </p>
 *
 * @author : cc
 * @date : 2022/3/4 18:31
 */
public abstract class AbstractRuntimeException extends RuntimeException implements FrameException {

    protected AbstractRuntimeException() {
        super();
    }

    protected AbstractRuntimeException(String message) {
        super(message);
    }

    protected AbstractRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    protected AbstractRuntimeException(Throwable cause) {
        super(cause);
    }

    protected AbstractRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public Result<String> getResult() {
        Result<String> result = Result.failure(getFeedback());
        result.setMessage(super.getMessage());
        result.stackTrace(super.getStackTrace());
        result.detail(super.getMessage());
        return result;
    }
}
