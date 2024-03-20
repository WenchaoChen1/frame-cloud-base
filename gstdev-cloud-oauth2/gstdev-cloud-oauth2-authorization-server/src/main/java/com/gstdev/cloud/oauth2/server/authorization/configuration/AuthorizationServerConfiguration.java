// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.oauth2.server.authorization.configuration;


import com.gstdev.cloud.oauth2.server.authorization.handler.DefaultAuthenticationFailureHandler;
import com.gstdev.cloud.oauth2.server.authorization.provider.PasswordAuthenticationProvider;
import com.gstdev.cloud.oauth2.server.authorization.service.DefaultUserDetailsService;
import com.gstdev.cloud.oauth2.server.authorization.service.OAuth2ClientService;
import com.gstdev.cloud.oauth2.server.authorization.service.RedisOAuth2AuthorizationConsentService;
import com.gstdev.cloud.oauth2.server.authorization.service.RedisOAuth2AuthorizationService;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import com.gstdev.cloud.oauth2.server.authorization.converter.PasswordAuthenticationConverter;
import com.gstdev.cloud.oauth2.server.authorization.customizer.DefaultOAuth2TokenCustomizer;
import com.gstdev.cloud.oauth2.server.authorization.handler.DefaultAccessDeniedHandler;
import com.gstdev.cloud.oauth2.server.authorization.handler.DefaultAuthenticationEntryPoint;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.OAuth2Token;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.authorization.*;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.JdbcRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;


import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.annotation.Resource;
import java.security.*;
import java.time.Duration;
import java.util.Arrays;
import java.util.UUID;

