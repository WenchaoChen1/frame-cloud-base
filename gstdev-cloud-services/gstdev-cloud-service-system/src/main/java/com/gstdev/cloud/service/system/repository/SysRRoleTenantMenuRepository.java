package com.gstdev.cloud.service.system.repository;

import com.gstdev.cloud.data.core.repository.BaseRepository;
import com.gstdev.cloud.service.system.domain.entity.SysRRoleTenantMenu;
import com.gstdev.cloud.service.system.domain.generator.SysRRoleTenantMenuEmbeddablePK;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface SysRRoleTenantMenuRepository extends BaseRepository<SysRRoleTenantMenu, SysRRoleTenantMenuEmbeddablePK> {



    void deleteAllByRoleId(String roleIds);

    List<SysRRoleTenantMenu> findAllByRoleId(String roleId);

    List<SysRRoleTenantMenu> findAllByRoleIdIn(List<String> roleIds);
}


