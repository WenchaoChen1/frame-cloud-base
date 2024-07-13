package com.gstdev.cloud.service.system.service;

import com.gstdev.cloud.data.core.service.BaseServiceImpl;
import com.gstdev.cloud.service.system.domain.entity.SysRAccountTenantMenu;
import com.gstdev.cloud.service.system.domain.generator.SysRAccountTenantMenuEmbeddablePK;
import com.gstdev.cloud.service.system.repository.SysRAccountTenantMenuRepository;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Set;

@Transactional
public class SysRAccountTenantMenuServiceImpl extends BaseServiceImpl<SysRAccountTenantMenu, SysRAccountTenantMenuEmbeddablePK, SysRAccountTenantMenuRepository> implements SysRAccountTenantMenuService {

    @Resource
    private SysRAccountTenantMenuRepository repository;
    @Resource
    @Lazy
    private SysRAccountTenantMenuServiceImpl service;

    public SysRAccountTenantMenuServiceImpl(SysRAccountTenantMenuRepository sysRAccountTenantMenuRepository) {
        super(sysRAccountTenantMenuRepository);
    }

    @Override
    public SysRAccountTenantMenuRepository getRepository() {
        return repository;
    }

    @Override
    public SysRAccountTenantMenuServiceImpl getService() {
        return service;
    }


    @Override
    @Transactional
    public void updateAccountAssignedTenantMenu(String accountId, List<String> tenantMenuIds) {
        getRepository().deleteAllByAccountId(accountId);
        if (ObjectUtils.isEmpty(tenantMenuIds)) {
            return;
        }
        saveAllAndFlush(toEntityList(accountId, tenantMenuIds));
    }

    @Override
    public List<String> getAllTenantMenuIdByAccountId(String accountId) {
        return getRepository().findAllByAccountId(accountId).stream().map(SysRAccountTenantMenu::getTenantMenuId).toList();
    }

    @Override
    public List<String> getAllTenantMenuIdByAccountIds(Set<String> accountIds) {
        return getRepository().findAllByAccountIdIn(accountIds).stream().map(SysRAccountTenantMenu::getTenantMenuId).toList();
    }

    List<SysRAccountTenantMenu> toEntityList(String accountId, List<String> tenantMenuIds) {
        return tenantMenuIds.stream().map(tenantMenuId -> {
            SysRAccountTenantMenu sysRAccountTenantMenu = new SysRAccountTenantMenu();
            sysRAccountTenantMenu.setTenantMenuId(tenantMenuId);
            sysRAccountTenantMenu.setAccountId(accountId);
            return sysRAccountTenantMenu;
        }).toList();
    }

    List<SysRAccountTenantMenu> toEntityList(List<String> accountIds, String businessPermissionId) {
        return accountIds.stream().map(accountId -> {
            SysRAccountTenantMenu sysRAccountTenantMenu = new SysRAccountTenantMenu();
            sysRAccountTenantMenu.setTenantMenuId(businessPermissionId);
            sysRAccountTenantMenu.setAccountId(accountId);
            return sysRAccountTenantMenu;
        }).toList();
    }



}
