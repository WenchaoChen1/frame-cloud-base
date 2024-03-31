//package com.gstdev.cloud.oauth2.resource.server.autoconfigure.metadata;
//
//import cn.herodotus.engine.assistant.core.json.jackson2.utils.Jackson2Utils;
//import cn.herodotus.engine.oauth2.resource.autoconfigure.bus.RemoteSecurityMetadataSyncEvent;
//import cn.herodotus.engine.oauth2.authorization.processor.SecurityMetadataSourceAnalyzer;
//import cn.herodotus.engine.oauth2.core.definition.domain.SecurityAttribute;
//import org.apache.commons.collections4.CollectionUtils;
//import org.apache.commons.lang3.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.cloud.bus.ServiceMatcher;
//import org.springframework.context.ApplicationListener;
//
//import java.util.List;
//
///**
// * <p>Description: Security Metadata 数据同步监听 </p>
// *
// * @author : gengwei.zheng
// * @date : 2021/8/6 12:23
// */
//public class RemoteSecurityMetadataSyncListener implements ApplicationListener<RemoteSecurityMetadataSyncEvent> {
//
//    private static final Logger log = LoggerFactory.getLogger(RemoteSecurityMetadataSyncListener.class);
//
//    private final SecurityMetadataSourceAnalyzer securityMetadataSourceAnalyzer;
//    private final ServiceMatcher serviceMatcher;
//
//    public RemoteSecurityMetadataSyncListener(SecurityMetadataSourceAnalyzer securityMetadataSourceAnalyzer, ServiceMatcher serviceMatcher) {
//        this.securityMetadataSourceAnalyzer = securityMetadataSourceAnalyzer;
//        this.serviceMatcher = serviceMatcher;
//    }
//
//    @Override
//    public void onApplicationEvent(RemoteSecurityMetadataSyncEvent event) {
//
//        if (!serviceMatcher.isFromSelf(event)) {
//            log.info("[Herodotus] |- Remote security metadata sync listener, response event!");
//
//            String data = event.getData();
//            if (StringUtils.isNotBlank(data)) {
//                List<SecurityAttribute> securityMetadata = Jackson2Utils.toList(data, SecurityAttribute.class);
//
//                if (CollectionUtils.isNotEmpty(securityMetadata)) {
//                    log.debug("[Herodotus] |- Got security attributes from service [{}], current [{}] start to process security attributes.", event.getOriginService(), event.getDestinationService());
//                    securityMetadataSourceAnalyzer.processSecurityAttribute(securityMetadata);
//                }
//            }
//        }
//    }
//}
