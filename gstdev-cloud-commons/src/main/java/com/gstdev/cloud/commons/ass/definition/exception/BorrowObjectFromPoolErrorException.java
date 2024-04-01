package com.gstdev.cloud.commons.ass.definition.exception;

import com.gstdev.cloud.commons.ass.definition.constants.ErrorCodes;
import com.gstdev.cloud.commons.ass.definition.domain.Feedback;


/**
 * <p>Description: 获取从连接池中获取对象错误 </p>
 *
 * @author : cc
 * @date : 2023/11/6 13:48
 */
public class BorrowObjectFromPoolErrorException extends PlatformRuntimeException {

  public BorrowObjectFromPoolErrorException() {
    super();
  }

  public BorrowObjectFromPoolErrorException(String message) {
    super(message);
  }

  public BorrowObjectFromPoolErrorException(String message, Throwable cause) {
    super(message, cause);
  }

  public BorrowObjectFromPoolErrorException(Throwable cause) {
    super(cause);
  }

  protected BorrowObjectFromPoolErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

  @Override
  public Feedback getFeedback() {
    return ErrorCodes.BORROW_OBJECT_FROM_POOL_ERROR_EXCEPTION;
  }
}
