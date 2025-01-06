package com.gstdev.cloud.service.system.bus.listener;

import com.gstdev.cloud.message.core.logic.event.ChangeUserStatusEvent;
import org.springframework.context.ApplicationListener;

public interface LocalChangeUserStatusListener extends ApplicationListener<ChangeUserStatusEvent> {
    void onApplicationEvent(ChangeUserStatusEvent event);
}
