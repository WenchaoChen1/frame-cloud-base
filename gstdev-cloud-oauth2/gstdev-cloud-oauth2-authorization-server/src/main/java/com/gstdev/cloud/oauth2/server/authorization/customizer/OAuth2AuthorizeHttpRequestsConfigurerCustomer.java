package com.gstdev.cloud.oauth2.server.authorization.customizer;

import com.gstdev.cloud.oauth2.server.authorization.properties.SecurityMatcherConfigurer;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.stereotype.Component;

// TODO 单独注入
//@Im
@Component
public class OAuth2AuthorizeHttpRequestsConfigurerCustomer implements Customizer<AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry> {
  private final SecurityMatcherConfigurer securityMatcherConfigurer;
//  private final SecurityAuthorizationManager securityAuthorizationManager;

  public OAuth2AuthorizeHttpRequestsConfigurerCustomer(SecurityMatcherConfigurer securityMatcherConfigurer) {
    this.securityMatcherConfigurer = securityMatcherConfigurer;
  }

  public void customize(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry configurer) {

//      configurer
//        .requestMatchers(this.securityMatcherConfigurer.getStaticResourceArray())).permitAll()
//        .requestMatchers(this.securityMatcherConfigurer.getPermitAllArray())).permitAll()
////        .requestMatchers(new RequestMatcher[]{EndpointRequest.toAnyEndpoint()})).permitAll()
//        .anyRequest()).access(this.securityAuthorizationManager);
    configurer
      .requestMatchers(this.securityMatcherConfigurer.getStaticResourceArray()).permitAll()
      .requestMatchers(this.securityMatcherConfigurer.getPermitAllArray()).permitAll()
      .requestMatchers(this.securityMatcherConfigurer.getWhiteAllArray()).permitAll()
      .anyRequest().authenticated();
  }
}
