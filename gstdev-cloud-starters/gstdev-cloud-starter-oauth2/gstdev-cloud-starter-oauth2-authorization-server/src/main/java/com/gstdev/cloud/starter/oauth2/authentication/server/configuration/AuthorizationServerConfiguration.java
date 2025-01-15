// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.starter.oauth2.authentication.server.configuration;


import com.gstdev.cloud.base.core.utils.ResourceUtils;
import com.gstdev.cloud.base.definition.constants.DefaultConstants;
import com.gstdev.cloud.oauth2.authorization.server.configurer.OAuth2AuthenticationProviderConfigurer;
import com.gstdev.cloud.oauth2.authorization.server.consumer.OAuth2AuthorizationCodeAuthenticationProviderConsumer;
import com.gstdev.cloud.oauth2.authorization.server.consumer.OAuth2ClientCredentialsAuthenticationProviderConsumer;
import com.gstdev.cloud.oauth2.authorization.server.customizer.OAuth2FormLoginConfigurerCustomizer;
import com.gstdev.cloud.oauth2.authorization.server.properties.OAuth2AuthenticationProperties;
import com.gstdev.cloud.oauth2.authorization.server.provider.OAuth2ResourceOwnerPasswordAuthenticationConverter;
import com.gstdev.cloud.oauth2.authorization.server.provider.OAuth2SocialCredentialsAuthenticationConverter;
import com.gstdev.cloud.oauth2.authorization.server.response.OAuth2AccessTokenResponseHandler;
import com.gstdev.cloud.oauth2.authorization.server.response.OAuth2AuthenticationFailureResponseHandler;
import com.gstdev.cloud.oauth2.authorization.server.utils.OAuth2ConfigurerUtils;
import com.gstdev.cloud.oauth2.core.definition.service.ClientDetailsService;
import com.gstdev.cloud.oauth2.core.enums.Certificate;
import com.gstdev.cloud.oauth2.resource.server.customizer.OAuth2ResourceServerConfigurerCustomer;
import com.gstdev.cloud.oauth2.resource.server.customizer.OAuth2SessionManagementConfigurerCustomer;
import com.gstdev.cloud.oauth2.resource.server.properties.OAuth2AuthorizationProperties;
import com.gstdev.cloud.rest.protect.crypto.processor.HttpCryptoProcessor;
import com.gstdev.cloud.service.identity.compliance.response.OAuth2DeviceVerificationResponseHandler;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.Resource;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.web.authentication.*;
import org.springframework.security.rsa.crypto.KeyStoreKeyFactory;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationConverter;

import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Arrays;
import java.util.UUID;

/**
 * <p>授权服务配置</p>
 */
@AutoConfiguration
@Slf4j
@Configuration(proxyBeanMethods = false)
@AutoConfigureAfter({RedisAutoConfiguration.class})
@EnableConfigurationProperties({OAuth2AuthenticationProperties.class})
//@EnableConfigurationProperties(AuthorizationServerProperties.class)
//@Import({OAuth2AuthorizationConfiguration.class, OAuth2AuthenticationConfiguration.class})
public class AuthorizationServerConfiguration {

