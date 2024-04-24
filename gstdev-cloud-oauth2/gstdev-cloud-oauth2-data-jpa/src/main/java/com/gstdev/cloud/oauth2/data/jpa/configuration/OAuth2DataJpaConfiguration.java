package com.gstdev.cloud.oauth2.data.jpa.configuration;

import com.gstdev.cloud.oauth2.data.jpa.service.FrameAuthorizationConsentService;
import com.gstdev.cloud.oauth2.data.jpa.service.FrameAuthorizationService;
import com.gstdev.cloud.oauth2.data.jpa.service.FrameRegisteredClientService;
import com.gstdev.cloud.oauth2.data.jpa.storage.JpaOAuth2AuthorizationConsentService;
import com.gstdev.cloud.oauth2.data.jpa.storage.JpaOAuth2AuthorizationService;
import com.gstdev.cloud.oauth2.data.jpa.storage.JpaRegisteredClientRepository;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;

/**
 * <p>Description: OAuth2 Manager 模块配置 </p>
 *
 * @author : cc
 * @date : 2022/3/1 18:25
 */
@Configuration(proxyBeanMethods = false)
@EntityScan(basePackages = {
  "com.gstdev.cloud.oauth2.data.jpa.entity"
})
@EnableJpaRepositories(basePackages = {
  "com.gstdev.cloud.oauth2.data.jpa.repository",
})
@ComponentScan(basePackages = {
  "com.gstdev.cloud.oauth2.data.jpa.service",
})
public class OAuth2DataJpaConfiguration {

  private static final Logger log = LoggerFactory.getLogger(OAuth2DataJpaConfiguration.class);

  @PostConstruct
  public void postConstruct() {
    log.debug("[GstDev Cloud] |- SDK [OAuth2 Data JPA] Auto Configure.");
  }

  @Bean
  @ConditionalOnMissingBean
  public RegisteredClientRepository registeredClientRepository(FrameRegisteredClientService registeredClientService, PasswordEncoder passwordEncoder) {
    JpaRegisteredClientRepository jpaRegisteredClientRepository = new JpaRegisteredClientRepository(registeredClientService, passwordEncoder);
    log.debug("[GstDev Cloud] |- Bean [Jpa Registered Client Repository] Auto Configure.");
    return jpaRegisteredClientRepository;
  }

  @Bean
  @ConditionalOnMissingBean
  public OAuth2AuthorizationService authorizationService(FrameAuthorizationService authorizationService, RegisteredClientRepository registeredClientRepository) {
    JpaOAuth2AuthorizationService jpaOAuth2AuthorizationService = new JpaOAuth2AuthorizationService(authorizationService, registeredClientRepository);
    log.debug("[GstDev Cloud] |- Bean [Jpa OAuth2 Authorization Service] Auto Configure.");
    return jpaOAuth2AuthorizationService;
  }

  @Bean
  @ConditionalOnMissingBean
  public OAuth2AuthorizationConsentService authorizationConsentService(FrameAuthorizationConsentService authorizationConsentService, RegisteredClientRepository registeredClientRepository) {
    JpaOAuth2AuthorizationConsentService jpaOAuth2AuthorizationConsentService = new JpaOAuth2AuthorizationConsentService(authorizationConsentService, registeredClientRepository);
    log.debug("[GstDev Cloud] |- Bean [Jpa OAuth2 Authorization Consent Service] Auto Configure.");
    return jpaOAuth2AuthorizationConsentService;
  }
}
