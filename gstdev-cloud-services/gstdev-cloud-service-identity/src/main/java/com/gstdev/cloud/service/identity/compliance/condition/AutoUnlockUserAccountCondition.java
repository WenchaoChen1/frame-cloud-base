package com.gstdev.cloud.service.identity.compliance.condition;

import com.gstdev.cloud.base.core.context.PropertyResolver;
import com.gstdev.cloud.oauth2.core.constants.OAuth2Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * <p>Description: 阿里云短信开启条件 </p>
 *
 * @author : cc
 * @date : 2022/1/27 16:23
 */
public class AutoUnlockUserAccountCondition implements Condition {

    private static final Logger log = LoggerFactory.getLogger(AutoUnlockUserAccountCondition.class);

    @SuppressWarnings("NullableProblems")
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata metadata) {
        boolean result = PropertyResolver.getBoolean(conditionContext, OAuth2Constants.ITEM_COMPLIANCE_AUTO_UNLOCK, true);
        log.debug("[GstDev Cloud] |- Condition [Auto Unlock User Account] value is [{}]", result);
        return result;
    }
}
