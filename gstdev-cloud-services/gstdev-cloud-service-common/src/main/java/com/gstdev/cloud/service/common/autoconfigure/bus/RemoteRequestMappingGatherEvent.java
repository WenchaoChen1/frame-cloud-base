package com.gstdev.cloud.service.common.autoconfigure.bus;

import org.springframework.cloud.bus.event.RemoteApplicationEvent;

/**
 * <p>Description: Request Mapping 收集远程事件 </p>
 *
 * @author : cc
 * @date : 2021/8/6 11:23
 */
public class RemoteRequestMappingGatherEvent extends RemoteApplicationEvent {

    private String data;

    public RemoteRequestMappingGatherEvent() {
        super();
    }

    public RemoteRequestMappingGatherEvent(String data, String originService, String destinationService) {
        super(data, originService, RemoteApplicationEvent.DEFAULT_DESTINATION_FACTORY.getDestination(destinationService));
        this.data = data;
    }

    public String getData() {
        return data;
    }
}
