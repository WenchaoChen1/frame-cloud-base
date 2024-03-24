//package com.gstdev.cloud.oauth2.server.authorization.customizer;
//
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer;
//import org.springframework.security.core.session.SessionRegistry;
//
//public class OAuth2SessionManagementConfigurerCustomer implements Customizer<SessionManagementConfigurer<HttpSecurity>> {
//  private final SessionRegistry sessionRegistry;
//
//  public OAuth2SessionManagementConfigurerCustomer(SessionRegistry sessionRegistry) {
//    this.sessionRegistry = sessionRegistry;
//  }
//
//  public void customize(SessionManagementConfigurer<HttpSecurity> configurer) {
//    configurer.sessionAuthenticationStrategy(new HerodotusSessionAuthenticationStrategy(this.sessionRegistry));
//  }
//}
