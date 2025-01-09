

package com.gstdev.cloud.access.core.exception;

import com.gstdev.cloud.access.core.constants.AccessErrorCodes;
import com.gstdev.cloud.base.definition.domain.Feedback;
import com.gstdev.cloud.base.definition.exception.PlatformRuntimeException;

/**
 * <p>Description: 接入身份认证错误 </p>
 *
 * @author : cc
 * @date : 2022/1/26 10:54
 */
public class AccessIdentityVerificationFailedException extends PlatformRuntimeException {

    public AccessIdentityVerificationFailedException() {
        super();
    }

    public AccessIdentityVerificationFailedException(String message) {
        super(message);
    }

    public AccessIdentityVerificationFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccessIdentityVerificationFailedException(Throwable cause) {
        super(cause);
    }

    public AccessIdentityVerificationFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public Feedback getFeedback() {
        return AccessErrorCodes.ACCESS_IDENTITY_VERIFICATION_FAILED;
    }
}
