package com.gstdev.cloud.service.system.service;

import com.gstdev.cloud.data.core.service.BaseService;
import com.gstdev.cloud.service.system.domain.entity.SysRRoleTenantMenu;
import com.gstdev.cloud.service.system.domain.generator.SysRRoleTenantMenuEmbeddablePK;

import java.util.List;
import java.util.Set;

public interface SysRRoleTenantMenuService extends BaseService<SysRRoleTenantMenu, SysRRoleTenantMenuEmbeddablePK> {


    void updateRoleAssignedTenantMenu(String roleId, List<String> tenantMenuIds);

    Set<String> getAllTenantMenuIdByRoleId(String roleId);

    List<String> getAllTenantMenuIdByRoleIds(List<String> allRoleIdByAccountIds);
}


