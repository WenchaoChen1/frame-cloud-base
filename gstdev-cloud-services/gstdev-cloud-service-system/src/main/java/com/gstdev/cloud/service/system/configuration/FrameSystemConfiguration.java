package com.gstdev.cloud.service.system.configuration;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * <p>Description:  模块配置 </p>
 */
@Configuration(proxyBeanMethods = false)
@EntityScan(basePackages = {
        "com.gstdev.cloud.service.system.domain.entity",
})
@EnableJpaRepositories(basePackages = {
        "com.gstdev.cloud.service.system.repository",
})
@ComponentScan(basePackages = {
        "com.gstdev.cloud.service.system.mapper",
//    "com.gstdev.cloud.service.system.controller",
})
@Import({FrameSystemServiceConfiguration.class, FrameSystemControllerConfiguration.class})
public class FrameSystemConfiguration {

    private static final Logger log = LoggerFactory.getLogger(FrameSystemConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.debug("[GstDev Cloud] |- SDK [Frame System Auto Configure.");
    }

}
