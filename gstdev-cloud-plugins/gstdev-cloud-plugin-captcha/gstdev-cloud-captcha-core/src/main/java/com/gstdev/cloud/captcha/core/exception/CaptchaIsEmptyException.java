package com.gstdev.cloud.captcha.core.exception;


import com.gstdev.cloud.base.definition.domain.Feedback;
import com.gstdev.cloud.base.definition.exception.PlatformRuntimeException;
import com.gstdev.cloud.captcha.core.constants.CaptchaErrorCodes;

/**
 * <p>Description: 验证码为空 </p>
 *
 * @author : cc
 * @date : 2024/12/24 18:11
 */
public class CaptchaIsEmptyException extends PlatformRuntimeException {

    public CaptchaIsEmptyException() {
        super();
    }

    public CaptchaIsEmptyException(String message) {
        super(message);
    }

    public CaptchaIsEmptyException(String message, Throwable cause) {
        super(message, cause);
    }

    public CaptchaIsEmptyException(Throwable cause) {
        super(cause);
    }

    protected CaptchaIsEmptyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public Feedback getFeedback() {
        return CaptchaErrorCodes.CAPTCHA_IS_EMPTY;
    }
}
