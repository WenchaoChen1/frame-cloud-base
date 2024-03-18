package com.gstdev.cloud.oauth2.server.resource.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@EnableWebSecurity
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties({WhiteListProperties.class})
public class ResourceServerConfig {

  @Resource
  private WhiteListProperties whiteListProperties;

  // @formatter:off
  @Bean
  SecurityFilterChain securityFilterchain(HttpSecurity http) throws Exception {
    List<String> whiteList = whiteListProperties.getWhitelist();
    log.info("白名单:{", whiteList.size());
    log.info("白名单:{}", whiteList.toString());
    http
      .authorizeHttpRequests(authorizeRequests -> {
        for (String s:whiteList) {
          authorizeRequests.antMatchers(s).permitAll();
        }
        authorizeRequests.antMatchers("/swagger-ui/**", "/swagger-resources/**", "/v3/**").permitAll();
        authorizeRequests.antMatchers("/actuator/**", "/instances/**").permitAll();
        authorizeRequests.antMatchers("/xrebel/**").permitAll();
        authorizeRequests.anyRequest().authenticated();
      })
      .oauth2ResourceServer()
      .jwt();
    return http.build();
  }
  // @formatter:on
}
