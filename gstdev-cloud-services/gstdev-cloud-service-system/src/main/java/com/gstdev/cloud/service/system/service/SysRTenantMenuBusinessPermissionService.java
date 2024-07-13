package com.gstdev.cloud.service.system.service;

import com.gstdev.cloud.data.core.service.BaseService;
import com.gstdev.cloud.service.system.domain.entity.SysRTenantMenuBusinessPermission;
import com.gstdev.cloud.service.system.domain.generator.SysRTenantMenuBusinessPermissionEmbeddablePK;

import java.util.List;
import java.util.Set;

public interface SysRTenantMenuBusinessPermissionService extends BaseService<SysRTenantMenuBusinessPermission, SysRTenantMenuBusinessPermissionEmbeddablePK> {

    void updateBusinessPermissionAssignedTenantMenu(String businessPermissionId, List<String> tenantMenuIds);

    List<String> getAllTenantMenuIdByBusinessPermissionIds(Set<String> tenantBusinessPermissionIds);
}


