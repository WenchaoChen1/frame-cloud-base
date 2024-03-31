package cn.herodotus.engine.message.mqtt.annotation;

import cn.herodotus.engine.message.mqtt.condition.MqttEnabledCondition;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * <p>Description: Mqtt 配置开启条件注解 </p>
 *
 * @author : cc
 * @date : 2023/11/2 15:59
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(MqttEnabledCondition.class)
public @interface ConditionalOnMqttEnabled {
}
