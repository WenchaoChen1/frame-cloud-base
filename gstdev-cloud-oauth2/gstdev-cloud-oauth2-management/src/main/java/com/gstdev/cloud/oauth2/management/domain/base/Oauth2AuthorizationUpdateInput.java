package com.gstdev.cloud.oauth2.management.domain.base;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Date;
import java.io.Serializable;


@Getter
@Setter
public class Oauth2AuthorizationUpdateInput implements Serializable {

    private String id;
    private Instant accessTokenExpiresAt;
    private Instant accessTokenIssuedAt;
    private String accessTokenMetadata;
    private String accessTokenScopes;
    private String accessTokenType;
    private String accessTokenValue;
    private String attributes;
    private Instant authorizationCodeExpiresAt;
    private Instant authorizationCodeIssuedAt;
    private String authorizationCodeMetadata;
    private String authorizationCodeValue;
    private String authorizationGrantType;
    private String authorizedScopes;
    private Instant deviceCodeExpiresAt;
    private Instant deviceCodeIssuedAt;
    private String deviceCodeMetadata;
    private String deviceCodeValue;
    private String oidcIdTokenClaims;
    private Instant oidcIdTokenExpiresAt;
    private Instant oidcIdTokenIssuedAt;
    private String oidcIdTokenMetadata;
    private String oidcIdTokenValue;
    private String principalName;
    private Instant refreshTokenExpiresAt;
    private Instant refreshTokenIssuedAt;
    private String refreshTokenMetadata;
    private String refreshTokenValue;
    private String registeredClientId;
    private String state;
    private Instant userCodeExpiresAt;
    private Instant userCodeIssuedAt;
    private String userCodeMetadata;
    private String userCodeValue;


}

