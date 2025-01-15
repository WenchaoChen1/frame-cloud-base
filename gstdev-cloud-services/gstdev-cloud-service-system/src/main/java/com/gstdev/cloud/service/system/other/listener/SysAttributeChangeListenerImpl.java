package com.gstdev.cloud.service.system.other.listener;

import com.gstdev.cloud.service.system.other.processor.SecurityMetadataDistributeProcessor;
import com.gstdev.cloud.service.system.domain.entity.SysAttribute;
import com.gstdev.cloud.service.system.domain.event.SysAttributeChangeEvent;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *  <p>Description: SysSecurityAttribute变更事件监听 </p>
 *
 * @author WenchaoChen
 * @data 2025/1/15 10:00
 */
public class SysAttributeChangeListenerImpl implements SysAttributeChangeListener {

    private static final Logger log = LoggerFactory.getLogger(SysAttributeChangeListenerImpl.class);

    private final SecurityMetadataDistributeProcessor securityMetadataDistributeProcessor;

    public SysAttributeChangeListenerImpl(SecurityMetadataDistributeProcessor securityMetadataDistributeProcessor) {
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
