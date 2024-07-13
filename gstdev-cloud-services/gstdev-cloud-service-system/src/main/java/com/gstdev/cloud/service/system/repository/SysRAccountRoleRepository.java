package com.gstdev.cloud.service.system.repository;

import com.gstdev.cloud.data.core.repository.BaseRepository;
import com.gstdev.cloud.service.system.domain.entity.SysRAccountRole;
import com.gstdev.cloud.service.system.domain.generator.SysRAccountRoleEmbeddablePK;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Transactional
public interface SysRAccountRoleRepository extends BaseRepository<SysRAccountRole, SysRAccountRoleEmbeddablePK> {

    void deleteAllByAccountId(String accountId);
    void deleteAllByRoleId(String accountId);

    List<SysRAccountRole> findAllByAccountId(String accountId);
    List<SysRAccountRole> findAllByRoleId(String roleId);

    List<SysRAccountRole> findAllByAccountIdIn(Set<String> accountIds);
}


