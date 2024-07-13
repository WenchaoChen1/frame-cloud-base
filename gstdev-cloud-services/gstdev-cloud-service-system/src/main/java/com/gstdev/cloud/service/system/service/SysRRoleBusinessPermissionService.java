package com.gstdev.cloud.service.system.service;

import com.gstdev.cloud.data.core.service.BaseService;
import com.gstdev.cloud.service.system.domain.entity.SysRRoleBusinessPermission;
import com.gstdev.cloud.service.system.domain.generator.SysRRoleBusinessPermissionEmbeddablePK;

import java.util.List;

public interface SysRRoleBusinessPermissionService extends BaseService<SysRRoleBusinessPermission, SysRRoleBusinessPermissionEmbeddablePK> {


    void updateRoleAssignedBusinessPermission(String roleId, List<String> businessPermissionIds);

    List<String> getAllBusinessPermissionIdByRoleIds(List<String> allRoleIdByAccountIds);
}


