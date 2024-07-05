// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================
package com.gstdev.cloud.oauth2.authorization.server.provider;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.OAuth2AccessToken;

import java.util.Collection;
import java.util.Map;

/**
 * <p>Description: OAuth2 (Security) 表单登录 Token </p>
 *
 * @author : cc
 * @date : 2022/4/12 10:24
 * @see UsernamePasswordAuthenticationToken
 */
public class OAuth2FormLoginAuthenticationToken extends UsernamePasswordAuthenticationToken {
    private OAuth2AccessToken accessToken;
    private Map<String, Object> additionalParameters;
    public OAuth2FormLoginAuthenticationToken(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public OAuth2FormLoginAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }

    public OAuth2AccessToken getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(OAuth2AccessToken accessToken) {
        this.accessToken = accessToken;
    }

    public Map<String, Object> getAdditionalParameters() {
        return additionalParameters;
    }

    public void setAdditionalParameters(Map<String, Object> additionalParameters) {
        this.additionalParameters = additionalParameters;
    }
}
