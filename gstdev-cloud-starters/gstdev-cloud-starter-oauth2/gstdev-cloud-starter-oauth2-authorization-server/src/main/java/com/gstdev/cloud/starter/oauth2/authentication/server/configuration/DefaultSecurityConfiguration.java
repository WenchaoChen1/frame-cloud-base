// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.starter.oauth2.authentication.server.configuration;

import com.gstdev.cloud.captcha.core.processor.CaptchaRendererFactory;
import com.gstdev.cloud.oauth2.authorization.server.configurer.OAuth2FormLoginSecureConfigurer;
import com.gstdev.cloud.oauth2.authorization.server.customizer.OAuth2FormLoginConfigurerCustomizer;
import com.gstdev.cloud.oauth2.authorization.server.processor.DefaultSecurityUserDetailsService;
import com.gstdev.cloud.oauth2.authorization.server.properties.OAuth2AuthenticationProperties;
import com.gstdev.cloud.oauth2.authorization.server.utils.OAuth2ConfigurerUtils;
import com.gstdev.cloud.oauth2.core.definition.strategy.StrategyUserDetailsService;
import com.gstdev.cloud.oauth2.core.response.FrameAccessDeniedHandler;
import com.gstdev.cloud.oauth2.core.response.FrameAuthenticationEntryPoint;
import com.gstdev.cloud.oauth2.resource.server.customizer.OAuth2AuthorizeHttpRequestsConfigurerCustomer;
import com.gstdev.cloud.oauth2.resource.server.customizer.OAuth2ResourceServerConfigurerCustomer;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.OAuth2Token;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenGenerator;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

/**
 * <p>Description: 默认安全配置 </p>
 *
 * @author : cc
 * @date : 2022/2/12 20:53
 */
@AutoConfiguration
@EnableWebSecurity
public class DefaultSecurityConfiguration {

    private static final Logger log = LoggerFactory.getLogger(DefaultSecurityConfiguration.class);

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
    @Order
    @SneakyThrows
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http,
                                                          UserDetailsService userDetailsService,
                                                          OAuth2AuthenticationProperties authenticationProperties,
                                                          CaptchaRendererFactory captchaRendererFactory,
//                                                        OAuth2SessionManagementConfigurerCustomer oauth2SessionManagementConfigurerCustomer,
                                                          OAuth2ResourceServerConfigurerCustomer oauth2ResourceServerConfigurerCustomer,
                                                          OAuth2FormLoginConfigurerCustomizer oauth2FormLoginConfigurerCustomizer,
                                                          OAuth2AuthorizeHttpRequestsConfigurerCustomer oauth2AuthorizeHttpRequestsConfigurerCustomer,
                                                          UrlBasedCorsConfigurationSource configurationSource) {
//    http
//      //表单login处理重定向到登录页面
//      //授权服务器过滤器链
//      .formLogin(Customizer.withDefaults());
        // 禁用CSRF 开启跨域
//    http.csrf(AbstractHttpConfigurer::disable).cors(AbstractHttpConfigurer::disable);
        // TODO 不知道做什么的
        http.csrf(c -> c.disable()).cors(cors -> cors.configurationSource(configurationSource));
        // @formatter:off
        OAuth2AuthorizationService authorizationService = OAuth2ConfigurerUtils.getAuthorizationService(http);
        OAuth2TokenGenerator<? extends OAuth2Token> tokenGenerator = OAuth2ConfigurerUtils.getTokenGenerator(http);
        ;
    http
      .authorizeHttpRequests(oauth2AuthorizeHttpRequestsConfigurerCustomer)
            .formLogin(oauth2FormLoginConfigurerCustomizer)
//      .sessionManagement(oauth2SessionManagementConfigurerCustomer)
      .exceptionHandling(exceptions -> {
        exceptions.authenticationEntryPoint(new FrameAuthenticationEntryPoint());
        exceptions.accessDeniedHandler(new FrameAccessDeniedHandler());
      })
      .oauth2ResourceServer(oauth2ResourceServerConfigurerCustomer)
      .with(new OAuth2FormLoginSecureConfigurer<>(authorizationService,tokenGenerator,userDetailsService, authenticationProperties, captchaRendererFactory), (configurer) -> {
      });


    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    log.info("[GstDev Cloud] |- Bean [password] Auto Configure.");
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }
//  @Bean
//  @ConditionalOnMissingBean
//  public AuthenticationEventPublisher authenticationEventPublisher(ApplicationContext applicationContext) {
//    log.debug("[GstDev Cloud] |- Bean [Authentication Event Publisher] Auto Configure.");
//    return new DefaultOAuth2AuthenticationEventPublisher(applicationContext);
//  }

  /**
   * 用户
   *
   * @return
   */
  @Bean
  @ConditionalOnMissingBean
  public UserDetailsService userDetailsService(StrategyUserDetailsService strategyUserDetailsService) {
    DefaultSecurityUserDetailsService defaultSecurityUserDetailsService = new DefaultSecurityUserDetailsService(strategyUserDetailsService);
    log.debug("[GstDev Cloud] |- Bean [GstDev Cloud User Details Service] Auto Configure.");
    return defaultSecurityUserDetailsService;
  }

//  @Bean
//  @ConditionalOnMissingBean
//  public ClientDetailsService clientDetailsService(OAuth2ApplicationService applicationService) {
//    HerodotusClientDetailsService herodotusClientDetailsService = new HerodotusClientDetailsService(applicationService);
//    log.debug("[GstDev Cloud] |- Bean [GstDev Cloud Client Details Service] Auto Configure.");
//    return herodotusClientDetailsService;
//  }
}
