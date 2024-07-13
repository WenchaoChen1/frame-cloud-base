package com.gstdev.cloud.service.system.configuration;

import com.gstdev.cloud.service.system.mapper.*;
import com.gstdev.cloud.service.system.repository.*;
import com.gstdev.cloud.service.system.service.*;
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
public class FrameSystemServiceConfiguration {

    private static final Logger log = LoggerFactory.getLogger(FrameSystemServiceConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.debug("[GstDev Cloud] |- SDK [Frame System Service Auto Configure.");
    }

    @Bean
    @ConditionalOnMissingBean
    public SysTenantService sysTenantService(SysTenantRepository tenantRepository, SysTenantMapper tenantMapper) {
        log.debug("[GstDev Cloud] |- Frame Configure Tenant Service");
        return new SysTenantServiceImpl(tenantRepository, tenantMapper);
    }

    @Bean
    @ConditionalOnMissingBean
    public SysUserService sysUserService(SysUserRepository userRepository, SysUserMapper userMappe) {
        log.debug("[GstDev Cloud] |- Frame Configure User Service");
        return new SysUserServiceImpl(userRepository, userMappe);
    }

    @Bean
    @ConditionalOnMissingBean
    public SysAccountService sysAccountService(SysAccountRepository accountRepository, SysAccountMapper accountMapper) {
        log.debug("[GstDev Cloud] |- Frame Configure Account Service");
        return new SysAccountServiceImpl(accountRepository, accountMapper);
    }

    @Bean
    @ConditionalOnMissingBean
    public SysRoleService sysRoleService(SysRoleRepository roleRepository, SysRoleMapper roleMapper) {
        log.debug("[GstDev Cloud] |- Frame Configure Role Service");
        return new SysRoleServiceImpl(roleRepository, roleMapper);
    }

    @Bean
    @ConditionalOnMissingBean
    public SysMenuService sysMenuService(SysMenuRepository menuRepository, SysMenuMapper menuMapper) {
        log.debug("[GstDev Cloud] |- Frame Configure Menu Service");
        return new SysMenuServiceImpl(menuRepository, menuMapper);
    }

    @Bean
    @ConditionalOnMissingBean
    public SysTenantMenuService sysTenantMenuService(SysTenantMenuRepository rTenantMenuRepository, RTenantMenuMapper rTenantMenuMapper) {
        log.debug("[GstDev Cloud] |- Frame Configure R Tenant Menu Service");
        return new SysTenantMenuServiceImpl(rTenantMenuRepository, rTenantMenuMapper);
    }

    @Bean
    @ConditionalOnMissingBean
    public SysPermissionService sysPermissionService(SysPermissionRepository sysPermissionRepository, SysPermissionMapper sysPermissionMapper) {
        log.debug("[GstDev Cloud] |- Frame Configure Permission Service");
        return new SysPermissionServiceImpl(sysPermissionRepository, sysPermissionMapper);
    }

    @Bean
    @ConditionalOnMissingBean
    public SysInterfaceService sysInterfaceService(SysInterfaceRepository sysInterfaceRepository) {
        log.debug("[GstDev Cloud] |- Frame Configure Interface Service");
        return new SysInterfaceServiceImpl(sysInterfaceRepository);
    }

    @Bean
    @ConditionalOnMissingBean
    public SysAttributeService sysAttributeService(SysAttributeRepository sysAttributeRepository) {
        log.debug("[GstDev Cloud] |- Frame Configure Attribute Service");
        return new SysAttributeServiceImpl(sysAttributeRepository);
    }

    @Bean
    @ConditionalOnMissingBean
    public SysDepartService sysDepartService(SysDepartRepository departRepository, DepartMapper departMappe) {
        log.debug("[GstDev Cloud] |- Frame Configure Depart Service");
        return new SysDepartServiceImpl(departRepository, departMappe);
    }

    @Bean
    @ConditionalOnMissingBean
    public SysDictService sysDictService(SysDictRepository dictRepository, DictMapper dictMapper) {
        log.debug("[GstDev Cloud] |- Frame Configure Dict Service");
        return new SysDictServiceImpl(dictRepository, dictMapper);
    }

