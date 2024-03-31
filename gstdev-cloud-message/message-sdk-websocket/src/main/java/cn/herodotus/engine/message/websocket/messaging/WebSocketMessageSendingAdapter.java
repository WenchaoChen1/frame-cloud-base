package cn.herodotus.engine.message.websocket.messaging;

import cn.herodotus.engine.message.core.definition.MessageSendingAdapter;
import cn.herodotus.engine.message.core.definition.domain.WebSocketMessage;
import cn.herodotus.engine.message.core.definition.event.TemplateMessageSendingEvent;
import cn.herodotus.engine.message.websocket.definition.WebSocketMessageSender;
import org.apache.commons.lang3.StringUtils;

/**
 * <p>Description: WebSocket 消息发送适配器 </p>
 * <p>
 * 用于模块外部，统一门面方式发送消息使用。
 *
 * @author : cc
 * @date : 2023/10/26 20:18
 */
public class WebSocketMessageSendingAdapter implements MessageSendingAdapter<WebSocketMessage, TemplateMessageSendingEvent<WebSocketMessage>> {

    private final WebSocketMessageSender webSocketMessageSender;

    public WebSocketMessageSendingAdapter(WebSocketMessageSender webSocketMessageSender) {
        this.webSocketMessageSender = webSocketMessageSender;
    }


    @Override
    public void onApplicationEvent(TemplateMessageSendingEvent<WebSocketMessage> event) {
        WebSocketMessage message = event.getData();

        if (StringUtils.isNotBlank(message.getUser())) {
            webSocketMessageSender.toUser(message.getUser(), message.getDestination(), message.getPayload());
        } else {
            webSocketMessageSender.toAll(message.getDestination(), message.getPayload());
        }
    }
}
