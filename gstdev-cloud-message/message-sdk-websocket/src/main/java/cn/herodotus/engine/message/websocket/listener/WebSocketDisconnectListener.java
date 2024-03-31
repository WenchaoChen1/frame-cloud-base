package cn.herodotus.engine.message.websocket.listener;

import cn.herodotus.engine.message.websocket.definition.AbstractWebSocketStatusListener;
import cn.herodotus.engine.message.websocket.definition.WebSocketMessageSender;
import cn.herodotus.engine.message.websocket.domain.WebSocketPrincipal;
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
