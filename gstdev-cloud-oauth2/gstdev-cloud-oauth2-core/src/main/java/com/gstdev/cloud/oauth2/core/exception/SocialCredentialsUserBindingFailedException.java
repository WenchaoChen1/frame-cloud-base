package com.gstdev.cloud.oauth2.core.exception;

/**
 * <p>Description: 社交登录绑定用户出错 </p>
 *
 * @author : cc
 * @date : 2021/5/18 9:31
 */
public class SocialCredentialsUserBindingFailedException extends PlatformAuthenticationException {

    public SocialCredentialsUserBindingFailedException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public SocialCredentialsUserBindingFailedException(String msg) {
        super(msg);
    }
}
