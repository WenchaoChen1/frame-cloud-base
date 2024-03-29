package com.gstdev.cloud.data.tenant.configuration;

import com.gstdev.cloud.data.tenant.annotation.ConditionalOnSchemaApproach;
import com.gstdev.cloud.data.tenant.hibernate.SchemaMultiTenantConnectionProvider;
import jakarta.annotation.PostConstruct;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * <p>Description: 共享数据库，独立Schema多租户方式配置 </p>
 *
 * @author : cc
 * @date : 2023/3/28 22:26
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnSchemaApproach
public class SchemaApproachConfiguration {

    private static final Logger log = LoggerFactory.getLogger(SchemaApproachConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.debug("[Herodotus] |- SDK [Schema Approach] Auto Configure.");
    }

    @Bean
    public MultiTenantConnectionProvider multiTenantConnectionProvider(DataSource dataSource) {
        SchemaMultiTenantConnectionProvider schemaMultiTenantConnectionProvider = new SchemaMultiTenantConnectionProvider(dataSource);
        log.debug("[Herodotus] |- Bean [Multi Tenant Connection Provider] Auto Configure.");
        return schemaMultiTenantConnectionProvider;
    }
}
