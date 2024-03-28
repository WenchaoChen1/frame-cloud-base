//// ====================================================
////
//// This file is part of the GstDev Cloud Platform.
////
//// Create by GstDev <support@gstdev.com>
//// Copyright (c) 2020-2025 gstdev.com
////
//// ====================================================
//package com.gstdev.cloud.oauth2.authentication.provider;
//
//import cn.herodotus.engine.oauth2.core.definition.HerodotusGrantType;
//import org.apache.commons.collections4.CollectionUtils;
//import org.springframework.lang.Nullable;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AuthorizationGrantAuthenticationToken;
//import org.springframework.util.Assert;
//
//import java.util.Collections;
//import java.util.HashSet;
//import java.util.Map;
//import java.util.Set;
//
///**
// * <p>Description: 自定义社会化登录认证Token </p>
// *
// * @author : gengwei.zheng
// * @date : 2022/3/31 14:54
// */
//public class OAuth2SocialCredentialsAuthenticationToken extends OAuth2AuthorizationGrantAuthenticationToken {
//
//    private final Set<String> scopes;
//
//    public OAuth2SocialCredentialsAuthenticationToken(Authentication clientPrincipal, @Nullable Set<String> scopes, @Nullable Map<String, Object> additionalParameters) {
//        super(HerodotusGrantType.SOCIAL, clientPrincipal, additionalParameters);
//        Assert.notNull(clientPrincipal, "clientPrincipal cannot be null");
//        this.scopes = Collections.unmodifiableSet(CollectionUtils.isNotEmpty(scopes) ? new HashSet<>(scopes) : Collections.emptySet());
//    }
//
//    public Set<String> getScopes() {
//        return scopes;
//    }
//}
