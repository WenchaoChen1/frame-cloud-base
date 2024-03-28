// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.oauth2.authentication.configuration;

import com.gstdev.cloud.oauth2.authentication.handler.DefaultAccessDeniedHandler;
import com.gstdev.cloud.oauth2.authentication.handler.DefaultAuthenticationEntryPoint;
import com.gstdev.cloud.oauth2.authorization.customizer.OAuth2AuthorizeHttpRequestsConfigurerCustomer;
import com.gstdev.cloud.oauth2.authorization.customizer.OAuth2ResourceServerConfigurerCustomer;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

/**
 * <p>授权服务器安全策略</p>
 */
@Slf4j
@AutoConfiguration
@EnableWebSecurity
public class DefaultSecurityConfiguration {


  @Bean
  public UrlBasedCorsConfigurationSource configurationSource() {
    CorsConfiguration corsConfiguration = new CorsConfiguration();
    corsConfiguration.addAllowedOrigin("*");
    corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
    corsConfiguration.setAllowedHeaders(List.of("*"));
    corsConfiguration.setMaxAge(3600L);

    UrlBasedCorsConfigurationSource corsConfigurationSource = new UrlBasedCorsConfigurationSource();
    corsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);

    return corsConfigurationSource;
  }

  /**
   * 配置 请求授权
   *
   * @param http
   * @return
   * @throws Exception
   */
  @Bean
  @Order(2)
  @SneakyThrows
  public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http,
                                                        UrlBasedCorsConfigurationSource configurationSource,
                                                        //  BearerTokenResolver bearerTokenResolver,
                                                        JwtDecoder jwtDecoder,
                                                        OAuth2AuthorizeHttpRequestsConfigurerCustomer oauth2AuthorizeHttpRequestsConfigurerCustomer,
                                                        OAuth2ResourceServerConfigurerCustomer oauth2ResourceServerConfigurerCustomer) {
    DefaultAccessDeniedHandler accessDeniedHandler = new DefaultAccessDeniedHandler();
    DefaultAuthenticationEntryPoint authenticationEntryPoint = new DefaultAuthenticationEntryPoint();
    http
        .authorizeHttpRequests(oauth2AuthorizeHttpRequestsConfigurerCustomer)
//      .authorizeHttpRequests(authorizeRequests -> {
//        if (CollectionUtil.isNotEmpty(whiteListProperties.getWhitelist())) {
//          authorizeRequests.requestMatchers(Convert.toStrArray(whiteListProperties)).permitAll();
//        }
//        authorizeRequests.requestMatchers("/swagger-ui/**", "/swagger-resources/**", "/v3/**").permitAll();
//        authorizeRequests.anyRequest().authenticated();
//      })
//      .sessionManagement(sessionManager -> sessionManager.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
//      .csrf().disable()
      // TODO 不知道做什么的
      .csrf(c -> c.disable())
      .cors(cors -> cors.configurationSource(configurationSource))
//      .exceptionHandling(handling -> {
//        handling.accessDeniedHandler(accessDeniedHandler);
//        handling.authenticationEntryPoint(authenticationEntryPoint);
//      })
      .oauth2ResourceServer(oauth2ResourceServerConfigurerCustomer)
//      .oauth2ResourceServer(resourceServer -> {
//        resourceServer.jwt(jwt -> jwt.decoder(jwtDecoder));
//                    resourceServer.bearerTokenResolver(bearerTokenResolver);
//        resourceServer.accessDeniedHandler(accessDeniedHandler);
//        resourceServer.authenticationEntryPoint(authenticationEntryPoint);
//      })
      //表单login处理重定向到登录页面
      //授权服务器过滤器链
      .formLogin(Customizer.withDefaults());

    return http.build();
  }



}
