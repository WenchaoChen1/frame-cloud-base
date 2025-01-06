package com.gstdev.cloud.service.system.bus.processor;

import com.gstdev.cloud.rest.service.scan.RequestMapping;

import java.util.List;

public interface RequestMappingStoreProcessor {
    void postProcess(List<RequestMapping> requestMappings);
}
