package com.gstdev.cloud.message.websocket.definition;

import com.gstdev.cloud.message.core.constants.MessageConstants;
import com.gstdev.cloud.message.core.definition.domain.WebSocketMessage;

/**
 * <p>Description: WebSocket 消息发送操作定义 </p>
 *
 * @author : cc
 * @date : 2023/10/26 23:32
 */
public interface WebSocketMessageSender {

    /**
     * 发送 WebSocket 点对点消息。发送信息给指定用户
     *
     * @param user        用户唯一标识
     * @param destination 消息通道
     * @param payload     消息内容
     */
    void toUser(String user, String destination, Object payload);

    /**
     * 送 WebSocket 点对点消息。发送信息给指定用户
     *
     * @param webSocketMessage 消息实体 {@link WebSocketMessage}
     */
    default void toUser(WebSocketMessage webSocketMessage) {
        toUser(webSocketMessage.getUser(), webSocketMessage.getDestination(), webSocketMessage.getPayload());
    }

    /**
     * 发送 WebSocket 广播消息。发送全员信息
     *
     * @param destination 消息通道
     * @param payload     消息内容
     */
    void toAll(String destination, Object payload);

    /**
     * 发送公告信息
     *
     * @param payload 消息内容
     */
    default void announcement(Object payload) {
        toAll(MessageConstants.WEBSOCKET_DESTINATION_BROADCAST_NOTICE, payload);
    }

    /**
     * 发送实时在线用户统计信息
     *
     * @param payload 消息内容
     */
    default void online(Object payload) {
        toAll(MessageConstants.WEBSOCKET_DESTINATION_BROADCAST_ONLINE, payload);
    }
}
