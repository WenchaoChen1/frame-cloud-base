package com.gstdev.cloud.service.system.service;

import com.gstdev.cloud.data.core.service.BaseServiceImpl;
import com.gstdev.cloud.service.system.domain.entity.SysRTenantMenuBusinessPermission;
import com.gstdev.cloud.service.system.domain.generator.SysRTenantMenuBusinessPermissionEmbeddablePK;
import com.gstdev.cloud.service.system.repository.SysRTenantMenuBusinessPermissionRepository;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Set;

@Transactional
public class SysRTenantMenuBusinessPermissionServiceImpl extends BaseServiceImpl<SysRTenantMenuBusinessPermission, SysRTenantMenuBusinessPermissionEmbeddablePK, SysRTenantMenuBusinessPermissionRepository> implements SysRTenantMenuBusinessPermissionService {

    @Resource
    private SysRTenantMenuBusinessPermissionRepository repository;
    @Resource
    @Lazy
    private SysRTenantMenuBusinessPermissionServiceImpl service;

    public SysRTenantMenuBusinessPermissionServiceImpl(SysRTenantMenuBusinessPermissionRepository sysRTenantMenuBusinessPermissionRepository) {
        super(sysRTenantMenuBusinessPermissionRepository);
    }

    @Override
    public SysRTenantMenuBusinessPermissionRepository getRepository() {
        return repository;
    }

    @Override
    public SysRTenantMenuBusinessPermissionServiceImpl getService() {
        return service;
    }


    @Override
    @Transactional
    public void updateBusinessPermissionAssignedTenantMenu(String businessPermissionId, List<String> tenantMenuIds) {
        getRepository().deleteAllByBusinessPermissionId(businessPermissionId);
        if (ObjectUtils.isEmpty(tenantMenuIds)) {
            return;
        }
        saveAllAndFlush(toEntityList(tenantMenuIds, businessPermissionId));
    }

    @Override
    public List<String> getAllTenantMenuIdByBusinessPermissionIds(Set<String> tenantBusinessPermissionIds) {
        return getService().findAllByBusinessPermissionIdIn(tenantBusinessPermissionIds).stream().map(SysRTenantMenuBusinessPermission::getTenantMenuId).toList();
    }

    private List<SysRTenantMenuBusinessPermission> findAllByBusinessPermissionIdIn(Set<String> tenantBusinessPermissionIds) {
        return getRepository().findAllByBusinessPermissionIdIn(tenantBusinessPermissionIds);
    }

    List<SysRTenantMenuBusinessPermission> toEntityList(String TenantMenuId, List<String> businessPermissionIds) {
        return businessPermissionIds.stream().map(businessPermissionId -> {
            SysRTenantMenuBusinessPermission sysRTenantMenuBusinessPermission = new SysRTenantMenuBusinessPermission();
            sysRTenantMenuBusinessPermission.setBusinessPermissionId(businessPermissionId);
            sysRTenantMenuBusinessPermission.setTenantMenuId(TenantMenuId);
            return sysRTenantMenuBusinessPermission;
        }).toList();
    }

    List<SysRTenantMenuBusinessPermission> toEntityList(List<String> tenantMenuIds, String businessPermissionId) {
        return tenantMenuIds.stream().map(tenantMenuId -> {
            SysRTenantMenuBusinessPermission sysRTenantMenuBusinessPermission = new SysRTenantMenuBusinessPermission();
            sysRTenantMenuBusinessPermission.setBusinessPermissionId(businessPermissionId);
            sysRTenantMenuBusinessPermission.setTenantMenuId(tenantMenuId);
            return sysRTenantMenuBusinessPermission;
        }).toList();
    }



}
