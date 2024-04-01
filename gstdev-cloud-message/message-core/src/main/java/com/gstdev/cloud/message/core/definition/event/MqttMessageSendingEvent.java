package com.gstdev.cloud.message.core.definition.event;

import com.gstdev.cloud.message.core.definition.domain.MqttMessage;

import java.time.Clock;

/**
 * <p>Description: Mqtt 类型消息 </p>
 *
 * @author : cc
 * @date : 2023/11/2 16:05
 */
public class MqttMessageSendingEvent extends AbstractApplicationEvent<MqttMessage> {

  public MqttMessageSendingEvent(MqttMessage data) {
    super(data);
  }

  public MqttMessageSendingEvent(MqttMessage data, Clock clock) {
    super(data, clock);
  }
}
