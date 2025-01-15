

package com.gstdev.cloud.access.sms.condition;

import com.gstdev.cloud.access.core.constants.AccessConstants;
import com.gstdev.cloud.base.core.context.PropertyResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * <p>Description: 短信开启条件 </p>
 *
 * @author : cc
 * @date : 2022/1/26 15:33
 */
public class SmsEnabledCondition implements Condition {

    private static final Logger log = LoggerFactory.getLogger(SmsEnabledCondition.class);

    @SuppressWarnings("NullableProblems")
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata metadata) {
        boolean result = PropertyResolver.getBoolean(conditionContext, AccessConstants.ITEM_SMS_ENABLED);
        log.debug("[GstDev Cloud] |- Condition [Sms Enabled] value is [{}]", result);
        return result;
    }
}
