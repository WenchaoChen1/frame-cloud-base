package com.gstdev.cloud.service.system.service;

import com.gstdev.cloud.data.core.service.BaseService;
import com.gstdev.cloud.message.core.logic.domain.RequestMapping;
import com.gstdev.cloud.service.system.domain.entity.SysInterface;

import java.util.Collection;
import java.util.List;

public interface SysInterfaceService extends BaseService<SysInterface, String> {

    List<SysInterface> findAllocatable();

    List<SysInterface> storeRequestMappings(Collection<RequestMapping> requestMappings);

    List<SysInterface> toSysInterfaces(Collection<RequestMapping> requestMappings);

    /**
     * 获取过期的接口ID
     * @return
     */
    List<String> getInterfacesIdStausExpired();
}