@Slf4j
@Configuration(proxyBeanMethods = false)
@AutoConfigureAfter(RedisAutoConfiguration.class)
@EnableConfigurationProperties(AuthorizationServerProperties.class)
public class AuthorizationServerConfiguration {
//  @Resource
//  private AuthorizationServerProperties authorizationServerProperties;
//
//  @Autowired
//  private PasswordEncoder passwordEncoder;
//
//  @Bean
////  @Order(Ordered.HIGHEST_PRECEDENCE)
//  public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity httpSecurity, JwtDecoder jwtDecoder, BearerTokenResolver bearerTokenResolver, OAuth2AuthorizationService authorizationService) throws Exception {
//    OAuth2AuthorizationServerConfigurer authorizationServerConfigurer = new OAuth2AuthorizationServerConfigurer();
//
//    DefaultAuthenticationFailureHandler errorResponseHandler = new DefaultAuthenticationFailureHandler();
//    DefaultAccessDeniedHandler accessDeniedHandler = new DefaultAccessDeniedHandler();
//    DefaultAuthenticationEntryPoint authenticationEntryPoint = new DefaultAuthenticationEntryPoint();
//
//    authorizationServerConfigurer.authorizationEndpoint(endpoint -> endpoint.errorResponseHandler(errorResponseHandler));
//    authorizationServerConfigurer.clientAuthentication(endpoint -> endpoint.errorResponseHandler(errorResponseHandler));
//    authorizationServerConfigurer.tokenRevocationEndpoint(endpoint -> endpoint.errorResponseHandler(errorResponseHandler));
//
//    authorizationServerConfigurer.tokenEndpoint(endpoint -> {
//      // @formatter:off
//      endpoint.accessTokenRequestConverter(
//        new DelegatingAuthenticationConverter(Arrays.asList(
//          new OAuth2AuthorizationCodeAuthenticationConverter(),
//          new OAuth2RefreshTokenAuthenticationConverter(),
//          new OAuth2ClientCredentialsAuthenticationConverter(),
//          new PasswordAuthenticationConverter()
//        ))
//      );
//       // @formatter:on
//      endpoint.errorResponseHandler(errorResponseHandler);
//    });
//
//    RequestMatcher endpointsMatcher = authorizationServerConfigurer.getEndpointsMatcher();
//    // @formatter:off
//    httpSecurity.requestMatcher(endpointsMatcher)
//      .authorizeRequests(authorizeRequests -> authorizeRequests.anyRequest().authenticated())
//      .csrf(csrf -> csrf.ignoringRequestMatchers(endpointsMatcher))
//
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
//      })
//      .sessionManagement(sessionManager -> sessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
////      .formLogin(Customizer.withDefaults())
//      .apply(authorizationServerConfigurer);
//    // @formatter:on
//
//    SecurityFilterChain securityFilterChain = httpSecurity.build();
//
//    AuthenticationManager authenticationManager = httpSecurity.getSharedObject(AuthenticationManager.class);
//    @SuppressWarnings("unchecked")
//    OAuth2TokenGenerator<? extends OAuth2Token> tokenGenerator = httpSecurity.getSharedObject(OAuth2TokenGenerator.class);
//
//    PasswordAuthenticationProvider resourceOwnerPasswordAuthenticationProvider = new PasswordAuthenticationProvider(authenticationManager, authorizationService, tokenGenerator);
//    httpSecurity.authenticationProvider(resourceOwnerPasswordAuthenticationProvider);
//
//    return securityFilterChain;
//  }
//
//  @Bean
//  public RegisteredClientRepository registeredClientRepository() {
//
////     @formatter:off
//    RegisteredClient codeRegisteredClient = RegisteredClient
//    .withId(UUID.randomUUID().toString())
//    .clientId("code-client")
//    .clientName("Code Client")
//    .clientSecret(passwordEncoder.encode("black123"))
//
//    .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
//
//    .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
//    .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
//
//    .redirectUri("http://127.0.0.1:8080/authorized")
//    .redirectUri("http://www.baidu.com")
//
//    .scope("project:read")
//    .scope("project:write")
//
//    .clientSettings(ClientSettings.builder()
//      .requireAuthorizationConsent(false)
//      .requireProofKey(false)
//      .build()
//    )
//
//    .tokenSettings(TokenSettings.builder()
//      .accessTokenTimeToLive(Duration.ofHours(1))
//      .refreshTokenTimeToLive(Duration.ofHours(3))
//      .idTokenSignatureAlgorithm(SignatureAlgorithm.ES256)
//      .reuseRefreshTokens(true)
//      .build()
//    )
//    .build();
//
//    RegisteredClient credentialsRegisteredClient = RegisteredClient
//    .withId(UUID.randomUUID().toString())
//    .clientId("credentials-client")
//    .clientName("Credentials Client")
//    .clientSecret(passwordEncoder.encode("black123"))
//
//    .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
//
//    .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
//    .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
//
//    .clientSettings(ClientSettings.builder()
//      .requireAuthorizationConsent(false)
//      .requireProofKey(false)
//      .build()
//    )
//
//    .tokenSettings(TokenSettings.builder()
//      .accessTokenTimeToLive(Duration.ofHours(720))
//      .refreshTokenTimeToLive(Duration.ofHours(720))
//      .idTokenSignatureAlgorithm(SignatureAlgorithm.ES256)
//      .reuseRefreshTokens(true)
//      .build()
//    )
//    .build();
//
//    RegisteredClient passwordRegisteredClient = RegisteredClient
//      .withId(UUID.randomUUID().toString())
//      .clientId("password-client")
//      .clientName("Password Client")
//      .clientSecret(passwordEncoder.encode("black123"))
//
//      .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
//
//      .authorizationGrantType(AuthorizationGrantType.PASSWORD)
//      .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
//
//      .clientSettings(ClientSettings.builder()
//        .requireAuthorizationConsent(false)
//        .requireProofKey(false)
//        .build()
//      )
//
//      .tokenSettings(TokenSettings.builder()
//        .accessTokenTimeToLive(Duration.ofHours(720))
//        .refreshTokenTimeToLive(Duration.ofHours(720))
//        .idTokenSignatureAlgorithm(SignatureAlgorithm.ES256)
//        .reuseRefreshTokens(true)
//        .build()
//      )
//      .build();
////     @formatter:on
////    JdbcRegisteredClientRepository registeredClientRepository = new JdbcRegisteredClientRepository(jdbcTemplate);
//
////    /** 此处保留作为client入库代码案例
////     RegisteredClient registeredClient = RegisteredClient.withId(UUID.randomUUID().toString())
////     .clientId("messaging-client")
////     .clientSecret("secret")
////     .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
////     .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
////     .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
////     .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
////     .redirectUri("http://127.0.0.1:8080/login/oauth2/code/messaging-client-oidc")
////     .redirectUri("http://127.0.0.1:8080/authorized")
////     .scope(OidcScopes.OPENID)
////     .scope("message.read")
////     .scope("message.write")
////     .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
////     .build();
////
////    if (null == registeredClientRepository.findByClientId("messaging-client")) {
////      registeredClientRepository.save(registeredClient);
////      log.info("Add client");
////    }
//
////     registeredClientRepository.save(registeredClient);
////    return registeredClientRepository;
//    return new InMemoryRegisteredClientRepository(codeRegisteredClient, credentialsRegisteredClient, passwordRegisteredClient);
//  }
//
  @Bean
  public AuthorizationServerSettings authorizationServerSettings(AuthorizationServerProperties authorizationServerProperties) {
    // @formatter:off
    return AuthorizationServerSettings.builder()
      .issuer(authorizationServerProperties.getIssuerUri())
      .authorizationEndpoint(authorizationServerProperties.getAuthorizationEndpoint())
      .tokenEndpoint(authorizationServerProperties.getAccessTokenEndpoint())
      .tokenRevocationEndpoint(authorizationServerProperties.getTokenRevocationEndpoint())
      .tokenIntrospectionEndpoint(authorizationServerProperties.getTokenIntrospectionEndpoint())
      .jwkSetEndpoint(authorizationServerProperties.getJwkSetEndpoint())
      .build();
    // @formatter:on
  }
//
//  @Bean
//  public JWKSource<SecurityContext> jwkSource() throws Exception {
//    String path = authorizationServerProperties.getJksJksPath();
//    String alias = authorizationServerProperties.getJksAlias();
//    String pass = authorizationServerProperties.getJksPass();
//
//    ClassPathResource resource = new ClassPathResource(path);
//    KeyStore jks = KeyStore.getInstance("jks");
//    char[] pin = pass.toCharArray();
//    jks.load(resource.getInputStream(), pin);
//    RSAKey rsaKey = RSAKey.load(jks, alias, pin);
//    JWKSet jwkSet = new JWKSet(rsaKey);
//
//    return (jwkSelector, securityContext) -> jwkSelector.select(jwkSet);
//  }
//
//  @Bean
//  public JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwkSource) {
//    return OAuth2AuthorizationServerConfiguration.jwtDecoder(jwkSource);
//  }
//
//  @Bean
//  public OAuth2TokenCustomizer<JwtEncodingContext> buildCustomizer() {
//    DefaultOAuth2TokenCustomizer tokenCustomizer = new DefaultOAuth2TokenCustomizer();
//    return tokenCustomizer;
//  }
//
//  public JwtAuthenticationConverter jwtAuthenticationConverter() {
//    var converter = new JwtAuthenticationConverter();
//    var authorities = new JwtGrantedAuthoritiesConverter();
//
//    authorities.setAuthoritiesClaimName("authorities");
//    authorities.setAuthorityPrefix("");
//
//    converter.setJwtGrantedAuthoritiesConverter(authorities);
//
//    return converter;
//  }
//
//  @Bean
//  public BearerTokenResolver bearerTokenResolver() {
//    return new DefaultBearerTokenResolver();
//  }
//
//  @Bean
//  public OAuth2AuthorizationService authorizationService() {
//    //Redis存储token
//    return new InMemoryOAuth2AuthorizationService();
//  }
//
//  @Bean
//  public OAuth2AuthorizationConsentService authorizationConsentService() {
//    return new RedisOAuth2AuthorizationConsentService();
//  }
//
//  @Bean
//  public UserDetailsService userDetailsService() {
//    return new DefaultUserDetailsService();
//  }




