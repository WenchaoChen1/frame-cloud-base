package com.gstdev.cloud.message.core;

import com.gstdev.cloud.base.core.context.ServiceContextHolder;
import com.gstdev.cloud.message.core.definition.domain.StreamMessage;
import com.gstdev.cloud.message.core.definition.domain.TemplateMessage;
import com.gstdev.cloud.message.core.definition.domain.WebSocketMessage;
import com.gstdev.cloud.message.core.definition.event.AbstractApplicationEvent;
import com.gstdev.cloud.message.core.definition.event.StreamMessageSendingEvent;
import com.gstdev.cloud.message.core.definition.event.TemplateMessageSendingEvent;
import org.apache.commons.lang3.StringUtils;

/**
 * <p>Description: 统一消息发送门面 </p>
 * <p>
 * 将 Spring Event、Spring Cloud Stream、WebSocket 消息队列等多种消息发送方式融合，构建统一的消息发送门面，以统一的接口支持各种类型的消息发送
 *
 * @author : cc
 * @date : 2023/10/26 11:38
 */
class MessageSendingFacade {

    /**
     * 发送消息
     *
     * @param event {@link AbstractApplicationEvent}
     */
    private static <T> void postProcess(AbstractApplicationEvent<T> event) {
        ServiceContextHolder.getInstance().publishEvent(event);
    }

    /**
     * 发送事件类型消息
     *
     * @param event 消息
     * @param <T>   消息实体
     */
    public static <T> void event(AbstractApplicationEvent<T> event) {
        postProcess(event);
    }

    /**
     * 发送 MessageTemplate 类型消息
     *
     * @param message 消息
     * @param <T>     继承 {@link TemplateMessage} 类型消息实体
     */
    public static <T extends TemplateMessage> void template(T message) {
        postProcess(new TemplateMessageSendingEvent<>(message));
    }

    /**
     * 发送 Spring Cloud Stream 类型消息
     *
     * @param message 消息
     * @param <T>     继承 {@link StreamMessage} 类型消息实体
     */
    public static <T extends StreamMessage> void stream(T message) {
        postProcess(new StreamMessageSendingEvent<>(message));
    }

    /**
     * 发送 WebSocket 点对点消息
     *
     * @param user        用户唯一标识
     * @param destination 消息同奥
     * @param payload     消息内容
     * @param <T>         消息内容类型
     */
    public static <T> void pointToPoint(String user, String destination, T payload) {
        WebSocketMessage message = new WebSocketMessage();
        if (StringUtils.isNotBlank(user)) {
            message.setUser(user);
        }
        message.setDestination(destination);
        message.setPayload(payload);
        template(message);
    }

    /**
     * 发送 WebSocket 广播消息
     *
     * @param destination 消息同奥
     * @param payload     消息内容
     * @param <T>         消息内容类型
     */
    public static <T> void broadcast(String destination, T payload) {
        pointToPoint(null, destination, payload);
    }
}
