package com.gstdev.cloud.oauth2.authentication.configuration;

import com.gstdev.cloud.oauth2.authentication.customizer.OAuth2FormLoginConfigurerCustomizer;
import com.gstdev.cloud.oauth2.authentication.properties.OAuth2AuthenticationProperties;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: frame-cloud-base
 * @description: OAuth2 认证基础模块配置
 * @author: wenchao.chen
 * @create: 2024/03/25 14:34
 **/
@AutoConfiguration
//@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties({OAuth2AuthenticationProperties.class})
public class OAuth2AuthenticationConfiguration {

  private static final Logger log = LoggerFactory.getLogger(OAuth2AuthenticationConfiguration.class);

  @PostConstruct
  public void postConstruct() {
    log.debug("[GstDev Cloud] |- SDK [OAuth2 Authentication] Auto Configure.");
  }

//  @Bean
//  public LockedUserDetailsStampManager lockedUserDetailsStampManager(OAuth2AuthenticationProperties authenticationProperties) {
//    LockedUserDetailsStampManager manager = new LockedUserDetailsStampManager(authenticationProperties);
//    log.trace("[GstDev Cloud] |- Bean [Locked UserDetails Stamp Manager] Auto Configure.");
//    return manager;
//  }
//
//  @Bean
//  public SignInFailureLimitedStampManager signInFailureLimitedStampManager(OAuth2AuthenticationProperties authenticationProperties) {
//    SignInFailureLimitedStampManager manager = new SignInFailureLimitedStampManager(authenticationProperties);
//    log.trace("[GstDev Cloud] |- Bean [SignIn Failure Limited Stamp Manager] Auto Configure.");
//    return manager;
//  }
//
  @Bean
  @ConditionalOnMissingBean
  public OAuth2FormLoginConfigurerCustomizer oauth2FormLoginConfigurerCustomer(OAuth2AuthenticationProperties authenticationProperties) {
    OAuth2FormLoginConfigurerCustomizer configurer = new OAuth2FormLoginConfigurerCustomizer(authenticationProperties);
    log.trace("[GstDev Cloud] |- Bean [OAuth2 FormLogin Configurer Customer] Auto Configure.");
    return configurer;
  }
//
//  @Bean
//  public OAuth2TokenCustomizer<JwtEncodingContext> jwtTokenCustomizer() {
//    HerodotusJwtTokenCustomizer customizer = new HerodotusJwtTokenCustomizer();
//    log.trace("[GstDev Cloud] |- Bean [OAuth2 Jwt Token Customizer] Auto Configure.");
//    return customizer;
//  }
//
//  @Bean
//  public OAuth2TokenCustomizer<OAuth2TokenClaimsContext> opaqueTokenCustomizer() {
//    HerodotusOpaqueTokenCustomizer customizer = new HerodotusOpaqueTokenCustomizer();
//    log.trace("[GstDev Cloud] |- Bean [OAuth2 Opaque Token Customizer] Auto Configure.");
//    return customizer;
//  }
//
//  @Bean
//  public ErrorCodeMapperBuilderCustomizer oauth2ErrorCodeMapperBuilderCustomizer() {
//    OAuth2ErrorCodeMapperBuilderCustomizer customizer = new OAuth2ErrorCodeMapperBuilderCustomizer();
//    log.debug("[GstDev Cloud] |- Strategy [OAuth2 ErrorCodeMapper Builder Customizer] Auto Configure.");
//    return customizer;
//  }
}
