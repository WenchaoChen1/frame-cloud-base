package com.gstdev.cloud.message.core.definition.event;

import com.gstdev.cloud.message.core.definition.domain.TemplateMessage;

import java.time.Clock;

/**
 * <p>Description: Spring 框架 MessageTemplate 类型消息发送事件 </p>
 *
 * @author : cc
 * @date : 2023/10/26 15:16
 */
public class TemplateMessageSendingEvent<T extends TemplateMessage> extends AbstractApplicationEvent<T> {
  public TemplateMessageSendingEvent(T data) {
    super(data);
  }

  public TemplateMessageSendingEvent(T data, Clock clock) {
    super(data, clock);
  }
}
