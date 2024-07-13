package com.gstdev.cloud.service.system.service;

import com.gstdev.cloud.data.core.service.BaseServiceImpl;
import com.gstdev.cloud.service.system.domain.entity.SysRAccountBusinessPermission;
import com.gstdev.cloud.service.system.domain.generator.SysRAccountBusinessPermissionEmbeddablePK;
import com.gstdev.cloud.service.system.repository.SysRAccountBusinessPermissionRepository;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Set;

@Transactional
public class SysRAccountBusinessPermissionServiceImpl extends BaseServiceImpl<SysRAccountBusinessPermission, SysRAccountBusinessPermissionEmbeddablePK, SysRAccountBusinessPermissionRepository> implements SysRAccountBusinessPermissionService {

    @Resource
    private SysRAccountBusinessPermissionRepository repository;
    @Resource
    @Lazy
    private SysRAccountBusinessPermissionServiceImpl service;

    public SysRAccountBusinessPermissionServiceImpl(SysRAccountBusinessPermissionRepository sysRAccountBusinessPermissionRepository) {
        super(sysRAccountBusinessPermissionRepository);
    }

    @Override
    public SysRAccountBusinessPermissionRepository getRepository() {
        return repository;
    }

    @Override
    public SysRAccountBusinessPermissionServiceImpl getService() {
        return service;
    }


    @Override
    @Transactional
    public void updateAccountAssignedBusinessPermission(String accountId, List<String> businessPermissionIds) {
        getRepository().deleteAllByAccountId(accountId);
        if (ObjectUtils.isEmpty(businessPermissionIds)) {
            return;
        }
        saveAllAndFlush(toEntityList(accountId, businessPermissionIds));
    }

    @Override
    public List<String> getAllBusinessPermissionIdByAccountId(String accountId) {
        return getRepository().findAllByAccountId(accountId).stream().map(SysRAccountBusinessPermission::getBusinessPermissionId).toList();
    }

    @Override
    public List<String> getAllBusinessPermissionIdByAccountIds(Set<String> accountIds) {
        return getRepository().findAllByAccountIdIn(accountIds).stream().map(SysRAccountBusinessPermission::getBusinessPermissionId).toList();
    }

    List<SysRAccountBusinessPermission> toEntityList(String accountId, List<String> businessPermissionIds) {
        return businessPermissionIds.stream().map(businessPermissionId -> {
            SysRAccountBusinessPermission sysRAccountBusinessPermission = new SysRAccountBusinessPermission();
            sysRAccountBusinessPermission.setBusinessPermissionId(businessPermissionId);
            sysRAccountBusinessPermission.setAccountId(accountId);
            return sysRAccountBusinessPermission;
        }).toList();
    }

    List<SysRAccountBusinessPermission> toEntityList(List<String> accountIds, String businessPermissionId) {
        return accountIds.stream().map(accountId -> {
            SysRAccountBusinessPermission sysRAccountBusinessPermission = new SysRAccountBusinessPermission();
            sysRAccountBusinessPermission.setBusinessPermissionId(businessPermissionId);
            sysRAccountBusinessPermission.setAccountId(accountId);
            return sysRAccountBusinessPermission;
        }).toList();
    }



}
