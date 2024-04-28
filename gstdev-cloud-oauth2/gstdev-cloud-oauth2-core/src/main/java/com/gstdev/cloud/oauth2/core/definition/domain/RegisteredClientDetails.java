// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================
package com.gstdev.cloud.oauth2.core.definition.domain;

import java.time.Instant;

/**
 * <p>Description: RegisteredClient 属性定义 </p>
 *
 * @author : cc
 * @date : 2023/5/12 23:10
 */
public interface RegisteredClientDetails {

    String getId();

    String getClientId();

    Instant getClientIdIssuedAt();

    String getClientSecret();

    Instant getClientSecretExpiresAt();

    String getClientAuthenticationMethods();

    String getAuthorizationGrantTypes();

    String getRedirectUris();

    String getPostLogoutRedirectUris();
}
