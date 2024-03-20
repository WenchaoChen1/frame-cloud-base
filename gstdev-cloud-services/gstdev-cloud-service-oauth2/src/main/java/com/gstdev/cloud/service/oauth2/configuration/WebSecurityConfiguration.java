// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.service.oauth2.configuration;

import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.interfaces.RSAPublicKey;
import java.util.ArrayList;
import java.util.List;

import com.gstdev.cloud.oauth2.server.authorization.handler.DefaultAccessDeniedHandler;
import com.gstdev.cloud.oauth2.server.authorization.handler.DefaultAuthenticationEntryPoint;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.web.BearerTokenResolver;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.annotation.Resource;

@Slf4j
@EnableWebSecurity
@EnableConfigurationProperties(WhiteListProperties.class)
public class WebSecurityConfiguration {
//
//  @Resource
//  private WhiteListProperties whiteListProperties;
//
//  @Bean
//  public PasswordEncoder passwordEncoder() {
//    return new BCryptPasswordEncoder();
//  }
//
//  @Bean
//  public UrlBasedCorsConfigurationSource configurationSource() {
//    CorsConfiguration corsConfiguration = new CorsConfiguration();
//    corsConfiguration.addAllowedOrigin("*");
//    corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
//    corsConfiguration.setAllowedHeaders(List.of("*"));
//    corsConfiguration.setMaxAge(3600L);
//
//    UrlBasedCorsConfigurationSource corsConfigurationSource = new UrlBasedCorsConfigurationSource();
//    corsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
//
//    return corsConfigurationSource;
//  }
//
//  @Bean
//  SecurityFilterChain defaultSecurityFilterChain(HttpSecurity httpsSecurity, JwtDecoder jwtDecoder, BearerTokenResolver bearerTokenResolver, UrlBasedCorsConfigurationSource configurationSource) throws Exception {
//    DefaultAccessDeniedHandler accessDeniedHandler = new DefaultAccessDeniedHandler();
//    DefaultAuthenticationEntryPoint authenticationEntryPoint = new DefaultAuthenticationEntryPoint();
//    List<String> whiteList = whiteListProperties.getWhitelist();
//
//    // @formatter:off
//    httpsSecurity.authorizeRequests(authorizeRequests -> {
//      for (String s : whiteList) {
//        authorizeRequests.antMatchers(s).permitAll();
//        log.info(s);
//      }
//      authorizeRequests.antMatchers("/swagger-ui/**","/swagger-resources/**", "/v3/**").permitAll();
//      authorizeRequests.anyRequest().authenticated();
//    })
//      .sessionManagement(sessionManager -> sessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//      .csrf().disable()
//      .cors(cors -> cors.configurationSource(configurationSource))
//      .formLogin(Customizer.withDefaults())
//      .exceptionHandling(handling -> {
//        handling.accessDeniedHandler(accessDeniedHandler);
//        handling.authenticationEntryPoint(authenticationEntryPoint);
//      })
//      .oauth2ResourceServer(resourceServer -> {
//        resourceServer.jwt(jwt -> jwt.decoder(jwtDecoder));
//        resourceServer.bearerTokenResolver(bearerTokenResolver);
//
//        resourceServer.accessDeniedHandler(accessDeniedHandler);
//        resourceServer.authenticationEntryPoint(authenticationEntryPoint);
//      });
//    // @formatter:on
//
//    return httpsSecurity.build();
//  }
  /**
   * 配置 请求授权
   *
   * @param http
   * @return
   * @throws Exception
   */
  @Bean
  SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
    // 配置 请求授权
    http.authorizeRequests(authorizeRequests ->
        // 任何请求都需要认证（不对未登录用户开放）
        authorizeRequests.anyRequest().authenticated()
      )
      // 表单登录
      .formLogin()
      .and()
      .logout().logoutSuccessUrl("http://127.0.0.1:8000")
      .and()
      .oauth2ResourceServer().jwt();
    return http.build();
  }

  /**
   * 模拟用户
   *
   * @return
   */
  @Bean
  UserDetailsService users() {
    UserDetails user = User.builder()
      .username("admin")
      .password("123456")
      .passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder()::encode)
      .roles("USER")
      //.authorities("SCOPE_userinfo")
      .build();
    return new InMemoryUserDetailsManager(user);
  }

  /**
   * jwt解码器
   * 客户端认证授权后，需要访问user信息，解码器可以从令牌中解析出user信息
   *
   * @return
   */
  @SneakyThrows
  @Bean
  JwtDecoder jwtDecoder() {
    CertificateFactory certificateFactory = CertificateFactory.getInstance("x.509");
    // 读取cer公钥证书来配置解码器
    ClassPathResource resource = new ClassPathResource("myjks.cer");
    Certificate certificate = certificateFactory.generateCertificate(resource.getInputStream());
    RSAPublicKey publicKey = (RSAPublicKey) certificate.getPublicKey();
    return NimbusJwtDecoder.withPublicKey(publicKey).build();
  }

  /**
   * 开放一些端点的访问控制
   * 不需要认证就可以访问的端口
   * @return
   */
  //@Bean
/*    WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().antMatchers("/actuator/health", "/actuator/info");
    }*/
}
