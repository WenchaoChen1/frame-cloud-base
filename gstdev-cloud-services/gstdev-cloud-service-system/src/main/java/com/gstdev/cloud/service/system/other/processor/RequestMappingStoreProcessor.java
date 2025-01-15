package com.gstdev.cloud.service.system.other.processor;

import com.gstdev.cloud.rest.service.scan.RequestMapping;

import java.util.List;
/**
 * <p>Description: RequestMapping存储服务 </p>
 *
 * @author WenchaoChen
 * @data 2025/1/15 10:00
 */
public interface RequestMappingStoreProcessor {
    void postProcess(List<RequestMapping> requestMappings);
}
