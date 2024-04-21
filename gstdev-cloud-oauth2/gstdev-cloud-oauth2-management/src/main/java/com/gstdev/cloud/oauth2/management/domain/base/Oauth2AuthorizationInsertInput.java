// ====================================================
//
// This file is part of the Riching Cloud Platform.
//
// Create by Riching Tech <support@richingtech.com>
// Copyright (c) 2020-2025 richingtech.com
//
// ====================================================

package com.gstdev.cloud.oauth2.management.domain.base;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.io.Serializable;


@Getter
@Setter
public class Oauth2AuthorizationInsertInput implements Serializable {

    private String id;
    private LocalDateTime accessTokenExpiresAt;
    private LocalDateTime accessTokenIssuedAt;
    private String accessTokenMetadata;
    private String accessTokenScopes;
    private String accessTokenType;
    private String accessTokenValue;
    private String attributes;
    private LocalDateTime authorizationCodeExpiresAt;
    private LocalDateTime authorizationCodeIssuedAt;
    private String authorizationCodeMetadata;
    private String authorizationCodeValue;
    private String authorizationGrantType;
    private String authorizedScopes;
    private LocalDateTime deviceCodeExpiresAt;
    private LocalDateTime deviceCodeIssuedAt;
    private String deviceCodeMetadata;
    private String deviceCodeValue;
    private String oidcIdTokenClaims;
    private LocalDateTime oidcIdTokenExpiresAt;
    private LocalDateTime oidcIdTokenIssuedAt;
    private String oidcIdTokenMetadata;
    private String oidcIdTokenValue;
    private String principalName;
    private LocalDateTime refreshTokenExpiresAt;
    private LocalDateTime refreshTokenIssuedAt;
    private String refreshTokenMetadata;
    private String refreshTokenValue;
    private String registeredClientId;
    private String state;
    private LocalDateTime userCodeExpiresAt;
    private LocalDateTime userCodeIssuedAt;
    private String userCodeMetadata;
    private String userCodeValue;


}

