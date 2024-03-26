package com.gstdev.cloud.captcha.core.exception;


import com.gstdev.cloud.captcha.core.constants.CaptchaErrorCodes;
import com.gstdev.cloud.commons.domain.Feedback;
import com.gstdev.cloud.commons.exception.PlatformRuntimeException;

/**
 * <p>Description: 验证码不匹配错误 </p>
 *
 * @author : cc
 * @date : 2024/12/15 17:56
 */
public class CaptchaMismatchException extends PlatformRuntimeException {

    public CaptchaMismatchException() {
        super();
    }

    public CaptchaMismatchException(String message) {
        super(message);
    }

    public CaptchaMismatchException(String message, Throwable cause) {
        super(message, cause);
    }

    public CaptchaMismatchException(Throwable cause) {
        super(cause);
    }

    protected CaptchaMismatchException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public Feedback getFeedback() {
        return CaptchaErrorCodes.CAPTCHA_MISMATCH;
    }
}
