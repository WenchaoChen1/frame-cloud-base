package com.gstdev.cloud.message.mqtt.messaging;

import com.gstdev.cloud.message.core.definition.MessageSendingAdapter;
import com.gstdev.cloud.message.core.definition.domain.MqttMessage;
import com.gstdev.cloud.message.core.definition.event.MqttMessageSendingEvent;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * <p>Description: Mqtt 消息发送适配器 </p>
 *
 * @author : cc
 * @date : 2023/11/2 13:43
 */
@Component
public class MqttMessageSendingAdapter implements MessageSendingAdapter<MqttMessage, MqttMessageSendingEvent> {

    private final MqttMessagingTemplate mqttMessagingTemplate;

    public MqttMessageSendingAdapter(MqttMessagingTemplate mqttMessagingTemplate) {
        this.mqttMessagingTemplate = mqttMessagingTemplate;
    }

    @Override
    public void onApplicationEvent(MqttMessageSendingEvent event) {
        MqttMessage mqttMessage = event.getData();

        if (StringUtils.isNotBlank(mqttMessage.getTopic()) && ObjectUtils.isNotEmpty(mqttMessage.getQos())) {
            if (StringUtils.isNotBlank(mqttMessage.getResponseTopic()) && StringUtils.isNotBlank(mqttMessage.getCorrelationData())) {
                mqttMessagingTemplate.publish(mqttMessage.getTopic(),
                    mqttMessage.getResponseTopic(),
                    mqttMessage.getCorrelationData(),
                    mqttMessage.getQos(),
                    mqttMessage.getPayload());
            } else {
                mqttMessagingTemplate.publish(mqttMessage.getTopic(), mqttMessage.getQos(), mqttMessage.getPayload());
            }
        } else {
            if (StringUtils.isNotBlank(mqttMessage.getTopic())) {
                mqttMessagingTemplate.publish(mqttMessage.getTopic(), mqttMessage.getPayload());
            }

            if (ObjectUtils.isNotEmpty(mqttMessage.getQos())) {
                mqttMessagingTemplate.publish(mqttMessage.getQos(), mqttMessage.getPayload());
            }
        }
    }
}
