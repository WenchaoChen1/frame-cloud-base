package com.gstdev.cloud.commons.exception.properties;


import com.gstdev.cloud.commons.constant.ErrorCodes;
import com.gstdev.cloud.commons.domain.Feedback;
import com.gstdev.cloud.commons.exception.PlatformRuntimeException;

/**
 * <p>Description: Url 格式错误 </p>
 *
 * @author : cc
 * @date : 2022/3/6 12:49
 */
public class UrlFormatIncorrectException extends PlatformRuntimeException {

    public UrlFormatIncorrectException() {
        super();
    }

    public UrlFormatIncorrectException(String message) {
        super(message);
    }

    public UrlFormatIncorrectException(String message, Throwable cause) {
        super(message, cause);
    }

    public UrlFormatIncorrectException(Throwable cause) {
        super(cause);
    }

    protected UrlFormatIncorrectException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public Feedback getFeedback() {
        return ErrorCodes.URL_FORMAT_INCORRECT_EXCEPTION;
    }
}
