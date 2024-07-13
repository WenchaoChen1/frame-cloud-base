package com.gstdev.cloud.service.system.repository;


import com.gstdev.cloud.data.core.enums.DataItemStatus;
import com.gstdev.cloud.data.core.repository.BaseRepository;
import com.gstdev.cloud.service.system.domain.entity.SysPermission;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SysPermissionRepository extends BaseRepository<SysPermission, String> {

    @Modifying
    @Transactional
    @Query("UPDATE SysPermission p SET p.status = :status WHERE p.permissionType = :permissionType")
    void updateStatusByPermissionType(@Param("status") DataItemStatus status, @Param("permissionType") String permissionType);

    @Query("SELECT DISTINCT e.permissionType FROM SysPermission e")
    List<String> findDistinctPermissionTypes();
}
