package com.gstdev.cloud.data.tenant.configuration;

import com.gstdev.cloud.data.tenant.hibernate.FrameTenantIdentifierResolver;
import jakarta.annotation.PostConstruct;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>Description: 共享数据库，独立Schema，共享数据表多租户配置 </p>
 *
 * @author : cc
 * @date : 2023/3/28 22:26
 */
@Configuration(proxyBeanMethods = false)
public class DiscriminatorApproachConfiguration {

    private static final Logger log = LoggerFactory.getLogger(DiscriminatorApproachConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.debug("[GstDev Cloud] |- SDK [Discriminator Approach] Auto Configure.");
    }

    @Bean
    public CurrentTenantIdentifierResolver currentTenantIdentifierResolver() {
        FrameTenantIdentifierResolver frameTenantIdentifierResolver = new FrameTenantIdentifierResolver();
        log.debug("[GstDev Cloud] |- Bean [Current Tenant Identifier Resolver] Auto Configure.");
        return frameTenantIdentifierResolver;
    }
}
