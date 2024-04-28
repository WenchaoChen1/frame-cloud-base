package com.gstdev.cloud.message.websocket.configuration;

import com.gstdev.cloud.message.core.definition.domain.WebSocketMessage;
import com.gstdev.cloud.message.websocket.annotation.ConditionalOnMultipleWebSocketInstance;
import com.gstdev.cloud.message.websocket.annotation.ConditionalOnSingleWebSocketInstance;
import com.gstdev.cloud.message.websocket.definition.WebSocketMessageSender;
import com.gstdev.cloud.message.websocket.interceptor.WebSocketAuthenticationHandshakeInterceptor;
import com.gstdev.cloud.base.core.support.BearerTokenResolver;
import com.gstdev.cloud.message.websocket.messaging.*;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.user.SimpUserRegistry;

import java.util.function.Consumer;

/**
 * <p>Description: WebSocket 处理器相关配置 </p>
 *
 * @author : cc
 * @date : 2022/12/29 15:52
 */
@Configuration(proxyBeanMethods = false)
public class MessageWebSocketConfiguration {

    private static final Logger log = LoggerFactory.getLogger(MessageWebSocketConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.debug("[GstDev Cloud] |- Module [Message WebSocket] Auto Configure.");
    }

    @Bean
    public WebSocketAuthenticationHandshakeInterceptor webSocketPrincipalHandshakeHandler(BearerTokenResolver bearerTokenResolver) {
        WebSocketAuthenticationHandshakeInterceptor interceptor = new WebSocketAuthenticationHandshakeInterceptor(bearerTokenResolver);
        log.trace("[GstDev Cloud] |- Bean [WebSocket Authentication Handshake Interceptor] Auto Configure.");
        return interceptor;
    }

    @Bean
    @ConditionalOnMissingBean
    public WebSocketMessagingTemplate webSocketMessagingTemplate(SimpMessagingTemplate simpMessagingTemplate, SimpUserRegistry simpUserRegistry) {
        WebSocketMessagingTemplate template = new WebSocketMessagingTemplate(simpMessagingTemplate, simpUserRegistry);
        log.trace("[GstDev Cloud] |- Bean [WebSocket Messaging Template] Auto Configure.");
        return template;
    }

    @Configuration(proxyBeanMethods = false)
    @ConditionalOnSingleWebSocketInstance
    static class SingleInstanceConfiguration {
        @Bean
        @ConditionalOnMissingBean
        public WebSocketMessageSender singleInstanceMessageSender(WebSocketMessagingTemplate webSocketMessagingTemplate) {
            SingleInstanceMessageSender sender = new SingleInstanceMessageSender(webSocketMessagingTemplate);
            log.debug("[GstDev Cloud] |- Strategy [Single Instance Web Socket Message Sender] Auto Configure.");
            return sender;
        }
    }

    @Configuration(proxyBeanMethods = false)
    @ConditionalOnMultipleWebSocketInstance
    static class MultipleInstanceConfiguration {

        @Bean
        @ConditionalOnMissingBean
        public WebSocketMessageSender multipleInstanceMessageSender(WebSocketMessagingTemplate webSocketMessagingTemplate) {
            MultipleInstanceMessageSender sender = new MultipleInstanceMessageSender(webSocketMessagingTemplate);
            log.debug("[GstDev Cloud] |- Strategy [Multiple Instance Web Socket Message Sender] Auto Configure.");
            return sender;
        }

        @Bean
        public Consumer<WebSocketMessage> webSocketConsumer(WebSocketMessagingTemplate webSocketMessagingTemplate) {
            MultipleInstanceMessageSyncConsumer consumer = new MultipleInstanceMessageSyncConsumer(webSocketMessagingTemplate);
            log.trace("[GstDev Cloud] |- Bean [Multiple Instance Message Receiver] Auto Configure.");
            return consumer;
        }
    }

    @Configuration(proxyBeanMethods = false)
    @Import({
        WebSocketMessageBrokerConfiguration.class,
    })
    @ComponentScan(basePackages = {
        "com.gstdev.cloud.message.websocket.controller",
        "com.gstdev.cloud.message.websocket.listener",
    })
    static class WebSocketConfiguration {

        @Bean
        public WebSocketMessageSendingAdapter webSocketMessageSendingAdapter(WebSocketMessageSender webSocketMessageSender) {
            WebSocketMessageSendingAdapter adapter = new WebSocketMessageSendingAdapter(webSocketMessageSender);
            log.trace("[GstDev Cloud] |- Bean [WebSocket Message Sending Adapter] Auto Configure.");
            return adapter;
        }
    }
}
