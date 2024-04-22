package com.gstdev.cloud.oauth2.core.exception;

import com.gstdev.cloud.captcha.core.constants.CaptchaErrorCodes;
import com.gstdev.cloud.base.definition.domain.Feedback;

/**
 * <p>Description: Oauth2 使用的验证码不匹配错误 </p>
 *
 * @author : cc
 * @date : 2021/12/24 12:08
 */
public class OAuth2CaptchaMismatchException extends OAuth2CaptchaException {

  public OAuth2CaptchaMismatchException(String msg, Throwable cause) {
    super(msg, cause);
  }

  public OAuth2CaptchaMismatchException(String msg) {
    super(msg);
  }

  @Override
  public Feedback getFeedback() {
    return CaptchaErrorCodes.CAPTCHA_MISMATCH;
  }
}
