package com.gstdev.cloud.oauth2.resource.server.customizer;

import com.gstdev.cloud.oauth2.resource.server.processor.SecurityAuthorizationManager;
import com.gstdev.cloud.oauth2.resource.server.processor.SecurityMatcherConfigurer;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
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
public class OAuth2AuthorizeHttpRequestsConfigurerCustomer implements Customizer<AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry> {

    private final SecurityMatcherConfigurer securityMatcherConfigurer;
    private final SecurityAuthorizationManager securityAuthorizationManager;

    public OAuth2AuthorizeHttpRequestsConfigurerCustomer(SecurityMatcherConfigurer securityMatcherConfigurer, SecurityAuthorizationManager securityAuthorizationManager) {
        this.securityMatcherConfigurer = securityMatcherConfigurer;
        this.securityAuthorizationManager = securityAuthorizationManager;
    }

    public void customize(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry configurer) {

        configurer
            .requestMatchers(securityMatcherConfigurer.getStaticRequestMatchers()).permitAll()
            .requestMatchers(securityMatcherConfigurer.getPermitAllRequestMatchers()).permitAll()
            .requestMatchers(EndpointRequest.toAnyEndpoint()).permitAll()
            .anyRequest().access(securityAuthorizationManager);

    }
}