  /**
   * 授权配置
   * // @Order 表示加载优先级；HIGHEST_PRECEDENCE为最高优先级
   *
   * @param http
   * @return
   * @throws Exception
   */
  @Bean
  @Order(Ordered.HIGHEST_PRECEDENCE)
  public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {
    // 定义授权服务配置器
    OAuth2AuthorizationServerConfigurer authorizationServerConfigurer =
      new OAuth2AuthorizationServerConfigurer();

    // 获取授权服务器相关的请求端点
    RequestMatcher authorizationServerEndpointsMatcher =
      authorizationServerConfigurer.getEndpointsMatcher();

    http
      // 拦截对 授权服务器 相关端点的请求
      .requestMatcher(authorizationServerEndpointsMatcher)
      // 拦载到的请求需要认证确认（登录）
      .authorizeRequests()
      // 其余所有请求都要认证
      .anyRequest().authenticated()
      .and()
      // 忽略掉相关端点的csrf（跨站请求）：对授权端点的访问可以是跨站的
      .csrf(csrf -> csrf
        .ignoringRequestMatchers(authorizationServerEndpointsMatcher))

      //.and()
      // 表单登录
      .formLogin()
      .and()
      .logout().logoutSuccessUrl("http://127.0.0.1:8000")
      .and()
      // 应用 授权服务器的配置
      .apply(authorizationServerConfigurer);
    return http.build();
  }

