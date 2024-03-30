package com.gstdev.cloud.data.tenant.condition;

import com.gstdev.cloud.commons.ass.core.context.PropertyResolver;
import com.gstdev.cloud.data.core.constants.DataConstants;
import com.gstdev.cloud.data.core.enums.MultiTenantApproach;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * <p>Description: 标准算法策略条件 </p>
 *
 * @author : cc
 * @date : 2022/5/3 23:00
 */
public class SchemaApproachCondition implements Condition {

    private static final Logger log = LoggerFactory.getLogger(SchemaApproachCondition.class);

    @SuppressWarnings("NullableProblems")
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        String property = PropertyResolver.getProperty(conditionContext, DataConstants.ITEM_MULTI_TENANT_APPROACH);
        boolean result = StringUtils.isNotBlank(property) && StringUtils.equalsIgnoreCase(property, MultiTenantApproach.SCHEMA.name());
        log.debug("[GstDev Cloud] |- Condition [Schema Approach] value is [{}]", result);
        return result;
    }
}
