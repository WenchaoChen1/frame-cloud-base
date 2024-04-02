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

    // 构造函数，接收 OAuth2AuthenticationProperties 对象
    public OAuth2FormLoginConfigurerCustomizer(OAuth2AuthenticationProperties authenticationProperties) {
        this.authenticationProperties = authenticationProperties;
    }

    // 实现 Customizer 接口的方法，用于定制化 FormLoginConfigurer
    @Override
    public void customize(FormLoginConfigurer<HttpSecurity> configurer) {
        // 设置登录页面 URL
        configurer
            .loginPage(getFormLogin().getLoginPageUrl())
            // 设置用户名参数名
            .usernameParameter(getFormLogin().getUsernameParameter())
            // 设置密码参数名
            .passwordParameter(getFormLogin().getPasswordParameter());

        // 如果设置了失败转发 URL，则进行配置
        if (StringUtils.isNotBlank(getFormLogin().getFailureForwardUrl())) {
            configurer.failureForwardUrl(getFormLogin().getFailureForwardUrl());
        }
        // 如果设置了成功转发 URL，则进行配置
        if (StringUtils.isNotBlank(getFormLogin().getSuccessForwardUrl())) {
            configurer.successForwardUrl(getFormLogin().getSuccessForwardUrl());
        }
    }

    // 获取 FormLogin 对象
    private OAuth2AuthenticationProperties.FormLogin getFormLogin() {
        return authenticationProperties.getFormLogin();
    }
}
