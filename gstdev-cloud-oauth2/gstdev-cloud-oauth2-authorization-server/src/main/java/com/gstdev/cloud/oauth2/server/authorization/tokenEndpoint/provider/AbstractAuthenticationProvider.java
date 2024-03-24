//package com.gstdev.cloud.oauth2.server.authorization.tokenEndpoint.provider;
//
//
//import java.nio.charset.StandardCharsets;
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//import java.util.ArrayList;
//import java.util.Base64;
//import java.util.Collections;
//import java.util.Comparator;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.LinkedHashSet;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.session.SessionInformation;
//import org.springframework.security.core.session.SessionRegistry;
//import org.springframework.security.oauth2.core.AuthorizationGrantType;
//import org.springframework.security.oauth2.core.ClaimAccessor;
//import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
//import org.springframework.security.oauth2.core.OAuth2AccessToken;
//import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
//import org.springframework.security.oauth2.core.OAuth2Error;
//import org.springframework.security.oauth2.core.OAuth2RefreshToken;
//import org.springframework.security.oauth2.core.OAuth2Token;
//import org.springframework.security.oauth2.core.OAuth2AccessToken.TokenType;
//import org.springframework.security.oauth2.core.oidc.OidcIdToken;
//import org.springframework.security.oauth2.jwt.Jwt;
//import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
//import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
//import org.springframework.security.oauth2.server.authorization.OAuth2Authorization.Token;
//import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AccessTokenAuthenticationToken;
//import org.springframework.security.oauth2.server.authorization.authentication.OAuth2ClientAuthenticationToken;
//import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
//import org.springframework.security.oauth2.server.authorization.token.DefaultOAuth2TokenContext;
//import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenContext;
//import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenGenerator;
//import org.springframework.util.CollectionUtils;
//
//public abstract class AbstractAuthenticationProvider implements AuthenticationProvider {
//  private final Log logger = LogFactory.getLog(this.getClass());
//  private static final OAuth2TokenType ID_TOKEN_TOKEN_TYPE = new OAuth2TokenType("id_token");
//
//  public AbstractAuthenticationProvider() {
//  }
//
//  private static String createHash(String value) throws NoSuchAlgorithmException {
//    MessageDigest md = MessageDigest.getInstance("SHA-256");
//    byte[] digest = md.digest(value.getBytes(StandardCharsets.US_ASCII));
//    return Base64.getUrlEncoder().withoutPadding().encodeToString(digest);
//  }
//
//  protected OAuth2AccessToken createOAuth2AccessToken(DefaultOAuth2TokenContext.Builder tokenContextBuilder, OAuth2Authorization.Builder authorizationBuilder, OAuth2TokenGenerator<? extends OAuth2Token> tokenGenerator, String errorUri) {
//    OAuth2TokenContext tokenContext = ((DefaultOAuth2TokenContext.Builder)tokenContextBuilder.tokenType(OAuth2TokenType.ACCESS_TOKEN)).build();
//    OAuth2Token generatedAccessToken = tokenGenerator.generate(tokenContext);
//    if (generatedAccessToken == null) {
//      OAuth2Error error = new OAuth2Error("server_error", "The token generator failed to generate the access token.", errorUri);
//      throw new OAuth2AuthenticationException(error);
//    } else {
//      if (this.logger.isTraceEnabled()) {
//        this.logger.trace("Generated access token");
//      }
//
//      OAuth2AccessToken accessToken = new OAuth2AccessToken(TokenType.BEARER, generatedAccessToken.getTokenValue(), generatedAccessToken.getIssuedAt(), generatedAccessToken.getExpiresAt(), tokenContext.getAuthorizedScopes());
//      if (generatedAccessToken instanceof ClaimAccessor) {
//        authorizationBuilder.token(accessToken, (metadata) -> {
//          metadata.put(Token.CLAIMS_METADATA_NAME, ((ClaimAccessor)generatedAccessToken).getClaims());
//        });
//      } else {
//        authorizationBuilder.accessToken(accessToken);
//      }
//
//      return accessToken;
//    }
//  }
//
//  protected OAuth2RefreshToken creatOAuth2RefreshToken(DefaultOAuth2TokenContext.Builder tokenContextBuilder, OAuth2Authorization.Builder authorizationBuilder, OAuth2TokenGenerator<? extends OAuth2Token> tokenGenerator, String errorUri, OAuth2ClientAuthenticationToken clientPrincipal, RegisteredClient registeredClient) {
//    OAuth2RefreshToken refreshToken = null;
//    if (registeredClient.getAuthorizationGrantTypes().contains(AuthorizationGrantType.REFRESH_TOKEN) && !clientPrincipal.getClientAuthenticationMethod().equals(ClientAuthenticationMethod.NONE)) {
//      OAuth2TokenContext tokenContext = ((DefaultOAuth2TokenContext.Builder)tokenContextBuilder.tokenType(OAuth2TokenType.REFRESH_TOKEN)).build();
//      OAuth2Token generatedRefreshToken = tokenGenerator.generate(tokenContext);
//      if (!(generatedRefreshToken instanceof OAuth2RefreshToken)) {
//        OAuth2Error error = new OAuth2Error("server_error", "The token generator failed to generate the refresh token.", errorUri);
//        throw new OAuth2AuthenticationException(error);
//      }
//
//      if (this.logger.isTraceEnabled()) {
//        this.logger.trace("Generated refresh token");
//      }
//
//      refreshToken = (OAuth2RefreshToken)generatedRefreshToken;
//      authorizationBuilder.refreshToken(refreshToken);
//    }
//
//    return refreshToken;
//  }
//
//  protected OidcIdToken createOidcIdToken(Authentication principal, SessionRegistry sessionRegistry, DefaultOAuth2TokenContext.Builder tokenContextBuilder, OAuth2Authorization.Builder authorizationBuilder, OAuth2TokenGenerator<? extends OAuth2Token> tokenGenerator, String errorUri, Set<String> requestedScopes) {
//    OidcIdToken idToken;
//    if (requestedScopes.contains("openid")) {
//      SessionInformation sessionInformation = this.getSessionInformation(principal, sessionRegistry);
//      if (sessionInformation != null) {
//        try {
//          sessionInformation = new SessionInformation(sessionInformation.getPrincipal(), createHash(sessionInformation.getSessionId()), sessionInformation.getLastRequest());
//        } catch (NoSuchAlgorithmException var13) {
//          OAuth2Error error = new OAuth2Error("server_error", "Failed to compute hash for Session ID.", errorUri);
//          throw new OAuth2AuthenticationException(error);
//        }
//
//        tokenContextBuilder.put(SessionInformation.class, sessionInformation);
//      }
//
//      OAuth2TokenContext tokenContext = ((DefaultOAuth2TokenContext.Builder)((DefaultOAuth2TokenContext.Builder)tokenContextBuilder.tokenType(ID_TOKEN_TOKEN_TYPE)).authorization(authorizationBuilder.build())).build();
//      OAuth2Token generatedIdToken = tokenGenerator.generate(tokenContext);
//      if (!(generatedIdToken instanceof Jwt)) {
//        OAuth2Error error = new OAuth2Error("server_error", "The token generator failed to generate the ID token.", errorUri);
//        throw new OAuth2AuthenticationException(error);
//      }
//
//      if (this.logger.isTraceEnabled()) {
//        this.logger.trace("Generated id token");
//      }
//
//      idToken = new OidcIdToken(generatedIdToken.getTokenValue(), generatedIdToken.getIssuedAt(), generatedIdToken.getExpiresAt(), ((Jwt)generatedIdToken).getClaims());
//      authorizationBuilder.token(idToken, (metadata) -> {
//        metadata.put(Token.CLAIMS_METADATA_NAME, idToken.getClaims());
//      });
//    } else {
//      idToken = null;
//    }
//
//    return idToken;
//  }
//
//  private SessionInformation getSessionInformation(Authentication principal, SessionRegistry sessionRegistry) {
//    SessionInformation sessionInformation = null;
//    if (sessionRegistry != null) {
//      List<SessionInformation> sessions = sessionRegistry.getAllSessions(principal.getPrincipal(), false);
//      if (!CollectionUtils.isEmpty(sessions)) {
//        sessionInformation = (SessionInformation)sessions.get(0);
//        if (sessions.size() > 1) {
////          List<SessionInformation> sessions = new ArrayList(sessions);
//           sessions = new ArrayList(sessions);
//          sessions.sort(Comparator.comparing(SessionInformation::getLastRequest));
//          sessionInformation = (SessionInformation)sessions.get(sessions.size() - 1);
//        }
//      }
//    }
//
//    return sessionInformation;
//  }
//
//  protected Map<String, Object> idTokenAdditionalParameters(OidcIdToken idToken) {
//    Map<String, Object> additionalParameters = Collections.emptyMap();
//    if (idToken != null) {
//      additionalParameters = new HashMap();
//      ((Map)additionalParameters).put("id_token", idToken.getTokenValue());
//    }
//
//    return (Map)additionalParameters;
//  }
//
//  protected Set<String> validateScopes(Set<String> requestedScopes, RegisteredClient registeredClient) {
//    Set<String> authorizedScopes = Collections.emptySet();
//    if (!CollectionUtils.isEmpty(requestedScopes)) {
//      Iterator var4 = requestedScopes.iterator();
//
//      while(var4.hasNext()) {
//        String requestedScope = (String)var4.next();
//        if (!registeredClient.getScopes().contains(requestedScope)) {
//          throw new OAuth2AuthenticationException("invalid_scope");
//        }
//      }
//
//      authorizedScopes = new LinkedHashSet(requestedScopes);
//    }
//
//    return (Set)authorizedScopes;
//  }
//
//  protected OAuth2AccessTokenAuthenticationToken createOAuth2AccessTokenAuthenticationToken(Authentication source, OAuth2AccessTokenAuthenticationToken destination) {
////    if (source instanceof UsernamePasswordAuthenticationToken) {
////      Object var4 = source.getPrincipal();
////      if (var4 instanceof HerodotusUser) {
////        HerodotusUser user = (HerodotusUser)var4;
////        destination.setDetails(PrincipalUtils.toPrincipalDetails(user));
////      }
////    }
//
//    return destination;
//  }
//}
