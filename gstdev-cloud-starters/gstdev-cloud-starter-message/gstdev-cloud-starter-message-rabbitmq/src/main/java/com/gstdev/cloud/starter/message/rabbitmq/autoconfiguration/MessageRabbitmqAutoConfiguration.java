package com.gstdev.cloud.starter.message.rabbitmq.autoconfiguration;

import com.gstdev.cloud.message.rabbitmq.configuration.RabbitmqConfiguration;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Import;

/**
 * <p>Description: RabbitMQ Message 模块自动注入配置 </p>
 *
 * @author : cc
 * @date : 2023/6/7 8:55
 */
@AutoConfiguration
@Import(RabbitmqConfiguration.class)
public class MessageRabbitmqAutoConfiguration {

    private static final Logger log = LoggerFactory.getLogger(MessageRabbitmqAutoConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.info("[GstDev Cloud] |- Module [Message RabbiMQ Starter] Auto Configure.");
    }
}
