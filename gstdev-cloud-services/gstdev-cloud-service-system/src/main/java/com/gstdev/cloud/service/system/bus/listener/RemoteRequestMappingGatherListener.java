package com.gstdev.cloud.service.system.bus.listener;

import com.gstdev.cloud.base.core.json.jackson2.utils.Jackson2Utils;
import com.gstdev.cloud.rest.service.scan.RequestMapping;
import com.gstdev.cloud.service.common.autoconfigure.bus.RemoteRequestMappingGatherEvent;
import com.gstdev.cloud.service.system.bus.processor.RequestMappingStoreProcessor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>Description: SecurityMetadata远程变更事件监听 </p>
 */
public class RemoteRequestMappingGatherListener implements ApplicationListener<RemoteRequestMappingGatherEvent> {

    private static final Logger log = LoggerFactory.getLogger(RemoteRequestMappingGatherListener.class);

    private final RequestMappingStoreProcessor requestMappingStoreProcessor;

    @Autowired
    public RemoteRequestMappingGatherListener(RequestMappingStoreProcessor requestMappingStoreProcessor) {
        this.requestMappingStoreProcessor = requestMappingStoreProcessor;
    }

    @Override
    public void onApplicationEvent(RemoteRequestMappingGatherEvent event) {

        log.info("[Gstdev Cloud] |- Request mapping gather REMOTE listener, response event!");

        String requestMapping = event.getData();
        log.info("[Gstdev Cloud] |- Fetch data [{}]", requestMapping);
        if (ObjectUtils.isNotEmpty(requestMapping)) {
            List<RequestMapping> requestMappings = Jackson2Utils.toList(requestMapping, RequestMapping.class);
            if (CollectionUtils.isNotEmpty(requestMappings)) {
                requestMappingStoreProcessor.postProcess(requestMappings);
            }
        }
    }

}
