//package com.gstdev.cloud.commons.ass.core.conditon;
//
//import com.gstdev.cloud.commons.ass.core.context.PropertyResolver;
//import com.gstdev.cloud.commons.ass.definition.constants.BaseConstants;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.annotation.Condition;
//import org.springframework.context.annotation.ConditionContext;
//import org.springframework.core.type.AnnotatedTypeMetadata;
//
///**
// * <p>Description: Swagger 开启条件 </p>
// *
// * @author : cc
// * @date : 2022/3/17 14:34
// */
//public class SwaggerEnabledCondition implements Condition {
//
//    private static final Logger log = LoggerFactory.getLogger(SwaggerEnabledCondition.class);
//
//    @SuppressWarnings("NullableProblems")
//    @Override
//    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata metadata) {
//        boolean result = PropertyResolver.getBoolean(conditionContext, BaseConstants.ITEM_SWAGGER_ENABLED);
//        log.debug("[GstDev Cloud] |- Condition [Swagger Enabled] value is [{}]", result);
//        return result;
//    }
//}
