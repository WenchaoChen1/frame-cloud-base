package com.gstdev.cloud.service.identity.domain.application;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gstdev.cloud.data.core.enums.DataItemStatus;
import com.gstdev.cloud.oauth2.core.enums.AllJwsAlgorithm;
import com.gstdev.cloud.oauth2.core.enums.ApplicationType;
import com.gstdev.cloud.oauth2.core.enums.SignatureJwsAlgorithm;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.time.Instant;

@Getter
@Setter
public class ApplicationManageDetailVO {

    private String applicationId;
    private String applicationName;
    private String abbreviation;
    private String logo;
    private String homepage;
    private ApplicationType applicationType;
    private String authorizationGrantTypes;
    private String clientAuthenticationMethods;
    private Instant clientSecretExpiresAt;
    private String redirectUris;
    private String postLogoutRedirectUris;
    private Boolean requireProofKey = Boolean.FALSE;
    private Boolean requireAuthorizationConsent = Boolean.TRUE;
    private String jwkSetUrl;
    private AllJwsAlgorithm authenticationSigningAlgorithm;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Duration accessTokenValidity = Duration.ofMinutes(5);
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Duration refreshTokenValidity = Duration.ofMinutes(60);
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Duration authorizationCodeValidity = Duration.ofMinutes(5);
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Duration deviceCodeValidity = Duration.ofMinutes(5);
    private Boolean reuseRefreshTokens = Boolean.TRUE;
    private SignatureJwsAlgorithm idTokenSignatureAlgorithmJwsAlgorithm = SignatureJwsAlgorithm.RS256;

    private String clientId;
    private String clientSecret;

}
