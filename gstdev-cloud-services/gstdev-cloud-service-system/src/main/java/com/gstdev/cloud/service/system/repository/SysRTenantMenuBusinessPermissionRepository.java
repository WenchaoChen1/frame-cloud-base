package com.gstdev.cloud.service.system.repository;

import com.gstdev.cloud.data.core.repository.BaseRepository;
import com.gstdev.cloud.service.system.domain.entity.SysRTenantMenuBusinessPermission;
import com.gstdev.cloud.service.system.domain.generator.SysRTenantMenuBusinessPermissionEmbeddablePK;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Transactional
public interface SysRTenantMenuBusinessPermissionRepository extends BaseRepository<SysRTenantMenuBusinessPermission, SysRTenantMenuBusinessPermissionEmbeddablePK> {
    List<SysRTenantMenuBusinessPermission> findAllByBusinessPermissionId(String businessPermissionId);

    void deleteAllByBusinessPermissionId(String businessPermissionId);


    List<SysRTenantMenuBusinessPermission> findAllByBusinessPermissionIdIn(Set<String> tenantBusinessPermissionIds);
}


