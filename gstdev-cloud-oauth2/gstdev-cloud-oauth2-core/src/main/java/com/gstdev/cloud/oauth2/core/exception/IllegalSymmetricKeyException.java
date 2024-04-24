package com.gstdev.cloud.oauth2.core.exception;


import com.gstdev.cloud.base.definition.constants.ErrorCodes;
import com.gstdev.cloud.base.definition.domain.Feedback;

/**
 * <p> Description : 非法加密Key FrameException </p>
 *
 * @author : cc
 * @date : 2020/1/28 17:32
 */
public class IllegalSymmetricKeyException extends PlatformAuthenticationException {

  public IllegalSymmetricKeyException(String msg, Throwable cause) {
    super(msg, cause);
  }

  public IllegalSymmetricKeyException(String msg) {
    super(msg);
  }

  @Override
  public Feedback getFeedback() {
    return ErrorCodes.ILLEGAL_SYMMETRIC_KEY;
  }
}
