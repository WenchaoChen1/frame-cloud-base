//// ====================================================
////
//// This file is part of the GstDev Cloud Platform.
////
//// Create by GstDev <support@gstdev.com>
//// Copyright (c) 2020-2025 gstdev.com
////
//// ====================================================
//package com.gstdev.cloud.oauth2.authentication.configurer;
//
//import cn.herodotus.engine.oauth2.authentication.properties.OAuth2AuthenticationProperties;
//import cn.herodotus.engine.oauth2.core.definition.details.FormLoginWebAuthenticationDetails;
//import jakarta.servlet.http.HttpServletRequest;
//import org.springframework.security.authentication.AuthenticationDetailsSource;
//
///**
// * <p>Description: 表单登录 Details 定义 </p>
// *
// * @author : gengwei.zheng
// * @date : 2022/4/12 10:41
// */
//public class OAuth2FormLoginWebAuthenticationDetailSource implements AuthenticationDetailsSource<HttpServletRequest, FormLoginWebAuthenticationDetails> {
//
//    private final OAuth2AuthenticationProperties authenticationProperties;
//
//    public OAuth2FormLoginWebAuthenticationDetailSource(OAuth2AuthenticationProperties authenticationProperties) {
//        this.authenticationProperties = authenticationProperties;
//    }
//
//    @Override
//    public FormLoginWebAuthenticationDetails buildDetails(HttpServletRequest context) {
//        return new FormLoginWebAuthenticationDetails(context, authenticationProperties.getFormLogin().getCloseCaptcha(), authenticationProperties.getFormLogin().getCaptchaParameter(), authenticationProperties.getFormLogin().getCategory());
//    }
//}
