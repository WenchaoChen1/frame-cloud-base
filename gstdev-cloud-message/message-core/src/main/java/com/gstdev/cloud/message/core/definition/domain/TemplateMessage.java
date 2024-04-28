package com.gstdev.cloud.message.core.definition.domain;

import com.google.common.base.MoreObjects;

/**
 * <p>Description: Spring Messaging Template 类型消息参数实体 </p>
 *
 * @author : cc
 * @date : 2023/10/26 14:55
 */
public class TemplateMessage implements Message {

    private String destination;

    private Object payload;

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Object getPayload() {
        return payload;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("destination", destination)
            .add("payload", payload)
            .toString();
    }
}
