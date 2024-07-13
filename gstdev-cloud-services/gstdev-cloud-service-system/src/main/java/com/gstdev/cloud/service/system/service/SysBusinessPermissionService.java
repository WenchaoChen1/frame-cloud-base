package com.gstdev.cloud.service.system.service;

import com.gstdev.cloud.data.core.service.BaseService;
import com.gstdev.cloud.service.system.domain.entity.SysBusinessPermission;
import com.gstdev.cloud.service.system.domain.pojo.sysBusinessPermission.TenantBusinessPermissionTreeDto;

import java.util.List;
import java.util.Set;

public interface SysBusinessPermissionService extends BaseService<SysBusinessPermission, String> {

    Set<String> getAllTenantMenuIdByBusinessPermissionId(String businessPermissionId);

    /**
     * 获取租户的业务权限树
     * @param tenantId
     * @return
     */
    List<TenantBusinessPermissionTreeDto> getTenantBusinessPermissionTree(String tenantId);

}


