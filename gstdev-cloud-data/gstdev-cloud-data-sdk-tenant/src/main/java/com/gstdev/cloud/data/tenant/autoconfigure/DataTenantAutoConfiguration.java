package com.gstdev.cloud.data.tenant.autoconfigure;

import com.gstdev.cloud.data.tenant.configuration.DatabaseApproachConfiguration;
import com.gstdev.cloud.data.tenant.configuration.DiscriminatorApproachConfiguration;
import com.gstdev.cloud.data.tenant.configuration.SchemaApproachConfiguration;
import com.gstdev.cloud.data.tenant.properties.MultiTenantProperties;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

/**
 * <p>Description: 多租户模块统一配置 </p>
 *
 * @author : cc
 * @date : 2023/3/28 23:12
 */
@AutoConfiguration
@EnableConfigurationProperties(MultiTenantProperties.class)
@Import({
        DiscriminatorApproachConfiguration.class,
        SchemaApproachConfiguration.class,
        DatabaseApproachConfiguration.class,
})
public class DataTenantAutoConfiguration {

    private static final Logger log = LoggerFactory.getLogger(SchemaApproachConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.info("[GstDev Cloud] |- Module [Data Tenant] Auto Configure.");
    }
}
