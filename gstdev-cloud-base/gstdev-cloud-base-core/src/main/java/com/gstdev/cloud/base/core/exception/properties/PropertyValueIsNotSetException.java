package com.gstdev.cloud.base.core.exception.properties;

import com.gstdev.cloud.base.definition.constants.ErrorCodes;
import com.gstdev.cloud.base.definition.domain.Feedback;
import com.gstdev.cloud.base.definition.exception.PlatformRuntimeException;

/**
 * <p>Description: Property 属性值没有设置错误 </p>
 *
 * @author : cc
 * @date : 2022/3/6 13:57
 */
public class PropertyValueIsNotSetException extends PlatformRuntimeException {

  public PropertyValueIsNotSetException() {
    super();
  }

  public PropertyValueIsNotSetException(String message) {
    super(message);
  }

  public PropertyValueIsNotSetException(String message, Throwable cause) {
    super(message, cause);
  }

  public PropertyValueIsNotSetException(Throwable cause) {
    super(cause);
  }

  protected PropertyValueIsNotSetException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

  @Override
  public Feedback getFeedback() {
    return ErrorCodes.PROPERTY_VALUE_IS_NOT_SET_EXCEPTION;
  }
}
