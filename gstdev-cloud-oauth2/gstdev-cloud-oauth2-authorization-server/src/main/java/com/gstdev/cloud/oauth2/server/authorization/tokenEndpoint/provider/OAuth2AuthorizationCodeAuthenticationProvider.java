package com.gstdev.cloud.oauth2.server.authorization.tokenEndpoint.provider;


import com.gstdev.cloud.oauth2.server.authorization.utils.OAuth2AuthenticationProviderUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.log.LogMessage;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.oauth2.core.*;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationCode;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AccessTokenAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AuthorizationCodeAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2ClientAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.context.AuthorizationServerContextHolder;
import org.springframework.security.oauth2.server.authorization.token.DefaultOAuth2TokenContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenGenerator;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.security.Principal;
import java.util.Map;

public final class OAuth2AuthorizationCodeAuthenticationProvider extends AbstractAuthenticationProvider {
  private final Log logger = LogFactory.getLog(this.getClass());
  private static final String ERROR_URI = "https://datatracker.ietf.org/doc/html/rfc6749#section-5.2";
  private static final OAuth2TokenType AUTHORIZATION_CODE_TOKEN_TYPE = new OAuth2TokenType("code");
  private final OAuth2AuthorizationService authorizationService;
  private final OAuth2TokenGenerator<? extends OAuth2Token> tokenGenerator;
  private SessionRegistry sessionRegistry;

  public OAuth2AuthorizationCodeAuthenticationProvider(OAuth2AuthorizationService authorizationService, OAuth2TokenGenerator<? extends OAuth2Token> tokenGenerator) {
    Assert.notNull(authorizationService, "authorizationService cannot be null");
    Assert.notNull(tokenGenerator, "tokenGenerator cannot be null");
    this.authorizationService = authorizationService;
    this.tokenGenerator = tokenGenerator;
  }

  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    OAuth2AuthorizationCodeAuthenticationToken authorizationCodeAuthentication = (OAuth2AuthorizationCodeAuthenticationToken)authentication;
    OAuth2ClientAuthenticationToken clientPrincipal = OAuth2AuthenticationProviderUtils.getAuthenticatedClientElseThrowInvalidClient(authorizationCodeAuthentication);
    RegisteredClient registeredClient = clientPrincipal.getRegisteredClient();
    if (this.logger.isTraceEnabled()) {
      this.logger.trace("Retrieved registered client");
    }

