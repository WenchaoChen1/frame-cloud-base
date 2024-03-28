// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.oauth2.authentication.configuration;


import com.gstdev.cloud.oauth2.authentication.configurer.OAuth2AuthenticationProviderConfigurer;
import com.gstdev.cloud.oauth2.authentication.consumer.OAuth2AuthorizationCodeAuthenticationProviderConsumer;
import com.gstdev.cloud.oauth2.authentication.converter.OAuth2PasswordAuthenticationConverter;
import com.gstdev.cloud.oauth2.authentication.handler.DefaultAccessDeniedHandler;
import com.gstdev.cloud.oauth2.authentication.handler.DefaultAuthenticationEntryPoint;
import com.gstdev.cloud.oauth2.authentication.handler.DefaultAuthenticationFailureHandler;
import com.gstdev.cloud.oauth2.authentication.handler.DefaultAuthenticationSuccessHandler;
import com.gstdev.cloud.oauth2.authentication.properties.OAuth2AuthenticationProperties;
import com.gstdev.cloud.oauth2.authentication.service.DefaultUserDetailsService;
import com.gstdev.cloud.oauth2.authorization.customizer.OAuth2ResourceServerConfigurerCustomer;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.authorization.InMemoryOAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.InMemoryOAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.security.oauth2.server.authorization.web.authentication.*;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.MediaTypeRequestMatcher;

import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.interfaces.RSAPublicKey;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * <p>授权服务配置</p>
 */
@AutoConfiguration
@Slf4j
@Configuration(proxyBeanMethods = false)
@AutoConfigureAfter({RedisAutoConfiguration.class})
//@EnableConfigurationProperties(AuthorizationServerProperties.class)
//@Import({OAuth2AuthorizationConfiguration.class, OAuth2AuthenticationConfiguration.class})
public class AuthorizationServerConfiguration {

////  @Bean
////  public OAuth2TokenCustomizer<JwtEncodingContext> buildCustomizer() {
////    DefaultOAuth2TokenCustomizer tokenCustomizer = new DefaultOAuth2TokenCustomizer();
////    return tokenCustomizer;
////  }
////
////  public JwtAuthenticationConverter jwtAuthenticationConverter() {
////    var converter = new JwtAuthenticationConverter();
////    var authorities = new JwtGrantedAuthoritiesConverter();
////    authorities.setAuthoritiesClaimName("authorities");
////    authorities.setAuthorityPrefix("");
////    converter.setJwtGrantedAuthoritiesConverter(authorities);
////    return converter;
////  }
////
////  @Bean
////  public BearerTokenResolver bearerTokenResolver() {
////    return new DefaultBearerTokenResolver();
////  }

//
//  /**
//   * <p>授权服务器元信息配置</p>
//   * <p>
//   * 授权服务器本身也提供了一个配置工具来配置其元信息，大多数都使用默认配置即可，唯一需要配置的其实只有授权服务器的地址issuer
//   * 在生产中这个地方应该配置为域名
//   *
//   * @return
//   */
////  @Bean
////  public ProviderSettings providerSettings() {
////    return ProviderSettings.builder().issuer("http://os.com:9000").build();
////  }
//

//===========================aaa========================================================================================

//  @Resource
//  private AuthorizationServerProperties authorizationServerProperties;

  //  @Resource
//  private PasswordEncoder passwordEncoder;

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
  @SneakyThrows
  public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http,
                                                                    JwtDecoder jwtDecoder,
                                                                    PasswordEncoder passwordEncoder,
                                                                    UserDetailsService userDetailsService,
                                                                    OAuth2AuthenticationProperties oauth2AuthenticationProperties,
                                                                    OAuth2ResourceServerConfigurerCustomer oauth2ResourceServerConfigurerCustomer,
                                                                    OAuth2AuthorizationService authorizationService) {
    log.debug("[GstDev Cloud] |- Bean [Authorization Server Security Filter Chain] Auto Configure.");

    //是一个便利 ( static) 实用程序方法，它将默认的 OAuth2 安全配置应用于HttpSecurity.
    OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);

