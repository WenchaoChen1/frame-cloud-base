package com.gstdev.cloud.rest.core.exception;

import com.gstdev.cloud.rest.core.constants.RestErrorCodes;
import com.gstdev.cloud.base.definition.domain.Feedback;

/**
 * <p>Description: 操作频繁Exception </p>
 *
 * @author : cc
 * @date : 2021/8/25 17:29
 */
public class FrequentRequestsException extends IllegalOperationException {

  public FrequentRequestsException() {
  }

  public FrequentRequestsException(String message) {
    super(message);
  }

  public FrequentRequestsException(String message, Throwable cause) {
    super(message, cause);
  }

  public FrequentRequestsException(Throwable cause) {
    super(cause);
  }

  public FrequentRequestsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

  @Override
  public Feedback getFeedback() {
    return RestErrorCodes.FREQUENT_REQUESTS;
  }
}
