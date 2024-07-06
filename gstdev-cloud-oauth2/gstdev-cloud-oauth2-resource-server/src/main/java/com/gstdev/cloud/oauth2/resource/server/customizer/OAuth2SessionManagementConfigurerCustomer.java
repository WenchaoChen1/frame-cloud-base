// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================
package com.gstdev.cloud.oauth2.resource.server.customizer;

import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer;
import org.springframework.security.core.session.SessionRegistry;

/**
 * <p>Description: SessionManagementConfigurer 扩展配置 </p>
 *
 * @author : cc
 * @date : 2023/8/31 21:32
 */
public class OAuth2SessionManagementConfigurerCustomer implements Customizer<SessionManagementConfigurer<HttpSecurity>> {

    private final SessionRegistry sessionRegistry;

    public OAuth2SessionManagementConfigurerCustomer(SessionRegistry sessionRegistry) {
        this.sessionRegistry = sessionRegistry;
    }


    @Override
    public void customize(SessionManagementConfigurer<HttpSecurity> configurer) {
        configurer.sessionAuthenticationStrategy(new FrameSessionAuthenticationStrategy(sessionRegistry));
    }
}
