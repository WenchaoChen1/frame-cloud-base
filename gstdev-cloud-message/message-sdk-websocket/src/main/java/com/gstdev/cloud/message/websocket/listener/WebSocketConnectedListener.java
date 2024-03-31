package com.gstdev.cloud.message.websocket.listener;

import com.gstdev.cloud.message.websocket.definition.AbstractWebSocketStatusListener;
import com.gstdev.cloud.message.websocket.definition.WebSocketMessageSender;
import com.gstdev.cloud.message.websocket.domain.WebSocketPrincipal;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;

/**
 * <p>Description: WebSocketUserOnlineListener </p>
 *
 * @author : cc
 * @date : 2022/12/29 22:17
 */
@Component
public class WebSocketConnectedListener extends AbstractWebSocketStatusListener<SessionConnectedEvent> {

    public WebSocketConnectedListener(WebSocketMessageSender webSocketMessageSender) {
        super(webSocketMessageSender);
    }

    @Override
    public void onApplicationEvent(SessionConnectedEvent event) {
        WebSocketPrincipal principal = (WebSocketPrincipal) event.getUser();

        connected(principal);
    }
}
