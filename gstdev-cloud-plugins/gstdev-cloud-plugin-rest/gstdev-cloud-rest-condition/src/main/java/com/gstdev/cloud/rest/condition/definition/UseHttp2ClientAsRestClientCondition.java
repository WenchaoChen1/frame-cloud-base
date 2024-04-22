package com.gstdev.cloud.rest.condition.definition;

import com.gstdev.cloud.rest.condition.constants.RestPropertyFinder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * <p>Description: 使用 Jdk Client 作为 RestTemplate 和 OpenFeign 引擎条件 </p>
 *
 * @author : cc
 * @date : 2023/6/15 21:27
 */
public class UseHttp2ClientAsRestClientCondition implements Condition {

  private static final Logger log = LoggerFactory.getLogger(UseHttp2ClientAsRestClientCondition.class);

  @SuppressWarnings("NullableProblems")
  @Override
  public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
    boolean result = RestPropertyFinder.isOpenFeignHttp2ClientEnabled(conditionContext);
    log.debug("[GstDev Cloud] |- Condition [Use Http2Client as Rest Client] value is [{}]", result);
    return result;
  }
}
