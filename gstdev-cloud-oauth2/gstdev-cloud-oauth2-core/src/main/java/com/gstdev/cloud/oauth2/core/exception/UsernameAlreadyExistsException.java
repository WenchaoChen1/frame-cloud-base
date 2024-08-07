package com.gstdev.cloud.oauth2.core.exception;

import com.gstdev.cloud.base.definition.domain.Feedback;
import com.gstdev.cloud.oauth2.core.constants.OAuth2ErrorCodes;

/**
 * <p>Description: UsernameAlreadyExistsException </p>
 *
 * @author : cc
 * @date : 2021/5/17 19:04
 */
public class UsernameAlreadyExistsException extends PlatformAuthenticationException {

    public UsernameAlreadyExistsException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public UsernameAlreadyExistsException(String msg) {
        super(msg);
    }

    @Override
    public Feedback getFeedback() {
        return OAuth2ErrorCodes.USERNAME_ALREADY_EXISTS;
    }
}
