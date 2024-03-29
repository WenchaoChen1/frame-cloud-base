package com.gstdev.cloud.oauth2.authentication.provider;

import com.gstdev.cloud.oauth2.authentication.properties.OAuth2AuthenticationProperties;
import com.gstdev.cloud.oauth2.authentication.token.OAuth2PasswordAuthenticationToken;
import com.gstdev.cloud.oauth2.authentication.utils.OAuth2AuthenticationProviderUtils;
import com.gstdev.cloud.oauth2.core.definition.HerodotusGrantType;
import com.gstdev.cloud.oauth2.core.definition.service.EnhanceUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
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
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenGenerator;
import org.springframework.util.Assert;

import java.security.Principal;
import java.util.Map;
import java.util.Set;


public class OAuth2PasswordAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
  private static final Logger log = LoggerFactory.getLogger(OAuth2PasswordAuthenticationProvider.class);
  private static final String ERROR_URI = "https://datatracker.ietf.org/doc/html/rfc6749#section-5.2";
  private final OAuth2AuthorizationService authorizationService;
  private final OAuth2TokenGenerator<? extends OAuth2Token> tokenGenerator;
  private SessionRegistry sessionRegistry;

  /**
   * Constructs an {@code OAuth2ClientCredentialsAuthenticationProvider} using the provided parameters.
   *
   * @param authorizationService the authorization service
   * @param tokenGenerator       – the token generator
   */
  public OAuth2PasswordAuthenticationProvider(OAuth2AuthorizationService authorizationService, OAuth2TokenGenerator<? extends OAuth2Token> tokenGenerator, UserDetailsService userDetailsService, OAuth2AuthenticationProperties complianceProperties) {
    super(authorizationService, userDetailsService, complianceProperties);
    Assert.notNull(tokenGenerator, "tokenGenerator cannot be null");
    this.authorizationService = authorizationService;
    this.tokenGenerator = tokenGenerator;
  }

  protected void additionalAuthenticationChecks(UserDetails userDetails, Map<String, Object> additionalParameters) throws AuthenticationException {
    String presentedPassword = (String) additionalParameters.get(OAuth2ParameterNames.PASSWORD);
    if (!this.getPasswordEncoder().matches(presentedPassword, userDetails.getPassword())) {
      log.debug("[GstDev Cloud] |- Failed to authenticate since password does not match stored value");
      throw new BadCredentialsException("Bad credentials");
    }
  }

  protected UserDetails retrieveUser(Map<String, Object> additionalParameters) throws AuthenticationException {
    String username = (String) additionalParameters.get(OAuth2ParameterNames.USERNAME);
    try {
      EnhanceUserDetailsService enhanceUserDetailsService = getUserDetailsService();
      UserDetails userDetails = enhanceUserDetailsService.loadUserByUsername(username);
      if (userDetails == null) {
        throw new InternalAuthenticationServiceException(
          "UserDetailsService returned null, which is an interface contract violation");
      }
      return userDetails;
    } catch (UsernameNotFoundException ex) {
      log.error("[GstDev Cloud] |- User name can not found ：[{}]", username);
      throw ex;
    } catch (InternalAuthenticationServiceException ex) {
      throw ex;
    } catch (Exception ex) {
      throw new InternalAuthenticationServiceException(ex.getMessage(), ex);
    }
  }

