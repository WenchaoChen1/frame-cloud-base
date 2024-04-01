package com.gstdev.cloud.message.mqtt.messaging;

import com.gstdev.cloud.message.mqtt.gateway.MessageSendingGateway;
import org.springframework.stereotype.Component;

/**
 * <p>Description: Mqtt 消息发送模版 </p>
 *
 * @author : cc
 * @date : 2023/10/31 12:07
 */
@Component
public class MqttMessagingTemplate {

  private final MessageSendingGateway messageSendingGateway;

  public MqttMessagingTemplate(MessageSendingGateway messageSendingGateway) {
    this.messageSendingGateway = messageSendingGateway;
  }

  public void publish(String payload) {
    messageSendingGateway.publish(payload);
  }

  public void publish(Integer qos, String payload) {
    messageSendingGateway.publish(qos, payload);
  }

  public void publish(String topic, String payload) {
    messageSendingGateway.publish(topic, payload);
  }

  public void publish(String topic, Integer qos, String payload) {
    messageSendingGateway.publish(topic, qos, payload);
  }

  public void publish(String topic, String responseTopic, String correlationData, Integer qos, String payload) {
    messageSendingGateway.publish(topic, responseTopic, correlationData, qos, payload);
  }
}
