package com.gstdev.cloud.service.system.domain.listener;

import com.gstdev.cloud.rest.core.definition.context.AbstractApplicationContextAware;
import com.gstdev.cloud.service.system.domain.entity.SysPermission;
import com.gstdev.cloud.service.system.domain.event.SysPermissionChangeEvent;
import jakarta.persistence.PostUpdate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Description: SysPermission实体数据变更监听 </p>
 */
public class SysPermissionEntityListener extends AbstractApplicationContextAware {

    private static final Logger log = LoggerFactory.getLogger(SysPermissionEntityListener.class);

    @PostUpdate
    protected void postUpdate(SysPermission entity) {
        log.debug("[GstDev Cloud] |- [1] SysPermission entity @PostUpdate activated, value is : [{}]. Trigger Permission change event.", entity.toString());
        publishEvent(new SysPermissionChangeEvent(entity));
    }
}
