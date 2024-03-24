//package com.gstdev.cloud.oauth2.server.authorization.tokenEndpoint.provider;
//
//
//import com.gstdev.cloud.oauth2.server.authorization.properties.OAuth2AuthenticationProperties;
//import com.gstdev.cloud.oauth2.server.authorization.service.DefaultUserDetailsService;
//import com.gstdev.cloud.oauth2.server.authorization.utils.OAuth2AuthenticationProviderUtils;
//import com.gstdev.cloud.oauth2.server.authorization.utils.OAuth2EndpointUtils;
//import org.apache.commons.collections4.CollectionUtils;
//import org.apache.commons.lang3.ObjectUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.support.MessageSourceAccessor;
//import org.springframework.security.authentication.*;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.SpringSecurityMessageSource;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.factory.PasswordEncoderFactories;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.oauth2.core.OAuth2RefreshToken;
//import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
//import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
//import org.springframework.util.Assert;
//
//import java.util.List;
//import java.util.Map;
//
//public abstract class AbstractUserDetailsAuthenticationProvider extends AbstractAuthenticationProvider {
//  private static final Logger log = LoggerFactory.getLogger(AbstractUserDetailsAuthenticationProvider.class);
//  private final MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();
//  private final UserDetailsService userDetailsService;
//  private final OAuth2AuthorizationService authorizationService;
//  private final OAuth2AuthenticationProperties authenticationProperties;
//  private PasswordEncoder passwordEncoder;
//
//  public AbstractUserDetailsAuthenticationProvider(OAuth2AuthorizationService authorizationService, UserDetailsService userDetailsService, OAuth2AuthenticationProperties authenticationProperties) {
//    this.userDetailsService = userDetailsService;
//    this.authorizationService = authorizationService;
//    this.authenticationProperties = authenticationProperties;
//    this.setPasswordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder());
//  }
//
//  public DefaultUserDetailsService getUserDetailsService() {
//    return (DefaultUserDetailsService)this.userDetailsService;
//  }
//
//  protected PasswordEncoder getPasswordEncoder() {
//    return this.passwordEncoder;
//  }
//
//  public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
//    Assert.notNull(passwordEncoder, "passwordEncoder cannot be null");
//    this.passwordEncoder = passwordEncoder;
//  }
//
//  protected abstract void additionalAuthenticationChecks(UserDetails userDetails, Map<String, Object> additionalParameters) throws AuthenticationException;
//
//  protected abstract UserDetails retrieveUser(Map<String, Object> additionalParameters) throws AuthenticationException;
//
//  private Authentication authenticateUserDetails(Map<String, Object> additionalParameters, String registeredClientId) throws AuthenticationException {
//    UserDetails user = this.retrieveUser(additionalParameters);
//    if (!user.isAccountNonLocked()) {
//      log.debug("[Herodotus] |- Failed to authenticate since user account is locked");
//      throw new LockedException(this.messages.getMessage("AbstractUserDetailsAuthenticationProvider.locked", "User account is locked"));
//    } else if (!user.isEnabled()) {
//      log.debug("[Herodotus] |- Failed to authenticate since user account is disabled");
//      throw new DisabledException(this.messages.getMessage("AbstractUserDetailsAuthenticationProvider.disabled", "User is disabled"));
//    } else if (!user.isAccountNonExpired()) {
//      log.debug("[Herodotus] |- Failed to authenticate since user account has expired");
//      throw new AccountExpiredException(this.messages.getMessage("AbstractUserDetailsAuthenticationProvider.expired", "User account has expired"));
//    } else {
//      this.additionalAuthenticationChecks(user, additionalParameters);
//      if (!user.isCredentialsNonExpired()) {
//        log.debug("[Herodotus] |- Failed to authenticate since user account credentials have expired");
//        throw new CredentialsExpiredException(this.messages.getMessage("AbstractUserDetailsAuthenticationProvider.credentialsExpired", "User credentials have expired"));
//      } else {
////        JpaOAuth2AuthorizationService jpaOAuth2AuthorizationService;
////        OAuth2AuthorizationService var5;
////        if (this.authenticationProperties.getSignInEndpointLimited().getEnabled() && !this.authenticationProperties.getSignInKickOutLimited().getEnabled()) {
////          var5 = this.authorizationService;
////          if (var5 instanceof JpaOAuth2AuthorizationService) {
////            jpaOAuth2AuthorizationService = (JpaOAuth2AuthorizationService)var5;
////            int count = jpaOAuth2AuthorizationService.findAuthorizationCount(registeredClientId, user.getUsername());
////            if (count >= this.authenticationProperties.getSignInEndpointLimited().getMaximum()) {
////              throw new AccountEndpointLimitedException("Use same endpoint signIn exceed limit");
////            }
////          }
////        }
//
////        if (!this.authenticationProperties.getSignInEndpointLimited().getEnabled() && this.authenticationProperties.getSignInKickOutLimited().getEnabled()) {
////          var5 = this.authorizationService;
////          if (var5 instanceof JpaOAuth2AuthorizationService) {
////            jpaOAuth2AuthorizationService = (JpaOAuth2AuthorizationService)var5;
////            List<OAuth2Authorization> authorizations = jpaOAuth2AuthorizationService.findAvailableAuthorizations(registeredClientId, user.getUsername());
////            if (CollectionUtils.isNotEmpty(authorizations)) {
////              authorizations.forEach((authorization) -> {
////                OAuth2Authorization.Token<OAuth2RefreshToken> refreshToken = authorization.getToken(OAuth2RefreshToken.class);
////                if (ObjectUtils.isNotEmpty(refreshToken)) {
////                  authorization = OAuth2AuthenticationProviderUtils.invalidate(authorization, (OAuth2RefreshToken)refreshToken.getToken());
////                }
////
////                log.debug("[Herodotus] |- Sign in user [{}] with token id [{}] will be kicked out.", user.getUsername(), authorization.getId());
////                jpaOAuth2AuthorizationService.save(authorization);
////              });
////            }
////          }
////        }
//
//        return new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
//      }
//    }
//  }
//
//  protected Authentication getUsernamePasswordAuthentication(Map<String, Object> additionalParameters, String registeredClientId) throws AuthenticationException {
//    Authentication authentication = null;
//
//    try {
//      authentication = this.authenticateUserDetails(additionalParameters, registeredClientId);
//    } catch (AccountStatusException var6) {
//      String exceptionName = var6.getClass().getSimpleName();
//      OAuth2EndpointUtils.throwError(exceptionName, var6.getMessage(), "https://datatracker.ietf.org/doc/html/rfc6749#section-5.2");
//    } catch (BadCredentialsException var7) {
//      OAuth2EndpointUtils.throwError("BadCredentialsException", var7.getMessage(), "https://datatracker.ietf.org/doc/html/rfc6749#section-5.2");
//    } catch (UsernameNotFoundException var8) {
//      OAuth2EndpointUtils.throwError("UsernameNotFoundException", var8.getMessage(), "https://datatracker.ietf.org/doc/html/rfc6749#section-5.2");
//    }
//
//    return authentication;
//  }
//}
