package com.gstdev.cloud.service.system.repository;

import com.gstdev.cloud.data.core.repository.BaseRepository;
import com.gstdev.cloud.service.system.domain.entity.SysRAccountBusinessPermission;
import com.gstdev.cloud.service.system.domain.generator.SysRAccountBusinessPermissionEmbeddablePK;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Transactional
public interface SysRAccountBusinessPermissionRepository extends BaseRepository<SysRAccountBusinessPermission, SysRAccountBusinessPermissionEmbeddablePK> {

    void deleteAllByAccountId(String accountId);

    List<SysRAccountBusinessPermission> findAllByAccountId(String accountId);

    List<SysRAccountBusinessPermission> findAllByAccountIdIn(Set<String> accountIds);
}


