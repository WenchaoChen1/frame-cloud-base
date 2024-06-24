package com.gstdev.cloud.captcha.core.exception;


import com.gstdev.cloud.base.definition.domain.Feedback;
import com.gstdev.cloud.base.definition.exception.PlatformRuntimeException;
import com.gstdev.cloud.captcha.core.constants.CaptchaErrorCodes;

/**
 * <p>Description: 验证码校验参数错误 </p>
 *
 * @author : cc
 * @date : 2024/12/15 17:54
 */
public class CaptchaParameterIllegalException extends PlatformRuntimeException {

    public CaptchaParameterIllegalException() {
        super();
    }

    public CaptchaParameterIllegalException(String message) {
        super(message);
    }

    public CaptchaParameterIllegalException(String message, Throwable cause) {
        super(message, cause);
    }

    public CaptchaParameterIllegalException(Throwable cause) {
        super(cause);
    }

    protected CaptchaParameterIllegalException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public Feedback getFeedback() {
        return CaptchaErrorCodes.CAPTCHA_PARAMETER_ILLEGAL;
    }
}
