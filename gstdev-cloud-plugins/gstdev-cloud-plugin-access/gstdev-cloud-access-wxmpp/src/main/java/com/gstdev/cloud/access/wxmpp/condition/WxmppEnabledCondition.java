

package com.gstdev.cloud.access.wxmpp.condition;

import com.gstdev.cloud.access.core.constants.AccessConstants;
import com.gstdev.cloud.base.core.context.PropertyResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * <p>Description: 微信公众号注入条件 </p>
 *
 * @author : cc
 * @date : 2021/5/27 22:13
 */
public class WxmppEnabledCondition implements Condition {

    private static final Logger log = LoggerFactory.getLogger(WxmppEnabledCondition.class);

    @SuppressWarnings("NullableProblems")
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata metadata) {
        boolean result = PropertyResolver.getBoolean(conditionContext, AccessConstants.ITEM_WXMPP_ENABLED);
        log.debug("[GstDev Cloud] |- Condition [Wxmpp Enabled] value is [{}]", result);
        return result;
    }
}
