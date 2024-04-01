package com.gstdev.cloud.oauth2.core.exception;


import com.gstdev.cloud.commons.ass.definition.constants.ErrorCodes;
import com.gstdev.cloud.commons.ass.definition.domain.Feedback;
import com.gstdev.cloud.commons.ass.definition.domain.Result;
import com.gstdev.cloud.commons.ass.definition.exception.HerodotusException;
import org.springframework.security.core.AuthenticationException;

/**
 * <p>Description: 平台认证基础Exception </p>
 *
 * @author : cc
 * @date : 2021/10/16 14:41
 */
public class PlatformAuthenticationException extends AuthenticationException implements HerodotusException {

  public PlatformAuthenticationException(String msg, Throwable cause) {
    super(msg, cause);
  }

  public PlatformAuthenticationException(String msg) {
    super(msg);
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
