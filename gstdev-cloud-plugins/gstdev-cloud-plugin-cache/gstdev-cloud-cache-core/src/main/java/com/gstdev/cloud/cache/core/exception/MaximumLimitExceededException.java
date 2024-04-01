package com.gstdev.cloud.cache.core.exception;

/**
 * <p>Description: 超出最大数量限制 </p>
 *
 * @author : cc
 * @date : 2022/7/6 23:03
 */
public class MaximumLimitExceededException extends Exception {

  public MaximumLimitExceededException() {
    super();
  }

  public MaximumLimitExceededException(String message) {
    super(message);
  }

  public MaximumLimitExceededException(String message, Throwable cause) {
    super(message, cause);
  }

  public MaximumLimitExceededException(Throwable cause) {
    super(cause);
  }

  protected MaximumLimitExceededException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