  /**
   * 注册客户端
   *
   * @param jdbcTemplate 操作数据库
   * @return 客户端仓库
   */
  @Bean
  public RegisteredClientRepository registeredClientRepository(JdbcTemplate jdbcTemplate) {
    // ---------- 1、检查当前客户端是否已注册
    // 操作数据库对象
    JdbcRegisteredClientRepository registeredClientRepository = new JdbcRegisteredClientRepository(jdbcTemplate);

        /*
         客户端在数据库中记录的区别
         ------------------------------------------
         id：仅表示客户端在数据库中的这个记录
         client_id：唯一标示客户端；请求token时，以此作为客户端的账号
         client_name：客户端的名称，可以省略
         client_secret：密码
         */
    String clientId_1 = "my_client";
    String clientId_2 = "micro_service";
    // 查询客户端是否存在
    RegisteredClient registeredClient_1 = registeredClientRepository.findByClientId(clientId_1);
    RegisteredClient registeredClient_2 = registeredClientRepository.findByClientId(clientId_2);

    // ---------- 2、添加客户端
    // 数据库中没有
    if (registeredClient_1 == null) {
      registeredClient_1 = this.createRegisteredClientAuthorizationCode(clientId_1);
      registeredClientRepository.save(registeredClient_1);
    }
    // 数据库中没有
    if (registeredClient_2 == null) {
      registeredClient_2 = this.createRegisteredClient(clientId_2);
      registeredClientRepository.save(registeredClient_2);
    }

    // ---------- 3、返回客户端仓库
    return registeredClientRepository;
  }

  /**
   * 定义客户端（令牌申请方式：授权码模式）
   *
   * @param clientId 客户端ID
   * @return
   */
  private RegisteredClient createRegisteredClientAuthorizationCode(final String clientId) {
    // JWT（Json Web Token）的配置项：TTL、是否复用refrechToken等等
    TokenSettings tokenSettings = TokenSettings.builder()
      // 令牌存活时间：2小时
      .accessTokenTimeToLive(Duration.ofHours(2))
      // 令牌可以刷新，重新获取
      .reuseRefreshTokens(true)
      // 刷新时间：30天（30天内当令牌过期时，可以用刷新令牌重新申请新令牌，不需要再认证）
      .refreshTokenTimeToLive(Duration.ofDays(30))
      .build();
    // 客户端相关配置
    ClientSettings clientSettings = ClientSettings.builder()
      // 是否需要用户授权确认
      .requireAuthorizationConsent(false)
      .build();

    return RegisteredClient
      // 客户端ID和密码
      .withId(UUID.randomUUID().toString())
      //.withId(id)
      .clientId(clientId)
      //.clientSecret("{noop}123456")
      .clientSecret(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("123456"))
      // 客户端名称：可省略
      .clientName("my_client_name")
      // 授权方法
      .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
      // 授权模式
      // ---- 【授权码模式】
      .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
      // ---------- 刷新令牌（授权码模式）
      .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
      /* 回调地址：
       * 授权服务器向当前客户端响应时调用下面地址；
       * 不在此列的地址将被拒统；
       * 只能使用IP或域名，不能使用localhost
       */
      .redirectUri("http://127.0.0.1:8000/login/oauth2/code/myClient")
      .redirectUri("http://127.0.0.1:8000")
      // 授权范围（当前客户端的授权范围）
      .scope("read")
      .scope("write")
      // JWT（Json Web Token）配置项
      .tokenSettings(tokenSettings)
      // 客户端配置项
      .clientSettings(clientSettings)
      .build();
  }

