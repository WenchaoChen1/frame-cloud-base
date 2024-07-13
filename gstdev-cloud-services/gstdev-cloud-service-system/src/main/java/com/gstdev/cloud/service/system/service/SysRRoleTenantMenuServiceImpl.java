package com.gstdev.cloud.service.system.service;

import com.gstdev.cloud.data.core.service.BaseServiceImpl;
import com.gstdev.cloud.service.system.domain.entity.SysRRoleTenantMenu;
import com.gstdev.cloud.service.system.domain.generator.SysRRoleTenantMenuEmbeddablePK;
import com.gstdev.cloud.service.system.repository.SysRRoleTenantMenuRepository;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

@Transactional
public class SysRRoleTenantMenuServiceImpl extends BaseServiceImpl<SysRRoleTenantMenu, SysRRoleTenantMenuEmbeddablePK, SysRRoleTenantMenuRepository> implements SysRRoleTenantMenuService {

    @Resource
    private SysRRoleTenantMenuRepository repository;
    @Resource
    @Lazy
    private SysRRoleTenantMenuServiceImpl service;

    public SysRRoleTenantMenuServiceImpl(SysRRoleTenantMenuRepository sysRRoleTenantMenuRepository) {
        super(sysRRoleTenantMenuRepository);
    }

    @Override
    public SysRRoleTenantMenuRepository getRepository() {
        return repository;
    }

    @Override
    public SysRRoleTenantMenuServiceImpl getService() {
        return service;
    }


    @Override
    @Transactional
    public void updateRoleAssignedTenantMenu(String roleIds, List<String> tenantMenuIds) {
        getRepository().deleteAllByRoleId(roleIds);
        if (ObjectUtils.isEmpty(tenantMenuIds)) {
            return;
        }
        saveAllAndFlush(toEntityList(roleIds, tenantMenuIds));
    }

    @Override
    public Set<String> getAllTenantMenuIdByRoleId(String roleId) {
        return getService().findAllByRoleId(roleId).stream().map(SysRRoleTenantMenu::getTenantMenuId).collect(toSet());
    }

    @Override
    public List<String> getAllTenantMenuIdByRoleIds(List<String> roleIds) {
        return getService().findAllByRoleIdIn(roleIds).stream().map(SysRRoleTenantMenu::getTenantMenuId).collect(toList());
    }

    private List<SysRRoleTenantMenu> findAllByRoleIdIn(List<String> roleIds) {
        return getRepository().findAllByRoleIdIn(roleIds);
    }

    public List<SysRRoleTenantMenu> findAllByRoleId(String roleId) {
        return getRepository().findAllByRoleId(roleId);
    }

    List<SysRRoleTenantMenu> toEntityList(String roleId, List<String> tenantMenuIds) {
        return tenantMenuIds.stream().map(tenantMenuId -> {
            SysRRoleTenantMenu sysRRoleTenantMenu = new SysRRoleTenantMenu();
            sysRRoleTenantMenu.setTenantMenuId(tenantMenuId);
            sysRRoleTenantMenu.setRoleId(roleId);
            return sysRRoleTenantMenu;
        }).toList();
    }

    List<SysRRoleTenantMenu> toEntityList(List<String> roleIds, String tenantMenuId) {
        return roleIds.stream().map(roleId -> {
            SysRRoleTenantMenu sysRRoleTenantMenu = new SysRRoleTenantMenu();
            sysRRoleTenantMenu.setTenantMenuId(tenantMenuId);
            sysRRoleTenantMenu.setRoleId(roleId);
            return sysRRoleTenantMenu;
        }).toList();
    }


}
