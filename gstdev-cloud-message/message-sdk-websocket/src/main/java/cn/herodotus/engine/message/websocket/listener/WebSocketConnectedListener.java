package cn.herodotus.engine.message.websocket.listener;

import cn.herodotus.engine.message.websocket.definition.AbstractWebSocketStatusListener;
import cn.herodotus.engine.message.websocket.definition.WebSocketMessageSender;
import cn.herodotus.engine.message.websocket.domain.WebSocketPrincipal;
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
