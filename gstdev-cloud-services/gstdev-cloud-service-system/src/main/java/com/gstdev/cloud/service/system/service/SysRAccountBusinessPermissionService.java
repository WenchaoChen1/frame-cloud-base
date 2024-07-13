package com.gstdev.cloud.service.system.service;

import com.gstdev.cloud.data.core.service.BaseService;
import com.gstdev.cloud.service.system.domain.entity.SysRAccountBusinessPermission;
import com.gstdev.cloud.service.system.domain.generator.SysRAccountBusinessPermissionEmbeddablePK;

import java.util.List;
import java.util.Set;

public interface SysRAccountBusinessPermissionService extends BaseService<SysRAccountBusinessPermission, SysRAccountBusinessPermissionEmbeddablePK> {


    void updateAccountAssignedBusinessPermission(String accountId, List<String> businessPermissionIds);

    List<String> getAllBusinessPermissionIdByAccountId(String accountId);

    List<String> getAllBusinessPermissionIdByAccountIds(Set<String> accountTenantBusinessPermissionAccountIds);
}


