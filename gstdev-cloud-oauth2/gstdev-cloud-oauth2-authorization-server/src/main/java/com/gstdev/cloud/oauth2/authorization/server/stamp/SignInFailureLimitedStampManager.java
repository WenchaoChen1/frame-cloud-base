//// ====================================================
////
//// This file is part of the GstDev Cloud Platform.
////
//// Create by GstDev <support@gstdev.com>
//// Copyright (c) 2020-2025 gstdev.com
////
//// ====================================================
//package com.gstdev.cloud.oauth2.authentication.stamp;
//
//import com.gstdev.cloud.cache.jetcache.stamp.AbstractCountStampManager;
//com.gstdev.cloud.oauth2.authorization.server.dto.SignInErrorStatus;
//com.gstdev.cloud.oauth2.authorization.server.properties.OAuth2AuthenticationProperties;
//import com.gstdev.cloud.oauth2.core.constants.OAuth2Constants;
//import org.apache.commons.lang3.ObjectUtils;
//import org.dromara.hutool.crypto.SecureUtil;
//
///**
// * <p>Description: 登录失败次数限制签章管理 </p>
// *
// * @author : cc
// * @date : 2022/7/6 23:36
// */
//public class SignInFailureLimitedStampManager extends AbstractCountStampManager {
//
//    private final OAuth2AuthenticationProperties authenticationProperties;
//
//    public SignInFailureLimitedStampManager(OAuth2AuthenticationProperties authenticationProperties) {
//        super(OAuth2Constants.CACHE_NAME_TOKEN_SIGN_IN_FAILURE_LIMITED);
//        this.authenticationProperties = authenticationProperties;
//    }
//
//    @Override
//    public Long nextStamp(String key) {
//        return 1L;
//    }
//
//    @Override
//    public void afterPropertiesSet() throws Exception {
//        super.setExpire(authenticationProperties.getSignInFailureLimited().getExpire());
//    }
//
//    public OAuth2AuthenticationProperties getAuthenticationProperties() {
//        return authenticationProperties;
//    }
//
//    public SignInErrorStatus errorStatus(String username) {
//        int maxTimes = authenticationProperties.getSignInFailureLimited().getMaxTimes();
//        Long storedTimes = get(SecureUtil.md5(username));
//
//        int errorTimes = 0;
//        if (ObjectUtils.isNotEmpty(storedTimes)) {
//            errorTimes = storedTimes.intValue();
//        }
//
//        int remainTimes = maxTimes;
//        if (errorTimes != 0) {
//            remainTimes = maxTimes - errorTimes;
//        }
//
//        boolean isLocked = false;
//        if (errorTimes == maxTimes) {
//            isLocked = true;
//        }
//
//        SignInErrorStatus status = new SignInErrorStatus();
//        status.setErrorTimes(errorTimes);
//        status.setRemainTimes(remainTimes);
//        status.setLocked(isLocked);
//
//        return status;
//    }
//}
