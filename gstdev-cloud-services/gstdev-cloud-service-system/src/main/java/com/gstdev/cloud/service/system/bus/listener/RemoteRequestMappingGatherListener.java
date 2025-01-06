package com.gstdev.cloud.service.system.bus.listener;

import com.gstdev.cloud.service.common.autoconfigure.bus.RemoteRequestMappingGatherEvent;
import org.springframework.context.ApplicationListener;

public interface RemoteRequestMappingGatherListener extends ApplicationListener<RemoteRequestMappingGatherEvent> {
}
