package cn.herodotus.engine.message.autoconfigure;

import com.gstdev.cloud.commons.ass.definition.function.ErrorCodeMapperBuilderCustomizer;
import cn.herodotus.engine.message.autoconfigure.customizer.MessageErrorCodeMapperBuilderCustomizer;
import cn.herodotus.engine.message.mqtt.annotation.EnableHerodotusMqtt;
import cn.herodotus.engine.message.websocket.annotation.EnableHerodotusWebSocket;
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
@EnableHerodotusWebSocket
@EnableHerodotusMqtt
public class MessageAutoConfiguration {

    private static final Logger log = LoggerFactory.getLogger(MessageAutoConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.info("[Herodotus] |- Module [Message Starter] Auto Configure.");
    }

    @Bean
    public ErrorCodeMapperBuilderCustomizer messageErrorCodeMapperBuilderCustomizer() {
        MessageErrorCodeMapperBuilderCustomizer customizer = new MessageErrorCodeMapperBuilderCustomizer();
        log.debug("[Herodotus] |- Strategy [Message ErrorCodeMapper Builder Customizer] Auto Configure.");
        return customizer;
    }
}
