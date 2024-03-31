package cn.herodotus.engine.message.websocket.messaging;

import cn.herodotus.engine.message.websocket.definition.AbstractWebSocketMessageSender;

/**
 * <p>Description: Web Socket 单一实例服务端消息发送 </p>
 *
 * @author : cc
 * @date : 2023/10/26 23:31
 */
public class SingleInstanceMessageSender extends AbstractWebSocketMessageSender {
    public SingleInstanceMessageSender(WebSocketMessagingTemplate webSocketMessagingTemplate) {
        super(webSocketMessagingTemplate);
    }
}
