//// ====================================================
////
//// This file is part of the GstDev Cloud Platform.
////
//// Create by GstDev <support@gstdev.com>
//// Copyright (c) 2020-2025 gstdev.com
////
//// ====================================================
//package com.gstdev.cloud.oauth2.resource.server.auditing;
//
//import org.apache.commons.lang3.ObjectUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.data.domain.AuditorAware;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContext;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthentication;
//import org.springframework.security.oauth2.server.resource.introspection.OAuth2IntrospectionAuthenticatedPrincipal;
//
//import java.util.Optional;
//
///**
// * <p>Description: 基于 Security 的数据库审计用户信息获取 </p>
// *
// * @author : cc
// * @date : 2023/4/7 15:56
// */
//public class SecurityAuditorAware implements AuditorAware<String> {
//
//    private static final Logger log = LoggerFactory.getLogger(SecurityAuditorAware.class);
//
//    @Override
//    public Optional<String> getCurrentAuditor() {
//
//        SecurityContext context = SecurityContextHolder.getContext();
//
//        if (ObjectUtils.isNotEmpty(context)) {
//            Authentication authentication = context.getAuthentication();
//            if (ObjectUtils.isNotEmpty(authentication)) {
//                if (authentication.isAuthenticated()) {
//                    if (authentication instanceof BearerTokenAuthentication bearerTokenAuthentication) {
//                        Object object = bearerTokenAuthentication.getPrincipal();
//                        if (object instanceof OAuth2IntrospectionAuthenticatedPrincipal principal) {
//                            String username = principal.getName();
//                            log.trace("[GstDev Cloud] |- Current auditor is : [{}]", username);
//                            return Optional.of(username);
//                        }
//                    }
//                }
//            }
//        }
//
//        return Optional.empty();
//    }
//}
