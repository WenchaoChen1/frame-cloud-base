package com.gstdev.cloud.message.websocket.annotation;

import com.gstdev.cloud.message.websocket.condition.MultipleWebSocketInstanceCondition;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * <p>Description: 是否为 WebSocket 多实例环境条件注解 </p>
 *
 * @author : cc
 * @date : 2023/9/14 13:51
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(MultipleWebSocketInstanceCondition.class)
public @interface ConditionalOnMultipleWebSocketInstance {
}
