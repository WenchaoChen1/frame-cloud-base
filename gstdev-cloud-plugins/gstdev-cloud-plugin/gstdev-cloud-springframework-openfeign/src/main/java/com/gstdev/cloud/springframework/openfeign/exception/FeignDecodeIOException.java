package com.gstdev.cloud.springframework.openfeign.exception;

import com.gstdev.cloud.base.definition.domain.Feedback;
import com.gstdev.cloud.base.definition.exception.PlatformRuntimeException;
import com.gstdev.cloud.springframework.openfeign.constants.OpenFeignErrorCodes;

/**
 * <p>Description: Feign 解码 IO 错误 </p>
 *
 */
public class FeignDecodeIOException extends PlatformRuntimeException {

    public FeignDecodeIOException() {
        super();
    }

    public FeignDecodeIOException(String message) {
        super(message);
    }

    public FeignDecodeIOException(String message, Throwable cause) {
        super(message, cause);
    }

    public FeignDecodeIOException(Throwable cause) {
        super(cause);
    }

    protected FeignDecodeIOException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public Feedback getFeedback() {
        return OpenFeignErrorCodes.FEIGN_DECODER_IO_EXCEPTION;
    }
}
