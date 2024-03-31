package cn.herodotus.engine.rest.core.exception;

import cn.herodotus.engine.assistant.definition.domain.Feedback;
import cn.herodotus.engine.assistant.definition.exception.PlatformRuntimeException;
import cn.herodotus.engine.rest.core.constants.RestErrorCodes;

/**
 * <p>Description: Feign 解码 IO 错误 </p>
 *
 * @author : cc
 * @date : 2022/5/30 11:17
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
        return RestErrorCodes.FEIGN_DECODER_IO_EXCEPTION;
    }
}
