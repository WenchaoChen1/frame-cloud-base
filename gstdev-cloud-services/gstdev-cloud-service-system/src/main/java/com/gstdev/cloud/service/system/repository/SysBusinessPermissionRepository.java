package com.gstdev.cloud.service.system.repository;


import com.gstdev.cloud.data.core.repository.BaseRepository;
import com.gstdev.cloud.service.system.domain.entity.SysBusinessPermission;

import java.util.List;

public interface SysBusinessPermissionRepository extends BaseRepository<SysBusinessPermission, String> {


    List<SysBusinessPermission> findByTenantId(String tenantId);
}
