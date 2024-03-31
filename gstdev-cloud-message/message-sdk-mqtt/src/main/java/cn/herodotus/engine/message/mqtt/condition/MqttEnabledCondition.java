package cn.herodotus.engine.message.mqtt.condition;

import com.gstdev.cloud.commons.ass.core.context.PropertyResolver;
import cn.herodotus.engine.message.core.constants.MessageConstants;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * <p>Description: Mqtt 开启判断条件 </p>
 *
 * @author : cc
 * @date : 2023/11/2 13:44
 */
public class MqttEnabledCondition implements Condition {

    private static final Logger log = LoggerFactory.getLogger(MqttEnabledCondition.class);

    @SuppressWarnings("NullableProblems")
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata metadata) {
        String username = PropertyResolver.getProperty(conditionContext, MessageConstants.ITEM_MQTT_USERNAME);
        String password = PropertyResolver.getProperty(conditionContext, MessageConstants.ITEM_MQTT_PASSWORD);
        boolean result = StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password);
        log.debug("[Herodotus] |- Condition [Mqtt Enabled] value is [{}]", result);
        return result;
    }
}
