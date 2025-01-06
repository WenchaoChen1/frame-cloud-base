package com.gstdev.cloud.service.system.bus.listener;

import com.gstdev.cloud.rest.service.scan.RequestMappingGatherEvent;
import org.springframework.context.ApplicationListener;

public interface LocalRequestMappingGatherListener extends ApplicationListener<RequestMappingGatherEvent> {
}
