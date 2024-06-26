package com.gstdev.cloud.message.websocket.definition;

import com.gstdev.cloud.cache.redis.utils.RedisBitMapUtils;
import com.gstdev.cloud.message.core.constants.MessageConstants;
import com.gstdev.cloud.message.websocket.domain.WebSocketPrincipal;
import com.gstdev.cloud.message.websocket.utils.WebSocketUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * <p>Description: 公共 WebSocketUserListener </p>
 *
 * @author : cc
 * @date : 2022/12/29 22:20
 */
public abstract class AbstractWebSocketStatusListener<E extends ApplicationEvent> implements ApplicationListener<E> {

    private static final Logger log = LoggerFactory.getLogger(AbstractWebSocketStatusListener.class);

    private final WebSocketMessageSender webSocketMessageSender;

    public AbstractWebSocketStatusListener(WebSocketMessageSender webSocketMessageSender) {
        this.webSocketMessageSender = webSocketMessageSender;
    }

    private void changeStatus(WebSocketPrincipal principal, boolean isOnline) {
        if (ObjectUtils.isNotEmpty(principal)) {

            RedisBitMapUtils.setBit(MessageConstants.REDIS_CURRENT_ONLINE_USER, principal.getName(), isOnline);

            String status = isOnline ? "Online" : "Offline";

            log.debug("[GstDev Cloud] |- WebSocket user [{}] is [{}].", principal, status);

            int count = WebSocketUtils.getOnlineCount();

            webSocketMessageSender.online(count);
        }
    }

    protected void connected(WebSocketPrincipal principal) {
        changeStatus(principal, true);
    }

    protected void disconnected(WebSocketPrincipal principal) {
        changeStatus(principal, false);
    }
}
