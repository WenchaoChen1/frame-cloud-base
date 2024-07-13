package com.gstdev.cloud.service.system.service;

import com.gstdev.cloud.data.core.service.BaseService;
import com.gstdev.cloud.service.system.domain.entity.SysRAccountTenantMenu;
import com.gstdev.cloud.service.system.domain.generator.SysRAccountTenantMenuEmbeddablePK;

import java.util.List;
import java.util.Set;

public interface SysRAccountTenantMenuService extends BaseService<SysRAccountTenantMenu, SysRAccountTenantMenuEmbeddablePK> {


    void updateAccountAssignedTenantMenu(String accountId, List<String> tenantMenuIds);

    List<String> getAllTenantMenuIdByAccountId(String accountId);
    List<String> getAllTenantMenuIdByAccountIds(Set<String> accountIds);
}


