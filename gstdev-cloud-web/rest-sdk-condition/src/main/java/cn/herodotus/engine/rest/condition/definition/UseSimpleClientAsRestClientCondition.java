package cn.herodotus.engine.rest.condition.definition;

import cn.herodotus.engine.rest.condition.constants.RestPropertyFinder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * <p>Description: 使用默认客户端作为 RestTemplate 和 OpenFeign 引擎条件 </p>
 *
 * @author : cc
 * @date : 2023/6/15 23:12
 */
public class UseSimpleClientAsRestClientCondition implements Condition {

    private static final Logger log = LoggerFactory.getLogger(UseSimpleClientAsRestClientCondition.class);

    @SuppressWarnings("NullableProblems")
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        boolean isHttp2ClientEnabled = RestPropertyFinder.isOpenFeignHttp2ClientEnabled(conditionContext);
        boolean isHttpClient5Enabled = RestPropertyFinder.isOpenFeignHttpClient5Enabled(conditionContext);
        boolean result = !isHttp2ClientEnabled && !isHttpClient5Enabled;
        log.debug("[GstDev Cloud] |- Condition [Use Simple Rest Client] value is [{}]", result);
        return result;
    }
}
