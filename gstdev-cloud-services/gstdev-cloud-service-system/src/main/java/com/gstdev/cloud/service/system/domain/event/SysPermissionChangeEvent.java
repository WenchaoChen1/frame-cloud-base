package com.gstdev.cloud.service.system.domain.event;


import com.gstdev.cloud.message.core.definition.event.AbstractApplicationEvent;
import com.gstdev.cloud.service.system.domain.entity.SysAttribute;
import com.gstdev.cloud.service.system.domain.entity.SysPermission;

import java.time.Clock;

/**
 * <p>Description: SysSecurityAttribute实体数据变更事件 </p>
 */
public class SysPermissionChangeEvent extends AbstractApplicationEvent<SysPermission> {

    public SysPermissionChangeEvent(SysPermission data) {
        super(data);
    }

    public SysPermissionChangeEvent(SysPermission data, Clock clock) {
        super(data, clock);
    }
}
