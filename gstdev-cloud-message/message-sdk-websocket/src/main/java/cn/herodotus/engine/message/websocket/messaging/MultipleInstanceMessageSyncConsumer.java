package cn.herodotus.engine.message.websocket.messaging;

import cn.herodotus.engine.message.core.constants.MessageConstants;
import cn.herodotus.engine.message.core.definition.domain.WebSocketMessage;
import org.apache.commons.lang3.StringUtils;

import java.util.function.Consumer;

/**
 * <p>Description: WebSocket 点对点消息跨实例处理 </p>
 *
 * @author : cc
 * @date : 2023/9/14 17:06
 */
public class MultipleInstanceMessageSyncConsumer implements Consumer<WebSocketMessage> {

    private final WebSocketMessagingTemplate webSocketMessagingTemplate;

    public MultipleInstanceMessageSyncConsumer(WebSocketMessagingTemplate webSocketMessagingTemplate) {
        this.webSocketMessagingTemplate = webSocketMessagingTemplate;
    }

    @Override
    public void accept(WebSocketMessage webSocketMessage) {
        if (StringUtils.equals(webSocketMessage.getUser(), MessageConstants.MESSAGE_TO_ALL)) {
            webSocketMessagingTemplate.broadcast(webSocketMessage.getDestination(), webSocketMessage.getPayload());
        } else {
            webSocketMessagingTemplate.pointToPoint(webSocketMessage.getUser(), webSocketMessage.getDestination(), webSocketMessage.getPayload());
        }
    }
}
