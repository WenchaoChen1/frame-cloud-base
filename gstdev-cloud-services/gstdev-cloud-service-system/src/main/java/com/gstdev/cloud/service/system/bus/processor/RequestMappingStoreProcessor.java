package com.gstdev.cloud.service.system.bus.processor;

import com.gstdev.cloud.rest.service.scan.RequestMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>Description: RequestMapping存储服务 </p>
 */
public class RequestMappingStoreProcessor {

    private static final Logger log = LoggerFactory.getLogger(RequestMappingStoreProcessor.class);

    private final SecurityMetadataDistributeProcessor securityMetadataDistributeProcessor;

    @Autowired
    public RequestMappingStoreProcessor(SecurityMetadataDistributeProcessor securityMetadataDistributeProcessor) {
        this.securityMetadataDistributeProcessor = securityMetadataDistributeProcessor;
    }

    @Async
    public void postProcess(List<RequestMapping> requestMappings) {
        log.debug("[Gstdev Cloud] |- [4] Async store request mapping process BEGIN!");
        securityMetadataDistributeProcessor.postRequestMappings(requestMappings);
    }
}
