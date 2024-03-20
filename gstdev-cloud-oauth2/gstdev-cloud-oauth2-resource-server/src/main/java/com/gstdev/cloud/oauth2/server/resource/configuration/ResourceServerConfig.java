package com.gstdev.cloud.oauth2.server.resource.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.resource.web.BearerTokenResolver;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@EnableWebSecurity
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties({WhiteListProperties.class})
public class ResourceServerConfig {

  @Resource
  private WhiteListProperties whiteListProperties;

//  // @formatter:off
//  @Bean
//  SecurityFilterChain securityFilterchain(HttpSecurity http) throws Exception {
//    List<String> whiteList = whiteListProperties.getWhitelist();
//    log.info("白名单:{", whiteList.size());
//    log.info("白名单:{}", whiteList.toString());
//    http
//      .authorizeHttpRequests(authorizeRequests -> {
//        for (String s:whiteList) {
//          authorizeRequests.antMatchers(s).permitAll();
//        }
//        authorizeRequests.antMatchers("/swagger-ui/**", "/swagger-resources/**", "/v3/**").permitAll();
//        authorizeRequests.antMatchers("/actuator/**", "/instances/**").permitAll();
//        authorizeRequests.antMatchers("/xrebel/**").permitAll();
//        authorizeRequests.anyRequest().authenticated();
//      })
//      .oauth2ResourceServer()
//      .jwt();
//    return http.build();
//  }
//  // @formatter:on
@Bean
SecurityFilterChain defaultSecurityFilterChain(HttpSecurity httpsSecurity, JwtDecoder jwtDecoder, BearerTokenResolver bearerTokenResolver, UrlBasedCorsConfigurationSource configurationSource) throws Exception {
//  DefaultAccessDeniedHandler accessDeniedHandler = new DefaultAccessDeniedHandler();
//  DefaultAuthenticationEntryPoint authenticationEntryPoint = new DefaultAuthenticationEntryPoint();
  List<String> whiteList = whiteListProperties.getWhitelist();

  // @formatter:off
  httpsSecurity.authorizeRequests(authorizeRequests -> {
      for (String s : whiteList) {
        authorizeRequests.antMatchers(s).permitAll();
        log.info(s);
      }
      authorizeRequests.antMatchers("/swagger-ui/**","/swagger-resources/**", "/v3/**").permitAll();
      authorizeRequests.anyRequest().authenticated();
    })
    .sessionManagement(sessionManager -> sessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
    .csrf().disable()
    .cors(cors -> cors.configurationSource(configurationSource))
//      .formLogin(Customizer.withDefaults())
    .exceptionHandling(handling -> {
//      handling.accessDeniedHandler(accessDeniedHandler);
//      handling.authenticationEntryPoint(authenticationEntryPoint);
    })
    .oauth2ResourceServer(resourceServer -> {
      resourceServer.jwt(jwt -> jwt.decoder(jwtDecoder));
      resourceServer.bearerTokenResolver(bearerTokenResolver);

//      resourceServer.accessDeniedHandler(accessDeniedHandler);
//      resourceServer.authenticationEntryPoint(authenticationEntryPoint);
    });
  // @formatter:on

  return httpsSecurity.build();
}
}
