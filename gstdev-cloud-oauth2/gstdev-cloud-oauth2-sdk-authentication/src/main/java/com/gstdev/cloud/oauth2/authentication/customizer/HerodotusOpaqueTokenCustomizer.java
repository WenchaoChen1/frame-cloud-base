//// ====================================================
////
//// This file is part of the GstDev Cloud Platform.
////
//// Create by GstDev <support@gstdev.com>
//// Copyright (c) 2020-2025 gstdev.com
////
//// ====================================================
//package com.gstdev.cloud.oauth2.authentication.customizer;
//
//import org.apache.commons.lang3.ObjectUtils;
//import org.springframework.security.authentication.AbstractAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
//import org.springframework.security.oauth2.server.authorization.authentication.OAuth2ClientAuthenticationToken;
//import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenClaimsContext;
//import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenClaimsSet;
//import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * <p>Description: Opaque Token Customizer </p>
// * <p>
// * An {@link OAuth2TokenCustomizer} provides the ability to customize the attributes of an OAuth2Token, which are accessible in the provided {@link org.springframework.security.oauth2.server.authorization.token.OAuth2TokenContext}.
// * It is used by an {@link org.springframework.security.oauth2.server.authorization.token.OAuth2TokenGenerator} to let it customize the attributes of the OAuth2Token before it is generated.
// *
// * @author : gengwei.zheng
// * @date : 2022/10/9 20:43
// */
//public class HerodotusOpaqueTokenCustomizer extends AbstractTokenCustomizer implements OAuth2TokenCustomizer<OAuth2TokenClaimsContext> {
//
//    @Override
//    public void customize(OAuth2TokenClaimsContext context) {
//
//        AbstractAuthenticationToken token = null;
//        Authentication clientAuthentication = SecurityContextHolder.getContext().getAuthentication();
//        if (clientAuthentication instanceof OAuth2ClientAuthenticationToken) {
//            token = (OAuth2ClientAuthenticationToken) clientAuthentication;
//        }
//
//        if (ObjectUtils.isNotEmpty(token)) {
//            if (token.isAuthenticated()) {
//
//                if (OAuth2TokenType.ACCESS_TOKEN.equals(context.getTokenType())) {
//                    Authentication authentication = context.getPrincipal();
//                    if (ObjectUtils.isNotEmpty(authentication)) {
//                        Map<String, Object> attributes = new HashMap<>();
//                        appendAll(attributes, authentication, context.getAuthorizedScopes());
//                        OAuth2TokenClaimsSet.Builder tokenClaimSetBuilder = context.getClaims();
//                        tokenClaimSetBuilder.claims(claims -> claims.putAll(attributes));
//                    }
//                }
//            }
//
//        }
//    }
//}
