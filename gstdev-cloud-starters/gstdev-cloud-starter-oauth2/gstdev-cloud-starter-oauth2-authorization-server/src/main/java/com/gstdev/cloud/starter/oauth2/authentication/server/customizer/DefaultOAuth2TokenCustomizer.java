//// ====================================================
////
//// This file is part of the GstDev Cloud Platform.
////
//// Create by GstDev Cloud <support@gstdev.com>
//// Copyright (c) 2022-2025 gstdev.com
////
//// ====================================================
//
//package com.gstdev.cloud.starter.oauth2.authentication.server.customizer;
//
//import org.springframework.security.core.Authentication;
//import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
//import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
//import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;
//
//public class DefaultOAuth2TokenCustomizer implements OAuth2TokenCustomizer<JwtEncodingContext> {
//
//  @Override
//  public void customize(JwtEncodingContext context) {
//    Authentication authentication = context.getPrincipal();
//    if (authentication.isAuthenticated()) {
//      if (OAuth2TokenType.ACCESS_TOKEN.equals(context.getTokenType())) {
//      }
//    }
//  }
//}
