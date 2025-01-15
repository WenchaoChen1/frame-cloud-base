//package com.gstdev.cloud.oauth2.authorization.server.autoconfigure;
//
//import com.gstdev.cloud.data.tenant.annotation.ConditionalOnDatabaseApproach;
//import jakarta.annotation.PostConstruct;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.autoconfigure.AutoConfiguration;
//import org.springframework.context.annotation.ComponentScan;
//
///**
// * <p>Description: 多租户 </p>
// *
// * @author : cc
// * @date : 2023/3/29 21:18
// */
//@AutoConfiguration
//@ConditionalOnDatabaseApproach
//@ComponentScan(basePackages = {
//        "cn.dev.engine.oauth2.authorization.autoconfigure.tenant",
//})
//public class TenantDataSourceAutoConfiguration {
//
//    private static final Logger log = LoggerFactory.getLogger(TenantDataSourceAutoConfiguration.class);
//
//    @PostConstruct
//    public void postConstruct() {
//        log.info("[GstDev Cloud] |- Module [Tenant Data Source] Auto Configure.");
//    }
//}
