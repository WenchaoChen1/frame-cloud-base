//package com.gstdev.cloud.oauth2.server.authorization.configuration;
//
//
//import jakarta.annotation.PostConstruct;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.core.session.SessionRegistry;
//import org.springframework.session.FindByIndexNameSessionRepository;
//import org.springframework.session.Session;
//import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisIndexedHttpSession;
//import org.springframework.session.security.SpringSessionBackedSessionRegistry;
//@Configuration(
//  proxyBeanMethods = false
//)
//@EnableRedisIndexedHttpSession
//public class OAuth2SessionConfiguration {
//  private static final Logger log = LoggerFactory.getLogger(OAuth2SessionConfiguration.class);
//
//  public OAuth2SessionConfiguration() {
//  }
//
//  @PostConstruct
//  public void postConstruct() {
//    log.debug("[Herodotus] |- SDK [OAuth2 Session Sharing] Auto Configure.");
//  }
//
//  @Bean
//  @ConditionalOnMissingBean
//  public <S extends Session> SessionRegistry sessionRegistry(FindByIndexNameSessionRepository<S> sessionRepository) {
//    SpringSessionBackedSessionRegistry<S> springSessionBackedSessionRegistry = new SpringSessionBackedSessionRegistry(sessionRepository);
//    log.trace("[Herodotus] |- Bean [Spring Session Backed Session Registry] Auto Configure.");
//    return springSessionBackedSessionRegistry;
//  }
//
////  @Bean
////  @ConditionalOnMissingBean
////  public OAuth2SessionManagementConfigurerCustomer sessionManagementConfigurerCustomer(SessionRegistry sessionRegistry) {
////    OAuth2SessionManagementConfigurerCustomer OAuth2SessionManagementConfigurerCustomer = new OAuth2SessionManagementConfigurerCustomer(sessionRegistry);
////    log.trace("[Herodotus] |- Bean [Session Management Configurer Customer] Auto Configure.");
////    return OAuth2SessionManagementConfigurerCustomer;
////  }
//
////  @Bean
////  @ConditionalOnMissingBean
////  public HttpSessionEventPublisher httpSessionEventPublisher() {
////    return new HttpSessionEventPublisher();
////  }
//}