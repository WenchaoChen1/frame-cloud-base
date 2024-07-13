package com.gstdev.cloud.service.system.service;

import com.gstdev.cloud.data.core.service.BaseServiceImpl;
import com.gstdev.cloud.service.system.domain.entity.SysRRoleBusinessPermission;
import com.gstdev.cloud.service.system.domain.generator.SysRRoleBusinessPermissionEmbeddablePK;
import com.gstdev.cloud.service.system.repository.SysRRoleBusinessPermissionRepository;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Transactional
public class SysRRoleBusinessPermissionServiceImpl extends BaseServiceImpl<SysRRoleBusinessPermission, SysRRoleBusinessPermissionEmbeddablePK, SysRRoleBusinessPermissionRepository> implements SysRRoleBusinessPermissionService {

    @Resource
    private SysRRoleBusinessPermissionRepository repository;
    @Resource
    @Lazy
    private SysRRoleBusinessPermissionServiceImpl service;

    public SysRRoleBusinessPermissionServiceImpl(SysRRoleBusinessPermissionRepository sysRRoleBusinessPermissionRepository) {
        super(sysRRoleBusinessPermissionRepository);
    }

    @Override
    public SysRRoleBusinessPermissionRepository getRepository() {
        return repository;
    }

    @Override
    public SysRRoleBusinessPermissionServiceImpl getService() {
        return service;
    }


    @Override
    @Transactional
    public void updateRoleAssignedBusinessPermission(String roleIds, List<String> businessPermissionIds) {
        getRepository().deleteAllByRoleId(roleIds);
        if (ObjectUtils.isEmpty(businessPermissionIds)) {
            return;
        }
        saveAllAndFlush(toEntityList(roleIds, businessPermissionIds));
    }

    @Override
    public List<String> getAllBusinessPermissionIdByRoleIds(List<String> roleIds) {
        return getService().findAllByRoleIdIn(roleIds).stream().map(SysRRoleBusinessPermission::getBusinessPermissionId).collect(toList());
    }

    private List<SysRRoleBusinessPermission>  findAllByRoleIdIn(List<String> roleIds) {
        return getRepository().findAllByRoleIdIn(roleIds);

    }

    List<SysRRoleBusinessPermission> toEntityList(String roleId, List<String> businessPermissionIds) {
        return businessPermissionIds.stream().map(businessPermissionId -> {
            SysRRoleBusinessPermission sysRRoleBusinessPermission = new SysRRoleBusinessPermission();
            sysRRoleBusinessPermission.setBusinessPermissionId(businessPermissionId);
            sysRRoleBusinessPermission.setRoleId(roleId);
            return sysRRoleBusinessPermission;
        }).toList();
    }

    List<SysRRoleBusinessPermission> toEntityList(List<String> roleIds, String businessPermissionId) {
        return roleIds.stream().map(roleId -> {
            SysRRoleBusinessPermission sysRRoleBusinessPermission = new SysRRoleBusinessPermission();
            sysRRoleBusinessPermission.setBusinessPermissionId(businessPermissionId);
            sysRRoleBusinessPermission.setRoleId(roleId);
            return sysRRoleBusinessPermission;
        }).toList();
    }



}
