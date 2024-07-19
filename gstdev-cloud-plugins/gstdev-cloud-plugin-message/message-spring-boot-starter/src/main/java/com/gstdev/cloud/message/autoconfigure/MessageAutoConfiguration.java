package com.gstdev.cloud.message.autoconfigure;

import com.gstdev.cloud.base.definition.function.ErrorCodeMapperBuilderCustomizer;
import com.gstdev.cloud.message.autoconfigure.customizer.MessageErrorCodeMapperBuilderCustomizer;
import com.gstdev.cloud.message.mqtt.annotation.EnablecFrameMqtt;
import com.gstdev.cloud.message.websocket.annotation.EnableFrameWebSocket;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * <p>Description: Message 模块自动注入配置 </p>
 *
 * @author : cc
 * @date : 2022/2/4 17:08
 */
@AutoConfiguration
@EnableFrameWebSocket
@EnablecFrameMqtt
public class MessageAutoConfiguration {

    private static final Logger log = LoggerFactory.getLogger(MessageAutoConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.info("[GstDev Cloud] |- Module [Message Starter] Auto Configure.");
    }

    @Bean
    public ErrorCodeMapperBuilderCustomizer messageErrorCodeMapperBuilderCustomizer() {
        MessageErrorCodeMapperBuilderCustomizer customizer = new MessageErrorCodeMapperBuilderCustomizer();
        log.debug("[GstDev Cloud] |- Strategy [Message ErrorCodeMapper Builder Customizer] Auto Configure.");
        return customizer;
    }
}
