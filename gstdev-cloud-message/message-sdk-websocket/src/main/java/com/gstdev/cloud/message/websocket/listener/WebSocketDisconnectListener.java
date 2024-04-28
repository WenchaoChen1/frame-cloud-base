package com.gstdev.cloud.message.websocket.listener;

import com.gstdev.cloud.message.websocket.definition.AbstractWebSocketStatusListener;
import com.gstdev.cloud.message.websocket.definition.WebSocketMessageSender;
import com.gstdev.cloud.message.websocket.domain.WebSocketPrincipal;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

/**
 * <p>Description: WebSocketUserDisconnectListener </p>
 *
 * @author : cc
 * @date : 2022/12/29 22:30
 */
@Component
public class WebSocketDisconnectListener extends AbstractWebSocketStatusListener<SessionDisconnectEvent> {

    public WebSocketDisconnectListener(WebSocketMessageSender webSocketMessageSender) {
        super(webSocketMessageSender);
    }

    @Override
    public void onApplicationEvent(SessionDisconnectEvent event) {
        WebSocketPrincipal principal = (WebSocketPrincipal) event.getUser();

        disconnected(principal);
    }
}
