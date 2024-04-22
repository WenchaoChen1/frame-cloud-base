package com.gstdev.cloud.rest.condition.definition;

import com.gstdev.cloud.commons.ass.core.enums.Architecture;
import com.gstdev.cloud.rest.condition.constants.RestPropertyFinder;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * <p>Description: 单体架构条件 </p>
 *
 * @author : cc
 * @date : 2022/1/9 10:47
 */
public class MonocoqueArchitectureCondition implements Condition {

  private static final Logger log = LoggerFactory.getLogger(MonocoqueArchitectureCondition.class);

  @SuppressWarnings("NullableProblems")
  @Override
  public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
    String property = RestPropertyFinder.getArchitecture(conditionContext);
    boolean result = StringUtils.isNotBlank(property) && StringUtils.equalsIgnoreCase(property, Architecture.MONOCOQUE.name());
    log.debug("[GstDev Cloud] |- Condition [Monocoque Architecture] value is [{}]", result);
    return result;
  }
}
