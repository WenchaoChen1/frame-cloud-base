package com.gstdev.cloud.service.system.other.listener;

import com.gstdev.cloud.service.system.domain.event.SysAttributeChangeEvent;
import org.springframework.context.ApplicationListener;
/**
 *  <p>Description: SysSecurityAttribute变更事件监听 </p>
 *
 * @author WenchaoChen
 * @data 2025/1/15 10:00
 */
public interface SysAttributeChangeListener extends ApplicationListener<SysAttributeChangeEvent> {
}
