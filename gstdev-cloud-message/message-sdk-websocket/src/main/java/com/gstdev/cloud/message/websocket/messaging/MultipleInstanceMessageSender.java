package com.gstdev.cloud.message.websocket.messaging;

import com.gstdev.cloud.base.core.context.ServiceContextHolder;
import com.gstdev.cloud.message.core.constants.MessageConstants;
import com.gstdev.cloud.message.core.definition.domain.StreamMessage;
import com.gstdev.cloud.message.core.definition.domain.WebSocketMessage;
import com.gstdev.cloud.message.core.definition.event.StreamMessageSendingEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Description: Web Socket 多实例服务端消息发送器 </p>
 *
 * @author : cc
 * @date : 2023/10/26 23:29
 */
public class MultipleInstanceMessageSender extends SingleInstanceMessageSender {

  private static final Logger log = LoggerFactory.getLogger(MultipleInstanceMessageSender.class);

  public MultipleInstanceMessageSender(WebSocketMessagingTemplate webSocketMessagingTemplate) {
    super(webSocketMessagingTemplate);
  }

  @Override
  public void toUser(String user, String destination, Object payload) {
    syncToUserMessage(user, destination, payload);
    super.toUser(user, destination, payload);
  }

  @Override
  public void toAll(String destination, Object payload) {
    syncToAllMessage(destination, payload);
    super.toAll(destination, payload);
  }

  private void syncMessageToOtherInstants(WebSocketMessage webSocketMessage) {
    StreamMessage streamMessage = new StreamMessage();
    streamMessage.setBindingName(MessageConstants.MULTIPLE_INSTANCE_OUTPUT);
    streamMessage.setData(webSocketMessage);

    log.debug("[GstDev Cloud] |- Sync message to other WebSocket instance.");
    ServiceContextHolder.getInstance().publishEvent(new StreamMessageSendingEvent<>(streamMessage));
  }

  private void syncToUserMessage(String user, String destination, Object payload) {
    WebSocketMessage webSocketMessage = new WebSocketMessage();
    webSocketMessage.setUser(user);
    webSocketMessage.setDestination(destination);
    webSocketMessage.setPayload(payload);
    syncMessageToOtherInstants(webSocketMessage);
  }

  private void syncToAllMessage(String destination, Object payload) {
    syncToUserMessage(MessageConstants.MESSAGE_TO_ALL, destination, payload);
  }
}
