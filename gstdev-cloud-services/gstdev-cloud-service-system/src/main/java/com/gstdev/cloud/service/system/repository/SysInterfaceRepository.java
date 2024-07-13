package com.gstdev.cloud.service.system.repository;


import com.gstdev.cloud.data.core.enums.DataItemStatus;
import com.gstdev.cloud.data.core.repository.BaseRepository;
import com.gstdev.cloud.service.system.domain.entity.SysInterface;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>Description: 系统应用程序接口 Repository </p>
 */
public interface SysInterfaceRepository extends BaseRepository<SysInterface, String> {
    @Modifying
    @Transactional
    @Query("UPDATE SysInterface p SET p.status = :status WHERE p.serviceId = :serviceId")
    void updateStatusByServiceId(@Param("status") DataItemStatus status, @Param("serviceId") String serviceId);
}
