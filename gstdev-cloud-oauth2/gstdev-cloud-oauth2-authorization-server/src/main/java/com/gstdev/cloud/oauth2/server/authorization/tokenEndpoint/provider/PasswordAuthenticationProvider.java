// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.oauth2.server.authorization.tokenEndpoint.provider;

import com.gstdev.cloud.oauth2.server.authorization.tokenEndpoint.token.PasswordAuthenticationToken;
import com.gstdev.cloud.oauth2.server.authorization.utils.OAuth2EndpointUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.core.*;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AccessTokenAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2ClientAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.context.AuthorizationServerContextHolder;
import org.springframework.security.oauth2.server.authorization.token.DefaultOAuth2TokenContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenGenerator;
import org.springframework.util.CollectionUtils;

import java.security.Principal;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * 密码模式身份验证提供者
 * <p>
 * 处理基于用户名和密码的身份验证
 *
 */
@Slf4j
public class PasswordAuthenticationProvider implements AuthenticationProvider {

//  private final AuthenticationManager authenticationManager;
  private final OAuth2AuthorizationService oAuth2AuthorizationService;
  private final OAuth2TokenGenerator<? extends OAuth2Token> oAuth2TokenGenerator;

  /**
   * Constructs an {@code OAuth2ResourceOwnerPasswordAuthenticationProviderNew} using the provided parameters.
   *
   * @param authenticationManager the authentication manager
   * @param oAuth2AuthorizationService  the authorization service
   * @param oAuth2TokenGenerator        the token generator
   */
  public PasswordAuthenticationProvider(
//    AuthenticationManager authenticationManager,
                                        OAuth2AuthorizationService oAuth2AuthorizationService,
                                        OAuth2TokenGenerator<? extends OAuth2Token> oAuth2TokenGenerator) {
//    Assert.notNull(authorizationService, "authorizationService cannot be null");
//    Assert.notNull(tokenGenerator, "tokenGenerator cannot be null");
//    this.authenticationManager = authenticationManager;
    this.oAuth2AuthorizationService = oAuth2AuthorizationService;
    this.oAuth2TokenGenerator = oAuth2TokenGenerator;
  }

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    PasswordAuthenticationToken resourceOwnerPasswordAuthentication = (PasswordAuthenticationToken) authentication;
    OAuth2ClientAuthenticationToken clientPrincipal = getAuthenticatedClientElseThrowInvalidClient(resourceOwnerPasswordAuthentication);

    RegisteredClient registeredClient = clientPrincipal.getRegisteredClient();

    // 验证客户端是否支持授权类型(grant_type=password)
    if (!registeredClient.getAuthorizationGrantTypes().contains(AuthorizationGrantType.PASSWORD)) {
      throw new OAuth2AuthenticationException(OAuth2ErrorCodes.UNAUTHORIZED_CLIENT);
    }

    // 生成用户名密码身份验证令牌
    Map<String, Object> additionalParameters = resourceOwnerPasswordAuthentication.getAdditionalParameters();
    String username = (String) additionalParameters.get(OAuth2ParameterNames.USERNAME);
    String password = (String) additionalParameters.get(OAuth2ParameterNames.PASSWORD);

