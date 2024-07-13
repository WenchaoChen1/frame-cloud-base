package com.gstdev.cloud.service.system.domain.event;


import com.gstdev.cloud.message.core.definition.event.AbstractApplicationEvent;
import com.gstdev.cloud.service.system.domain.entity.SysAttribute;

import java.time.Clock;

/**
 * <p>Description: SysSecurityAttribute实体数据变更事件 </p>
 */
public class SysAttributeChangeEvent extends AbstractApplicationEvent<SysAttribute> {

    public SysAttributeChangeEvent(SysAttribute data) {
        super(data);
    }

    public SysAttributeChangeEvent(SysAttribute data, Clock clock) {
        super(data, clock);
    }
}
