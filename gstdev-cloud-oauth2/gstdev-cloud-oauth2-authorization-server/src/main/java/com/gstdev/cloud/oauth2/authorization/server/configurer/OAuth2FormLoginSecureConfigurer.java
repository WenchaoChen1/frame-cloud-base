// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================
package com.gstdev.cloud.oauth2.authorization.server.configurer;

import com.gstdev.cloud.captcha.core.processor.CaptchaRendererFactory;
import com.gstdev.cloud.oauth2.authorization.server.provider.OAuth2FormLoginAuthenticationProvider;
import com.gstdev.cloud.oauth2.authorization.server.properties.OAuth2AuthenticationProperties;
import com.gstdev.cloud.oauth2.authorization.server.response.OAuth2FormLoginAuthenticationFailureHandler;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.SecurityContextRepository;

/**
 * <p>Description: OAuth2 Form Login Configurer </p>
 * <p>
 * 使用此种方式，相当于额外增加了一种表单登录方式。因此对原有的 http.formlogin进行的配置，对当前此种方式的配置并不生效。
 *
 * @author : cc
 * @date : 2022/4/12 13:29
 * @see org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer
 */
public class OAuth2FormLoginSecureConfigurer<H extends HttpSecurityBuilder<H>> extends AbstractHttpConfigurer<OAuth2FormLoginSecureConfigurer<H>, H> {

  private final UserDetailsService userDetailsService;
  private final OAuth2AuthenticationProperties authenticationProperties;
  private final CaptchaRendererFactory captchaRendererFactory;

  public OAuth2FormLoginSecureConfigurer(UserDetailsService userDetailsService, OAuth2AuthenticationProperties authenticationProperties, CaptchaRendererFactory captchaRendererFactory) {
    this.userDetailsService = userDetailsService;
    this.authenticationProperties = authenticationProperties;
    this.captchaRendererFactory = captchaRendererFactory;
  }

  @Override
  public void configure(H httpSecurity) throws Exception {

    AuthenticationManager authenticationManager = httpSecurity.getSharedObject(AuthenticationManager.class);
    SecurityContextRepository securityContextRepository = httpSecurity.getSharedObject(SecurityContextRepository.class);

    OAuth2FormLoginAuthenticationFilter filter = getOAuth2FormLoginAuthenticationFilter(authenticationManager, securityContextRepository);

    OAuth2FormLoginAuthenticationProvider provider = new OAuth2FormLoginAuthenticationProvider(captchaRendererFactory);
    provider.setUserDetailsService(userDetailsService);
    provider.setHideUserNotFoundExceptions(false);

    httpSecurity
      .authenticationProvider(provider)
      .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
  }

  private OAuth2FormLoginAuthenticationFilter getOAuth2FormLoginAuthenticationFilter(
    AuthenticationManager authenticationManager,
    SecurityContextRepository securityContextRepository) {
    OAuth2FormLoginAuthenticationFilter filter = new OAuth2FormLoginAuthenticationFilter(authenticationManager);
    filter.setUsernameParameter(getFormLogin().getUsernameParameter());
    filter.setPasswordParameter(getFormLogin().getPasswordParameter());
    filter.setAuthenticationDetailsSource(new OAuth2FormLoginWebAuthenticationDetailSource(authenticationProperties));

    filter.setAuthenticationFailureHandler(new OAuth2FormLoginAuthenticationFailureHandler(getFormLogin().getFailureForwardUrl()));
    filter.setSecurityContextRepository(securityContextRepository);
    return filter;
  }

  private OAuth2AuthenticationProperties.FormLogin getFormLogin() {
    return authenticationProperties.getFormLogin();
  }
}
