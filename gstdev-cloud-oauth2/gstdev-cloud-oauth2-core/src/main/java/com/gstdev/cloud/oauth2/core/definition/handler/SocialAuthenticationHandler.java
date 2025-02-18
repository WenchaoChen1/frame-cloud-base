// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================
package com.gstdev.cloud.oauth2.core.definition.handler;

import com.gstdev.cloud.base.definition.domain.oauth2.AccessPrincipal;
import com.gstdev.cloud.oauth2.core.definition.domain.DefaultSecurityUser;
import org.springframework.security.core.AuthenticationException;

/**
 * <p>Description: 社交登录处理器 </p>
 *
 * @author : cc
 * @date : 2021/4/4 17:34
 */
public interface SocialAuthenticationHandler {

    /**
     * 社交登录
     * <p>
     * 1. 首先在第三方系统进行认证，或者手机号码、扫码认证。返回认证后的信息
     * 2. 根据认证返回的信息，在系统中查询是否有对应的用户信息。
     * 2.1. 如果有对应的信息，根据需要更新社交用户的信息，然后返回系统用户信息，进行登录。
     * 2.2. 如果没有对应信息，就先进行用户的注册，然后进行社交用户和系统用户的绑定。
     *
     * @param source          社交登录提供者分类
     * @param accessPrincipal 社交登录所需要的信息 {@link AccessPrincipal}
     * @return 系统用户
     * @throws AuthenticationException {@link AuthenticationException} 认证错误
     */
    DefaultSecurityUser authentication(String source, AccessPrincipal accessPrincipal) throws AuthenticationException;

}
