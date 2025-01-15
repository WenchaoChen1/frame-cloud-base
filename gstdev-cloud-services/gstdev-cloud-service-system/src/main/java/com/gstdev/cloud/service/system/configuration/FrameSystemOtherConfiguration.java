package com.gstdev.cloud.service.system.configuration;

import com.gstdev.cloud.oauth2.core.definition.handler.SocialAuthenticationHandler;
import com.gstdev.cloud.service.system.other.listener.*;
import com.gstdev.cloud.service.system.other.processor.*;
import com.gstdev.cloud.service.system.service.SysUserService;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class FrameSystemOtherConfiguration {

    private static final Logger log = LoggerFactory.getLogger(FrameSystemOtherConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.debug("[GstDev Cloud] |- SDK [Frame System Bus Auto Configure.");
    }

    @Bean
    @ConditionalOnMissingBean
    public SecurityMetadataDistributeProcessor securityMetadataDistributeProcessor() {
        log.debug("[GstDev Cloud] |- Frame Configure Security Metadata Distribute Processor");
        return new SecurityMetadataDistributeProcessorImpl();
    }

    @Bean
    @ConditionalOnMissingBean
    public RequestMappingStoreProcessor requestMappingStoreProcessor(SecurityMetadataDistributeProcessor securityMetadataDistributeProcessor) {
        log.debug("[GstDev Cloud] |- Frame Configure Request Mapping Store Processor");
        return new RequestMappingStoreProcessorImpl(securityMetadataDistributeProcessor);
    }
    @Bean
    @ConditionalOnMissingBean
    public SocialAuthenticationHandler defaultSocialAuthenticationHandler() {
        SocialAuthenticationHandlerImpl defaultSocialAuthenticationHandler = new SocialAuthenticationHandlerImpl();
        log.info("[GstDev Cloud] |- Bean [Default Social Authentication Handler] Auto Configure.");
        return defaultSocialAuthenticationHandler;
    }
    @Bean
    @ConditionalOnMissingBean
    public LocalChangeUserStatusListener localChangeUserStatusListener(SysUserService sysUserService) {
        log.debug("[GstDev Cloud] |- Frame Configure Local Change User Status Listener");
        return new LocalChangeUserStatusListenerImpl(sysUserService);
    }
    @Bean
    @ConditionalOnMissingBean
    public LocalRequestMappingGatherListener localRequestMappingGatherListener(RequestMappingStoreProcessor requestMappingStoreProcessor) {
        log.debug("[GstDev Cloud] |- Frame Configure Local Request Mapping Gather Listener");
        return new LocalRequestMappingGatherListenerImpl(requestMappingStoreProcessor);
    }
    @Bean
    @ConditionalOnMissingBean
    public RemoteChangeUserStatusListener remoteChangeUserStatusListener(SysUserService sysUserService) {
        log.debug("[GstDev Cloud] |- Frame Configure Remote Change User Status Listener");
        return new RemoteChangeUserStatusListenerImpl(sysUserService);
    }
    @Bean
    @ConditionalOnMissingBean
    public RemoteRequestMappingGatherListener remoteRequestMappingGatherListener(RequestMappingStoreProcessor requestMappingStoreProcessor) {
        log.debug("[GstDev Cloud] |- Frame Configure Remote Request Mapping Gather Listener");
        return new RemoteRequestMappingGatherListenerImpl(requestMappingStoreProcessor);
    }

    @Bean
    @ConditionalOnMissingBean
    public SysAttributeChangeListener sysAttributeChangeListener(SecurityMetadataDistributeProcessor securityMetadataDistributeProcessor) {
        log.debug("[GstDev Cloud] |- Frame Configure Sys Attribute Change Listener");
        return new SysAttributeChangeListenerImpl(securityMetadataDistributeProcessor);
    }

}
