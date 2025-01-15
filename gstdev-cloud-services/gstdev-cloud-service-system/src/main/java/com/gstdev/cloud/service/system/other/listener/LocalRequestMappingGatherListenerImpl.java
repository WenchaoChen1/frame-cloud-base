package com.gstdev.cloud.service.system.other.listener;

import com.gstdev.cloud.rest.service.scan.RequestMapping;
import com.gstdev.cloud.rest.service.scan.RequestMappingGatherEvent;
import com.gstdev.cloud.service.system.other.processor.RequestMappingStoreProcessor;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * <p>Description: 本地RequestMapping收集监听 </p>
 * <p>
 * 主要在单体式架构，以及 system 服务自身使用
 *
 * @author WenchaoChen
 * @data 2025/1/15 10:00
 */
public class LocalRequestMappingGatherListenerImpl implements LocalRequestMappingGatherListener {

    private static final Logger log = LoggerFactory.getLogger(LocalRequestMappingGatherListenerImpl.class);

    private final RequestMappingStoreProcessor requestMappingStoreProcessor;

    @Autowired
    public LocalRequestMappingGatherListenerImpl(RequestMappingStoreProcessor requestMappingStoreProcessor) {
        this.requestMappingStoreProcessor = requestMappingStoreProcessor;
    }

    @Override
    public void onApplicationEvent(RequestMappingGatherEvent event) {

        log.info("[Gstdev Cloud] |- Request mapping gather LOCAL listener, response event!");

        List<RequestMapping> requestMappings = event.getData();
        if (CollectionUtils.isNotEmpty(requestMappings)) {
            requestMappingStoreProcessor.postProcess(requestMappings);
        }
    }
}
