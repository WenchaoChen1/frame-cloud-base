package com.gstdev.cloud.service.system.service;

import com.gstdev.cloud.data.core.service.BaseService;
import com.gstdev.cloud.service.system.domain.entity.SysRAccountRole;
import com.gstdev.cloud.service.system.domain.generator.SysRAccountRoleEmbeddablePK;

import java.util.List;
import java.util.Set;

public interface SysRAccountRoleService extends BaseService<SysRAccountRole, SysRAccountRoleEmbeddablePK> {


    void updateAccountAssignedRole(String accountId, List<String> tenantMenuIds);

    List<String> getAllRoleIdByAccountId(String accountId);

    List<String> getAllRoleIdByAccountIds(Set<String> accountRoleAccountIds);
}


