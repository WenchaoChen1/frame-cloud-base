package com.gstdev.cloud.oauth2.authorization.configuration;

import com.gstdev.cloud.oauth2.authorization.customizer.OAuth2AuthorizeHttpRequestsConfigurerCustomer;
import com.gstdev.cloud.oauth2.authorization.customizer.OAuth2ResourceServerConfigurerCustomer;
import com.gstdev.cloud.oauth2.authorization.processor.SecurityMatcherConfigurer;
import com.gstdev.cloud.oauth2.authorization.properties.OAuth2AuthorizationProperties;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.jwt.JwtDecoder;

/**
 * @program: frame-cloud-base
 * @description:
 * @author: wenchao.chen
 * @create: 2024/03/25 15:17
 **/
@AutoConfiguration
@EnableConfigurationProperties({OAuth2AuthorizationProperties.class})
//@EnableMethodSecurity(proxyTargetClass = true)
//@Import({
//  SecurityGlobalExceptionHandler.class,
//  OAuth2SessionConfiguration.class,
//})
public class OAuth2AuthorizationConfiguration {

  private static final Logger log = LoggerFactory.getLogger(OAuth2AuthorizationConfiguration.class);

  @PostConstruct
  public void postConstruct() {
    log.debug("[GstDev Cloud] |- SDK [OAuth2 Authorization] Auto Configure.");
  }

//  @Bean
//  @ConditionalOnMissingBean
//  public SecurityMetadataSourceStorage securityMetadataSourceStorage() {
//    SecurityMetadataSourceStorage securityMetadataSourceStorage = new SecurityMetadataSourceStorage();
//    log.trace("[GstDev Cloud] |- Bean [Security Metadata Source Storage] Auto Configure.");
//    return securityMetadataSourceStorage;
//  }
//
//  @Bean
//  @ConditionalOnMissingBean
//  public SecurityMatcherConfigurer securityMatcherConfigurer(OAuth2AuthorizationProperties authorizationProperties, ResourceUrlProvider resourceUrlProvider) {
//    SecurityMatcherConfigurer securityMatcherConfigurer = new SecurityMatcherConfigurer(authorizationProperties, resourceUrlProvider);
//    log.trace("[GstDev Cloud] |- Bean [Security Metadata Configurer] Auto Configure.");
//    return securityMatcherConfigurer;
//  }
//
//  @Bean
//  @ConditionalOnMissingBean
//  public SecurityAuthorizationManager securityAuthorizationManager(SecurityMetadataSourceStorage securityMetadataSourceStorage, SecurityMatcherConfigurer securityMatcherConfigurer) {
//    SecurityAuthorizationManager securityAuthorizationManager = new SecurityAuthorizationManager(securityMetadataSourceStorage, securityMatcherConfigurer);
//    log.trace("[GstDev Cloud] |- Bean [Authorization Manager] Auto Configure.");
//    return securityAuthorizationManager;
//  }
//
  @Bean
  @ConditionalOnMissingBean
//  public OAuth2AuthorizeHttpRequestsConfigurerCustomer authorizeHttpRequestsConfigurerCustomer(SecurityMatcherConfigurer securityMatcherConfigurer, SecurityAuthorizationManager securityAuthorizationManager) {
  public OAuth2AuthorizeHttpRequestsConfigurerCustomer authorizeHttpRequestsConfigurerCustomer(SecurityMatcherConfigurer securityMatcherConfigurer) {
//    OAuth2AuthorizeHttpRequestsConfigurerCustomer OAuth2AuthorizeHttpRequestsConfigurerCustomer = new OAuth2AuthorizeHttpRequestsConfigurerCustomer(securityMatcherConfigurer, securityAuthorizationManager);
    OAuth2AuthorizeHttpRequestsConfigurerCustomer OAuth2AuthorizeHttpRequestsConfigurerCustomer = new OAuth2AuthorizeHttpRequestsConfigurerCustomer(securityMatcherConfigurer);
    log.trace("[GstDev Cloud] |- Bean [Authorize Http Requests Configurer Customer] Auto Configure.");
    return OAuth2AuthorizeHttpRequestsConfigurerCustomer;
  }
//
//  @Bean
//  @ConditionalOnMissingBean
//  public SecurityMetadataSourceAnalyzer securityMetadataSourceAnalyzer(SecurityMetadataSourceStorage securityMetadataSourceStorage, SecurityMatcherConfigurer securityMatcherConfigurer) {
//    SecurityMetadataSourceAnalyzer securityMetadataSourceAnalyzer = new SecurityMetadataSourceAnalyzer(securityMetadataSourceStorage, securityMatcherConfigurer);
//    log.trace("[GstDev Cloud] |- Bean [Security Metadata Source Analyzer] Auto Configure.");
//    return securityMetadataSourceAnalyzer;
//  }
//
  @Bean
  @ConditionalOnMissingBean
  public OAuth2ResourceServerConfigurerCustomer oauth2ResourceServerConfigurerCustomer(OAuth2AuthorizationProperties authorizationProperties, JwtDecoder jwtDecoder, OAuth2ResourceServerProperties resourceServerProperties) {
    OAuth2ResourceServerConfigurerCustomer oauth2ResourceServerConfigurerCustomer = new OAuth2ResourceServerConfigurerCustomer(authorizationProperties, jwtDecoder, resourceServerProperties);
    log.trace("[GstDev Cloud] |- Bean [OAuth2 Resource Server Configurer Customer] Auto Configure.");
    return oauth2ResourceServerConfigurerCustomer;
  }
//
//  @Bean
//  @ConditionalOnMissingBean
//  @ConditionalOnBean(OAuth2ResourceServerConfigurerCustomer.class)
//  public BearerTokenResolver bearerTokenResolver(OAuth2ResourceServerConfigurerCustomer oauth2ResourceServerConfigurerCustomer) {
//    BearerTokenResolver bearerTokenResolver = oauth2ResourceServerConfigurerCustomer.createBearerTokenResolver();
//    log.trace("[GstDev Cloud] |- Bean [Bearer Token Resolver] Auto Configure.");
//    return bearerTokenResolver;
//  }
//
//  @Bean
//  public AuditorAware<String> auditorAware() {
//    SecurityAuditorAware securityAuditorAware = new SecurityAuditorAware();
//    log.debug("[GstDev Cloud] |- Bean [Security Auditor Aware] Auto Configure.");
//    return securityAuditorAware;
//  }
}
