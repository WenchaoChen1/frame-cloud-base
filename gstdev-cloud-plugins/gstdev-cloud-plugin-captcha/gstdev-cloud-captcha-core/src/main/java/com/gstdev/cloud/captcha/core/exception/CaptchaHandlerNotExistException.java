package com.gstdev.cloud.captcha.core.exception;


import com.gstdev.cloud.base.definition.domain.Feedback;
import com.gstdev.cloud.base.definition.exception.PlatformRuntimeException;
import com.gstdev.cloud.captcha.core.constants.CaptchaErrorCodes;

/**
 * <p>Description: 验证码处理器不存在 </p>
 *
 * @author : cc
 * @date : 2024/12/15 17:53
 */
public class CaptchaHandlerNotExistException extends PlatformRuntimeException {

    public CaptchaHandlerNotExistException() {
        super();
    }

    public CaptchaHandlerNotExistException(String message) {
        super(message);
    }

    public CaptchaHandlerNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public CaptchaHandlerNotExistException(Throwable cause) {
        super(cause);
    }

    protected CaptchaHandlerNotExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public Feedback getFeedback() {
        return CaptchaErrorCodes.CAPTCHA_HANDLER_NOT_EXIST;
    }
}
