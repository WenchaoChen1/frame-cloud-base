package com.gstdev.cloud.service.system.bus.listener;//package com.frame.template.autoconfigure.service.system.listener;
//
//import com.frame.template.autoconfigure.service.system.processor.SecurityMetadataDistributeProcessor;
//import com.gstdev.cloud.service.system.domain.entity.SysAttribute;
//import com.gstdev.cloud.service.system.domain.entity.SysPermission;
//import com.gstdev.cloud.service.system.domain.event.SysAttributeChangeEvent;
//import com.gstdev.cloud.service.system.domain.event.SysPermissionChangeEvent;
//import org.apache.commons.lang3.ObjectUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.ApplicationListener;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
///**
// * <p>Description: SysSecurityPermission变更事件监听 </p>
// */
//@Component
//public class SysPermissionChangeListener implements ApplicationListener<SysPermissionChangeEvent> {
//
//    private static final Logger log = LoggerFactory.getLogger(SysPermissionChangeListener.class);
//
//    private final SecurityMetadataDistributeProcessor securityMetadataDistributeProcessor;
//
//    public SysPermissionChangeListener(SecurityMetadataDistributeProcessor securityMetadataDistributeProcessor) {
//        this.securityMetadataDistributeProcessor = securityMetadataDistributeProcessor;
//    }
//
//    @Override
//    public void onApplicationEvent(SysPermissionChangeEvent event) {
//
//        log.debug("[Gstdev Cloud] |- SysPermission Change Listener, response event!");
//
//        SysPermission sysPermission = event.getData();
//        if (ObjectUtils.isNotEmpty(sysPermission)) {
//            log.debug("[Gstdev Cloud] |- Got SysAttribute, start to process SysAttribute change.");
//            securityMetadataDistributeProcessor.distributeChangedSecurityAttribute(sysPermission);
//        }
//    }
//}
