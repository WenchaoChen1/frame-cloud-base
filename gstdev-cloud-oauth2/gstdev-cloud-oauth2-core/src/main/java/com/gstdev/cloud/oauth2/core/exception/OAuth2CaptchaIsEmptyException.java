package com.gstdev.cloud.oauth2.core.exception;


import com.gstdev.cloud.captcha.core.constants.CaptchaErrorCodes;
import com.gstdev.cloud.commons.ass.definition.domain.Feedback;

/**
 * <p>Description: 验证码为空 </p>
 *
 * @author : cc
 * @date : 2021/12/24 18:08
 */
public class OAuth2CaptchaIsEmptyException extends OAuth2CaptchaException {

  public OAuth2CaptchaIsEmptyException(String msg, Throwable cause) {
    super(msg, cause);
  }

  public OAuth2CaptchaIsEmptyException(String msg) {
    super(msg);
  }

  @Override
  public Feedback getFeedback() {
    return CaptchaErrorCodes.CAPTCHA_IS_EMPTY;
  }
}
