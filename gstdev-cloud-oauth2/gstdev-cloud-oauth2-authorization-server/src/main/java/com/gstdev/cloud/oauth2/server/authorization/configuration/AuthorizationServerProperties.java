// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.oauth2.server.authorization.configuration;

import com.gstdev.cloud.oauth2.server.authorization.constant.OAuth2Constants;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "gstdev.cloud.security.oauth2", ignoreUnknownFields = true)
public class AuthorizationServerProperties {

  //AuthorizationServerSettings
  private String issuerUri;
  private String authorizationEndpoint = OAuth2Constants.DEFAULT_AUTHORIZATION_ENDPOINT;
  private String deviceAuthorizationEndpoint = OAuth2Constants.DEFAULT_DEVICE_AUTHORIZATION_ENDPOINT;
  private String deviceVerificationEndpoint = OAuth2Constants.DEFAULT_DEVICE_VERIFICATION_ENDPOINT;
  private String tokenEndpoint = OAuth2Constants.DEFAULT_TOKEN_ENDPOINT;
  private String tokenIntrospectionEndpoint = OAuth2Constants.DEFAULT_TOKEN_REVOCATION_ENDPOINT;
  private String tokenRevocationEndpoint = OAuth2Constants.DEFAULT_TOKEN_INTROSPECTION_ENDPOINT;
  private String jwkSetEndpoint = OAuth2Constants.DEFAULT_JWK_SET_ENDPOINT;
  private String oidcLogoutEndpoint = OAuth2Constants.DEFAULT_OIDC_LOGOUNT_ENDPOINT;
  private String oidcUserInfoEndpoint = OAuth2Constants.DEFAULT_OIDC_USER_INFO_ENDPOINT;
  private String oidcClientRegistrationEndpoint = OAuth2Constants.DEFAULT_OIDC_CLIENT_REGISTRATION_ENDPOINT;


  private String jksJksPath = OAuth2Constants.DEFAULT_JKS_JKS_PATH;
  private String jksCerPath = OAuth2Constants.DEFAULT_JKS_CER_PATH;
  private String jksAlias = OAuth2Constants.DEFAULT_JKS_ALIAS;
  private String jksPass = OAuth2Constants.DEFAULT_JKS_PASS;
}

