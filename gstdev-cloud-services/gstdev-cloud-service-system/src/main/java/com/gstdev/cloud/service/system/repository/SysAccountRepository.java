package com.gstdev.cloud.service.system.repository;

import com.gstdev.cloud.data.core.repository.BaseRepository;
import com.gstdev.cloud.service.system.domain.entity.SysAccount;

import java.util.List;

public interface SysAccountRepository extends BaseRepository<SysAccount, String> {
    /**
     * 通过登录名查账号和用户信息
     *
     * @param identity
     * @return
     */
//    SysAccount findByIdentity(String identity);

    /**
     * 查询
     *
     * @param userId
     * @param tenantId
     * @return
     */

//    SysAccount findByUserIdAndTenantId(String userId, String tenantId);
//
//    SysAccount findAccountByType(String userId);

    List<SysAccount> findAllByUserUserId(String userId);

    List<SysAccount> findAllByTenantId(String tenantId);
}
