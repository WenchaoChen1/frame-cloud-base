package com.gstdev.cloud.service.identity.configuration;

import com.gstdev.cloud.service.identity.controller.*;
import com.gstdev.cloud.service.identity.service.OAuth2ApplicationService;
import com.gstdev.cloud.service.identity.service.OAuth2DeviceService;
import com.gstdev.cloud.service.identity.service.OAuth2ProductService;
import com.gstdev.cloud.service.identity.service.OAuth2ScopeService;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService;

/**
 * <p>Description:  模块配置 </p>
 */
@Configuration(proxyBeanMethods = false)
public class FrameIdentityControllerConfiguration {

    private static final Logger log = LoggerFactory.getLogger(FrameIdentityControllerConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.info("[GstDev Cloud] |- SDK [Frame System Controller Auto Configure.");
    }

    @Bean
    @ConditionalOnMissingBean
    public CaptchaController captchaController() {
        log.info("[GstDev Cloud] |- Frame Configure Captcha Controller");
        return new CaptchaController();
    }

    @Bean
    @ConditionalOnMissingBean
    public ConsentController consentController(OAuth2ApplicationService applicationService, OAuth2AuthorizationConsentService authorizationConsentService, OAuth2ScopeService scopeService) {
        log.info("[GstDev Cloud] |- Frame Configure Consent Controller");
        return new ConsentController(applicationService, authorizationConsentService, scopeService);
    }

    @Bean
    @ConditionalOnMissingBean
    public DeviceController deviceController() {
        log.info("[GstDev Cloud] |- Frame Configure Device Controller");
        return new DeviceController();
    }

    @Bean
    @ConditionalOnMissingBean
    public IdentityController identityController() {
        log.info("[GstDev Cloud] |- Frame Configure Identity Controller");
        return new IdentityController();
    }

    @Bean
    @ConditionalOnMissingBean
    public LoginController loginController() {
        log.info("[GstDev Cloud] |- Frame Configure Login Controller");
        return new LoginController();
    }

    @Bean
    @ConditionalOnMissingBean
    public OAuth2ApplicationController oAuth2ApplicationController() {
        log.info("[GstDev Cloud] |- Frame Configure OAuth2 Application Controller");
        return new OAuth2ApplicationController();
    }

    @Bean
    @ConditionalOnMissingBean
    public OAuth2AuthorizationController oAuth2AuthorizationController() {
        log.info("[GstDev Cloud] |- Frame Configure OAuth2 Authorization Controller");
        return new OAuth2AuthorizationController();
    }

    @Bean
    @ConditionalOnMissingBean
    public OAuth2ComplianceController oAuth2ComplianceController() {
        log.info("[GstDev Cloud] |- Frame Configure OAuth2 Compliance Controller");
        return new OAuth2ComplianceController();
    }

    @Bean
    @ConditionalOnMissingBean
    public OAuth2ConstantController oAuth2ConstantController() {
        log.info("[GstDev Cloud] |- Frame Configure OAuth2 Constant Controller");
        return new OAuth2ConstantController();
    }

    @Bean
    @ConditionalOnMissingBean
    public OAuth2DeviceController oAuth2DeviceController(OAuth2DeviceService oAuth2DeviceService) {
        log.info("[GstDev Cloud] |- Frame Configure OAuth2 Device Controller");
        return new OAuth2DeviceController(oAuth2DeviceService);
    }

    @Bean
    @ConditionalOnMissingBean
    public OAuth2ProductController oAuth2ProductController(OAuth2ProductService oAuth2ProductService) {
        log.info("[GstDev Cloud] |- Frame Configure OAuth2 Product Controller");
        return new OAuth2ProductController(oAuth2ProductService);
    }

    @Bean
    @ConditionalOnMissingBean
    public OAuth2ScopeController oAuth2ScopeController() {
        log.info("[GstDev Cloud] |- Frame Configure OAuth2 Scope Controller");
        return new OAuth2ScopeController();
    }

    @Bean
    @ConditionalOnMissingBean
    public OAuthSignOutController oAuthSignOutController() {
        log.info("[GstDev Cloud] |- Frame Configure OAuth SignOut Controller");
        return new OAuthSignOutController();
    }

}
