package com.gstdev.cloud.service.system.service;

import com.gstdev.cloud.data.core.service.BaseServiceImpl;
import com.gstdev.cloud.service.system.domain.entity.SysRAccountRole;
import com.gstdev.cloud.service.system.domain.generator.SysRAccountRoleEmbeddablePK;
import com.gstdev.cloud.service.system.repository.SysRAccountRoleRepository;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Set;

@Transactional
public class SysRAccountRoleServiceImpl extends BaseServiceImpl<SysRAccountRole, SysRAccountRoleEmbeddablePK, SysRAccountRoleRepository> implements SysRAccountRoleService {

    @Resource
    private SysRAccountRoleRepository repository;
    @Resource
    @Lazy
    private SysRAccountRoleServiceImpl service;

    public SysRAccountRoleServiceImpl(SysRAccountRoleRepository sysRAccountRoleRepository) {
        super(sysRAccountRoleRepository);
    }

    @Override
    public SysRAccountRoleRepository getRepository() {
        return repository;
    }

    @Override
    public SysRAccountRoleServiceImpl getService() {
        return service;
    }


    @Override
    @Transactional
    public void updateAccountAssignedRole(String accountId, List<String> roleIds) {
        getRepository().deleteAllByAccountId(accountId);
        if (ObjectUtils.isEmpty(roleIds)) {
            return;
        }
        saveAllAndFlush(toEntityList(accountId, roleIds));
    }

    @Override
    public List<String> getAllRoleIdByAccountId(String accountId) {
        return getRepository().findAllByAccountId(accountId).stream().map(SysRAccountRole::getRoleId).toList();
    }

    @Override
    public List<String> getAllRoleIdByAccountIds(Set<String> accountIds) {
        return getRepository().findAllByAccountIdIn(accountIds).stream().map(SysRAccountRole::getRoleId).toList();
    }

    List<SysRAccountRole> toEntityList(String accountId, List<String> roleIds) {
        return roleIds.stream().map(roleId -> {
            SysRAccountRole sysRAccountRole = new SysRAccountRole();
            sysRAccountRole.setRoleId(roleId);
            sysRAccountRole.setAccountId(accountId);
            return sysRAccountRole;
        }).toList();
    }

    List<SysRAccountRole> toEntityList(List<String> accountIds, String businessPermissionId) {
        return accountIds.stream().map(accountId -> {
            SysRAccountRole sysRAccountRole = new SysRAccountRole();
            sysRAccountRole.setRoleId(businessPermissionId);
            sysRAccountRole.setAccountId(accountId);
            return sysRAccountRole;
        }).toList();
    }



}
