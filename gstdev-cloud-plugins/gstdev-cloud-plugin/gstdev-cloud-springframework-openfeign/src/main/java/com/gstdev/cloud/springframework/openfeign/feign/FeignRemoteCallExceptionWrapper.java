package com.gstdev.cloud.springframework.openfeign.feign;

import com.gstdev.cloud.base.definition.domain.Result;
import com.gstdev.cloud.base.definition.exception.PlatformRuntimeException;

/**
 * <p>Description: Feign Fallback 错误统一封装器 </p>
 */
public class FeignRemoteCallExceptionWrapper extends PlatformRuntimeException {

    private Result<String> result;

    public FeignRemoteCallExceptionWrapper(Result<String> result) {
        this.result = result;
    }

    public FeignRemoteCallExceptionWrapper(String message, Result<String> result) {
        super(message);
        this.result = result;
    }

    public FeignRemoteCallExceptionWrapper(String message, Throwable cause, Result<String> result) {
        super(message, cause);
        this.result = result;
    }

    public FeignRemoteCallExceptionWrapper(Throwable cause, Result<String> result) {
        super(cause);
        this.result = result;
    }

    public FeignRemoteCallExceptionWrapper(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Result<String> result) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.result = result;
    }

    @Override
    public Result<String> getResult() {
        return result;
    }
}
