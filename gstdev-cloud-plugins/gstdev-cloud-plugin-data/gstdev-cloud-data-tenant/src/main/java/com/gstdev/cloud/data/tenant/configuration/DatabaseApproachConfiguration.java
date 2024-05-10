//package com.gstdev.cloud.data.tenant.configuration;
//
//import com.gstdev.cloud.data.tenant.annotation.ConditionalOnDatabaseApproach;
//import com.gstdev.cloud.data.tenant.datasource.MultiTenantDataSourceFactory;
//import com.gstdev.cloud.data.tenant.hibernate.DatabaseMultiTenantConnectionProvider;
//import com.gstdev.cloud.data.tenant.properties.MultiTenantProperties;
//import jakarta.annotation.PostConstruct;
//import org.hibernate.cfg.Environment;
//import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
//import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
//import org.springframework.boot.autoconfigure.domain.EntityScan;
//import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
//import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
//import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.JpaVendorAdapter;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.sql.DataSource;
//import java.util.Map;
//import java.util.Objects;
//import java.util.function.Supplier;
//
///**
// * <p>Description: 独立数据库多租户方式配置 </p>
// *
// * @author : cc
// * @date : 2023/3/28 23:37
// */
//@Configuration(proxyBeanMethods = false)
//@ConditionalOnDatabaseApproach
//@EnableTransactionManagement
//@EntityScan(basePackages = {
//    "com.gstdev.cloud.data.tenant.entity",
//})
//@EnableJpaRepositories(basePackages = {
//    "com.gstdev.cloud.data.tenant.repository",
//})
//public class DatabaseApproachConfiguration {
//
//    private static final Logger log = LoggerFactory.getLogger(DatabaseApproachConfiguration.class);
//
//    @PostConstruct
//    public void postConstruct() {
//        log.debug("[GstDev Cloud] |- SDK [Database Approach] Auto Configure.");
//    }
//
//    @Bean
//    public MultiTenantConnectionProvider multiTenantConnectionProvider(DataSource dataSource) {
//        DatabaseMultiTenantConnectionProvider herodotusTenantConnectionProvider = new DatabaseMultiTenantConnectionProvider(dataSource);
//        log.debug("[GstDev Cloud] |- Bean [Multi Tenant Connection Provider] Auto Configure.");
//        return herodotusTenantConnectionProvider;
//    }
//
//    @Primary
//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, HibernateProperties hibernateProperties, JpaVendorAdapter jpaVendorAdapter, JpaProperties jpaProperties, MultiTenantProperties multiTenantProperties, MultiTenantConnectionProvider multiTenantConnectionProvider, CurrentTenantIdentifierResolver currentTenantIdentifierResolver) {
//
//        Supplier<String> defaultDdlMode = hibernateProperties::getDdlAuto;
//        Map<String, Object> properties = hibernateProperties.determineHibernateProperties(jpaProperties.getProperties(), new HibernateSettings().ddlAuto(defaultDdlMode));
//
//        properties.put(Environment.MULTI_TENANT_CONNECTION_PROVIDER, multiTenantConnectionProvider);
//        properties.put(Environment.MULTI_TENANT_IDENTIFIER_RESOLVER, currentTenantIdentifierResolver);
//        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
//        entityManagerFactory.setDataSource(dataSource);
//        //此处不能省略，哪怕你使用了 @EntityScan，实际上 @EntityScan 会失效
//        entityManagerFactory.setPackagesToScan(multiTenantProperties.getPackageToScan());
//        entityManagerFactory.setJpaVendorAdapter(jpaVendorAdapter);
//        entityManagerFactory.setJpaPropertyMap(properties);
//        return entityManagerFactory;
//    }
//
//    @Primary
//    @Bean
//    @ConditionalOnClass({LocalContainerEntityManagerFactoryBean.class})
//    public PlatformTransactionManager transactionManager(LocalContainerEntityManagerFactoryBean entityManagerFactory) {
//        return new JpaTransactionManager(Objects.requireNonNull(entityManagerFactory.getObject()));
//    }
//
//    @Bean
//    @ConditionalOnClass({LocalContainerEntityManagerFactoryBean.class})
//    public MultiTenantDataSourceFactory multiTenantDataSourceFactory() {
//        MultiTenantDataSourceFactory multiTenantDataSourceFactory = new MultiTenantDataSourceFactory();
//        log.debug("[GstDev Cloud] |- Bean [Multi Tenant DataSource Factory] Auto Configure.");
//        return multiTenantDataSourceFactory;
//    }
//}
