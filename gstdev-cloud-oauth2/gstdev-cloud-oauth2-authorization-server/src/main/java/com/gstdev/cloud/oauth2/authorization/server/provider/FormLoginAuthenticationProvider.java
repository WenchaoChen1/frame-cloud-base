//package com.gstdev.cloud.oauth2.authorization.server.provider;
//
//import com.gstdev.cloud.oauth2.authorization.server.properties.OAuth2AuthenticationProperties;
//import com.gstdev.cloud.oauth2.authorization.server.token.OAuth2PasswordAuthenticationToken;
//import com.gstdev.cloud.oauth2.core.definition.FrameGrantType;
//import com.gstdev.cloud.oauth2.core.definition.service.EnhanceUserDetailsService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.InternalAuthenticationServiceException;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.session.SessionRegistry;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.oauth2.core.OAuth2AccessToken;
//import org.springframework.security.oauth2.core.OAuth2Token;
//import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
//import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
//import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
//import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
//import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
//import org.springframework.security.oauth2.server.authorization.context.AuthorizationServerContextHolder;
//import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
//import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
//import org.springframework.security.oauth2.server.authorization.token.DefaultOAuth2TokenContext;
//import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenGenerator;
//import org.springframework.util.Assert;
//
//import java.security.Principal;
//import java.time.Duration;
//import java.util.Map;
//import java.util.Set;
//import java.util.UUID;
//
//
//public class FormLoginAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
//    private static final Logger log = LoggerFactory.getLogger(FormLoginAuthenticationProvider.class);
//    private static final String ERROR_URI = "https://datatracker.ietf.org/doc/html/rfc6749#section-5.2";
//    private final OAuth2AuthorizationService authorizationService;
//    private final OAuth2TokenGenerator<? extends OAuth2Token> tokenGenerator;
//    private SessionRegistry sessionRegistry;
//
//    /**
//     * Constructs an {@code OAuth2ClientCredentialsAuthenticationProvider} using the provided parameters.
//     *
//     * @param authorizationService the authorizaticon service
//     * @param tokenGenerator       – the token generator
//     */
//    public FormLoginAuthenticationProvider(OAuth2AuthorizationService authorizationService
//            , OAuth2TokenGenerator<? extends OAuth2Token> tokenGenerator
//            , UserDetailsService userDetailsService
//            , OAuth2AuthenticationProperties complianceProperties) {
//        super(authorizationService, userDetailsService, complianceProperties);
//        Assert.notNull(tokenGenerator, "tokenGenerator cannot be null");
//        this.authorizationService = authorizationService;
//        this.tokenGenerator = tokenGenerator;
//    }
//
//    @Override
//    protected void additionalAuthenticationChecks(UserDetails userDetails, Map<String, Object> additionalParameters) throws AuthenticationException {
//        String presentedPassword = (String) additionalParameters.get(OAuth2ParameterNames.PASSWORD);
//        if (!this.getPasswordEncoder().matches(presentedPassword, userDetails.getPassword())) {
//            log.debug("[GstDev Cloud] |- Failed to authenticate since password does not match stored value");
//            throw new BadCredentialsException("Bad credentials");
//        }
//    }
//
//    @Override
//    protected UserDetails retrieveUser(Map<String, Object> additionalParameters) throws AuthenticationException {
//        String username = (String) additionalParameters.get(OAuth2ParameterNames.USERNAME);
//        try {
//            EnhanceUserDetailsService enhanceUserDetailsService = getUserDetailsService();
//            UserDetails userDetails = enhanceUserDetailsService.loadUserByUsername(username);
//            if (userDetails == null) {
//                throw new InternalAuthenticationServiceException(
//                        "UserDetailsService returned null, which is an interface contract violation");
//            }
//            return userDetails;
//        } catch (UsernameNotFoundException ex) {
//            log.error("[GstDev Cloud] |- User name can not found ：[{}]", username);
//            throw ex;
//        } catch (InternalAuthenticationServiceException ex) {
//            throw ex;
//        } catch (Exception ex) {
//            throw new InternalAuthenticationServiceException(ex.getMessage(), ex);
//        }
//    }
//
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        OAuth2PasswordAuthenticationToken resourceOwnerPasswordAuthentication = (OAuth2PasswordAuthenticationToken) authentication;
//
//        RegisteredClient registeredClient = RegisteredClient
//                .withId(UUID.randomUUID().toString())
////                .clientId("password-client")
////                .clientName("Password Client")
////                .clientSecret("123456")
////                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
////                .authorizationGrantType(AuthorizationGrantType.PASSWORD)
////                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
//                // 客户端设置，设置用户需要确认授权
//                .clientSettings(ClientSettings.builder()
//                        .requireAuthorizationConsent(true)
//                        .requireProofKey(false)
//                        .build())
//                // 添加tokenSettings，将accessTokenFormat改为REFERENCE即可获取Opaque Token
//                .tokenSettings(TokenSettings.builder()
//                        // 令牌存活时间：2小时
//                        .accessTokenTimeToLive(Duration.ofHours(2))
//                        // 令牌可以刷新，重新获取
//                        .reuseRefreshTokens(false)
//                        .build())
//                .build();
//
//
//
//        Authentication principal = getUsernamePasswordAuthentication(resourceOwnerPasswordAuthentication.getAdditionalParameters(), registeredClient.getId());
//
//        // Default to configured scopes
//        Set<String> authorizedScopes = validateScopes(resourceOwnerPasswordAuthentication.getScopes(), registeredClient);
//
//        OAuth2Authorization.Builder authorizationBuilder = OAuth2Authorization.withRegisteredClient(registeredClient)
//                .principalName(principal.getName())
//                .authorizationGrantType(FrameGrantType.PASSWORD)
//                .authorizedScopes(authorizedScopes)
//                .attribute(Principal.class.getName(), principal);
//
//        // @formatter:off
//    DefaultOAuth2TokenContext.Builder tokenContextBuilder = DefaultOAuth2TokenContext.builder()
//      .registeredClient(registeredClient)
//      .principal(principal)
//      .authorizationServerContext(AuthorizationServerContextHolder.getContext())
//      .authorizedScopes(authorizedScopes)
//      .tokenType(OAuth2TokenType.ACCESS_TOKEN)
//      .authorizationGrantType(FrameGrantType.PASSWORD)
//      .authorizationGrant(resourceOwnerPasswordAuthentication);
//    // @formatter:on
//
//        // ----- Access token -----
//        OAuth2AccessToken accessToken = createOAuth2AccessToken(tokenContextBuilder, authorizationBuilder, this.tokenGenerator, ERROR_URI);
//
//
//        OAuth2Authorization authorization = authorizationBuilder.build();
//
//        this.authorizationService.save(authorization);
//
//        log.debug("[GstDev Cloud] |- Resource Owner Password returning OAuth2AccessTokenAuthenticationToken.");
//
//        OAuth2FormLoginAuthenticationToken oAuth2FormLoginAuthenticationToken = new OAuth2FormLoginAuthenticationToken(
//                null, null, accessToken);
//        return oAuth2FormLoginAuthenticationToken;
////        OAuth2AccessTokenAuthenticationToken accessTokenAuthenticationToken = new OAuth2AccessTokenAuthenticationToken(
////                registeredClient, null, accessToken, null, null);
////        return createOAuth2AccessTokenAuthenticationToken(principal, accessTokenAuthenticationToken);
//    }
//
//    /**
//     * Sets the {@link SessionRegistry} used to track OpenID Connect sessions.
//     *
//     * @param sessionRegistry the {@link SessionRegistry} used to track OpenID Connect sessions
//     * @since 1.1.1
//     */
//    public void setSessionRegistry(SessionRegistry sessionRegistry) {
//        Assert.notNull(sessionRegistry, "sessionRegistry cannot be null");
//        this.sessionRegistry = sessionRegistry;
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        boolean supports = FormLoginAuthenticationProvider.class.isAssignableFrom(authentication);
//        log.trace("[GstDev Cloud] |- Resource Owner Password Authentication is supports! [{}]", supports);
//        return supports;
//    }
//
//
//}
