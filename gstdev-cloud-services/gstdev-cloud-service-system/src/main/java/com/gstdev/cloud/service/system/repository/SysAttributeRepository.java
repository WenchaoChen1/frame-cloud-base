package com.gstdev.cloud.service.system.repository;


import com.gstdev.cloud.data.core.repository.BaseRepository;
import com.gstdev.cloud.service.system.domain.entity.SysAttribute;
import jakarta.persistence.QueryHint;
import org.hibernate.jpa.AvailableHints;
import org.springframework.data.jpa.repository.QueryHints;

import java.util.List;

public interface SysAttributeRepository extends BaseRepository<SysAttribute, String> {

    @QueryHints(@QueryHint(name = AvailableHints.HINT_CACHEABLE, value = "true"))
    List<SysAttribute> findByAttributeIdIn(List<String> ids);

    @QueryHints(@QueryHint(name = AvailableHints.HINT_CACHEABLE, value = "true"))
    List<SysAttribute> findAllByServiceId(String serviceId);
}
