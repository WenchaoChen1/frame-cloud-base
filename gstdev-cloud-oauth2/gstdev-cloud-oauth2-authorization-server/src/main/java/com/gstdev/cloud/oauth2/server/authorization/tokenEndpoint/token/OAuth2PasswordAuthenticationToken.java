//package com.gstdev.cloud.oauth2.server.authorization.tokenEndpoint.token;
//
//import org.apache.commons.collections4.CollectionUtils;
//import org.springframework.lang.Nullable;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.oauth2.core.AuthorizationGrantType;
//import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AuthorizationGrantAuthenticationToken;
//import org.springframework.util.Assert;
//
//import java.util.Collections;
//import java.util.HashSet;
//import java.util.Map;
//import java.util.Set;
//
//public class OAuth2PasswordAuthenticationToken extends OAuth2AuthorizationGrantAuthenticationToken {
//  private final Set<String> scopes;
//
//  public OAuth2PasswordAuthenticationToken(Authentication clientPrincipal, @Nullable Set<String> scopes, @Nullable Map<String, Object> additionalParameters) {
//    super(AuthorizationGrantType.PASSWORD, clientPrincipal, additionalParameters);
//    Assert.notNull(clientPrincipal, "clientPrincipal cannot be null");
//    this.scopes = Collections.unmodifiableSet((Set)(CollectionUtils.isNotEmpty(scopes) ? new HashSet(scopes) : Collections.emptySet()));
//  }
//
//  public Set<String> getScopes() {
//    return this.scopes;
//  }
//}
