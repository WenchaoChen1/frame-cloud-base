package com.gstdev.cloud.service.system.bus.processor;

import com.gstdev.cloud.service.system.domain.entity.SysAttribute;

import java.util.List;

public interface SecurityMetadataDistributeProcessora {
    void distributeChangedSecurityAttribute(List<SysAttribute> allById);
}
