package com.gstdev.cloud.oauth2.authorization.server.configurer;

import com.gstdev.cloud.oauth2.authorization.server.provider.OAuth2PasswordAuthenticationProvider;
import com.gstdev.cloud.oauth2.authorization.server.properties.OAuth2AuthenticationProperties;
import com.gstdev.cloud.oauth2.authorization.server.utils.OAuth2ConfigurerUtils;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.OAuth2Token;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenGenerator;

public class OAuth2AuthenticationProviderConfigurer extends AbstractHttpConfigurer<OAuth2AuthenticationProviderConfigurer, HttpSecurity> {
    //  private final SessionRegistry sessionRegistry;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;
    private final OAuth2AuthenticationProperties authenticationProperties;

    public OAuth2AuthenticationProviderConfigurer(PasswordEncoder passwordEncoder
        , UserDetailsService userDetailsService
        , OAuth2AuthenticationProperties authenticationProperties) {
//  public OAuth2AuthenticationProviderConfigurer(SessionRegistry sessionRegistry, PasswordEncoder passwordEncoder, UserDetailsService userDetailsService, OAuth2AuthenticationProperties authenticationProperties) {
//    this.sessionRegistry = sessionRegistry;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
        this.authenticationProperties = authenticationProperties;
    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        OAuth2AuthorizationService authorizationService = OAuth2ConfigurerUtils.getAuthorizationService(httpSecurity);
        OAuth2TokenGenerator<? extends OAuth2Token> tokenGenerator = OAuth2ConfigurerUtils.getTokenGenerator(httpSecurity);
        OAuth2PasswordAuthenticationProvider resourceOwnerPasswordAuthenticationProvider =
            new OAuth2PasswordAuthenticationProvider(authorizationService, tokenGenerator, this.userDetailsService, this.authenticationProperties);
        resourceOwnerPasswordAuthenticationProvider.setPasswordEncoder(this.passwordEncoder);
//    resourceOwnerPasswordAuthenticationProvider.setSessionRegistry(this.sessionRegistry);

        httpSecurity.authenticationProvider(resourceOwnerPasswordAuthenticationProvider);
//    OAuth2SocialCredentialsAuthenticationProvider socialCredentialsAuthenticationProvider =
//        new OAuth2SocialCredentialsAuthenticationProvider(authorizationService, tokenGenerator, this.userDetailsService, this.authenticationProperties);
//    socialCredentialsAuthenticationProvider.setSessionRegistry(this.sessionRegistry);
//    httpSecurity.authenticationProvider(socialCredentialsAuthenticationProvider);
    }
}
