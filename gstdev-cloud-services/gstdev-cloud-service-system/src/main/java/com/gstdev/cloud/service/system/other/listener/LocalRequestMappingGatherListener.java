package com.gstdev.cloud.service.system.other.listener;

import com.gstdev.cloud.rest.service.scan.RequestMappingGatherEvent;
import org.springframework.context.ApplicationListener;
/**
 * <p>Description: 本地RequestMapping收集监听 </p>
 * <p>
 * 主要在单体式架构，以及 system 服务自身使用
 *
 * @author WenchaoChen
 * @data 2025/1/15 10:00
 */
public interface LocalRequestMappingGatherListener extends ApplicationListener<RequestMappingGatherEvent> {
}
