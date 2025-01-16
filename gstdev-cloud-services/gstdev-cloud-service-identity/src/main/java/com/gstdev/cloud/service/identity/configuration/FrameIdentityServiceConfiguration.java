package com.gstdev.cloud.service.identity.configuration;

import com.gstdev.cloud.oauth2.data.jpa.repository.FrameRegisteredClientRepository;
import com.gstdev.cloud.service.identity.repository.*;
import com.gstdev.cloud.service.identity.service.*;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;

/**
 * <p>Description:  模块配置 </p>
 */
@Configuration(proxyBeanMethods = false)
public class FrameIdentityServiceConfiguration {

    private static final Logger log = LoggerFactory.getLogger(FrameIdentityServiceConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.info("[GstDev Cloud] |- SDK [Frame System Service Auto Configure.");
    }

    @Bean
    @ConditionalOnMissingBean
    public InterfaceSecurityService interfaceSecurityService() {
        log.info("[GstDev Cloud] |- Frame Configure Interface Security Service");
        return new InterfaceSecurityService();
    }

    @Bean
    @ConditionalOnMissingBean
    public OAuth2ApplicationService oAuth2ApplicationService(RegisteredClientRepository registeredClientRepository, FrameRegisteredClientRepository frameRegisteredClientRepository, OAuth2ApplicationRepository applicationRepository, OAuth2ScopeRepository oAuth2ScopeRepository, OAuth2ScopeService scopeService) {
        log.info("[GstDev Cloud] |- Frame Configure OAuth2 Application Service");
        return new OAuth2ApplicationService(registeredClientRepository, frameRegisteredClientRepository, applicationRepository, oAuth2ScopeRepository, scopeService);
    }

    @Bean
    @ConditionalOnMissingBean
    public OAuth2ComplianceService oAuth2ComplianceService(OAuth2ComplianceRepository complianceRepository) {
        log.info("[GstDev Cloud] |- Frame Configure OAuth2 Compliance Service");
        return new OAuth2ComplianceService(complianceRepository);
    }
    @Bean
    @ConditionalOnMissingBean
    public OAuth2ConstantService oAuth2ConstantService() {
        log.info("[GstDev Cloud] |- Frame Configure OAuth2 Constant Service");
        return new OAuth2ConstantService();
    }
    @Bean
    @ConditionalOnMissingBean
    public OAuth2DeviceService oAuth2DeviceService(RegisteredClientRepository registeredClientRepository, FrameRegisteredClientRepository frameRegisteredClientRepository, OAuth2DeviceRepository deviceRepository, OAuth2ScopeService scopeService) {
        log.info("[GstDev Cloud] |- Frame Configure OAuth2 Device Service");
        return new OAuth2DeviceService(registeredClientRepository, frameRegisteredClientRepository, deviceRepository, scopeService);
    }

    @Bean
    @ConditionalOnMissingBean
    public OAuth2PermissionService oAuth2PermissionService(OAuth2PermissionRepository permissionRepository) {
        log.info("[GstDev Cloud] |- Frame Configure OAuth2 Permission Service");
        return new OAuth2PermissionService(permissionRepository);
    }
    @Bean
    @ConditionalOnMissingBean
    public OAuth2ProductService oAuth2ProductService(OAuth2ProductRepository productRepository) {
        log.info("[GstDev Cloud] |- Frame Configure OAuth2 Product Service");
        return new OAuth2ProductService(productRepository);
    }

    @Bean
    @ConditionalOnMissingBean
    public OAuth2ScopeService oAuth2ScopeService(OAuth2ScopeRepository oAuth2ScopeRepository) {
        log.info("[GstDev Cloud] |- Frame Configure OAuth2 Scope Service");
        return new OAuth2ScopeService(oAuth2ScopeRepository);
    }



}
