package com.gstdev.cloud.service.system.repository;


import com.gstdev.cloud.data.core.repository.BaseRepository;
import com.gstdev.cloud.data.core.repository.BaseTreeRepository;
import com.gstdev.cloud.service.system.domain.entity.SysRole;

import java.util.List;


public interface SysRoleRepository extends BaseRepository<SysRole, String> {



    /*------------------------------------------以上是系统访问控制自定义代码--------------------------------------------*/

    List<SysRole> findAllByTenantId(String tenantId);
}
