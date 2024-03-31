package cn.herodotus.engine.rest.condition.definition;

import cn.herodotus.engine.rest.condition.constants.RestPropertyFinder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * <p>Description: Request Mapping 扫描条件 </p>
 *
 * @author : cc
 * @date : 2021/5/22 16:11
 */
public class RequestMappingScanCondition implements Condition {

    private static final Logger log = LoggerFactory.getLogger(RequestMappingScanCondition.class);

    @SuppressWarnings("NullableProblems")
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        boolean result = RestPropertyFinder.isScanEnabled(conditionContext);
        log.debug("[GstDev Cloud] |- Condition [Request Mapping Scan] value is [{}]", result);
        return result;
    }
}