//    SessionRegistry sessionRegistry = OAuth2ConfigurerUtils.getOptionalBean(http, SessionRegistry.class);

    //提供完全自定义 OAuth2 授权服务器安全配置的能力。它允许您指定要使用的核心组件 - 例如，RegisteredClientRepository、
    // OAuth2AuthorizationService、OAuth2TokenGenerator和其他。此外，它还允许您自定义协议端点的请求处理逻辑 -
    // 例如，授权端点、设备授权端点、设备验证端点、令牌端点、令牌内省端点等。
    DefaultAuthenticationFailureHandler errorResponseHandler = new DefaultAuthenticationFailureHandler();
    DefaultAuthenticationSuccessHandler successResponseHandler = new DefaultAuthenticationSuccessHandler();
    DefaultAccessDeniedHandler accessDeniedHandler = new DefaultAccessDeniedHandler();
    DefaultAuthenticationEntryPoint authenticationEntryPoint = new DefaultAuthenticationEntryPoint();

    OAuth2AuthorizationServerConfigurer authorizationServerConfigurer = http.getConfigurer(OAuth2AuthorizationServerConfigurer.class);
//    authorizationServerConfigurer.registeredClientRepository(registeredClientRepository);
//    authorizationServerConfigurer.authorizationService(authorizationService);
//    authorizationServerConfigurer.authorizationConsentService(authorizationConsentService);
//    authorizationServerConfigurer.authorizationServerSettings(authorizationServerSettings);
//    authorizationServerConfigurer.tokenGenerator(tokenGenerator);
    authorizationServerConfigurer.clientAuthentication(clientAuthentication -> {
      clientAuthentication.errorResponseHandler(errorResponseHandler);//（AuthenticationFailureHandler后处理器）用于处理失败的客户端身份验证并返回OAuth2Error响应。
    });
    authorizationServerConfigurer.authorizationEndpoint(authorizationEndpoint -> {
      authorizationEndpoint.errorResponseHandler(errorResponseHandler);
//      authorizationEndpoint.consentPage(authorizationServerProperties.getu);
    });
    authorizationServerConfigurer.deviceAuthorizationEndpoint(deviceAuthorizationEndpoint -> {
      deviceAuthorizationEndpoint.errorResponseHandler(errorResponseHandler);
      //      authorizationEndpoint.consentPage(authorizationServerProperties.getu);
    });
    authorizationServerConfigurer.deviceVerificationEndpoint(deviceVerificationEndpoint -> {
      deviceVerificationEndpoint.errorResponseHandler(errorResponseHandler);
      //      authorizationEndpoint.consentPage(authorizationServerProperties.getu);
    });

// TODO 保留这三行  可替换
//    AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
//    @SuppressWarnings("unchecked")
//    OAuth2TokenGenerator<? extends OAuth2Token> tokenGenerator = http.getSharedObject(OAuth2TokenGenerator.class);
    authorizationServerConfigurer.tokenEndpoint(tokenEndpoint -> {
      AuthenticationConverter delegatingAuthenticationConverter = new DelegatingAuthenticationConverter(Arrays.asList(
        new OAuth2AuthorizationCodeAuthenticationConverter(),
        new OAuth2RefreshTokenAuthenticationConverter(),
        new OAuth2ClientCredentialsAuthenticationConverter(),
        //TODO 多加的
        new OAuth2DeviceCodeAuthenticationConverter(),
        //自定义授权模式转换器(Converter)
//        new PasswordAuthenticationConverter()
        new OAuth2PasswordAuthenticationConverter()
      ));
      tokenEndpoint.accessTokenRequestConverter(delegatingAuthenticationConverter);
//
//      // 自定义授权模式提供者(Provider)
//      tokenEndpoint.authenticationProviders(authenticationProviders ->// <2>
//        authenticationProviders.addAll(
//          // 自定义授权模式提供者(Provider)
//          List.of(
//            new PasswordAuthenticationProvider(authenticationManager, authorizationService, tokenGenerator)
//          )
//        )
//      );
//      tokenEndpoint.accessTokenResponseHandler(successResponseHandler);// 自定义成功响应
      // @formatter:on
//      tokenEndpoint.accessTokenResponseHandler(new OAuth2AccessTokenResponseHandler(httpCryptoProcessor));
//      tokenEndpoint.authenticationProviders(new OAuth2AuthorizationCodeAuthenticationProviderConsumer(http, sessionRegistry));
      tokenEndpoint.authenticationProviders(new OAuth2AuthorizationCodeAuthenticationProviderConsumer(http));
      tokenEndpoint.errorResponseHandler(errorResponseHandler);// 自定义失败响应
    });
    authorizationServerConfigurer.tokenIntrospectionEndpoint(tokenIntrospectionEndpoint -> tokenIntrospectionEndpoint.errorResponseHandler(errorResponseHandler));
    authorizationServerConfigurer.tokenRevocationEndpoint(tokenRevocationEndpoint -> tokenRevocationEndpoint.errorResponseHandler(errorResponseHandler));
