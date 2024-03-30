package com.gstdev.cloud.oauth2.resource.customizer;

import com.gstdev.cloud.oauth2.resource.processor.SecurityMatcherConfigurer;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.stereotype.Component;

/**
 * @program: frame-cloud-base
 * @description: AuthorizeHttpRequestsConfigurer 扩展配置
 * @author: wenchao.chen
 * @create: 2024/03/25 15:55
 **/
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

    configurer
      .requestMatchers(securityMatcherConfigurer.getStaticRequestMatchers()).permitAll()
      .requestMatchers(securityMatcherConfigurer.getPermitAllRequestMatchers()).permitAll()
//      .requestMatchers(EndpointRequest.toAnyEndpoint()).permitAll()
//      .anyRequest().access(securityAuthorizationManager);
      .anyRequest().authenticated();

  }
}
