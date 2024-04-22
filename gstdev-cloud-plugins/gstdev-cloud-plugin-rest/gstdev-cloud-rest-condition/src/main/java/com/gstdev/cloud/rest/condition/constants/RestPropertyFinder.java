package com.gstdev.cloud.rest.condition.constants;


import com.gstdev.cloud.commons.ass.core.context.PropertyResolver;
import com.gstdev.cloud.commons.ass.definition.constants.BaseConstants;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ConditionContext;

/**
 * <p>Description: 策略模块配置获取器 </p>
 *
 * @author : cc
 * @date : 2022/2/1 19:23
 */
public class RestPropertyFinder {

  public static String getApplicationName(ApplicationContext applicationContext) {
    return PropertyResolver.getProperty(applicationContext.getEnvironment(), BaseConstants.ITEM_SPRING_APPLICATION_NAME);
  }

  public static String getCryptoStrategy(ConditionContext conditionContext, String defaultValue) {
    return PropertyResolver.getProperty(conditionContext, RestConstants.ITEM_PROTECT_CRYPTO_STRATEGY, defaultValue);
  }

  public static String getCryptoStrategy(ConditionContext conditionContext) {
    return PropertyResolver.getProperty(conditionContext, RestConstants.ITEM_PROTECT_CRYPTO_STRATEGY);
  }

  public static boolean isScanEnabled(ConditionContext conditionContext) {
    return PropertyResolver.getBoolean(conditionContext, RestConstants.ITEM_SCAN_ENABLED);
  }

  public static boolean isOpenFeignHttp2ClientEnabled(ConditionContext conditionContext) {
    return PropertyResolver.getBoolean(conditionContext, RestConstants.ITEM_OPENFEIGN_HTTP2CLIENT_ENABLED);
  }

  public static boolean isOpenFeignHttpClient5Enabled(ConditionContext conditionContext) {
    return PropertyResolver.getBoolean(conditionContext, RestConstants.ITEM_OPENFEIGN_HTTPCLIENT5_ENABLED);
  }

  public static String getDataAccessStrategy(ConditionContext conditionContext, String defaultValue) {
    return PropertyResolver.getProperty(conditionContext, RestConstants.ITEM_PLATFORM_DATA_ACCESS_STRATEGY, defaultValue);
  }

  public static String getDataAccessStrategy(ConditionContext conditionContext) {
    return PropertyResolver.getProperty(conditionContext, RestConstants.ITEM_PLATFORM_DATA_ACCESS_STRATEGY);
  }

  public static String getArchitecture(ConditionContext conditionContext, String defaultValue) {
    return PropertyResolver.getProperty(conditionContext, RestConstants.ITEM_PLATFORM_ARCHITECTURE, defaultValue);
  }

  public static String getArchitecture(ConditionContext conditionContext) {
    return PropertyResolver.getProperty(conditionContext, RestConstants.ITEM_PLATFORM_ARCHITECTURE);
  }
}
