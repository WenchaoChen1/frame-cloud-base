//// ====================================================
////
//// This file is part of the GstDev Cloud Platform.
////
//// Create by GstDev <support@gstdev.com>
//// Copyright (c) 2020-2025 gstdev.com
////
//// ====================================================
//package com.gstdev.cloud.oauth2.authorization.server.configurer;
//
//import com.gstdev.cloud.captcha.core.processor.CaptchaRendererFactory;
//import com.gstdev.cloud.oauth2.authorization.server.properties.OAuth2AuthenticationProperties;
//import com.gstdev.cloud.oauth2.authorization.server.provider.OAuth2FormLoginAuthenticationProvider;
//import com.gstdev.cloud.oauth2.authorization.server.response.OAuth2FormLoginAuthenticationFailureHandler;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
//import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.oauth2.core.OAuth2Token;
//import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenGenerator;
//import org.springframework.security.web.authentication.ForwardAuthenticationFailureHandler;
//import org.springframework.security.web.authentication.ForwardAuthenticationSuccessHandler;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.security.web.authentication.ui.DefaultLoginPageGeneratingFilter;
//import org.springframework.security.web.context.SecurityContextRepository;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//import org.springframework.security.web.util.matcher.RequestMatcher;
//
///**
// * <p>Description: OAuth2 Form Login Configurer </p>
// * <p>
// * 使用此种方式，相当于额外增加了一种表单登录方式。因此对原有的 http.formlogin进行的配置，对当前此种方式的配置并不生效。
// *
// * @author : cc
// * @date : 2022/4/12 13:29
// * @see AbstractAuthenticationFilterConfigurer
// */
////public class OAuth2FormLoginSecureConfigurer<H extends HttpSecurityBuilder<H>> extends AbstractHttpConfigurer<OAuth2FormLoginSecureConfigurer<H>, H> {
//public class OAuth2FormLoginSecureConfigurer1<H extends HttpSecurityBuilder<H>> extends AbstractAuthenticationFilterConfigurer<H, OAuth2FormLoginSecureConfigurer1<H>, OAuth2FormLoginAuthenticationFilter> {
//    private final UserDetailsService userDetailsService;
//    private final OAuth2AuthenticationProperties authenticationProperties;
//    private final CaptchaRendererFactory captchaRendererFactory;
//    OAuth2TokenGenerator<? extends OAuth2Token> tokenGenerator;
//
//    public OAuth2FormLoginSecureConfigurer1(UserDetailsService userDetailsService, OAuth2AuthenticationProperties authenticationProperties, CaptchaRendererFactory captchaRendererFactory, OAuth2TokenGenerator<? extends OAuth2Token> tokenGenerator) {
//        super(new OAuth2FormLoginAuthenticationFilter(), (String) null);
//        this.usernameParameter("username");
//        this.passwordParameter("password");
//        this.userDetailsService = userDetailsService;
//        this.authenticationProperties = authenticationProperties;
//        this.captchaRendererFactory = captchaRendererFactory;
//        this.tokenGenerator = tokenGenerator;
//    }
//
//
//    @Override
//    public void configure(H httpSecurity) throws Exception {
//        super.configure(httpSecurity);
//
//        AuthenticationManager authenticationManager = httpSecurity.getSharedObject(AuthenticationManager.class);
//        SecurityContextRepository securityContextRepository = httpSecurity.getSharedObject(SecurityContextRepository.class);
//
//        OAuth2FormLoginAuthenticationFilter filter = getOAuth2FormLoginAuthenticationFilter(authenticationManager, securityContextRepository);
//
//        OAuth2FormLoginAuthenticationProvider provider = new OAuth2FormLoginAuthenticationProvider(captchaRendererFactory);
//        provider.setUserDetailsService(userDetailsService);
//        provider.setHideUserNotFoundExceptions(false);
////
//        httpSecurity
//                .authenticationProvider(provider)
//                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
////
////        this.successHandler(new OAuth2AccessLoginTokenResponseHandler(tokenGenerator));
////        this.failureHandler(new OAuth2AuthenticationFailureResponseHandler());
//    }
//
//    private OAuth2FormLoginAuthenticationFilter getOAuth2FormLoginAuthenticationFilter(
//            AuthenticationManager authenticationManager,
//            SecurityContextRepository securityContextRepository) {
//        OAuth2FormLoginAuthenticationFilter filter = new OAuth2FormLoginAuthenticationFilter(authenticationManager);
//        filter.setUsernameParameter(getFormLogin().getUsernameParameter());
//        filter.setPasswordParameter(getFormLogin().getPasswordParameter());
//        filter.setAuthenticationDetailsSource(new OAuth2FormLoginWebAuthenticationDetailSource(authenticationProperties));
//
//        filter.setAuthenticationFailureHandler(new OAuth2FormLoginAuthenticationFailureHandler(getFormLogin().getFailureForwardUrl()));
//        filter.setSecurityContextRepository(securityContextRepository);
//        return filter;
//    }
//
//    private OAuth2AuthenticationProperties.FormLogin getFormLogin() {
//        return authenticationProperties.getFormLogin();
//    }
//
//
//    @Override
//    public OAuth2FormLoginSecureConfigurer1<H> loginPage(String loginPage) {
//        return super.loginPage(loginPage);
//    }
//
//
//    public OAuth2FormLoginSecureConfigurer1<H> usernameParameter(String usernameParameter) {
//        this.getAuthenticationFilter().setUsernameParameter(usernameParameter);
//        return this;
//    }
//
//    public OAuth2FormLoginSecureConfigurer1<H> passwordParameter(String passwordParameter) {
//        this.getAuthenticationFilter().setPasswordParameter(passwordParameter);
//        return this;
//    }
//
//    public OAuth2FormLoginSecureConfigurer1<H> failureForwardUrl(String forwardUrl) {
//        this.failureHandler(new ForwardAuthenticationFailureHandler(forwardUrl));
//        return this;
//    }
//
//    public OAuth2FormLoginSecureConfigurer1<H> successForwardUrl(String forwardUrl) {
//        this.successHandler(new ForwardAuthenticationSuccessHandler(forwardUrl));
//        return this;
//    }
//
//    @Override
//    public void init(H http) throws Exception {
//        super.init(http);
//        this.initDefaultLoginFilter(http);
//    }
//
//    @Override
//    protected RequestMatcher createLoginProcessingUrlMatcher(String loginProcessingUrl) {
//        return new AntPathRequestMatcher(loginProcessingUrl, "POST");
//    }
//
//    private String getUsernameParameter() {
//        return this.getAuthenticationFilter().getUsernameParameter();
//    }
//
//    private String getPasswordParameter() {
//        return this.getAuthenticationFilter().getPasswordParameter();
//    }
//
//    private void initDefaultLoginFilter(H http) {
//        DefaultLoginPageGeneratingFilter loginPageGeneratingFilter = http.getSharedObject(DefaultLoginPageGeneratingFilter.class);
//        if (loginPageGeneratingFilter != null && !this.isCustomLoginPage()) {
//            loginPageGeneratingFilter.setFormLoginEnabled(true);
//            loginPageGeneratingFilter.setUsernameParameter(this.getUsernameParameter());
//            loginPageGeneratingFilter.setPasswordParameter(this.getPasswordParameter());
//            loginPageGeneratingFilter.setLoginPageUrl(this.getLoginPage());
//            loginPageGeneratingFilter.setFailureUrl(this.getFailureUrl());
//            loginPageGeneratingFilter.setAuthenticationUrl(this.getLoginProcessingUrl());
//        }
//
//    }
//}
