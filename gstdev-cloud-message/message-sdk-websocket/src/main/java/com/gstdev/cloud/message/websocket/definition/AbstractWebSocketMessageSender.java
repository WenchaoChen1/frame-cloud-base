package com.gstdev.cloud.message.websocket.definition;

import com.gstdev.cloud.message.websocket.messaging.WebSocketMessagingTemplate;

/**
 * <p>Description: WebSocketMessageSender 抽象实现 </p>
 *
 * @author : cc
 * @date : 2023/10/26 23:45
 */
public abstract class AbstractWebSocketMessageSender implements WebSocketMessageSender {

    private final WebSocketMessagingTemplate webSocketMessagingTemplate;

    protected AbstractWebSocketMessageSender(WebSocketMessagingTemplate webSocketMessagingTemplate) {
        this.webSocketMessagingTemplate = webSocketMessagingTemplate;
    }

    @Override
    public void toUser(String user, String destination, Object payload) {
        webSocketMessagingTemplate.pointToPoint(user, destination, payload);
    }

    @Override
    public void toAll(String destination, Object payload) {
        webSocketMessagingTemplate.broadcast(destination, payload);
    }
}
