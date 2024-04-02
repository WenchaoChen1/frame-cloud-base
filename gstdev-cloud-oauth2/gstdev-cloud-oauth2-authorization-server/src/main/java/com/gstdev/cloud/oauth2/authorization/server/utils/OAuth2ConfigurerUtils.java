package com.gstdev.cloud.oauth2.authorization.server.utils;


import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.core.ResolvableType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.core.OAuth2Token;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.authorization.InMemoryOAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.InMemoryOAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.token.*;
import org.springframework.util.StringUtils;

import java.util.Map;


/**
 * @author : wenchao.chen
 * @program: frame-cloud-base
 * @description: <p>Description: OAuth 2.0 Configurers 工具方法类</p>
 * <p>
 * 新版 spring-security-oauth2-authorization-server 很多代码都是“包”级可访问的，外部无法使用。为了方便扩展将其提取出来，便于使用。
 * <p>
 * 代码内容与原包代码基本一致。
 * @date: 2024/03/25 10:34
 */
// TODO NU
public final class OAuth2ConfigurerUtils {
    // 私有构造函数，防止实例化
    private OAuth2ConfigurerUtils() {
    }

    /**
     * 获取注册的客户端存储库实例
     *
     * @param httpSecurity HttpSecurity对象
     * @return RegisteredClientRepository实例
     */
    public static RegisteredClientRepository getRegisteredClientRepository(HttpSecurity httpSecurity) {
        RegisteredClientRepository registeredClientRepository = httpSecurity.getSharedObject(RegisteredClientRepository.class);
        if (registeredClientRepository == null) {
            registeredClientRepository = getBean(httpSecurity, RegisteredClientRepository.class);
            httpSecurity.setSharedObject(RegisteredClientRepository.class, registeredClientRepository);
        }
        return registeredClientRepository;
    }

    /**
     * 获取授权服务实例
     *
     * @param httpSecurity HttpSecurity对象
     * @return OAuth2AuthorizationService实例
     */
    public static OAuth2AuthorizationService getAuthorizationService(HttpSecurity httpSecurity) {
        OAuth2AuthorizationService authorizationService = httpSecurity.getSharedObject(OAuth2AuthorizationService.class);
        if (authorizationService == null) {
            authorizationService = getOptionalBean(httpSecurity, OAuth2AuthorizationService.class);
            if (authorizationService == null) {
                authorizationService = new InMemoryOAuth2AuthorizationService();
            }
            httpSecurity.setSharedObject(OAuth2AuthorizationService.class, authorizationService);
        }
        return authorizationService;
    }

    /**
     * 获取授权同意服务实例
     *
     * @param httpSecurity HttpSecurity对象
     * @return OAuth2AuthorizationConsentService实例
     */
    public static OAuth2AuthorizationConsentService getAuthorizationConsentService(HttpSecurity httpSecurity) {
        OAuth2AuthorizationConsentService authorizationConsentService = httpSecurity.getSharedObject(OAuth2AuthorizationConsentService.class);
        if (authorizationConsentService == null) {
            authorizationConsentService = getOptionalBean(httpSecurity, OAuth2AuthorizationConsentService.class);
            if (authorizationConsentService == null) {
                authorizationConsentService = new InMemoryOAuth2AuthorizationConsentService();
            }
            httpSecurity.setSharedObject(OAuth2AuthorizationConsentService.class, authorizationConsentService);
        }
        return authorizationConsentService;
    }

