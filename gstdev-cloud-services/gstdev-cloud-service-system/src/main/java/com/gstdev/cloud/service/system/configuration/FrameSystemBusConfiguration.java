package com.gstdev.cloud.service.system.configuration;

import com.gstdev.cloud.service.system.bus.listener.*;
import com.gstdev.cloud.service.system.bus.processor.DefaultSecurityMetadataDistributeProcessor;
import com.gstdev.cloud.service.system.bus.processor.RequestMappingStoreProcessor;
import com.gstdev.cloud.service.system.bus.processor.SecurityMetadataDistributeProcessor;
import com.gstdev.cloud.service.system.service.SysUserService;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class FrameSystemBusConfiguration {

    private static final Logger log = LoggerFactory.getLogger(FrameSystemBusConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.debug("[GstDev Cloud] |- SDK [Frame System Bus Auto Configure.");
    }

    @Bean
    @ConditionalOnMissingBean
    public SecurityMetadataDistributeProcessor securityMetadataDistributeProcessor() {
        log.debug("[GstDev Cloud] |- Frame Configure Security Metadata Distribute Processor");
        return new DefaultSecurityMetadataDistributeProcessor();
    }

    @Bean
    @ConditionalOnMissingBean
    public RequestMappingStoreProcessor requestMappingStoreProcessor(SecurityMetadataDistributeProcessor securityMetadataDistributeProcessor) {
        log.debug("[GstDev Cloud] |- Frame Configure Request Mapping Store Processor");
        return new RequestMappingStoreProcessor(securityMetadataDistributeProcessor);
    }

    @Bean
    @ConditionalOnMissingBean
    public LocalChangeUserStatusListener localChangeUserStatusListener(SysUserService sysUserService) {
        log.debug("[GstDev Cloud] |- Frame Configure Local Change User Status Listener");
        return new LocalChangeUserStatusListener(sysUserService);
    }
    @Bean
    @ConditionalOnMissingBean
    public LocalRequestMappingGatherListener localRequestMappingGatherListener(RequestMappingStoreProcessor requestMappingStoreProcessor) {
        log.debug("[GstDev Cloud] |- Frame Configure Local Request Mapping Gather Listener");
        return new LocalRequestMappingGatherListener(requestMappingStoreProcessor);
    }
    @Bean
    @ConditionalOnMissingBean
    public RemoteChangeUserStatusListener remoteChangeUserStatusListener(SysUserService sysUserService) {
        log.debug("[GstDev Cloud] |- Frame Configure Remote Change User Status Listener");
        return new RemoteChangeUserStatusListener(sysUserService);
    }
    @Bean
    @ConditionalOnMissingBean
    public RemoteRequestMappingGatherListener remoteRequestMappingGatherListener(RequestMappingStoreProcessor requestMappingStoreProcessor) {
        log.debug("[GstDev Cloud] |- Frame Configure Remote Request Mapping Gather Listener");
        return new RemoteRequestMappingGatherListener(requestMappingStoreProcessor);
    }

    @Bean
    @ConditionalOnMissingBean
    public SysAttributeChangeListener sysAttributeChangeListener(SecurityMetadataDistributeProcessor securityMetadataDistributeProcessor) {
        log.debug("[GstDev Cloud] |- Frame Configure Sys Attribute Change Listener");
        return new SysAttributeChangeListener(securityMetadataDistributeProcessor);
    }

}
