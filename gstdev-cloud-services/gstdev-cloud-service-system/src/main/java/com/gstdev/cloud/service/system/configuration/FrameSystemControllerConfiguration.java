package com.gstdev.cloud.service.system.configuration;

import com.gstdev.cloud.service.system.controller.*;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>Description:  模块配置 </p>
 */
@Configuration(proxyBeanMethods = false)
public class FrameSystemControllerConfiguration {

    private static final Logger log = LoggerFactory.getLogger(FrameSystemControllerConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.debug("[GstDev Cloud] |- SDK [Frame System Controller Auto Configure.");
    }

    @Bean
    @ConditionalOnMissingBean
    public SysTenantController sysTenantController() {
        log.debug("[GstDev Cloud] |- Frame Configure Tenant Controller");
        return new SysTenantController();
    }

    @Bean
    @ConditionalOnMissingBean
    public SysUserController sysUserController() {
        log.debug("[GstDev Cloud] |- Frame Configure User Controller");
        return new SysUserController();
    }

    @Bean
    @ConditionalOnMissingBean
    public SysAccountController sysAccountController() {
        log.debug("[GstDev Cloud] |- Frame Configure Account Controller");
        return new SysAccountController();
    }

    @Bean
    @ConditionalOnMissingBean
    public SysRoleController sysRoleController() {
        log.debug("[GstDev Cloud] |- Frame Configure Role Controller");
        return new SysRoleController();
    }

    @Bean
    @ConditionalOnMissingBean
    public SysMenuController sysMenuController() {
        log.debug("[GstDev Cloud] |- Frame Configure Menu Controller");
        return new SysMenuController();
    }

    @Bean
    @ConditionalOnMissingBean
    public SysTenantMenuController sysTenantMenuController() {
        log.debug("[GstDev Cloud] |- Frame Configure R Tenant Menu Controller");
        return new SysTenantMenuController();
    }

    @Bean
    @ConditionalOnMissingBean
    public SysPermissionController sysPermissionController() {
        log.debug("[GstDev Cloud] |- Frame Configure Permission Controller");
        return new SysPermissionController();
    }

//    @Bean
//    @ConditionalOnMissingBean
//    public SysInterfaceController sysInterfaceController() {
//        log.debug("[GstDev Cloud] |- Frame Configure Interface Controller");
//        return new SysInterfaceController();
//    }

    @Bean
    @ConditionalOnMissingBean
    public SysAttributeController sysAttributeController() {
        log.debug("[GstDev Cloud] |- Frame Configure Attribute Controller");
        return new SysAttributeController();
    }

//    @Bean
//    @ConditionalOnMissingBean
//    public SysDepartController sysDepartController() {
//        log.debug("[GstDev Cloud] |- Frame Configure Depart Controller");
//        return new SysDepartController();
//    }
//
//    @Bean
//    @ConditionalOnMissingBean
//    public SysDictController sysDictController() {
//        log.debug("[GstDev Cloud] |- Frame Configure Dict Controller");
//        return new SysDictController();
//    }
    @Bean
    @ConditionalOnMissingBean
    public SysRAttributeMenuController sysRAttributeMenuController(){
        log.debug("[GstDev Cloud] |- Frame Configure R Attribute Menu Controller");
        return new SysRAttributeMenuController();
    }
    @Bean
    @ConditionalOnMissingBean
    public SysBusinessPermissionController sysBusinessPermissionController(){
        log.debug("[GstDev Cloud] |- Frame Configure Business Permission Controller");
        return new SysBusinessPermissionController();
    }

//    @Bean
//    @ConditionalOnMissingBean
//    public SysTenantDictController sysTenantDictController(TenantDictMapper tenantDictMapper, TenantDictRepository tenantDictRepository) {
//        log.debug("[GstDev Cloud] |- Frame Configure Tenant Dict Controller");
//        return new SysTenantDictController();
//    }
    @Bean
    @ConditionalOnMissingBean
    public SysConstantController sysConstantController() {
        log.debug("[GstDev Cloud] |- Frame Configure Constant Controller");
        return new SysConstantController();
    }

    @Bean
    @ConditionalOnMissingBean
    public SysSecurityController sysSecurityController() {
        log.debug("[GstDev Cloud] |- Frame Configure Constant Controller");
        return new SysSecurityController();
    }
}
