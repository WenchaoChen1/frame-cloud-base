//package com.gstdev.cloud.commons.web;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.DisposableBean;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ApplicationContextAware;
//
//@Slf4j
//public class SpringContextHolder implements ApplicationContextAware, DisposableBean {
//
//  private static ApplicationContext applicationContext = null;
//
//  @SuppressWarnings("unchecked")
//  public static <T> T getBean(String name) {
//    assertContextInjected();
//    return (T) applicationContext.getBean(name);
//  }
//
//  public static <T> T getBean(Class<T> requiredType) {
//    assertContextInjected();
//    return applicationContext.getBean(requiredType);
//  }
//
//  private static void assertContextInjected() {
//    if (applicationContext == null) {
//      throw new IllegalStateException("applicaitonContext属性未注入, 请在applicationContext" + ".xml中定义SpringContextHolder或在SpringBoot启动类中注册SpringContextHolder.");
//    }
//  }
//
//  private static void clearHolder() {
//    log.debug("清除SpringContextHolder中的ApplicationContext:" + applicationContext);
//    applicationContext = null;
//  }
//
//  @Override
//  public void destroy() {
//    SpringContextHolder.clearHolder();
//  }
//
//  @Override
//  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//    if (SpringContextHolder.applicationContext != null) {
//      log.warn("SpringContextHolder中的ApplicationContext被覆盖, 原有ApplicationContext为:" + SpringContextHolder.applicationContext);
//    }
//
//    SpringContextHolder.applicationContext = applicationContext;
//  }
//}
