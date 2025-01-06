package com.gstdev.cloud.service.system.bus.listener;

import com.gstdev.cloud.rest.service.scan.RequestMapping;
import com.gstdev.cloud.rest.service.scan.RequestMappingGatherEvent;
import com.gstdev.cloud.service.system.bus.processor.RequestMappingStoreProcessor;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;

import java.util.List;

/**
 * <p>Description: 本地RequestMapping收集监听 </p>
 * <p>
 * 主要在单体式架构，以及 UUA 服务自身使用
 */
public class DefaultLocalRequestMappingGatherListener implements LocalRequestMappingGatherListener {

    private static final Logger log = LoggerFactory.getLogger(DefaultLocalRequestMappingGatherListener.class);

    private final RequestMappingStoreProcessor requestMappingStoreProcessor;

    @Autowired
    public DefaultLocalRequestMappingGatherListener(RequestMappingStoreProcessor requestMappingStoreProcessor) {
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
