package com.gstdev.cloud.service.system.service;

import com.gstdev.cloud.data.core.service.BaseDtoService;
import com.gstdev.cloud.data.core.service.BaseService;
import com.gstdev.cloud.service.system.domain.base.SysPermission.SysPermissionDto;
import com.gstdev.cloud.service.system.domain.entity.SysPermission;

import java.util.List;

public interface SysPermissionService extends BaseService<SysPermission, String> {

    void permissionInit();

    List<String> findDistinctPermissionTypes();
}


