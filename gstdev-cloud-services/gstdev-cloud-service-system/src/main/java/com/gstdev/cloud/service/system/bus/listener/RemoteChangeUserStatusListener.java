package com.gstdev.cloud.service.system.bus.listener;

import com.gstdev.cloud.service.common.autoconfigure.bus.RemoteChangeUserStatusEvent;
import org.springframework.context.ApplicationListener;

public interface RemoteChangeUserStatusListener extends ApplicationListener<RemoteChangeUserStatusEvent> {
}
