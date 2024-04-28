package com.gstdev.cloud.oauth2.management.domain.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.io.Serializable;


@Getter
@Setter
public class Oauth2AuthorizationVo implements Serializable {

    private String id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date  accessTokenExpiresAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date  accessTokenIssuedAt;
    private String accessTokenMetadata;
    private String accessTokenScopes;
    private String accessTokenType;
    private String accessTokenValue;
    private String attributes;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date  authorizationCodeExpiresAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date  authorizationCodeIssuedAt;
    private String authorizationCodeMetadata;
    private String authorizationCodeValue;
    private String authorizationGrantType;
    private String authorizedScopes;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date  deviceCodeExpiresAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date  deviceCodeIssuedAt;
    private String deviceCodeMetadata;
    private String deviceCodeValue;
    private String oidcIdTokenClaims;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date  oidcIdTokenExpiresAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date  oidcIdTokenIssuedAt;
    private String oidcIdTokenMetadata;
    private String oidcIdTokenValue;
    private String principalName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date  refreshTokenExpiresAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date  refreshTokenIssuedAt;
    private String refreshTokenMetadata;
    private String refreshTokenValue;
    private String registeredClientId;
    private String state;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date  userCodeExpiresAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date  userCodeIssuedAt;
    private String userCodeMetadata;
    private String userCodeValue;


}