    /**
     * 授权配置
     * // @Order 表示加载优先级；HIGHEST_PRECEDENCE为最高优先级
     *
     * @param httpSecurity
     * @return
     * @throws Exception
     */
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    @SneakyThrows
    public SecurityFilterChain authorizationServerSecurityFilterChain(
            HttpSecurity httpSecurity,
            PasswordEncoder passwordEncoder,
            UserDetailsService userDetailsService,
            ClientDetailsService clientDetailsService,
            HttpCryptoProcessor httpCryptoProcessor,
//    OidcClientRegistrationResponseHandler oidcClientRegistrationResponseHandler,
            OAuth2AuthenticationProperties oauth2AuthenticationProperties,
            OAuth2DeviceVerificationResponseHandler oauth2DeviceVerificationResponseHandler,
            OAuth2FormLoginConfigurerCustomizer oauth2FormLoginConfigurerCustomizer,
            OAuth2SessionManagementConfigurerCustomer oauth2sessionManagementConfigurerCustomer,
            OAuth2ResourceServerConfigurerCustomer oauth2ResourceServerConfigurerCustomer
    ) {
        log.debug("[GstDev Cloud] |- Bean [Authorization Server Security Filter Chain] Auto Configure.");

        //是一个便利 ( static) 实用程序方法，它将默认的 OAuth2 安全配置应用于HttpSecurity.
        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(httpSecurity);

        SessionRegistry sessionRegistry = OAuth2ConfigurerUtils.getOptionalBean(httpSecurity, SessionRegistry.class);

        OAuth2AuthenticationFailureResponseHandler errorResponseHandler = new OAuth2AuthenticationFailureResponseHandler();
        OAuth2AuthorizationServerConfigurer authorizationServerConfigurer = httpSecurity.getConfigurer(OAuth2AuthorizationServerConfigurer.class);
//    authorizationServerConfigurer.registeredClientRepository(registeredClientRepository);
//    authorizationServerConfigurer.authorizationService(authorizationService);
//    authorizationServerConfigurer.authorizationConsentService(authorizationConsentService);
//    authorizationServerConfigurer.authorizationServerSettings(authorizationServerSettings);
//    authorizationServerConfigurer.tokenGenerator(tokenGenerator);
        authorizationServerConfigurer.clientAuthentication(clientAuthentication -> {
            clientAuthentication.errorResponseHandler(errorResponseHandler);//（AuthenticationFailureHandler后处理器）用于处理失败的客户端身份验证并返回OAuth2Error响应。
            clientAuthentication.authenticationProviders(new OAuth2ClientCredentialsAuthenticationProviderConsumer(httpSecurity, clientDetailsService));
        });
        authorizationServerConfigurer.authorizationEndpoint(authorizationEndpoint -> {
            authorizationEndpoint.errorResponseHandler(errorResponseHandler);
            authorizationEndpoint.consentPage(DefaultConstants.AUTHORIZATION_CONSENT_URI);
        });
        authorizationServerConfigurer.deviceAuthorizationEndpoint(deviceAuthorizationEndpoint -> {
            deviceAuthorizationEndpoint.errorResponseHandler(errorResponseHandler);
            deviceAuthorizationEndpoint.verificationUri(DefaultConstants.DEVICE_ACTIVATION_URI);

        });
        authorizationServerConfigurer.deviceVerificationEndpoint(deviceVerificationEndpoint -> {
            deviceVerificationEndpoint.errorResponseHandler(errorResponseHandler);
            deviceVerificationEndpoint.consentPage(DefaultConstants.AUTHORIZATION_CONSENT_URI);
            deviceVerificationEndpoint.deviceVerificationResponseHandler(oauth2DeviceVerificationResponseHandler);
        });

        authorizationServerConfigurer.tokenEndpoint(tokenEndpoint -> {
            AuthenticationConverter delegatingAuthenticationConverter = new DelegatingAuthenticationConverter(Arrays.asList(
                    new OAuth2AuthorizationCodeAuthenticationConverter()
                    , new OAuth2RefreshTokenAuthenticationConverter()
                    , new OAuth2ClientCredentialsAuthenticationConverter()
                    , new OAuth2DeviceCodeAuthenticationConverter()
                    //TODO 多加的
                    //自定义授权模式转换器(Converter)
                    , new OAuth2ResourceOwnerPasswordAuthenticationConverter(httpCryptoProcessor)
                    , new OAuth2SocialCredentialsAuthenticationConverter(httpCryptoProcessor)
            ));
            tokenEndpoint.accessTokenRequestConverter(delegatingAuthenticationConverter);
            tokenEndpoint.accessTokenResponseHandler(new OAuth2AccessTokenResponseHandler(httpCryptoProcessor));
            tokenEndpoint.errorResponseHandler(errorResponseHandler);// 自定义失败响应
            tokenEndpoint.authenticationProviders(new OAuth2AuthorizationCodeAuthenticationProviderConsumer(httpSecurity, sessionRegistry));
//            tokenEndpoint.authenticationProviders(new OAuth2AuthorizationCodeAuthenticationProviderConsumer(httpSecurity));
        });
        authorizationServerConfigurer.tokenIntrospectionEndpoint(tokenIntrospectionEndpoint -> {
            tokenIntrospectionEndpoint.errorResponseHandler(errorResponseHandler);
        });
        authorizationServerConfigurer.tokenRevocationEndpoint(tokenRevocationEndpoint -> {
            tokenRevocationEndpoint.errorResponseHandler(errorResponseHandler);
        });
//    authorizationServerConfigurer.authorizationServerMetadataEndpoint(authorizationServerMetadataEndpoint -> {
//    });
        authorizationServerConfigurer.oidc(oidc -> {
//                    oidc.providerConfigurationEndpoint(providerConfigurationEndpoint -> {
//                            })
//                            .logoutEndpoint(logoutEndpoint -> {
//                            })
//                            .userInfoEndpoint(userInfoEndpoint -> {
//                            })
//                            .clientRegistrationEndpoint(clientRegistrationEndpoint -> {
//                            });
//                    oidc.logoutEndpoint(logoutEndpoint -> {
//                    });
//                    oidc.userInfoEndpoint(userInfoEndpoint -> {
//                        userInfoEndpoint.errorResponseHandler(errorResponseHandler);
//                        userInfoEndpoint.userInfoMapper(new FrameOidcUserInfoMapper())
//                    });
//                    oidc.clientRegistrationEndpoint(clientRegistrationEndpoint -> {
//                        clientRegistrationEndpoint.errorResponseHandler(errorResponseHandler);
//                        clientRegistrationEndpoint.clientRegistrationResponseHandler(oidcClientRegistrationResponseHandler);
//                    });
        });
        // Enable OpenID Connect 1.0
        authorizationServerConfigurer.oidc(Customizer.withDefaults());


//    RequestMatcher endpointsMatcher = authorizationServerConfigurer.getEndpointsMatcher();
        // 仅拦截 OAuth2 Authorization Server 的相关 endpoint
//    http.securityMatcher(endpointsMatcher)
        httpSecurity
                // 开启请求认证
//      .authorizeHttpRequests(authorizeRequests -> authorizeRequests.anyRequest().authenticated())
                // 禁用对 OAuth2 Authorization Server 相关 endpoint 的 CSRF 防御
//      .csrf(csrf -> csrf.ignoringRequestMatchers(endpointsMatcher))
                .formLogin(oauth2FormLoginConfigurerCustomizer)
                .sessionManagement(oauth2sessionManagementConfigurerCustomer)
//      .addFilterBefore(new MultiTenantFilter(), AuthorizationFilter.class)
                // 接受用户信息和/或客户端注册的访问令牌
                .oauth2ResourceServer(oauth2ResourceServerConfigurerCustomer)
                .with(new OAuth2AuthenticationProviderConfigurer(sessionRegistry, passwordEncoder, userDetailsService, oauth2AuthenticationProperties), (configurer) -> {
                });
//                .with(new OAuth2AuthenticationProviderConfigurer(passwordEncoder, userDetailsService, oauth2AuthenticationProperties), (configurer) -> {});

        return httpSecurity.build();
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
        OAuth2AuthenticationProperties.AuthorizationServerSettings authorizationServerSettings = authorizationServerProperties.getAuthorizationServerSettings();
        // @formatter:off
    return AuthorizationServerSettings.builder()
      .issuer(authorizationServerSettings.getIssuerUri())
      .authorizationEndpoint(authorizationServerSettings.getAuthorizationEndpoint())
      // TODO +
      .deviceAuthorizationEndpoint(authorizationServerSettings.getDeviceAuthorizationEndpoint())
      .deviceVerificationEndpoint(authorizationServerSettings.getDeviceVerificationEndpoint())
      .tokenEndpoint(authorizationServerSettings.getTokenEndpoint())
      .tokenIntrospectionEndpoint(authorizationServerSettings.getTokenIntrospectionEndpoint())
      .tokenRevocationEndpoint(authorizationServerSettings.getTokenRevocationEndpoint())
      .jwkSetEndpoint(authorizationServerSettings.getJwkSetEndpoint())
      // TODO +
      .oidcLogoutEndpoint(authorizationServerSettings.getOidcLogoutEndpoint())
      .oidcUserInfoEndpoint(authorizationServerSettings.getOidcUserInfoEndpoint())
      .oidcClientRegistrationEndpoint(authorizationServerSettings.getOidcClientRegistrationEndpoint())
      .build();
    // @formatter:on
    }

//  @Bean
//  public RegisteredClientRepository registeredClientRepository(JdbcTemplate jdbcTemplate,PasswordEncoder passwordEncoder) {
//    // 操作数据库对象
////        JdbcRegisteredClientRepository registeredClientRepository = new JdbcRegisteredClientRepository(jdbcTemplate);
//    List<RegisteredClient> registeredClients = this.registryCloents(passwordEncoder);
//    registeredClients.forEach(registeredClient -> {
////      // ---------- 1、检查当前客户端是否已注册
////      if (registeredClientRepository.findByClientId(registeredClient.getClientId()) == null) {
////        // 2、数据库中没有 添加客户端
////        registeredClientRepository.save(registeredClient);
////        log.info("Add client");
////      }
//    });
////        // ---------- 3、返回客户端仓库
////        return registeredClientRepository;
//    return new InMemoryRegisteredClientRepository(registeredClients);
//  }

//  /**
//   * 令牌的发放记录
//   *
//   * @param jdbcTemplate               操作数据库
//   * @param registeredClientRepository 客户端仓库
//   * @return 授权服务
//   */
//  @Bean
//  public OAuth2AuthorizationService auth2AuthorizationService(JdbcTemplate jdbcTemplate,RegisteredClientRepository registeredClientRepository) {
////        return new JdbcOAuth2AuthorizationService(jdbcTemplate, registeredClientRepository);
//    return new InMemoryOAuth2AuthorizationService();
//  }

//  /**
//   * 把资源拥有者授权确认操作保存到数据库
//   * 资源拥有者（Resource Owner）对客户端的授权记录
//   *
//   * @param jdbcTemplate               操作数据库
//   * @param registeredClientRepository 客户端仓库
//   * @return
//   */
//  @Bean
//  public OAuth2AuthorizationConsentService auth2AuthorizationConsentService(JdbcTemplate jdbcTemplate,RegisteredClientRepository registeredClientRepository) {
////   return new JdbcOAuth2AuthorizationConsentService(jdbcTemplate, registeredClientRepository);
//    return new InMemoryOAuth2AuthorizationConsentService();
//  }

