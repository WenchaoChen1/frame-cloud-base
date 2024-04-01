package com.gstdev.cloud.commons.ass.definition.constants;

/**
 * <p>Description: 错误码构建顺序 </p>
 * <p>
 * 注解@Order或者接口Ordered的作用是定义Spring IOC容器中Bean的执行顺序的优先级，而不是定义Bean的加载顺序，Bean的加载顺序不受@Order或Ordered接口的影响
 *
 * @author : cc
 * @date : 2023/9/26 21:20
 */
public interface ErrorCodeMapperBuilderOrdered {

  int STEP = 10;

  int STANDARD = 0;
  int CACHE = STANDARD + STEP;
  int CAPTCHA = CACHE + STEP;
  int OAUTH2 = CAPTCHA + STEP;
  int REST = OAUTH2 + STEP;
  int MESSAGE = REST + STEP;
  int ACCESS = MESSAGE + STEP;
  int OSS = ACCESS + STEP;
}