//    authorizationServerConfigurer.authorizationServerMetadataEndpoint(authorizationServerMetadataEndpoint -> {
//    });
//    authorizationServerConfigurer.oidc(oidc -> oidc
//      .providerConfigurationEndpoint(providerConfigurationEndpoint -> {
//      })
//      .logoutEndpoint(logoutEndpoint -> {
//      })
//      .userInfoEndpoint(userInfoEndpoint -> {
//      })
//      .clientRegistrationEndpoint(clientRegistrationEndpoint -> {
//      })
//    );
    // Enable OpenID Connect 1.0
    authorizationServerConfigurer.oidc(Customizer.withDefaults());


//    RequestMatcher endpointsMatcher = authorizationServerConfigurer.getEndpointsMatcher();
    // 仅拦截 OAuth2 Authorization Server 的相关 endpoint
//    http.securityMatcher(endpointsMatcher)
    http
      //在未通过身份验证时重定向到登录页面
      //授权端点
      .exceptionHandling(exceptions -> {
        exceptions.defaultAuthenticationEntryPointFor(
          new LoginUrlAuthenticationEntryPoint("/login"),
          new MediaTypeRequestMatcher(MediaType.TEXT_HTML));
//        exceptions.accessDeniedHandler(accessDeniedHandler);
//        exceptions.authenticationEntryPoint(authenticationEntryPoint);
      })
      // 开启请求认证
//      .authorizeHttpRequests(authorizeRequests -> authorizeRequests.anyRequest().authenticated())
      // 禁用对 OAuth2 Authorization Server 相关 endpoint 的 CSRF 防御
//      .csrf(csrf -> csrf.ignoringRequestMatchers(endpointsMatcher))
//      .formLogin(oauth2FormLoginConfigurerCustomizer)
//      .sessionManagement(oauth2sessionManagementConfigurerCustomer)
//      .addFilterBefore(new MultiTenantFilter(), AuthorizationFilter.class)
      // 接受用户信息和/或客户端注册的访问令牌
//      .oauth2ResourceServer(oauth2ResourceServerConfigurerCustomer)
//      .oauth2ResourceServer(resourceServer -> {
//        // TODO 尝试删除
//        resourceServer.jwt(jwt -> jwt.decoder(jwtDecoder));
////        resourceServer.jwt(Customizer.withDefaults());
////        resourceServer.bearerTokenResolver(bearerTokenResolver);
////        resourceServer.accessDeniedHandler(accessDeniedHandler);
////        resourceServer.authenticationEntryPoint(authenticationEntryPoint);
//      })
//      .with(new OAuth2AuthenticationProviderConfigurer(sessionRegistry, passwordEncoder, userDetailsService, oauth2AuthenticationProperties), (configurer) -> {});
      .with(new OAuth2AuthenticationProviderConfigurer(passwordEncoder, userDetailsService, oauth2AuthenticationProperties), (configurer) -> {
      });


    return http.build();
  }

  /**
   * AuthorizationServerSettings是必需的组件。
   * AuthorizationServerSettings包含 OAuth2 授权服务器的配置设置。它指定URI协议端点以及颁发者标识符。协议端点的默认值URI如下
   *
   * @param authorizationServerProperties
   * @return
   */
  @Bean
  public AuthorizationServerSettings authorizationServerSettings(OAuth2AuthenticationProperties authorizationServerProperties) {
    // @formatter:off
    return AuthorizationServerSettings.builder()
//      .issuer(authorizationServerProperties.getIssuerUri())
//      .authorizationEndpoint(authorizationServerProperties.getAuthorizationEndpoint())
//      // TODO +
//      .deviceAuthorizationEndpoint(authorizationServerProperties.getDeviceAuthorizationEndpoint())
//      .deviceVerificationEndpoint(authorizationServerProperties.getDeviceVerificationEndpoint())
//      .tokenEndpoint(authorizationServerProperties.getTokenEndpoint())
//      .tokenIntrospectionEndpoint(authorizationServerProperties.getTokenIntrospectionEndpoint())
//      .tokenRevocationEndpoint(authorizationServerProperties.getTokenRevocationEndpoint())
//      .jwkSetEndpoint(authorizationServerProperties.getJwkSetEndpoint())
//      // TODO +
//      .oidcLogoutEndpoint(authorizationServerProperties.getOidcLogoutEndpoint())
//      .oidcUserInfoEndpoint(authorizationServerProperties.getOidcUserInfoEndpoint())
//      .oidcClientRegistrationEndpoint(authorizationServerProperties.getOidcClientRegistrationEndpoint())
      .build();
    // @formatter:on
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    //    return new BCryptPasswordEncoder();
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }

  @Bean
  public RegisteredClientRepository registeredClientRepository(JdbcTemplate jdbcTemplate) {
    // 操作数据库对象
//        JdbcRegisteredClientRepository registeredClientRepository = new JdbcRegisteredClientRepository(jdbcTemplate);
    List<RegisteredClient> registeredClients = this.registryCloents();
    registeredClients.forEach(registeredClient -> {
//      // ---------- 1、检查当前客户端是否已注册
//      if (registeredClientRepository.findByClientId(registeredClient.getClientId()) == null) {
//        // 2、数据库中没有 添加客户端
//        registeredClientRepository.save(registeredClient);
//        log.info("Add client");
//      }
    });
//        // ---------- 3、返回客户端仓库
//        return registeredClientRepository;
    return new InMemoryRegisteredClientRepository(registeredClients);
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
//        return new JdbcOAuth2AuthorizationService(jdbcTemplate, registeredClientRepository);
    return new InMemoryOAuth2AuthorizationService();
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
//    JdbcTemplate jdbcTemplate,
    RegisteredClientRepository registeredClientRepository) {
//        return new JdbcOAuth2AuthorizationConsentService(jdbcTemplate, registeredClientRepository);
    return new InMemoryOAuth2AuthorizationConsentService();
  }

  /**
   * 加载jwk资源
   * 用于生成令牌
   *
   * @return
   */
  @Bean
  @SneakyThrows
  public JWKSource<SecurityContext> jwkSource(OAuth2AuthenticationProperties oAuth2AuthenticationProperties) {
    String path = oAuth2AuthenticationProperties.getAuthorizationServerSettings().getJksJksPath();
    String alias = oAuth2AuthenticationProperties.getAuthorizationServerSettings().getJksAlias();
    String pass = oAuth2AuthenticationProperties.getAuthorizationServerSettings().getJksPass();

    ClassPathResource resource = new ClassPathResource(path);
    KeyStore jks = KeyStore.getInstance("jks");
    char[] pin = pass.toCharArray();
    jks.load(resource.getInputStream(), pin);
    RSAKey rsaKey = RSAKey.load(jks, alias, pin);
    JWKSet jwkSet = new JWKSet(rsaKey);

    return (jwkSelector, securityContext) -> jwkSelector.select(jwkSet);
  }

