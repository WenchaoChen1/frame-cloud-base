package com.gstdev.cloud.service.system.other.listener;

import com.gstdev.cloud.service.common.autoconfigure.bus.RemoteRequestMappingGatherEvent;
import org.springframework.context.ApplicationListener;
/**
 *  <p>Description: SecurityMetadata远程变更事件监听 </p>
 *
 * @author WenchaoChen
 * @data 2025/1/15 10:00
 */
public interface RemoteRequestMappingGatherListener extends ApplicationListener<RemoteRequestMappingGatherEvent> {
}
