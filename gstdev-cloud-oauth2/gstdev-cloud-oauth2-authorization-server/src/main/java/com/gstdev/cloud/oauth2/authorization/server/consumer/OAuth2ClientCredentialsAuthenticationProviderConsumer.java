//// ====================================================
////
//// This file is part of the GstDev Cloud Platform.
////
//// Create by GstDev <support@gstdev.com>
//// Copyright (c) 2020-2025 gstdev.com
////
//// ====================================================
//package com.gstdev.cloud.oauth2.authentication.consumer;
//
//com.gstdev.cloud.oauth2.authorization.server.utils.OAuth2ConfigurerUtils;
//import com.gstdev.cloud.oauth2.core.definition.service.ClientDetailsService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.oauth2.core.OAuth2Token;
//import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
//import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenGenerator;
//
//import java.util.List;
//import java.util.function.Consumer;
//
///**
// * <p>Description: OAuth2ClientCredentialsAuthenticationProvider 扩展 </p>
// * <p>
// * 用于替换 SAS 默认配置的 OAuth2ClientCredentialsAuthenticationProvider，以实现功能的扩展
// *
// * @author : cc
// * @date : 2023/9/1 14:29
// */
//public class OAuth2ClientCredentialsAuthenticationProviderConsumer implements Consumer<List<AuthenticationProvider>> {
//
//    private static final Logger log = LoggerFactory.getLogger(OAuth2ClientCredentialsAuthenticationProviderConsumer.class);
//
//    private final HttpSecurity httpSecurity;
//    private final ClientDetailsService clientDetailsService;
//
//    public OAuth2ClientCredentialsAuthenticationProviderConsumer(HttpSecurity httpSecurity, ClientDetailsService clientDetailsService) {
//        this.httpSecurity = httpSecurity;
//        this.clientDetailsService = clientDetailsService;
//    }
//
//    @Override
//    public void accept(List<AuthenticationProvider> authenticationProviders) {
//        authenticationProviders.removeIf(authenticationProvider ->
//                authenticationProvider instanceof org.springframework.security.oauth2.server.authorization.authentication.OAuth2ClientCredentialsAuthenticationProvider);
//
//        OAuth2AuthorizationService authorizationService = OAuth2ConfigurerUtils.getAuthorizationService(httpSecurity);
//        OAuth2TokenGenerator<? extends OAuth2Token> tokenGenerator = OAuth2ConfigurerUtils.getTokenGenerator(httpSecurity);
//        cn.herodotus.engine.oauth2.authentication.provider.OAuth2ClientCredentialsAuthenticationProvider provider = new cn.herodotus.engine.oauth2.authentication.provider.OAuth2ClientCredentialsAuthenticationProvider(authorizationService, tokenGenerator, clientDetailsService);
//        log.debug("[GstDev Cloud] |- Custom OAuth2ClientCredentialsAuthenticationProvider is in effect!");
//        authenticationProviders.add(provider);
//    }
//}