    /**
     * 获取令牌生成器实例
     *
     * @param httpSecurity HttpSecurity对象
     * @return OAuth2TokenGenerator实例
     */
    @SuppressWarnings("unchecked")
    public static OAuth2TokenGenerator<? extends OAuth2Token> getTokenGenerator(HttpSecurity httpSecurity) {
        OAuth2TokenGenerator<? extends OAuth2Token> tokenGenerator = httpSecurity.getSharedObject(OAuth2TokenGenerator.class);
        if (tokenGenerator == null) {
            tokenGenerator = getOptionalBean(httpSecurity, OAuth2TokenGenerator.class);
            if (tokenGenerator == null) {
                JwtGenerator jwtGenerator = getJwtGenerator(httpSecurity);
                OAuth2AccessTokenGenerator accessTokenGenerator = new OAuth2AccessTokenGenerator();
                OAuth2TokenCustomizer<OAuth2TokenClaimsContext> accessTokenCustomizer = getAccessTokenCustomizer(httpSecurity);
                if (accessTokenCustomizer != null) {
                    accessTokenGenerator.setAccessTokenCustomizer(accessTokenCustomizer);
                }
                OAuth2RefreshTokenGenerator refreshTokenGenerator = new OAuth2RefreshTokenGenerator();
                if (jwtGenerator != null) {
                    tokenGenerator = new DelegatingOAuth2TokenGenerator(
                        jwtGenerator, accessTokenGenerator, refreshTokenGenerator);
                } else {
                    tokenGenerator = new DelegatingOAuth2TokenGenerator(
                        accessTokenGenerator, refreshTokenGenerator);
                }
            }
            httpSecurity.setSharedObject(OAuth2TokenGenerator.class, tokenGenerator);
        }
        return tokenGenerator;
    }

    /**
     * 获取JWT生成器实例
     *
     * @param httpSecurity HttpSecurity对象
     * @return JwtGenerator实例
     */
    private static JwtGenerator getJwtGenerator(HttpSecurity httpSecurity) {
        JwtGenerator jwtGenerator = httpSecurity.getSharedObject(JwtGenerator.class);
        if (jwtGenerator == null) {
            JwtEncoder jwtEncoder = getJwtEncoder(httpSecurity);
            if (jwtEncoder != null) {
                jwtGenerator = new JwtGenerator(jwtEncoder);
                OAuth2TokenCustomizer<JwtEncodingContext> jwtCustomizer = getJwtCustomizer(httpSecurity);
                if (jwtCustomizer != null) {
                    jwtGenerator.setJwtCustomizer(jwtCustomizer);
                }
                httpSecurity.setSharedObject(JwtGenerator.class, jwtGenerator);
            }
        }
        return jwtGenerator;
    }

    /**
     * 获取JWT编码器实例
     *
     * @param httpSecurity HttpSecurity对象
     * @return JwtEncoder实例
     */
    private static JwtEncoder getJwtEncoder(HttpSecurity httpSecurity) {
        JwtEncoder jwtEncoder = httpSecurity.getSharedObject(JwtEncoder.class);
        if (jwtEncoder == null) {
            jwtEncoder = getOptionalBean(httpSecurity, JwtEncoder.class);
            if (jwtEncoder == null) {
                JWKSource<SecurityContext> jwkSource = getJwkSource(httpSecurity);
                if (jwkSource != null) {
                    jwtEncoder = new NimbusJwtEncoder(jwkSource);
                }
            }
            if (jwtEncoder != null) {
                httpSecurity.setSharedObject(JwtEncoder.class, jwtEncoder);
            }
        }
        return jwtEncoder;
    }

    /**
     * 获取JWK源实例
     *
     * @param httpSecurity HttpSecurity对象
     * @return JWKSource实例
     */
    @SuppressWarnings("unchecked")
    public static JWKSource<SecurityContext> getJwkSource(HttpSecurity httpSecurity) {
        JWKSource<SecurityContext> jwkSource = httpSecurity.getSharedObject(JWKSource.class);
        if (jwkSource == null) {
            ResolvableType type = ResolvableType.forClassWithGenerics(JWKSource.class, SecurityContext.class);
            jwkSource = getOptionalBean(httpSecurity, type);
            if (jwkSource != null) {
                httpSecurity.setSharedObject(JWKSource.class, jwkSource);
            }
        }
        return jwkSource;
    }

