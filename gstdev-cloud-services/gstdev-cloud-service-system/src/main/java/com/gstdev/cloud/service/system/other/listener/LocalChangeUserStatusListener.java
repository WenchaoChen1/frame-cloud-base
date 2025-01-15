package com.gstdev.cloud.service.system.other.listener;

import com.gstdev.cloud.message.core.logic.event.ChangeUserStatusEvent;
import org.springframework.context.ApplicationListener;
/**
 *  <p>Description: 本地用户状态变更监听 </p>
 *
 * @author WenchaoChen
 * @data 2025/1/15 10:00
 */
public interface LocalChangeUserStatusListener extends ApplicationListener<ChangeUserStatusEvent> {

    @Override
    void onApplicationEvent(ChangeUserStatusEvent event);
}
