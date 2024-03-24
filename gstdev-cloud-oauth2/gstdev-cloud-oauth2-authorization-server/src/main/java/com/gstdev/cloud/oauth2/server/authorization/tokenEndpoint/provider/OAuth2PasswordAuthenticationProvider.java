package com.gstdev.cloud.oauth2.server.authorization.tokenEndpoint.provider;

import com.gstdev.cloud.oauth2.server.authorization.properties.OAuth2AuthenticationProperties;
import com.gstdev.cloud.oauth2.server.authorization.service.DefaultUserDetailsService;
import com.gstdev.cloud.oauth2.server.authorization.tokenEndpoint.token.OAuth2PasswordAuthenticationToken;
import com.gstdev.cloud.oauth2.server.authorization.tokenEndpoint.token.PasswordAuthenticationToken;
import com.gstdev.cloud.oauth2.server.authorization.utils.OAuth2AuthenticationProviderUtils;
import com.gstdev.cloud.oauth2.server.authorization.utils.OAuth2EndpointUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.core.*;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
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
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.security.Principal;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;


public class OAuth2PasswordAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
  private static final Logger log = LoggerFactory.getLogger(OAuth2PasswordAuthenticationProvider.class);
  private static final String ERROR_URI = "https://datatracker.ietf.org/doc/html/rfc6749#section-5.2";
  private final OAuth2AuthorizationService authorizationService;
  private final OAuth2TokenGenerator<? extends OAuth2Token> tokenGenerator;
  private SessionRegistry sessionRegistry;

  public OAuth2PasswordAuthenticationProvider(OAuth2AuthorizationService authorizationService, OAuth2TokenGenerator<? extends OAuth2Token> tokenGenerator, UserDetailsService userDetailsService, OAuth2AuthenticationProperties complianceProperties) {
    super(authorizationService, userDetailsService, complianceProperties);
    Assert.notNull(tokenGenerator, "tokenGenerator cannot be null");
    this.authorizationService = authorizationService;
    this.tokenGenerator = tokenGenerator;
  }

  protected void additionalAuthenticationChecks(UserDetails userDetails, Map<String, Object> additionalParameters) throws AuthenticationException {
    String presentedPassword = (String) additionalParameters.get("password");
    if (!this.getPasswordEncoder().matches(presentedPassword, userDetails.getPassword())) {
      log.debug("[Herodotus] |- Failed to authenticate since password does not match stored value");
      throw new BadCredentialsException("Bad credentials");
    }
  }

  protected UserDetails retrieveUser(Map<String, Object> additionalParameters) throws AuthenticationException {
    String username = (String) additionalParameters.get("username");

    try {
      DefaultUserDetailsService enhanceUserDetailsService = this.getUserDetailsService();
      UserDetails userDetails = enhanceUserDetailsService.loadUserByUsername(username);
      if (userDetails == null) {
        throw new InternalAuthenticationServiceException("UserDetailsService returned null, which is an interface contract violation");
      } else {
        return userDetails;
      }
    } catch (UsernameNotFoundException var5) {
      log.error("[Herodotus] |- User name can not found ：[{}]", username);
      throw var5;
    } catch (InternalAuthenticationServiceException var6) {
      throw var6;
    } catch (Exception var7) {
      throw new InternalAuthenticationServiceException(var7.getMessage(), var7);
    }
  }

  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    OAuth2PasswordAuthenticationToken resourceOwnerPasswordAuthentication = (OAuth2PasswordAuthenticationToken) authentication;
    OAuth2ClientAuthenticationToken clientPrincipal = OAuth2AuthenticationProviderUtils.getAuthenticatedClientElseThrowInvalidClient(resourceOwnerPasswordAuthentication);
    RegisteredClient registeredClient = clientPrincipal.getRegisteredClient();
    if (!registeredClient.getAuthorizationGrantTypes().contains(AuthorizationGrantType.PASSWORD)) {
      throw new OAuth2AuthenticationException("unauthorized_client");
    } else {
      Authentication principal = this.getUsernamePasswordAuthentication(resourceOwnerPasswordAuthentication.getAdditionalParameters(), registeredClient.getId());
      Set<String> authorizedScopes = this.validateScopes(resourceOwnerPasswordAuthentication.getScopes(), registeredClient);

      OAuth2Authorization.Builder authorizationBuilder = OAuth2Authorization.withRegisteredClient(registeredClient)
        .principalName(principal.getName())
        .authorizationGrantType(AuthorizationGrantType.PASSWORD)
        .authorizedScopes(authorizedScopes).attribute(Principal.class.getName(), principal);

      DefaultOAuth2TokenContext.Builder tokenContextBuilder =  DefaultOAuth2TokenContext.builder().registeredClient(registeredClient)
        .principal(principal)
        .authorizationServerContext(AuthorizationServerContextHolder.getContext())
        .authorizedScopes(authorizedScopes).tokenType(OAuth2TokenType.ACCESS_TOKEN)
        .authorizationGrantType(AuthorizationGrantType.PASSWORD).authorizationGrant(resourceOwnerPasswordAuthentication);

      OAuth2AccessToken accessToken = this.createOAuth2AccessToken(tokenContextBuilder, authorizationBuilder, this.tokenGenerator, "https://datatracker.ietf.org/doc/html/rfc6749#section-5.2");
      OAuth2RefreshToken refreshToken = this.creatOAuth2RefreshToken(tokenContextBuilder, authorizationBuilder, this.tokenGenerator, "https://datatracker.ietf.org/doc/html/rfc6749#section-5.2", clientPrincipal, registeredClient);
//      OidcIdToken idToken = this.createOidcIdToken(principal, this.sessionRegistry, tokenContextBuilder, authorizationBuilder, this.tokenGenerator, "https://datatracker.ietf.org/doc/html/rfc6749#section-5.2", resourceOwnerPasswordAuthentication.getScopes());
      OidcIdToken idToken = this.createOidcIdToken(principal, null, tokenContextBuilder, authorizationBuilder, this.tokenGenerator, "https://datatracker.ietf.org/doc/html/rfc6749#section-5.2", resourceOwnerPasswordAuthentication.getScopes());
      OAuth2Authorization authorization = authorizationBuilder.build();
      this.authorizationService.save(authorization);
      log.debug("[Herodotus] |- Resource Owner Password returning OAuth2AccessTokenAuthenticationToken.");
      Map<String, Object> additionalParameters = this.idTokenAdditionalParameters(idToken);
      OAuth2AccessTokenAuthenticationToken accessTokenAuthenticationToken = new OAuth2AccessTokenAuthenticationToken(registeredClient, clientPrincipal, accessToken, refreshToken, additionalParameters);
      return this.createOAuth2AccessTokenAuthenticationToken(principal, accessTokenAuthenticationToken);
    }
  }

  public void setSessionRegistry(SessionRegistry sessionRegistry) {
    Assert.notNull(sessionRegistry, "sessionRegistry cannot be null");
    this.sessionRegistry = sessionRegistry;
  }

  public boolean supports(Class<?> authentication) {
    boolean supports = OAuth2PasswordAuthenticationToken.class.isAssignableFrom(authentication);
    log.trace("[Herodotus] |- Resource Owner Password Authentication is supports! [{}]", supports);
    return supports;
  }


