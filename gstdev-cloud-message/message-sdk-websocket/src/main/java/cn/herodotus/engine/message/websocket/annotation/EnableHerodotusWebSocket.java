package cn.herodotus.engine.message.websocket.annotation;

import cn.herodotus.engine.message.websocket.configuration.MessageWebSocketConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * <p>Description: 手动开启 WebSocket 配置 </p>
 *
 * 模块中的内容相对独立，而且仅有一个 Configuration，同时无需考虑注入顺序的模块，则使用 @Enable 风格配置
 *
 * @author : cc
 * @date : 2023/10/28 11:11
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@Import(MessageWebSocketConfiguration.class)
public @interface EnableHerodotusWebSocket {
}
