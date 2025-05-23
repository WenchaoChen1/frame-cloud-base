package com.gstdev.cloud.service.system.other.listener;

import com.gstdev.cloud.service.common.autoconfigure.bus.RemoteChangeUserStatusEvent;
import org.springframework.context.ApplicationListener;
/**
 *  <p>Description: 远程用户状态变更监听 </p>
 *
 * @author WenchaoChen
 * @data 2025/1/15 10:00
 */
public interface RemoteChangeUserStatusListener extends ApplicationListener<RemoteChangeUserStatusEvent> {
}