//  private final OAuth2TokenCustomizer<JwtEncodingContext> jwtCustomizer;
//  @Bean
//  OAuth2TokenGenerator<?> tokenGenerator(JWKSource<SecurityContext> jwkSource) {
//    JwtGenerator jwtGenerator = new JwtGenerator(new NimbusJwtEncoder(jwkSource));
//    jwtGenerator.setJwtCustomizer(jwtCustomizer);
//
//    OAuth2AccessTokenGenerator accessTokenGenerator = new OAuth2AccessTokenGenerator();
//    OAuth2RefreshTokenGenerator refreshTokenGenerator = new OAuth2RefreshTokenGenerator();
//    return new DelegatingOAuth2TokenGenerator(
//      jwtGenerator, accessTokenGenerator, refreshTokenGenerator);
//  }
//
//
//  @Bean
//  public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//    return authenticationConfiguration.getAuthenticationManager();
//  }


  //-------------------------------------------------------------------------web---------------------------------------

  /**
   * 用户
   *
   * @return
   */
  @Bean
  public UserDetailsService userDetailsService() {
    return new DefaultUserDetailsService();
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

  //-----------------------------封装---------------------------------------


//  public class OAuth2RegistryCloent {

  public List<RegisteredClient> registryCloents() {

             /*
         客户端在数据库中记录的区别
         ------------------------------------------
         id：仅表示客户端在数据库中的这个记录
         client_id：唯一标示客户端；请求token时，以此作为客户端的账号
         client_name：客户端的名称，可以省略
         client_secret：密码
         */
    //    /** 此处保留作为client入库代码案例
//     RegisteredClient registeredClient = RegisteredClient.withId(UUID.randomUUID().toString())
//     .clientId("messaging-client")
//     .clientSecret("secret")
//     .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
//     .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
//     .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
//     .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
//     .redirectUri("http://127.0.0.1:8080/login/oauth2/code/messaging-client-oidc")
//     .redirectUri("http://127.0.0.1:8080/authorized")
//     .scope(OidcScopes.OPENID)
//     .scope("message.read")
//     .scope("message.write")
//     .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
//     .build();
    RegisteredClient oidcClient = RegisteredClient.withId(UUID.randomUUID().toString())
      .clientId("oidc-client")
      .clientSecret("{noop}secret")
      .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
      .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
      .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
      .redirectUri("http://127.0.0.1:8080/login/oauth2/code/oidc-client")
      .postLogoutRedirectUri("http://127.0.0.1:8080/")
      .scope(OidcScopes.OPENID)
      .scope(OidcScopes.PROFILE)
      .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
      .build();
        /*
          如果使用明文，客户端认证时会自动升级加密方式，换句话说直接修改客户端密码，所以直接使用 bcrypt 加密避免不必要的麻烦
          官方ISSUE： https://github.com/spring-projects/spring-authorization-server/issues/1099
         */
//      String encodeSecret = passwordEncoder().encode(clientSecret);
//     @formatter:off
    RegisteredClient codeRegisteredClient = RegisteredClient
      .withId(UUID.randomUUID().toString())
      .clientId("code-client")
      .clientName("Code Client")
      .clientSecret(passwordEncoder().encode("black123"))
      .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
      .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
      .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
      .redirectUri("http://127.0.0.1:8080/authorized")
      .redirectUri("http://www.baidu.com")
      .scope("project:read")
      .scope("project:write")
      .clientSettings(ClientSettings.builder()
        .requireAuthorizationConsent(false)
        .requireProofKey(false)
        .build()
      )

      .tokenSettings(TokenSettings.builder()
        .accessTokenTimeToLive(Duration.ofHours(1))
        .refreshTokenTimeToLive(Duration.ofHours(3))
        .idTokenSignatureAlgorithm(SignatureAlgorithm.ES256)
        .reuseRefreshTokens(true)
        .build()
      )
      .build();

    RegisteredClient credentialsRegisteredClient = RegisteredClient
      .withId(UUID.randomUUID().toString())
      .clientId("credentials-client")
      .clientName("Credentials Client")
      .clientSecret(passwordEncoder().encode("black123"))
      .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
      .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
      .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
      .clientSettings(ClientSettings.builder()
        .requireAuthorizationConsent(false)
        .requireProofKey(false)
        .build()
      )
      .tokenSettings(TokenSettings.builder()
        .accessTokenTimeToLive(Duration.ofHours(720))
        .refreshTokenTimeToLive(Duration.ofHours(720))
        .idTokenSignatureAlgorithm(SignatureAlgorithm.ES256)
        .reuseRefreshTokens(true)
        .build()
      )
      .build();

    RegisteredClient passwordRegisteredClient = RegisteredClient
      .withId(UUID.randomUUID().toString())
      .clientId("password-client")
      .clientName("Password Client")
      .clientSecret(passwordEncoder().encode("black123"))
      .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
      .authorizationGrantType(AuthorizationGrantType.PASSWORD)
      .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
      .clientSettings(ClientSettings.builder()
        .requireAuthorizationConsent(false)
        .requireProofKey(false)
        .build()
      )

      .tokenSettings(TokenSettings.builder()
        .accessTokenTimeToLive(Duration.ofHours(720))
        .refreshTokenTimeToLive(Duration.ofHours(720))
        .idTokenSignatureAlgorithm(SignatureAlgorithm.ES256)
        .reuseRefreshTokens(true)
        .build()
      )
      .build();


    RegisteredClient registeredClient = RegisteredClient.withId(UUID.randomUUID().toString())
      .clientId("oauth2-client")
      .clientSecret(passwordEncoder().encode("123456"))
      // 客户端认证基于请求头
      .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
      // 配置授权的支持方式
      .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
      .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
      .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
      .redirectUri("https://www.baidu.com")
      .scope("user")
      .scope("admin")
      // 客户端设置，设置用户需要确认授权
      .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
      // 添加tokenSettings，将accessTokenFormat改为REFERENCE即可获取Opaque Token
      .tokenSettings(TokenSettings.builder().accessTokenFormat(OAuth2TokenFormat.REFERENCE)
        // 令牌存活时间：2小时
        .accessTokenTimeToLive(Duration.ofHours(2))
        // 令牌可以刷新，重新获取
        .reuseRefreshTokens(true)
        // 刷新时间：30天（30天内当令牌过期时，可以用刷新令牌重新申请新令牌，不需要再认证）
        .refreshTokenTimeToLive(Duration.ofDays(30)).build())
      .build();
    List<RegisteredClient> registryCloents = new ArrayList<>();
    registryCloents.add(createRegisteredClientAuthorizationCode("my_client"));
    registryCloents.add(createRegisteredClient("micro_service"));
    registryCloents.add(oidcClient);
    registryCloents.add(codeRegisteredClient);
    registryCloents.add(credentialsRegisteredClient);
    registryCloents.add(passwordRegisteredClient);

    return registryCloents;
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
//  }
}

