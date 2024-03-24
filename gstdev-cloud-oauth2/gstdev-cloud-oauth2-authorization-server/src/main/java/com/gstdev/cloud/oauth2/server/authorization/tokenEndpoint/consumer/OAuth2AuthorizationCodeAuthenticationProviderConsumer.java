//package com.gstdev.cloud.oauth2.server.authorization.tokenEndpoint.consumer;
//
//import java.util.List;
//import java.util.function.Consumer;
//
//import com.gstdev.cloud.oauth2.server.authorization.utils.OAuth2ConfigurerUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.core.session.SessionRegistry;
//import org.springframework.security.oauth2.core.OAuth2Token;
//import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
//import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AuthorizationCodeAuthenticationProvider;
//import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenGenerator;
//
//public class OAuth2AuthorizationCodeAuthenticationProviderConsumer implements Consumer<List<AuthenticationProvider>> {
//  private static final Logger log = LoggerFactory.getLogger(OAuth2AuthorizationCodeAuthenticationProviderConsumer.class);
//  private final HttpSecurity httpSecurity;
//  private final SessionRegistry sessionRegistry;
//
//  public OAuth2AuthorizationCodeAuthenticationProviderConsumer(HttpSecurity httpSecurity, SessionRegistry sessionRegistry) {
//    this.httpSecurity = httpSecurity;
//    this.sessionRegistry = sessionRegistry;
//  }
//
//  public void accept(List<AuthenticationProvider> authenticationProviders) {
//    authenticationProviders.removeIf((authenticationProvider) -> {
//      return authenticationProvider instanceof OAuth2AuthorizationCodeAuthenticationProvider;
//    });
//    OAuth2AuthorizationService authorizationService = OAuth2ConfigurerUtils.getAuthorizationService(this.httpSecurity);
//    OAuth2TokenGenerator<? extends OAuth2Token> tokenGenerator = OAuth2ConfigurerUtils.getTokenGenerator(this.httpSecurity);
//    com.gstdev.cloud.oauth2.server.authorization.tokenEndpoint.provider.OAuth2AuthorizationCodeAuthenticationProvider provider
//      = new com.gstdev.cloud.oauth2.server.authorization.tokenEndpoint.provider.OAuth2AuthorizationCodeAuthenticationProvider(authorizationService, tokenGenerator);
//    provider.setSessionRegistry(this.sessionRegistry);
//    log.debug("[Herodotus] |- Custom OAuth2AuthorizationCodeAuthenticationProvider is in effect!");
//    authenticationProviders.add(provider);
//  }
//}