    @Bean
    @ConditionalOnMissingBean
    public SysTenantDictService sysTenantDictService(TenantDictMapper tenantDictMapper, SysTenantDictRepository tenantDictRepository) {
        log.debug("[GstDev Cloud] |- Frame Configure Tenant Dict Service");
        return new SysTenantDictServiceImpl(tenantDictMapper, tenantDictRepository);
    }

    @Bean
    @ConditionalOnMissingBean
    public SysRAttributeMenuService sysRAttributeMenuService(SysRAttributeMenuRepository tenantDictRepository) {
        log.debug("[GstDev Cloud] |- Frame Configure R Attribute Service");
        return new SysRAttributeMenuServiceImpl(tenantDictRepository);
    }

    @Bean
    @ConditionalOnMissingBean
    public SysBusinessPermissionService sysBusinessPermissionService(SysBusinessPermissionRepository sysBusinessPermissionRepository) {
        log.debug("[GstDev Cloud] |- Frame Configure Business Permission Service");
        return new SysBusinessPermissionServiceImpl(sysBusinessPermissionRepository);
    }

    @Bean
    @ConditionalOnMissingBean
    public SysRTenantMenuBusinessPermissionService sysRTenantMenuBusinessPermissionService(SysRTenantMenuBusinessPermissionRepository sysRTenantMenuBusinessPermissionRepository) {
        log.debug("[GstDev Cloud] |- Frame Configure R Tenant Menu Business Permission Service");
        return new SysRTenantMenuBusinessPermissionServiceImpl(sysRTenantMenuBusinessPermissionRepository);
    }

    @Bean
    @ConditionalOnMissingBean
    public SysRRoleBusinessPermissionService sysRRoleBusinessPermissionService(SysRRoleBusinessPermissionRepository sysRRoleBusinessPermissionRepository) {
        log.debug("[GstDev Cloud] |- Frame Configure R Tenant Menu Business Permission Service");
        return new SysRRoleBusinessPermissionServiceImpl(sysRRoleBusinessPermissionRepository);
    }

    @Bean
    @ConditionalOnMissingBean
    public SysRRoleTenantMenuService sysRRoleTenantMenuService(SysRRoleTenantMenuRepository sysRRoleTenantMenuRepository) {
        log.debug("[GstDev Cloud] |- Frame Configure R Tenant Menu Business Permission Service");
        return new SysRRoleTenantMenuServiceImpl(sysRRoleTenantMenuRepository);
    }

    @Bean
    @ConditionalOnMissingBean
    public SysRAccountBusinessPermissionService sysRAccountBusinessPermissionService(SysRAccountBusinessPermissionRepository sysRAccountBusinessPermissionRepository) {
        log.debug("[GstDev Cloud] |- Frame Configure R Tenant Menu Business Permission Service");
        return new SysRAccountBusinessPermissionServiceImpl(sysRAccountBusinessPermissionRepository);
    }

    @Bean
    @ConditionalOnMissingBean
    public SysRAccountTenantMenuService sysRAccountTenantMenuService(SysRAccountTenantMenuRepository sysRAccountTenantMenuRepository) {
        log.debug("[GstDev Cloud] |- Frame Configure R Tenant Menu Business Permission Service");
        return new SysRAccountTenantMenuServiceImpl(sysRAccountTenantMenuRepository);
    }

    @Bean
    @ConditionalOnMissingBean
    public SysRAccountRoleService sysRAccountRoleService(SysRAccountRoleRepository sysRAccountRoleRepository) {
        log.debug("[GstDev Cloud] |- Frame Configure R Tenant Menu Business Permission Service");
        return new SysRAccountRoleServiceImpl(sysRAccountRoleRepository);
    }
    @Bean
    @ConditionalOnMissingBean
    public SysConstantService sysConstantService() {
        log.debug("[GstDev Cloud] |- Frame Configure Constant Service");
        return new SysConstantServiceImpl();
    }
    @Bean
    @ConditionalOnMissingBean
    public SysSecurityService sysSecurityService() {
        log.debug("[GstDev Cloud] |- Frame Configure Constant Service");
        return new SysSecurityServiceImpl();
    }
}
