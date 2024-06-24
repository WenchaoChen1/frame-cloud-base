package com.gstdev.cloud.oauth2.authorization.server.configuration;

import com.gstdev.cloud.cache.jetcache.autoconfigure.CacheJetCacheAutoConfiguration;
import com.gstdev.cloud.oauth2.authorization.server.customizer.BaseJwtTokenCustomizer;
import com.gstdev.cloud.oauth2.authorization.server.customizer.BaseOpaqueTokenCustomizer;
import com.gstdev.cloud.oauth2.authorization.server.customizer.OAuth2FormLoginConfigurerCustomizer;
import com.gstdev.cloud.oauth2.authorization.server.properties.OAuth2AuthenticationProperties;
import com.gstdev.cloud.oauth2.authorization.server.stamp.LockedUserDetailsStampManager;
import com.gstdev.cloud.oauth2.authorization.server.stamp.SignInFailureLimitedStampManager;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenClaimsContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;

/**
 * @program: frame-cloud-base
 * @description: OAuth2 认证基础模块配置
 * @author: wenchao.chen
 * @create: 2024/03/25 14:34
 **/
@AutoConfiguration(after = CacheJetCacheAutoConfiguration.class)
//@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties({OAuth2AuthenticationProperties.class})
public class OAuth2AuthenticationConfiguration {

    private static final Logger log = LoggerFactory.getLogger(OAuth2AuthenticationConfiguration.class);

    /**
     * 在构造函数执行后执行初始化操作
     */
    @PostConstruct
    public void postConstruct() {
        log.debug("[GstDev Cloud] |- SDK [OAuth2 Authentication] Auto Configure.");
    }

    /**
     * 创建一个 LockedUserDetailsStampManager 实例，用于管理锁定的用户详情信息。
     *
     * @param authenticationProperties OAuth2认证配置属性
     * @return LockedUserDetailsStampManager 实例
     */
    @Bean
    @DependsOn("jetCacheCreateCacheFactory")
    public LockedUserDetailsStampManager lockedUserDetailsStampManager(OAuth2AuthenticationProperties authenticationProperties) {
        LockedUserDetailsStampManager manager = new LockedUserDetailsStampManager(authenticationProperties);
        log.trace("[GstDev Cloud] |- Bean [Locked UserDetails Stamp Manager] Auto Configure.");
        return manager;
    }

    /**
     * 创建一个 SignInFailureLimitedStampManager 实例，用于管理登录失败的次数限制。
     *
     * @param authenticationProperties OAuth2认证配置属性
     * @return SignInFailureLimitedStampManager 实例
     */
    @Bean
    public SignInFailureLimitedStampManager signInFailureLimitedStampManager(OAuth2AuthenticationProperties authenticationProperties) {
        SignInFailureLimitedStampManager manager = new SignInFailureLimitedStampManager(authenticationProperties);
        log.trace("[GstDev Cloud] |- Bean [SignIn Failure Limited Stamp Manager] Auto Configure.");
        return manager;
    }

    /**
     * 创建一个 OAuth2FormLoginConfigurerCustomizer 实例，用于自定义OAuth2表单登录配置。
     *
     * @param authenticationProperties OAuth2认证配置属性
     * @return OAuth2FormLoginConfigurerCustomizer 实例
     */
    @Bean
    @ConditionalOnMissingBean
    public OAuth2FormLoginConfigurerCustomizer oauth2FormLoginConfigurerCustomer(OAuth2AuthenticationProperties authenticationProperties) {
        OAuth2FormLoginConfigurerCustomizer configurer = new OAuth2FormLoginConfigurerCustomizer(authenticationProperties);
        log.trace("[GstDev Cloud] |- Bean [OAuth2 FormLogin Configurer Customer] Auto Configure.");
        return configurer;
    }

    /**
     * 创建一个 OAuth2TokenCustomizer 实例，用于定制化OAuth2 JWT Token，例如添加额外信息或验证规则。
     *
     * @return OAuth2TokenCustomizer 实例
     */
    @Bean
    public OAuth2TokenCustomizer<JwtEncodingContext> jwtTokenCustomizer() {
        BaseJwtTokenCustomizer customizer = new BaseJwtTokenCustomizer();
        log.trace("[GstDev Cloud] |- Bean [OAuth2 Jwt Token Customizer] Auto Configure.");
        return customizer;
    }

    /**
     * 创建一个 OAuth2TokenCustomizer 实例，用于定制化不透明令牌。
     *
     * @return OAuth2TokenCustomizer 实例
     */
    @Bean
    public OAuth2TokenCustomizer<OAuth2TokenClaimsContext> opaqueTokenCustomizer() {
        BaseOpaqueTokenCustomizer customizer = new BaseOpaqueTokenCustomizer();
        log.trace("[GstDev Cloud] |- Bean [OAuth2 Opaque Token Customizer] Auto Configure.");
        return customizer;
    }
//
//  @Bean
//  public ErrorCodeMapperBuilderCustomizer oauth2ErrorCodeMapperBuilderCustomizer() {
//    OAuth2ErrorCodeMapperBuilderCustomizer customizer = new OAuth2ErrorCodeMapperBuilderCustomizer();
//    log.debug("[GstDev Cloud] |- Strategy [OAuth2 ErrorCodeMapper Builder Customizer] Auto Configure.");
//    return customizer;
//  }
}
