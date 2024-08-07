package com.gstdev.cloud.rest.service.scan;

import com.gstdev.cloud.message.core.definition.event.AbstractApplicationEvent;

import java.time.Clock;
import java.util.List;

/**
 * <p>Description: 本地RequestMapping收集事件 </p>
 *
 * @author : cc
 * @date : 2021/8/8 21:55
 */
public class RequestMappingGatherEvent extends AbstractApplicationEvent<List<RequestMapping>> {

    public RequestMappingGatherEvent(List<RequestMapping> data) {
        super(data);
    }

    public RequestMappingGatherEvent(List<RequestMapping> data, Clock clock) {
        super(data, clock);
    }
}
