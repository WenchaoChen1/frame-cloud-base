//// ====================================================
////
//// This file is part of the GstDev Cloud Platform.
////
//// Create by GstDev <support@gstdev.com>
//// Copyright (c) 2020-2025 gstdev.com
////
//// ====================================================
//package com.gstdev.cloud.oauth2.authentication.response;
//
//import com.gstdev.cloud.oauth2.core.constants.OAuth2ErrorKeys;
//import com.gstdev.cloud.oauth2.core.exception.AccountEndpointLimitedException;
//import com.gstdev.cloud.oauth2.core.exception.SessionExpiredException;
//import org.springframework.context.ApplicationEventPublisher;
//import org.springframework.security.authentication.*;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
//import org.springframework.security.oauth2.core.OAuth2Error;
//
///**
// * <p>Description: 扩展的 DefaultAuthenticationEventPublisher </p>
// * <p>
// * 支持 OAuth2AuthenticationException 解析
// *
// * @author : cc
// * @date : 2022/7/9 13:47
// */
//public class DefaultOAuth2AuthenticationEventPublisher extends DefaultAuthenticationEventPublisher {
//
//    public DefaultOAuth2AuthenticationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
//        super(applicationEventPublisher);
//    }
//
//    @Override
//    public void publishAuthenticationFailure(AuthenticationException exception, Authentication authentication) {
//        super.publishAuthenticationFailure(convert(exception), authentication);
//    }
//
//    private AuthenticationException convert(AuthenticationException exception) {
//        if (exception instanceof OAuth2AuthenticationException authenticationException) {
//            OAuth2Error error = authenticationException.getError();
//
//            return switch (error.getErrorCode()) {
//                case OAuth2ErrorKeys.ACCOUNT_EXPIRED ->
//                        new AccountExpiredException(exception.getMessage(), exception.getCause());
//                case OAuth2ErrorKeys.CREDENTIALS_EXPIRED ->
//                        new CredentialsExpiredException(exception.getMessage(), exception.getCause());
//                case OAuth2ErrorKeys.ACCOUNT_DISABLED ->
//                        new DisabledException(exception.getMessage(), exception.getCause());
//                case OAuth2ErrorKeys.ACCOUNT_LOCKED ->
//                        new LockedException(exception.getMessage(), exception.getCause());
//                case OAuth2ErrorKeys.ACCOUNT_ENDPOINT_LIMITED ->
//                        new AccountEndpointLimitedException(exception.getMessage(), exception.getCause());
//                case OAuth2ErrorKeys.USERNAME_NOT_FOUND ->
//                        new UsernameNotFoundException(exception.getMessage(), exception.getCause());
//                case OAuth2ErrorKeys.SESSION_EXPIRED ->
//                        new SessionExpiredException(exception.getMessage(), exception.getCause());
//                default -> new BadCredentialsException(exception.getMessage(), exception.getCause());
//            };
//        }
//
//        return exception;
//    }
//}
