package com.gstdev.cloud.starter.service.system.autoconfigure;

import com.gstdev.cloud.service.system.configuration.FrameSystemConfiguration;
import com.gstdev.cloud.starter.ouath2.resource.server.configuration.ResourceServerAutoConfiguration;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Import;

@AutoConfiguration
@Import({FrameSystemConfiguration.class, ResourceServerAutoConfiguration.class})
public class ServiceSystemAutoConfiguration {
    private static final Logger log = LoggerFactory.getLogger(ServiceSystemAutoConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.info("[GstDev Cloud] |- Module [Service System Server Starter] Auto Configure.");
    }
}
