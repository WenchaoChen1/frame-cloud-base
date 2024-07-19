package com.gstdev.cloud.message.rabbitmq.configuration;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

/**
 * <p>Description: RabbitMQ Message 模块自动注入配置 </p>
 *
 * @author : cc
 * @date : 2023/6/7 8:55
 */
@Configuration
public class RabbitmqConfiguration {

    private static final Logger log = LoggerFactory.getLogger(RabbitmqConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.info("[GstDev Cloud] |- [Message RabbiMQ] Auto Configuration.");
    }
}
