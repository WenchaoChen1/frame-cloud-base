// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================
package com.gstdev.cloud.oauth2.authorization.server.customizer;

import com.gstdev.cloud.oauth2.authorization.server.properties.OAuth2AuthenticationProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;

/**
 * <p>Description: FormLoginConfigurer 扩展配置 </p>
 *
 * @author : cc
 * @date : 2023/9/1 8:45
 */
public class OAuth2FormLoginConfigurerCustomizer implements Customizer<FormLoginConfigurer<HttpSecurity>> {

  private final OAuth2AuthenticationProperties authenticationProperties;

  public OAuth2FormLoginConfigurerCustomizer(OAuth2AuthenticationProperties authenticationProperties) {
    this.authenticationProperties = authenticationProperties;
  }

  @Override
  public void customize(FormLoginConfigurer<HttpSecurity> configurer) {
    configurer
      .loginPage(getFormLogin().getLoginPageUrl())
      .usernameParameter(getFormLogin().getUsernameParameter())
      .passwordParameter(getFormLogin().getPasswordParameter());

    if (StringUtils.isNotBlank(getFormLogin().getFailureForwardUrl())) {
      configurer.failureForwardUrl(getFormLogin().getFailureForwardUrl());
    }
    if (StringUtils.isNotBlank(getFormLogin().getSuccessForwardUrl())) {
      configurer.successForwardUrl(getFormLogin().getSuccessForwardUrl());
    }
  }

  private OAuth2AuthenticationProperties.FormLogin getFormLogin() {
    return authenticationProperties.getFormLogin();
  }
}
