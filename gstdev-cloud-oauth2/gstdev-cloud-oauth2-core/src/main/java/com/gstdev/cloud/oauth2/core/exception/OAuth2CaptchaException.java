package com.gstdev.cloud.oauth2.core.exception;


import com.gstdev.cloud.base.definition.constants.ErrorCodes;
import com.gstdev.cloud.base.definition.domain.Feedback;
import com.gstdev.cloud.base.definition.domain.Result;
import com.gstdev.cloud.base.definition.exception.HerodotusException;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.core.Authentication;

/**
 * <p>Description: OAuth2 验证码基础 Exception </p>
 * <p>
 * 这里没有用基础定义的 PlatformAuthorizationException。主要问题是在自定义表单登录时，如果使用基础的 {@link org.springframework.security.core.AuthenticationException}，
 * 在 Spring Security 标准代码中该Exception将不会抛出，而是进行二次的用户验证，这将导致在验证过程中直接跳过验证码的校验。
 *
 * @author : cc
 * @date : 2022/4/12 22:33
 * @see org.springframework.security.authentication.ProviderManager#authenticate(Authentication)
 */
public class OAuth2CaptchaException extends AccountStatusException implements HerodotusException {

  public OAuth2CaptchaException(String msg) {
    super(msg);
  }

  public OAuth2CaptchaException(String msg, Throwable cause) {
    super(msg, cause);
  }

  @Override
  public Feedback getFeedback() {
    return ErrorCodes.INTERNAL_SERVER_ERROR;
  }

  @Override
  public Result<String> getResult() {
    Result<String> result = Result.failure(getFeedback());
    result.stackTrace(super.getStackTrace());
    result.detail(super.getMessage());
    return result;
  }
}
