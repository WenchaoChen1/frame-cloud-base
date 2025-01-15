package com.gstdev.cloud.service.system.other.listener;

import com.gstdev.cloud.base.core.json.jackson2.utils.Jackson2Utils;
import com.gstdev.cloud.rest.service.scan.RequestMapping;
import com.gstdev.cloud.service.common.autoconfigure.bus.RemoteRequestMappingGatherEvent;
import com.gstdev.cloud.service.system.other.processor.RequestMappingStoreProcessor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 *  <p>Description: SecurityMetadata远程变更事件监听 </p>
 *
 * @author WenchaoChen
 * @data 2025/1/15 10:00
 */
public class RemoteRequestMappingGatherListenerImpl implements RemoteRequestMappingGatherListener {

    private static final Logger log = LoggerFactory.getLogger(RemoteRequestMappingGatherListenerImpl.class);

    private final RequestMappingStoreProcessor requestMappingStoreProcessor;

    @Autowired
    public RemoteRequestMappingGatherListenerImpl(RequestMappingStoreProcessor requestMappingStoreProcessor) {
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
