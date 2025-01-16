package com.gstdev.cloud.service.identity.configuration;

import com.gstdev.cloud.message.core.logic.strategy.AccountStatusEventManager;
import com.gstdev.cloud.oauth2.authorization.server.configuration.OAuth2AuthenticationConfiguration;
import com.gstdev.cloud.oauth2.authorization.server.stamp.SignInFailureLimitedStampManager;
import com.gstdev.cloud.oauth2.data.jpa.configuration.OAuth2DataJpaConfiguration;
import com.gstdev.cloud.service.identity.compliance.event.AccountStatusEventManagerImpl;
import com.gstdev.cloud.service.identity.compliance.listener.AuthenticationSuccessListener;
import com.gstdev.cloud.service.identity.compliance.response.OAuth2DeviceVerificationResponseHandler;
import com.gstdev.cloud.service.identity.compliance.response.OidcClientRegistrationResponseHandler;
import com.gstdev.cloud.service.identity.service.OAuth2ComplianceService;
import com.gstdev.cloud.service.identity.service.OAuth2DeviceService;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


/**
 * @program: frame-cloud-base
 * @description: OAuth2 授权服务器自动配置模块
 * @author: wenchao.chen
 * @create: 2024/03/27 17:04
 **/

@EntityScan(basePackages = {
        "com.gstdev.cloud.service.identity.domain.entity"
})
@EnableJpaRepositories(basePackages = {
        "com.gstdev.cloud.service.identity.repository",
})
@ComponentScan(basePackages = {
        "com.gstdev.cloud.service.identity.mapper",
//        "com.gstdev.cloud.service.identity.service",
//        "com.gstdev.cloud.service.identity.controller",
        "com.gstdev.cloud.service.identity.compliance.processor",
})
@Configuration(proxyBeanMethods = false)
@Import({OAuth2DataJpaConfiguration.class, OAuth2AuthenticationConfiguration.class, OAuth2ComplianceConfiguration.class,FrameIdentityServiceConfiguration.class
, FrameIdentityControllerConfiguration.class})
public class FrameIdentityConfiguration {

    private static final Logger log = LoggerFactory.getLogger(FrameIdentityConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.info("[GstDev Cloud] |- Module [OAuth2 Authorization Server Starter] Auto Configure.");
    }

    @Bean
    public AccountStatusEventManager accountStatusEventManager() {
        AccountStatusEventManagerImpl manager = new AccountStatusEventManagerImpl();
        log.trace("[GstDev Cloud] |- Bean [GstDev Cloud Account Status Event Manager] Auto Configure.");
        return manager;
    }

    @Bean
    @ConditionalOnMissingBean
    public AuthenticationSuccessListener authenticationSuccessListener(SignInFailureLimitedStampManager stampManager, OAuth2ComplianceService complianceService, OAuth2DeviceService deviceService) {
        AuthenticationSuccessListener listener = new AuthenticationSuccessListener(stampManager, complianceService);
        log.trace("[GstDev Cloud] |- Bean [OAuth2 Authentication Success Listener] Auto Configure.");
        return listener;
    }

    @Bean
    @ConditionalOnMissingBean
    public OAuth2DeviceVerificationResponseHandler oauth2DeviceVerificationResponseHandler(OAuth2DeviceService oauth2DeviceService) {
        OAuth2DeviceVerificationResponseHandler handler = new OAuth2DeviceVerificationResponseHandler(oauth2DeviceService);
        log.trace("[GstDev Cloud] |- Bean [OAuth2 Device Verification Response Handler] Auto Configure.");
        return handler;
    }

    @Bean
    @ConditionalOnMissingBean
    public OidcClientRegistrationResponseHandler oidcClientRegistrationResponseHandler(OAuth2DeviceService oauth2DeviceService) {
        OidcClientRegistrationResponseHandler handler = new OidcClientRegistrationResponseHandler(oauth2DeviceService);
        log.trace("[GstDev Cloud] |- Bean [Oidc Client Registration Response Handler] Auto Configure.");
        return handler;
    }

}