    OAuth2Authorization authorization = this.authorizationService.findByToken(authorizationCodeAuthentication.getCode(), AUTHORIZATION_CODE_TOKEN_TYPE);
    if (authorization == null) {
      throw new OAuth2AuthenticationException("invalid_grant");
    } else {
      if (this.logger.isTraceEnabled()) {
        this.logger.trace("Retrieved authorization with authorization code");
      }

      OAuth2Authorization.Token<OAuth2AuthorizationCode> authorizationCode = authorization.getToken(OAuth2AuthorizationCode.class);
      OAuth2AuthorizationRequest authorizationRequest = (OAuth2AuthorizationRequest)authorization.getAttribute(OAuth2AuthorizationRequest.class.getName());
      if (!registeredClient.getClientId().equals(authorizationRequest.getClientId())) {
        if (!authorizationCode.isInvalidated()) {
          authorization = OAuth2AuthenticationProviderUtils.invalidate(authorization, (OAuth2AuthorizationCode)authorizationCode.getToken());
          this.authorizationService.save(authorization);
          if (this.logger.isWarnEnabled()) {
            this.logger.warn(LogMessage.format("Invalidated authorization code used by registered client '%s'", registeredClient.getId()));
          }
        }

        throw new OAuth2AuthenticationException("invalid_grant");
      } else if (StringUtils.hasText(authorizationRequest.getRedirectUri()) && !authorizationRequest.getRedirectUri().equals(authorizationCodeAuthentication.getRedirectUri())) {
        throw new OAuth2AuthenticationException("invalid_grant");
      } else if (!authorizationCode.isActive()) {
        if (authorizationCode.isInvalidated()) {
          OAuth2Authorization.Token<? extends OAuth2Token> token = authorization.getRefreshToken() != null ? authorization.getRefreshToken() : authorization.getAccessToken();
          if (token != null) {
            authorization = OAuth2AuthenticationProviderUtils.invalidate(authorization, token.getToken());
            this.authorizationService.save(authorization);
            if (this.logger.isWarnEnabled()) {
              this.logger.warn(LogMessage.format("Invalidated authorization token(s) previously issued to registered client '%s'", registeredClient.getId()));
            }
          }
        }

        throw new OAuth2AuthenticationException("invalid_grant");
      } else {
        if (this.logger.isTraceEnabled()) {
          this.logger.trace("Validated token request parameters");
        }

        Authentication principal = (Authentication)authorization.getAttribute(Principal.class.getName());
        DefaultOAuth2TokenContext.Builder tokenContextBuilder = (DefaultOAuth2TokenContext.Builder)((DefaultOAuth2TokenContext.Builder)((DefaultOAuth2TokenContext.Builder)((DefaultOAuth2TokenContext.Builder)((DefaultOAuth2TokenContext.Builder)((DefaultOAuth2TokenContext.Builder)((DefaultOAuth2TokenContext.Builder)DefaultOAuth2TokenContext.builder().registeredClient(registeredClient)).principal(principal)).authorizationServerContext(AuthorizationServerContextHolder.getContext())).authorization(authorization)).authorizedScopes(authorization.getAuthorizedScopes())).authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)).authorizationGrant(authorizationCodeAuthentication);
        OAuth2Authorization.Builder authorizationBuilder = OAuth2Authorization.from(authorization);
        OAuth2AccessToken accessToken = this.createOAuth2AccessToken(tokenContextBuilder, authorizationBuilder, this.tokenGenerator, "https://datatracker.ietf.org/doc/html/rfc6749#section-5.2");
        OAuth2RefreshToken refreshToken = this.creatOAuth2RefreshToken(tokenContextBuilder, authorizationBuilder, this.tokenGenerator, "https://datatracker.ietf.org/doc/html/rfc6749#section-5.2", clientPrincipal, registeredClient);
        OidcIdToken idToken = this.createOidcIdToken(principal, this.sessionRegistry, tokenContextBuilder, authorizationBuilder, this.tokenGenerator, "https://datatracker.ietf.org/doc/html/rfc6749#section-5.2", authorizationRequest.getScopes());
//        OidcIdToken idToken = this.createOidcIdToken(principal, null, tokenContextBuilder, authorizationBuilder, this.tokenGenerator, "https://datatracker.ietf.org/doc/html/rfc6749#section-5.2", authorizationRequest.getScopes());
        authorization = authorizationBuilder.build();
        authorization = OAuth2AuthenticationProviderUtils.invalidate(authorization, (OAuth2AuthorizationCode)authorizationCode.getToken());
        this.authorizationService.save(authorization);
        if (this.logger.isTraceEnabled()) {
          this.logger.trace("Saved authorization");
        }

        Map<String, Object> additionalParameters = this.idTokenAdditionalParameters(idToken);
        if (this.logger.isTraceEnabled()) {
          this.logger.trace("Authenticated token request");
        }

        OAuth2AccessTokenAuthenticationToken accessTokenAuthenticationToken = new OAuth2AccessTokenAuthenticationToken(registeredClient, clientPrincipal, accessToken, refreshToken, additionalParameters);
        accessTokenAuthenticationToken.setDetails(principal);
//       return accessTokenAuthenticationToken;
        return this.createOAuth2AccessTokenAuthenticationToken(principal, accessTokenAuthenticationToken);
      }
    }
  }

  public void setSessionRegistry(SessionRegistry sessionRegistry) {
    Assert.notNull(sessionRegistry, "sessionRegistry cannot be null");
    this.sessionRegistry = sessionRegistry;
  }

  public boolean supports(Class<?> authentication) {
    return OAuth2AuthorizationCodeAuthenticationToken.class.isAssignableFrom(authentication);
  }
}
