// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================
package com.gstdev.cloud.oauth2.authorization.server.customizer;

import com.gstdev.cloud.base.definition.constants.BaseConstants;
import com.gstdev.cloud.oauth2.core.definition.domain.HerodotusUser;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AccessTokenAuthenticationToken;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>Description: TokenCustomizer 通用处理 </p>
 *
 * @author : cc
 * @date : 2022/10/12 10:20
 */
public abstract class AbstractTokenCustomizer {

    // 将认证信息中的所有属性添加到attributes中
    protected void appendAll(Map<String, Object> attributes, Authentication authentication, Set<String> authorizedScopes) {
        appendAuthorities(attributes, authentication); // 添加权限信息
        appendCommons(attributes, authentication, authorizedScopes); // 添加其他通用信息
    }

    // 添加权限信息到attributes中
    protected void appendAuthorities(Map<String, Object> attributes, Authentication authentication) {

        if (CollectionUtils.isNotEmpty(authentication.getAuthorities())) {
            Set<String> authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());
            attributes.put(BaseConstants.AUTHORITIES, authorities);
        }
    }

    // 添加通用信息到attributes中
    protected void appendCommons(Map<String, Object> attributes, Authentication authentication, Set<String> authorizedScopes) {
        if (CollectionUtils.isNotEmpty(authorizedScopes)) {
            attributes.put(OAuth2ParameterNames.SCOPE, authorizedScopes); // 添加授权范围
        }

        if (authentication instanceof UsernamePasswordAuthenticationToken) {
            putUserInfo(attributes, authentication.getPrincipal()); // 如果是用户名密码认证，添加用户信息
        }

        if (authentication instanceof OAuth2AccessTokenAuthenticationToken) {
            Object details = authentication.getDetails();
            putUserInfo(attributes, details); // 如果是OAuth2访问令牌认证，添加用户信息
        }

        attributes.put("license", "Apache-2.0 Licensed | Copyright © 2020-2025  CCC ");
    }

    // 添加用户信息到attributes中
    private void putUserInfo(Map<String, Object> attributes, Object object) {
        if (ObjectUtils.isNotEmpty(object) && object instanceof HerodotusUser principal) {
            attributes.put(BaseConstants.OPEN_ID, principal.getUserId());
            attributes.put(BaseConstants.ROLES, principal.getRoles());
            attributes.put(BaseConstants.AVATAR, principal.getAvatar());
            attributes.put(BaseConstants.EMPLOYEE_ID, principal.getEmployeeId());
        }
    }
}
