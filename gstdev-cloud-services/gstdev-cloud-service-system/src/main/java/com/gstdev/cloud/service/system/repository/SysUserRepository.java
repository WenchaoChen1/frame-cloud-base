package com.gstdev.cloud.service.system.repository;

import com.gstdev.cloud.data.core.repository.BaseRepository;
import com.gstdev.cloud.service.system.domain.entity.SysUser;

public interface SysUserRepository extends BaseRepository<SysUser, String> {
    /**
     * 查询
     *
     * @param username
     * @return
     */
    SysUser findByUsername(String username);
//
//    Optional<SysUser> findByEmail(String email);
//
//    Optional<SysUser> findByEmailAndDeletedFalse(String email);
}