//
//  @Override
//  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//    PasswordAuthenticationToken resourceOwnerPasswordAuthentication = (PasswordAuthenticationToken) authentication;
//    OAuth2ClientAuthenticationToken clientPrincipal = getAuthenticatedClientElseThrowInvalidClient(resourceOwnerPasswordAuthentication);
//
//    RegisteredClient registeredClient = clientPrincipal.getRegisteredClient();
//
//    // 验证客户端是否支持授权类型(grant_type=password)
//    if (!registeredClient.getAuthorizationGrantTypes().contains(AuthorizationGrantType.PASSWORD)) {
//      throw new OAuth2AuthenticationException(OAuth2ErrorCodes.UNAUTHORIZED_CLIENT);
//    }
//
//    // 生成用户名密码身份验证令牌
//    Map<String, Object> additionalParameters = resourceOwnerPasswordAuthentication.getAdditionalParameters();
//    String username = (String) additionalParameters.get(OAuth2ParameterNames.USERNAME);
//    String password = (String) additionalParameters.get(OAuth2ParameterNames.PASSWORD);
//
//    // 用户名密码身份验证，成功后返回带有权限的认证信息
//    Authentication usernamePasswordAuthentication = null;
//    try {
//      UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);
////      usernamePasswordAuthentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
////      log.debug("[GstDev Cloud] |- Resource Owner Password username and password authenticate success ：[{}]", usernamePasswordAuthentication);
//    } catch (AccountStatusException | BadCredentialsException ase) {
//      // 需要将其他类型的异常转换为 OAuth2AuthenticationException 才能被自定义异常捕获处理，逻辑源码 OAuth2TokenEndpointFilter#doFilterInternal
//      OAuth2EndpointUtils.throwError(OAuth2ErrorCodes.INVALID_GRANT, ase.getMessage(), OAuth2EndpointUtils.ACCESS_TOKEN_REQUEST_ERROR_URI);
//    }
//
//    // 验证申请访问范围(Scope)
//    Set<String> authorizedScopes = registeredClient.getScopes();
//    if (!CollectionUtils.isEmpty(resourceOwnerPasswordAuthentication.getScopes())) {
//      for (String requestedScope : resourceOwnerPasswordAuthentication.getScopes()) {
//        if (!registeredClient.getScopes().contains(requestedScope)) {
//          throw new OAuth2AuthenticationException(OAuth2ErrorCodes.INVALID_SCOPE);
//        }
//      }
//      authorizedScopes = new LinkedHashSet<>(resourceOwnerPasswordAuthentication.getScopes());
//    }
//
//    // 访问令牌(Access Token) 构造器
//    // @formatter:off
//    DefaultOAuth2TokenContext.Builder tokenContextBuilder = DefaultOAuth2TokenContext.builder()
//      .registeredClient(registeredClient)
//      .principal(usernamePasswordAuthentication) // 身份验证成功的认证信息(用户名、权限等信息)
//      .authorizationServerContext(AuthorizationServerContextHolder.getContext())
//      .authorizedScopes(authorizedScopes)
//      .tokenType(OAuth2TokenType.ACCESS_TOKEN)
//      .authorizationGrantType(AuthorizationGrantType.PASSWORD)// 授权方式
//      .authorizationGrant(resourceOwnerPasswordAuthentication);// 授权具体对象
//
//
//
//    // @formatter:on
//
//    // 生成访问令牌(Access Token)
//    OAuth2TokenContext tokenContext = tokenContextBuilder.tokenType(OAuth2TokenType.ACCESS_TOKEN).build();
//    OAuth2Token generatedAccessToken = tokenGenerator.generate(tokenContext);
//    if (generatedAccessToken == null) {
////      The token generator failed to generate the access token.
//      OAuth2EndpointUtils.throwError(OAuth2ErrorCodes.SERVER_ERROR, "OAuth2TokenGenerator failed to generate AccessToken.", OAuth2EndpointUtils.ACCESS_TOKEN_REQUEST_ERROR_URI);
//    }
//
//    OAuth2AccessToken accessToken = new OAuth2AccessToken(OAuth2AccessToken.TokenType.BEARER, generatedAccessToken.getTokenValue(), generatedAccessToken.getIssuedAt(), generatedAccessToken.getExpiresAt(), tokenContext.getAuthorizedScopes());
//
//    OAuth2Authorization.Builder authorizationBuilder = OAuth2Authorization.withRegisteredClient(registeredClient)
//      .principalName(usernamePasswordAuthentication.getName())
//      .authorizationGrantType(AuthorizationGrantType.PASSWORD)
//      .attribute(Principal.class.getName(), usernamePasswordAuthentication)
//      ;
//    if (generatedAccessToken instanceof ClaimAccessor) {
//      authorizationBuilder.token(accessToken, (metadata) -> metadata.put(OAuth2Authorization.Token.CLAIMS_METADATA_NAME, ((ClaimAccessor) generatedAccessToken).getClaims()));
//    } else {
//      authorizationBuilder.accessToken(accessToken);
//    }
//
//    // 生成刷新令牌(Refresh Token)
//    OAuth2RefreshToken refreshToken = null;
//    if (registeredClient.getAuthorizationGrantTypes().contains(AuthorizationGrantType.REFRESH_TOKEN) &&
//      // Do not issue refresh token to public client
//      !clientPrincipal.getClientAuthenticationMethod().equals(ClientAuthenticationMethod.NONE)) {
//
//      tokenContext = tokenContextBuilder.tokenType(OAuth2TokenType.REFRESH_TOKEN).build();
//      OAuth2Token generatedRefreshToken = oAuth2TokenGenerator.generate(tokenContext);
//      if (!(generatedRefreshToken instanceof OAuth2RefreshToken)) {
//        OAuth2EndpointUtils.throwError(OAuth2ErrorCodes.SERVER_ERROR, "OAuth2TokenGenerator failed to generate RefreshToken.", OAuth2EndpointUtils.ACCESS_TOKEN_REQUEST_ERROR_URI);
//      }
//
//      refreshToken = (OAuth2RefreshToken) generatedRefreshToken;
//      authorizationBuilder.refreshToken(refreshToken);
//    }
//
//    OAuth2Authorization authorization = authorizationBuilder.build();
//
//    // 持久化令牌发放记录到数据库
////    oAuth2AuthorizationService.save(authorization);
//
//    log.debug("[GstDev Cloud] |- Resource Owner Password returning OAuth2AccessTokenAuthenticationToken.");
//
//    return new OAuth2AccessTokenAuthenticationToken(registeredClient, clientPrincipal, accessToken, refreshToken);
//  }
//
//  /**
//   * 判断传入的 authentication 类型是否与当前认证提供者(AuthenticationProvider)相匹配--模板方法
//   * <p>
//   * ProviderManager#authenticate 遍历 providers 找到支持对应认证请求的 provider-迭代器模式
//   *
//   * @param authentication
//   * @return
//   */
//  @Override
//  public boolean supports(Class<?> authentication) {
//    boolean supports = PasswordAuthenticationToken.class.isAssignableFrom(authentication);
//    log.trace("[GstDev Cloud] |- Resource Owner Password Authentication is supports! [{}]", supports);
//
//    return supports;
//  }
//
//  private OAuth2ClientAuthenticationToken getAuthenticatedClientElseThrowInvalidClient(Authentication authentication) {
//    OAuth2ClientAuthenticationToken clientPrincipal = null;
//    if (OAuth2ClientAuthenticationToken.class.isAssignableFrom(authentication.getPrincipal().getClass())) {
//      clientPrincipal = (OAuth2ClientAuthenticationToken) authentication.getPrincipal();
//    }
//
//    if (clientPrincipal != null && clientPrincipal.isAuthenticated()) {
//      return clientPrincipal;
//    }
//
//    throw new OAuth2AuthenticationException(OAuth2ErrorCodes.INVALID_CLIENT);
//  }
}
