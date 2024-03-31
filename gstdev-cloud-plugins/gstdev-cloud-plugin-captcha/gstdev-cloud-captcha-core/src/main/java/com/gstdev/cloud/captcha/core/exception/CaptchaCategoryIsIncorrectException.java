package com.gstdev.cloud.captcha.core.exception;

import com.gstdev.cloud.captcha.core.constants.CaptchaErrorCodes;
import com.gstdev.cloud.commons.ass.definition.domain.Feedback;
import com.gstdev.cloud.commons.ass.definition.exception.PlatformRuntimeException;

/**
 * <p>Description: 验证码分类错误 </p>
 *
 * @author : cc
 * @date : 2024/12/15 17:51
 */
public class CaptchaCategoryIsIncorrectException extends PlatformRuntimeException {

    public CaptchaCategoryIsIncorrectException() {
        super();
    }

    public CaptchaCategoryIsIncorrectException(String message) {
        super(message);
    }

    public CaptchaCategoryIsIncorrectException(String message, Throwable cause) {
        super(message, cause);
    }

    public CaptchaCategoryIsIncorrectException(Throwable cause) {
        super(cause);
    }

    protected CaptchaCategoryIsIncorrectException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public Feedback getFeedback() {
        return CaptchaErrorCodes.CAPTCHA_CATEGORY_IS_INCORRECT;
    }
}
