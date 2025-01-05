package com.gstdev.cloud.service.system.bus.processor;

import com.gstdev.cloud.message.core.definition.strategy.StrategyEventManager;
import com.gstdev.cloud.oauth2.core.definition.domain.SecurityAttribute;
import com.gstdev.cloud.rest.service.scan.RequestMapping;
import com.gstdev.cloud.service.system.domain.entity.SysAttribute;

import java.util.List;

public interface SecurityMetadataDistributeProcessor extends StrategyEventManager<List<SecurityAttribute>> {
    void distributeChangedSecurityAttribute(List<SysAttribute> sysAttributes);
    void distributeChangedSecurityAttribute(SysAttribute sysAttribute);

    void postRequestMappings(List<RequestMapping> requestMappings);
}