    /**
     * 加载jwk资源
     * 用于生成令牌
     *
     * @return
     */
    @Bean
    @SneakyThrows
    public JWKSource<SecurityContext> jwkSource(OAuth2AuthorizationProperties authorizationProperties) {

        OAuth2AuthorizationProperties.Jwk jwk = authorizationProperties.getJwk();

        KeyPair keyPair = null;
        if (jwk.getCertificate() == Certificate.CUSTOM) {
            try {
                Resource[] resource = ResourceUtils.getResources(jwk.getJksKeyStore());
                if (ArrayUtils.isNotEmpty(resource)) {
                    KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(resource[0], jwk.getJksStorePassword().toCharArray());
                    keyPair = keyStoreKeyFactory.getKeyPair(jwk.getJksKeyAlias(), jwk.getJksKeyPassword().toCharArray());
                }
            } catch (IOException e) {
                log.error("[GstDev Cloud] |- Read custom certificate under resource folder error!", e);
            }

        } else {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            keyPair = keyPairGenerator.generateKeyPair();
        }

        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        RSAKey rsaKey = new RSAKey.Builder(publicKey)
                .privateKey(privateKey)
                .keyID(UUID.randomUUID().toString())
                .build();
        JWKSet jwkSet = new JWKSet(rsaKey);
        return (jwkSelector, securityContext) -> jwkSelector.select(jwkSet);
    }

    /**
     * jwt 解码
     */
    @Bean
    public JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwkSource) {
        return OAuth2AuthorizationServerConfiguration.jwtDecoder(jwkSource);
    }


}

