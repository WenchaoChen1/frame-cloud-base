package com.gstdev.cloud.service.system.domain.listener;

import com.gstdev.cloud.rest.core.definition.context.AbstractApplicationContextAware;
import com.gstdev.cloud.service.system.domain.entity.SysAttribute;
import com.gstdev.cloud.service.system.domain.event.SysAttributeChangeEvent;
import jakarta.persistence.PostUpdate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Description: SysAttribute实体数据变更监听 </p>
 */
public class SysAttributeEntityListener extends AbstractApplicationContextAware {

    private static final Logger log = LoggerFactory.getLogger(SysAttributeEntityListener.class);

    @PostUpdate
    protected void postUpdate(SysAttribute entity) {
        log.debug("[GstDev Cloud] |- [1] SysAttribute entity @PostUpdate activated, value is : [{}]. Trigger SysAttribute change event.", entity.toString());
        publishEvent(new SysAttributeChangeEvent(entity));
    }
}
