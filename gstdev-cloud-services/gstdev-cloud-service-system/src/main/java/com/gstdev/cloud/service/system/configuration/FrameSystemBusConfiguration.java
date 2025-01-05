package com.gstdev.cloud.service.system.configuration;

import com.gstdev.cloud.service.system.bus.processor.DefaultSecurityMetadataDistributeProcessor;
import com.gstdev.cloud.service.system.bus.processor.SecurityMetadataDistributeProcessor;
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


}
