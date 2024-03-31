package cn.herodotus.engine.rest.condition.definition;

import cn.herodotus.engine.assistant.core.enums.Target;
import cn.herodotus.engine.rest.condition.constants.RestPropertyFinder;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * <p>Description: 远程Feign数据访问策略条件定义 </p>
 *
 * @author : cc
 * @date : 2021/5/22 16:19
 */
public class RemoteDataAccessCondition implements Condition {

    private static final Logger log = LoggerFactory.getLogger(RemoteDataAccessCondition.class);

    @SuppressWarnings("NullableProblems")
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        String property = RestPropertyFinder.getDataAccessStrategy(conditionContext, Target.REMOTE.name());
        boolean result = StringUtils.isNotBlank(property) && StringUtils.equalsIgnoreCase(property, Target.REMOTE.name());
        log.debug("[GstDev Cloud] |- Condition [Remote Data Access] value is [{}]", result);
        return result;
    }
}