    /**
     * 获取JWT自定义器实例
     *
     * @param httpSecurity HttpSecurity对象
     * @return OAuth2TokenCustomizer实例
     */
    private static OAuth2TokenCustomizer<JwtEncodingContext> getJwtCustomizer(HttpSecurity httpSecurity) {
        ResolvableType type = ResolvableType.forClassWithGenerics(OAuth2TokenCustomizer.class, JwtEncodingContext.class);
        return getOptionalBean(httpSecurity, type);
    }

    /**
     * 获取访问令牌自定义器实例
     *
     * @param httpSecurity HttpSecurity对象
     * @return OAuth2TokenCustomizer实例
     */
    private static OAuth2TokenCustomizer<OAuth2TokenClaimsContext> getAccessTokenCustomizer(HttpSecurity httpSecurity) {
        ResolvableType type = ResolvableType.forClassWithGenerics(OAuth2TokenCustomizer.class, OAuth2TokenClaimsContext.class);
        return getOptionalBean(httpSecurity, type);
    }

    /**
     * 获取授权服务器设置实例
     *
     * @param httpSecurity HttpSecurity对象
     * @return AuthorizationServerSettings实例
     */
    public static AuthorizationServerSettings getAuthorizationServerSettings(HttpSecurity httpSecurity) {
        AuthorizationServerSettings authorizationServerSettings = httpSecurity.getSharedObject(AuthorizationServerSettings.class);
        if (authorizationServerSettings == null) {
            authorizationServerSettings = getBean(httpSecurity, AuthorizationServerSettings.class);
            httpSecurity.setSharedObject(AuthorizationServerSettings.class, authorizationServerSettings);
        }
        return authorizationServerSettings;
    }

    /**
     * 根据类型获取bean实例
     *
     * @param httpSecurity HttpSecurity对象
     * @param type         bean类型
     * @return bean实例
     */
    public static <T> T getBean(HttpSecurity httpSecurity, Class<T> type) {
        return httpSecurity.getSharedObject(ApplicationContext.class).getBean(type);
    }

    /**
     * 根据类型获取bean实例
     *
     * @param httpSecurity HttpSecurity对象
     * @param type         bean类型
     * @return bean实例
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(HttpSecurity httpSecurity, ResolvableType type) {
        ApplicationContext context = httpSecurity.getSharedObject(ApplicationContext.class);
        String[] names = context.getBeanNamesForType(type);
        if (names.length == 1) {
            return (T) context.getBean(names[0]);
        }
        if (names.length > 1) {
            throw new NoUniqueBeanDefinitionException(type, names);
        }
        throw new NoSuchBeanDefinitionException(type);
    }

    /**
     * 获取可选的bean实例
     *
     * @param httpSecurity HttpSecurity对象
     * @param type         bean类型
     * @return bean实例
     */
    public static <T> T getOptionalBean(HttpSecurity httpSecurity, Class<T> type) {
        Map<String, T> beansMap = BeanFactoryUtils.beansOfTypeIncludingAncestors(
            httpSecurity.getSharedObject(ApplicationContext.class), type);
        if (beansMap.size() > 1) {
            throw new NoUniqueBeanDefinitionException(type, beansMap.size(),
                "Expected single matching bean of type '" + type.getName() + "' but found " +
                    beansMap.size() + ": " + StringUtils.collectionToCommaDelimitedString(beansMap.keySet()));
        }
        return (!beansMap.isEmpty() ? beansMap.values().iterator().next() : null);
    }

    /**
     * 获取可选的bean实例
     *
     * @param httpSecurity HttpSecurity对象
     * @param type         bean类型
     * @return bean实例
     */
    @SuppressWarnings("unchecked")
    public static <T> T getOptionalBean(HttpSecurity httpSecurity, ResolvableType type) {
        ApplicationContext context = httpSecurity.getSharedObject(ApplicationContext.class);
        String[] names = context.getBeanNamesForType(type);
        if (names.length > 1) {
            throw new NoUniqueBeanDefinitionException(type, names);
        }
        return names.length == 1 ? (T) context.getBean(names[0]) : null;
    }

}
