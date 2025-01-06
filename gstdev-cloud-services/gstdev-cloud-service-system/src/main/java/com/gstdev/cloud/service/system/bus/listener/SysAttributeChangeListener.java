package com.gstdev.cloud.service.system.bus.listener;

import com.gstdev.cloud.service.system.domain.event.SysAttributeChangeEvent;
import org.springframework.context.ApplicationListener;

public interface SysAttributeChangeListener extends ApplicationListener<SysAttributeChangeEvent> {
}
