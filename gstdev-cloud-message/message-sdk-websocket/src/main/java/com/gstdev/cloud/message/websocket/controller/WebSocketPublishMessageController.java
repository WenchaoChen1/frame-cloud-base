package com.gstdev.cloud.message.websocket.controller;

import com.gstdev.cloud.message.core.constants.MessageConstants;
import com.gstdev.cloud.message.core.definition.domain.WebSocketMessage;
import com.gstdev.cloud.message.core.logic.domain.DialogueMessage;
import com.gstdev.cloud.message.core.logic.event.SendDialogueMessageEvent;
import com.gstdev.cloud.message.websocket.definition.WebSocketMessageSender;
import com.gstdev.cloud.message.websocket.domain.WebSocketPrincipal;
import com.gstdev.cloud.rest.core.definition.context.AbstractApplicationContextAware;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Description: 前端使用的 publish 响应接口 </p>
 *
 * @author : cc
 * @date : 2022/12/5 17:49
 */
@RestController
public class WebSocketPublishMessageController extends AbstractApplicationContextAware {

    private static final Logger log = LoggerFactory.getLogger(WebSocketPublishMessageController.class);

    private final WebSocketMessageSender webSocketMessageSender;

    public WebSocketPublishMessageController(WebSocketMessageSender webSocketMessageSender) {
        this.webSocketMessageSender = webSocketMessageSender;
    }

    @MessageMapping("/public/notice")
    @SendTo(MessageConstants.WEBSOCKET_DESTINATION_BROADCAST_NOTICE)
    public String notice(String message, StompHeaderAccessor headerAccessor) {
        System.out.println("---message---" + message);
        if (ObjectUtils.isNotEmpty(headerAccessor)) {
            System.out.println("---id---" + headerAccessor.getUser().getName());
        }

        return message;
    }

    /**
     * 发送私信消息。
     *
     * @param detail         前端数据 {@link DialogueMessage}
     * @param headerAccessor 在WebSocketChannelInterceptor拦截器中绑定上的对象
     */
    @MessageMapping("/private/message")
    public void sendPrivateMessage(@Payload DialogueMessage detail, StompHeaderAccessor headerAccessor) {

        WebSocketMessage response = new WebSocketMessage();
        response.setUser(detail.getReceiverId());
        response.setDestination(MessageConstants.WEBSOCKET_DESTINATION_PERSONAL_MESSAGE);

        if (StringUtils.isNotBlank(detail.getReceiverId()) && StringUtils.isNotBlank(detail.getReceiverName())) {
            if (StringUtils.isBlank(detail.getSenderId()) && StringUtils.isBlank(detail.getSenderName())) {
                WebSocketPrincipal sender = (WebSocketPrincipal) headerAccessor.getUser();
                detail.setSenderId(sender.getUserId());
                detail.setSenderName(sender.getUsername());
                detail.setSenderAvatar(sender.getAvatar());
            }

            this.publishEvent(new SendDialogueMessageEvent(detail));

            response.setPayload("私信发送成功");
        } else {
            response.setPayload("私信发送失败，参数错误");
        }

        webSocketMessageSender.toUser(response);
    }
}
