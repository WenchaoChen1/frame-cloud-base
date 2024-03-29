package com.gstdev.cloud.oauth2.core.exception;


import com.gstdev.cloud.commons.constant.ErrorCodes;
import com.gstdev.cloud.commons.domain.Feedback;

/**
 * <p> Description : 非法加密Key HerodotusException </p>
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
