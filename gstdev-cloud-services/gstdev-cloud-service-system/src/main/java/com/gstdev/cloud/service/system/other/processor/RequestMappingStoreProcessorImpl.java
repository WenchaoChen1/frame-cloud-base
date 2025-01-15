package com.gstdev.cloud.service.system.other.processor;

import com.gstdev.cloud.rest.service.scan.RequestMapping;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

/**
 * <p>Description: RequestMapping存储服务 </p>
 *
 * @author WenchaoChen
 * @data 2025/1/15 10:00
 */
public class RequestMappingStoreProcessorImpl implements RequestMappingStoreProcessor{

    private static final Logger log = LoggerFactory.getLogger(RequestMappingStoreProcessor.class);

    @Resource
    private final SecurityMetadataDistributeProcessor securityMetadataDistributeProcessor;

    public RequestMappingStoreProcessorImpl(SecurityMetadataDistributeProcessor securityMetadataDistributeProcessor) {
        this.securityMetadataDistributeProcessor = securityMetadataDistributeProcessor;
    }

    @Async
    @Override
    public void postProcess(List<RequestMapping> requestMappings) {
        log.debug("[Gstdev Cloud] |- [4] Async store request mapping process BEGIN!");
        securityMetadataDistributeProcessor.postRequestMappings(requestMappings);
    }
}
