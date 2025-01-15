package com.gstdev.cloud.service.identity.configuration;

import com.gstdev.cloud.message.core.logic.strategy.AccountStatusEventManager;
import com.gstdev.cloud.service.identity.compliance.event.AccountStatusEventManagerImpl;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

//@AutoConfiguration
//@Import({
//  OAuth2AuthenticationConfiguration.class,
//  OAuth2DataJpaConfiguration.class
//})
@EntityScan(basePackages = {
        "com.gstdev.cloud.service.identity.domain.entity"
})
@EnableJpaRepositories(basePackages = {
        "com.gstdev.cloud.service.identity.repository",
})
@ComponentScan(basePackages = {
        "com.gstdev.cloud.service.identity.mapper",
        "com.gstdev.cloud.service.identity.service",
        "com.gstdev.cloud.service.identity.controller",
        "com.gstdev.cloud.service.identity.compliance.processor",
})
@Configuration(proxyBeanMethods = false)
@Import({
        OAuth2ManagementConfiguration.class
})
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

}
