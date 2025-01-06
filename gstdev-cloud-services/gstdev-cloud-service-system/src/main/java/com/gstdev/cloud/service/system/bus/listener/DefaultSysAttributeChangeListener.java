package com.gstdev.cloud.service.system.bus.listener;

import com.gstdev.cloud.service.system.bus.processor.SecurityMetadataDistributeProcessor;
import com.gstdev.cloud.service.system.domain.entity.SysAttribute;
import com.gstdev.cloud.service.system.domain.event.SysAttributeChangeEvent;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;

/**
 * <p>Description: SysSecurityAttribute变更事件监听 </p>
 */
public class DefaultSysAttributeChangeListener implements SysAttributeChangeListener {

    private static final Logger log = LoggerFactory.getLogger(DefaultSysAttributeChangeListener.class);

    private final SecurityMetadataDistributeProcessor securityMetadataDistributeProcessor;

    public DefaultSysAttributeChangeListener(SecurityMetadataDistributeProcessor securityMetadataDistributeProcessor) {
        this.securityMetadataDistributeProcessor = securityMetadataDistributeProcessor;
    }

    @Override
    public void onApplicationEvent(SysAttributeChangeEvent event) {

        log.debug("[Gstdev Cloud] |- SysAttribute Change Listener, response event!");

        SysAttribute sysAttribute = event.getData();
        if (ObjectUtils.isNotEmpty(sysAttribute)) {
            log.debug("[Gstdev Cloud] |- Got SysAttribute, start to process SysAttribute change.");
            securityMetadataDistributeProcessor.distributeChangedSecurityAttribute(sysAttribute);
        }
    }
}
