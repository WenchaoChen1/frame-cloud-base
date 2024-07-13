package com.gstdev.cloud.service.system.repository;

import com.gstdev.cloud.data.core.repository.BaseRepository;
import com.gstdev.cloud.service.system.domain.entity.SysRAccountTenantMenu;
import com.gstdev.cloud.service.system.domain.generator.SysRAccountTenantMenuEmbeddablePK;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Transactional
public interface SysRAccountTenantMenuRepository extends BaseRepository<SysRAccountTenantMenu, SysRAccountTenantMenuEmbeddablePK> {

    void deleteAllByAccountId(String accountId);

    List<SysRAccountTenantMenu> findAllByAccountId(String accountId);

    List<SysRAccountTenantMenu> findAllByAccountIdIn(Set<String> accountIds);
}


