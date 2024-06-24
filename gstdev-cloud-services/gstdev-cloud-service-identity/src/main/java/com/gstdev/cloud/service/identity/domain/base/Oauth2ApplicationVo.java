// ====================================================
//
// This file is part of the Riching Cloud Platform.
//
// Create by Riching Tech <support@richingtech.com>
// Copyright (c) 2020-2025 richingtech.com
//
// ====================================================

package com.gstdev.cloud.service.identity.domain.base;

import com.gstdev.cloud.data.core.enums.DataItemStatus;
import com.gstdev.cloud.oauth2.core.enums.ApplicationType;
import com.gstdev.cloud.oauth2.core.enums.TokenFormat;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.Duration;
import java.time.Instant;


@Getter
@Setter
public class Oauth2ApplicationVo implements Serializable {

    private String applicationId;
    private String createBy;
    private Instant createTime;
    private Integer ranking;
    private String updateBy;
    private Instant updateTime;
    private String description;
    private Boolean isReserved;
    private Integer reversion;
    private DataItemStatus status;
    private String authorizationGrantTypes;
    private String clientAuthenticationMethods;
    private Instant clientIdIssuedAt;
    private Instant clientSecretExpiresAt;
    private String postLogoutRedirectUris;
    private String redirectUris;
    private TokenFormat accessTokenFormat;
    private Duration accessTokenValidity;
    private Integer signingAlgorithm;
    private Duration authorizationCodeValidity;
    private String clientId;
    private String clientSecret;
    private Duration deviceCodeValidity;
    private Integer signatureAlgorithm;
    private String jwkSetUrl;
    private Duration refreshTokenValidity;
    private Boolean requireAuthorizationConsent;
    private Boolean requireProofKey;
    private Boolean reuseRefreshTokens;
    private String abbreviation;
    private String applicationName;
    private ApplicationType applicationType;
    private String homepage;
    private String logo;
    private Instant createdAt;
    private String createdBy;
    private Instant updatedAt;
    private String updatedBy;


}

