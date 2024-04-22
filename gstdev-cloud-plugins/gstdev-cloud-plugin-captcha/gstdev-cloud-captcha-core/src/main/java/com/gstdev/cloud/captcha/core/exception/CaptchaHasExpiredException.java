package com.gstdev.cloud.captcha.core.exception;


import com.gstdev.cloud.captcha.core.constants.CaptchaErrorCodes;
import com.gstdev.cloud.base.definition.domain.Feedback;
import com.gstdev.cloud.base.definition.exception.PlatformRuntimeException;

/**
 * <p>Description: 验证码已过期 </p>
 *
 * @author : cc
 * @date : 2024/12/15 18:06
 */
public class CaptchaHasExpiredException extends PlatformRuntimeException {

  public CaptchaHasExpiredException() {
    super();
  }

  public CaptchaHasExpiredException(String message) {
    super(message);
  }

  public CaptchaHasExpiredException(String message, Throwable cause) {
    super(message, cause);
  }

  public CaptchaHasExpiredException(Throwable cause) {
    super(cause);
  }

  protected CaptchaHasExpiredException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

  @Override
  public Feedback getFeedback() {
    return CaptchaErrorCodes.CAPTCHA_HAS_EXPIRED;
  }
}