//  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//    OAuth2PasswordAuthenticationToken resourceOwnerPasswordAuthentication = (OAuth2PasswordAuthenticationToken) authentication;
//    OAuth2ClientAuthenticationToken clientPrincipal = OAuth2AuthenticationProviderUtils.getAuthenticatedClientElseThrowInvalidClient(resourceOwnerPasswordAuthentication);
//    RegisteredClient registeredClient = clientPrincipal.getRegisteredClient();
//    if (!registeredClient.getAuthorizationGrantTypes().contains(AuthorizationGrantType.PASSWORD)) {
//      throw new OAuth2AuthenticationException("unauthorized_client");
//    } else {
//      Authentication principal = this.getUsernamePasswordAuthentication(resourceOwnerPasswordAuthentication.getAdditionalParameters(), registeredClient.getId());
//      Set<String> authorizedScopes = this.validateScopes(resourceOwnerPasswordAuthentication.getScopes(), registeredClient);
//
//      OAuth2Authorization.Builder authorizationBuilder = OAuth2Authorization.withRegisteredClient(registeredClient)
//        .principalName(principal.getName())
//        .authorizationGrantType(AuthorizationGrantType.PASSWORD)
//        .authorizedScopes(authorizedScopes).attribute(Principal.class.getName(), principal);
//
//      DefaultOAuth2TokenContext.Builder tokenContextBuilder =  DefaultOAuth2TokenContext.builder().registeredClient(registeredClient)
//        .principal(principal)
//        .authorizationServerContext(AuthorizationServerContextHolder.getContext())
//        .authorizedScopes(authorizedScopes).tokenType(OAuth2TokenType.ACCESS_TOKEN)
//        .authorizationGrantType(AuthorizationGrantType.PASSWORD).authorizationGrant(resourceOwnerPasswordAuthentication);
//
//      OAuth2AccessToken accessToken = this.createOAuth2AccessToken(tokenContextBuilder, authorizationBuilder, this.tokenGenerator, "https://datatracker.ietf.org/doc/html/rfc6749#section-5.2");
//      OAuth2RefreshToken refreshToken = this.creatOAuth2RefreshToken(tokenContextBuilder, authorizationBuilder, this.tokenGenerator, "https://datatracker.ietf.org/doc/html/rfc6749#section-5.2", clientPrincipal, registeredClient);
////      OidcIdToken idToken = this.createOidcIdToken(principal, this.sessionRegistry, tokenContextBuilder, authorizationBuilder, this.tokenGenerator, "https://datatracker.ietf.org/doc/html/rfc6749#section-5.2", resourceOwnerPasswordAuthentication.getScopes());
//      OidcIdToken idToken = this.createOidcIdToken(principal, null, tokenContextBuilder, authorizationBuilder, this.tokenGenerator, "https://datatracker.ietf.org/doc/html/rfc6749#section-5.2", resourceOwnerPasswordAuthentication.getScopes());
//      OAuth2Authorization authorization = authorizationBuilder.build();
//      this.authorizationService.save(authorization);
//      log.debug("[GstDev Cloud] |- Resource Owner Password returning OAuth2AccessTokenAuthenticationToken.");
//      Map<String, Object> additionalParameters = this.idTokenAdditionalParameters(idToken);
//      OAuth2AccessTokenAuthenticationToken accessTokenAuthenticationToken = new OAuth2AccessTokenAuthenticationToken(registeredClient, clientPrincipal, accessToken, refreshToken, additionalParameters);
//      return this.createOAuth2AccessTokenAuthenticationToken(principal, accessTokenAuthenticationToken);
//    }
//  }


  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    OAuth2PasswordAuthenticationToken resourceOwnerPasswordAuthentication = (OAuth2PasswordAuthenticationToken) authentication;

    OAuth2ClientAuthenticationToken clientPrincipal =
      OAuth2AuthenticationProviderUtils.getAuthenticatedClientElseThrowInvalidClient(resourceOwnerPasswordAuthentication);
    RegisteredClient registeredClient = clientPrincipal.getRegisteredClient();

    if (!registeredClient.getAuthorizationGrantTypes().contains(HerodotusGrantType.PASSWORD)) {
      throw new OAuth2AuthenticationException(OAuth2ErrorCodes.UNAUTHORIZED_CLIENT);
    }

    Authentication principal = getUsernamePasswordAuthentication(resourceOwnerPasswordAuthentication.getAdditionalParameters(), registeredClient.getId());

    // Default to configured scopes
    Set<String> authorizedScopes = validateScopes(resourceOwnerPasswordAuthentication.getScopes(), registeredClient);

    OAuth2Authorization.Builder authorizationBuilder = OAuth2Authorization.withRegisteredClient(registeredClient)
      .principalName(principal.getName())
      .authorizationGrantType(HerodotusGrantType.PASSWORD)
      .authorizedScopes(authorizedScopes)
      .attribute(Principal.class.getName(), principal);

    // @formatter:off
    DefaultOAuth2TokenContext.Builder tokenContextBuilder = DefaultOAuth2TokenContext.builder()
      .registeredClient(registeredClient)
      .principal(principal)
      .authorizationServerContext(AuthorizationServerContextHolder.getContext())
      .authorizedScopes(authorizedScopes)
      .tokenType(OAuth2TokenType.ACCESS_TOKEN)
      .authorizationGrantType(HerodotusGrantType.PASSWORD)
      .authorizationGrant(resourceOwnerPasswordAuthentication);
    // @formatter:on

    // ----- Access token -----
    OAuth2AccessToken accessToken = createOAuth2AccessToken(tokenContextBuilder, authorizationBuilder, this.tokenGenerator, ERROR_URI);

    // ----- Refresh token -----
    OAuth2RefreshToken refreshToken = creatOAuth2RefreshToken(tokenContextBuilder, authorizationBuilder, this.tokenGenerator, ERROR_URI, clientPrincipal, registeredClient);

    // ----- ID token -----
//    OidcIdToken idToken = createOidcIdToken(principal, sessionRegistry, tokenContextBuilder, authorizationBuilder, this.tokenGenerator, ERROR_URI, resourceOwnerPasswordAuthentication.getScopes());
    OidcIdToken idToken = createOidcIdToken(principal, null, tokenContextBuilder, authorizationBuilder, this.tokenGenerator, ERROR_URI, resourceOwnerPasswordAuthentication.getScopes());

    OAuth2Authorization authorization = authorizationBuilder.build();

    this.authorizationService.save(authorization);

    log.debug("[GstDev Cloud] |- Resource Owner Password returning OAuth2AccessTokenAuthenticationToken.");

    Map<String, Object> additionalParameters = idTokenAdditionalParameters(idToken);

    OAuth2AccessTokenAuthenticationToken accessTokenAuthenticationToken = new OAuth2AccessTokenAuthenticationToken(
      registeredClient, clientPrincipal, accessToken, refreshToken, additionalParameters);
    return createOAuth2AccessTokenAuthenticationToken(principal, accessTokenAuthenticationToken);
  }
  /**
   * Sets the {@link SessionRegistry} used to track OpenID Connect sessions.
   *
   * @param sessionRegistry the {@link SessionRegistry} used to track OpenID Connect sessions
   * @since 1.1.1
   */
  public void setSessionRegistry(SessionRegistry sessionRegistry) {
    Assert.notNull(sessionRegistry, "sessionRegistry cannot be null");
    this.sessionRegistry = sessionRegistry;
  }

  @Override
  public boolean supports(Class<?> authentication) {
    boolean supports = OAuth2PasswordAuthenticationToken.class.isAssignableFrom(authentication);
    log.trace("[GstDev Cloud] |- Resource Owner Password Authentication is supports! [{}]", supports);
    return supports;
  }


}