package com.gstdev.cloud.message.mqtt.annotation;

import com.gstdev.cloud.message.mqtt.configuration.MessageMqttConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * <p>Description: 动开启 Mqtt 配置 </p>
 * <p>
 * 模块中的内容相对独立，而且仅有一个 Configuration，同时无需考虑注入顺序的模块，则使用 @Enable 风格配置
 *
 * @author : cc
 * @date : 2023/11/2 16:25
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@Import(MessageMqttConfiguration.class)
public @interface EnableHerodotusMqtt {
}
