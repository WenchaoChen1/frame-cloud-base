package com.gstdev.cloud.starter.service.common.autoconfigure;


import com.gstdev.cloud.service.common.autoconfigure.OAuth2ResourceServerAutoConfiguration;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Import;

@AutoConfiguration
@Import({OAuth2ResourceServerAutoConfiguration.class})
public class ServiceCommonAutoConfiguration {
    private static final Logger log = LoggerFactory.getLogger(ServiceCommonAutoConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.info("[GstDev Cloud] |- Module [Service Commone Server Starter] Auto Configure.");
    }
}
