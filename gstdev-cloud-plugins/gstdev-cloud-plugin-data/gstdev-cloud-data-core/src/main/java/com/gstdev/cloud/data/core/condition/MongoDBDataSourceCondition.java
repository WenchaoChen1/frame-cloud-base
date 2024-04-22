package com.gstdev.cloud.data.core.condition;

import com.gstdev.cloud.base.core.context.PropertyResolver;
import com.gstdev.cloud.data.core.constants.DataConstants;
import com.gstdev.cloud.data.core.enums.DataSource;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * <p>Description: 以 MongoDB 作为基础核心应用数据存储条件 </p>
 *
 * @author : cc
 * @date : 2022/1/9 10:47
 */
public class MongoDBDataSourceCondition implements Condition {

  private static final Logger log = LoggerFactory.getLogger(MongoDBDataSourceCondition.class);

  @SuppressWarnings("NullableProblems")
  @Override
  public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
    String property = PropertyResolver.getProperty(conditionContext, DataConstants.ITEM_DATA_DATA_SOURCE);
    boolean result = StringUtils.isNotBlank(property) && StringUtils.equalsIgnoreCase(property, DataSource.MONGODB.name());
    log.debug("[GstDev Cloud] |- Condition [MongoDB Data Source] value is [{}]", result);
    return result;
  }
}
