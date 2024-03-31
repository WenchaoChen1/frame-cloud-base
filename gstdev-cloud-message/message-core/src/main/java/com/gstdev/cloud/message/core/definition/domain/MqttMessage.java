package com.gstdev.cloud.message.core.definition.domain;

import com.google.common.base.MoreObjects;

/**
 * <p>Description: Mqtt 类型消息参数实体 </p>
 *
 * @author : cc
 * @date : 2023/11/2 16:05
 */
public class MqttMessage implements Message {

    private String topic;
    private String responseTopic;
    private String correlationData;
    private Integer qos;
    private String payload;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getResponseTopic() {
        return responseTopic;
    }

    public void setResponseTopic(String responseTopic) {
        this.responseTopic = responseTopic;
    }

    public String getCorrelationData() {
        return correlationData;
    }

    public void setCorrelationData(String correlationData) {
        this.correlationData = correlationData;
    }

    public Integer getQos() {
        return qos;
    }

    public void setQos(Integer qos) {
        this.qos = qos;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("topic", topic)
                .add("responseTopic", responseTopic)
                .add("correlationData", correlationData)
                .add("qos", qos)
                .add("payload", payload)
                .toString();
    }
}