    // 用户名密码身份验证，成功后返回带有权限的认证信息
    Authentication usernamePasswordAuthentication = null;
    try {
      UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);
//      usernamePasswordAuthentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
//      log.debug("[GstDev Cloud] |- Resource Owner Password username and password authenticate success ：[{}]", usernamePasswordAuthentication);
    } catch (AccountStatusException | BadCredentialsException ase) {
      // 需要将其他类型的异常转换为 OAuth2AuthenticationException 才能被自定义异常捕获处理，逻辑源码 OAuth2TokenEndpointFilter#doFilterInternal
      OAuth2EndpointUtils.throwError(OAuth2ErrorCodes.INVALID_GRANT, ase.getMessage(), OAuth2EndpointUtils.ACCESS_TOKEN_REQUEST_ERROR_URI);
    }

    // 验证申请访问范围(Scope)
    Set<String> authorizedScopes = registeredClient.getScopes();
    if (!CollectionUtils.isEmpty(resourceOwnerPasswordAuthentication.getScopes())) {
      for (String requestedScope : resourceOwnerPasswordAuthentication.getScopes()) {
        if (!registeredClient.getScopes().contains(requestedScope)) {
          throw new OAuth2AuthenticationException(OAuth2ErrorCodes.INVALID_SCOPE);
        }
      }
      authorizedScopes = new LinkedHashSet<>(resourceOwnerPasswordAuthentication.getScopes());
    }

    // 访问令牌(Access Token) 构造器
    // @formatter:off
    DefaultOAuth2TokenContext.Builder tokenContextBuilder = DefaultOAuth2TokenContext.builder()
      .registeredClient(registeredClient)
      .principal(usernamePasswordAuthentication) // 身份验证成功的认证信息(用户名、权限等信息)
      .authorizationServerContext(AuthorizationServerContextHolder.getContext())
      .authorizedScopes(authorizedScopes)
      .tokenType(OAuth2TokenType.ACCESS_TOKEN)
      .authorizationGrantType(AuthorizationGrantType.PASSWORD)// 授权方式
      .authorizationGrant(resourceOwnerPasswordAuthentication);// 授权具体对象



    // @formatter:on

    // 生成访问令牌(Access Token)
    OAuth2TokenContext tokenContext = tokenContextBuilder.tokenType(OAuth2TokenType.ACCESS_TOKEN).build();
    OAuth2Token generatedAccessToken = oAuth2TokenGenerator.generate(tokenContext);
    if (generatedAccessToken == null) {
//      The token generator failed to generate the access token.
      OAuth2EndpointUtils.throwError(OAuth2ErrorCodes.SERVER_ERROR, "OAuth2TokenGenerator failed to generate AccessToken.", OAuth2EndpointUtils.ACCESS_TOKEN_REQUEST_ERROR_URI);
    }

    OAuth2AccessToken accessToken = new OAuth2AccessToken(OAuth2AccessToken.TokenType.BEARER, generatedAccessToken.getTokenValue(), generatedAccessToken.getIssuedAt(), generatedAccessToken.getExpiresAt(), tokenContext.getAuthorizedScopes());

    OAuth2Authorization.Builder authorizationBuilder = OAuth2Authorization.withRegisteredClient(registeredClient)
      .principalName(usernamePasswordAuthentication.getName())
      .authorizationGrantType(AuthorizationGrantType.PASSWORD)
      .attribute(Principal.class.getName(), usernamePasswordAuthentication)
      ;
    if (generatedAccessToken instanceof ClaimAccessor) {
      authorizationBuilder.token(accessToken, (metadata) -> metadata.put(OAuth2Authorization.Token.CLAIMS_METADATA_NAME, ((ClaimAccessor) generatedAccessToken).getClaims()));
    } else {
      authorizationBuilder.accessToken(accessToken);
    }

    // 生成刷新令牌(Refresh Token)
    OAuth2RefreshToken refreshToken = null;
    if (registeredClient.getAuthorizationGrantTypes().contains(AuthorizationGrantType.REFRESH_TOKEN) &&
      // Do not issue refresh token to public client
      !clientPrincipal.getClientAuthenticationMethod().equals(ClientAuthenticationMethod.NONE)) {

      tokenContext = tokenContextBuilder.tokenType(OAuth2TokenType.REFRESH_TOKEN).build();
      OAuth2Token generatedRefreshToken = oAuth2TokenGenerator.generate(tokenContext);
      if (!(generatedRefreshToken instanceof OAuth2RefreshToken)) {
        OAuth2EndpointUtils.throwError(OAuth2ErrorCodes.SERVER_ERROR, "OAuth2TokenGenerator failed to generate RefreshToken.", OAuth2EndpointUtils.ACCESS_TOKEN_REQUEST_ERROR_URI);
      }

      refreshToken = (OAuth2RefreshToken) generatedRefreshToken;
      authorizationBuilder.refreshToken(refreshToken);
    }

    OAuth2Authorization authorization = authorizationBuilder.build();

    // 持久化令牌发放记录到数据库
    oAuth2AuthorizationService.save(authorization);

    log.debug("[GstDev Cloud] |- Resource Owner Password returning OAuth2AccessTokenAuthenticationToken.");

    return new OAuth2AccessTokenAuthenticationToken(registeredClient, clientPrincipal, accessToken, refreshToken);
  }

  /**
   * 判断传入的 authentication 类型是否与当前认证提供者(AuthenticationProvider)相匹配--模板方法
   * <p>
   * ProviderManager#authenticate 遍历 providers 找到支持对应认证请求的 provider-迭代器模式
   *
   * @param authentication
   * @return
   */
  @Override
  public boolean supports(Class<?> authentication) {
    boolean supports = PasswordAuthenticationToken.class.isAssignableFrom(authentication);
    log.trace("[GstDev Cloud] |- Resource Owner Password Authentication is supports! [{}]", supports);

    return supports;
  }

  private OAuth2ClientAuthenticationToken getAuthenticatedClientElseThrowInvalidClient(Authentication authentication) {
    OAuth2ClientAuthenticationToken clientPrincipal = null;
    if (OAuth2ClientAuthenticationToken.class.isAssignableFrom(authentication.getPrincipal().getClass())) {
      clientPrincipal = (OAuth2ClientAuthenticationToken) authentication.getPrincipal();
    }

    if (clientPrincipal != null && clientPrincipal.isAuthenticated()) {
      return clientPrincipal;
    }

    throw new OAuth2AuthenticationException(OAuth2ErrorCodes.INVALID_CLIENT);
  }
}