  /**
   * 定义客户端（令牌申请方式：客户端模式）
   *
   * @param clientId 客户端ID
   * @return
   */
  private RegisteredClient createRegisteredClient(final String clientId) {
    // JWT（Json Web Token）的配置项：TTL、是否复用refrechToken等等
    TokenSettings tokenSettings = TokenSettings.builder()
      // 令牌存活时间：1年
      .accessTokenTimeToLive(Duration.ofDays(365))
      // 令牌不可以刷新
      //.reuseRefreshTokens(false)
      .build();
    // 客户端相关配置
    ClientSettings clientSettings = ClientSettings.builder()
      // 是否需要用户授权确认
      .requireAuthorizationConsent(false)
      .build();

    return RegisteredClient
      // 客户端ID和密码
      .withId(UUID.randomUUID().toString())
      //.withId(id)
      .clientId("micro_service")
      //.clientSecret("{noop}123456")
      .clientSecret(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("123456"))
      // 客户端名称：可省略
      .clientName("micro_service")
      // 授权方法
      .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
      // 授权模式
      // ---- 【客户端模式】
      .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
      /* 回调地址：
       * 授权服务器向当前客户端响应时调用下面地址；
       * 不在此列的地址将被拒统；
       * 只能使用IP或域名，不能使用localhost
       */
      .redirectUri("http://127.0.0.1:8001")
      .redirectUri("http://127.0.0.1:8002")
      // 授权范围（当前客户端的角色）
      .scope("all")
      // JWT（Json Web Token）配置项
      .tokenSettings(tokenSettings)
      // 客户端配置项
      .clientSettings(clientSettings)
      .build();
  }

  /**
   * 令牌的发放记录
   *
   * @param jdbcTemplate               操作数据库
   * @param registeredClientRepository 客户端仓库
   * @return 授权服务
   */
  @Bean
  public OAuth2AuthorizationService auth2AuthorizationService(
    JdbcTemplate jdbcTemplate,
    RegisteredClientRepository registeredClientRepository) {
    return new JdbcOAuth2AuthorizationService(jdbcTemplate, registeredClientRepository);
  }

  /**
   * 把资源拥有者授权确认操作保存到数据库
   * 资源拥有者（Resource Owner）对客户端的授权记录
   *
   * @param jdbcTemplate               操作数据库
   * @param registeredClientRepository 客户端仓库
   * @return
   */
  @Bean
  public OAuth2AuthorizationConsentService auth2AuthorizationConsentService(
    JdbcTemplate jdbcTemplate,
    RegisteredClientRepository registeredClientRepository) {
    return new JdbcOAuth2AuthorizationConsentService(jdbcTemplate, registeredClientRepository);
  }


  /**
   * 加载jwk资源
   * 用于生成令牌
   * @return
   */
  @SneakyThrows
  @Bean
  public JWKSource<SecurityContext> jwkSource() {
    // 证书的路径
    String path = "myjks.jks";
    // 证书别名
    String alias = "myjks";
    // keystore 密码
    String pass = "123456";

    ClassPathResource resource = new ClassPathResource(path);
    KeyStore jks = KeyStore.getInstance("jks");
    char[] pin = pass.toCharArray();
    jks.load(resource.getInputStream(), pin);
    RSAKey rsaKey = RSAKey.load(jks, alias, pin);

    JWKSet jwkSet = new JWKSet(rsaKey);
    return (jwkSelector, securityContext) -> jwkSelector.select(jwkSet);
  }

  /**
   * <p>授权服务器元信息配置</p>
   * <p>
   * 授权服务器本身也提供了一个配置工具来配置其元信息，大多数都使用默认配置即可，唯一需要配置的其实只有授权服务器的地址issuer
   * 在生产中这个地方应该配置为域名
   *
   * @return
   */
//  @Bean
//  public ProviderSettings providerSettings() {
//    return ProviderSettings.builder().issuer("http://os.com:9000").build();
//  }
}
