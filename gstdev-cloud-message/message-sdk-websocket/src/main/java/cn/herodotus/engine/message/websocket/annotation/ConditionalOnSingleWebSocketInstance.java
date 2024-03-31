package cn.herodotus.engine.message.websocket.annotation;

import cn.herodotus.engine.message.websocket.condition.SingleWebSocketInstanceCondition;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * <p>Description: WebSocket 单实例环境条件注解 </p>
 *
 * @author : cc
 * @date : 2023/9/14 13:51
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(SingleWebSocketInstanceCondition.class)
public @interface ConditionalOnSingleWebSocketInstance {
}
