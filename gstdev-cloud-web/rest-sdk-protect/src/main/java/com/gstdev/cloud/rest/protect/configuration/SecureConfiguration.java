package com.gstdev.cloud.rest.protect.configuration;

import com.gstdev.cloud.cache.jetcache.autoconfigure.CacheJetCacheAutoConfiguration;
import com.gstdev.cloud.rest.condition.properties.SecureProperties;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Import;

/**
 * <p>Description: 接口安全配置 </p>
 *
 * @author : cc
 * @date : 2021/10/4 17:28
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties({SecureProperties.class})
@AutoConfiguration(after = {CacheJetCacheAutoConfiguration.class})
public class SecureConfiguration {

  private static final Logger log = LoggerFactory.getLogger(SecureConfiguration.class);

  @PostConstruct
  public void postConstruct() {
    log.debug("[GstDev Cloud] |- SDK [Protect Secure] Auto Configure.");
  }

//  @Bean
//  @ConditionalOnMissingBean
//  @DependsOn("jetCacheCreateCacheFactory")
//  public IdempotentStampManager idempotentStampManager(SecureProperties secureProperties) {
//    IdempotentStampManager idempotentStampManager = new IdempotentStampManager(secureProperties);
//    log.trace("[GstDev Cloud] |- Bean [Idempotent Stamp Manager] Auto Configure.");
//    return idempotentStampManager;
//  }
//
//  @Bean
//  @ConditionalOnMissingBean
//  @DependsOn("jetCacheCreateCacheFactory")
//  public AccessLimitedStampManager accessLimitedStampManager(SecureProperties secureProperties) {
//    AccessLimitedStampManager accessLimitedStampManager = new AccessLimitedStampManager(secureProperties);
//    log.trace("[GstDev Cloud] |- Bean [Access Limited Stamp Manager] Auto Configure.");
//    return accessLimitedStampManager;
//  }

//  @Bean
//  @ConditionalOnMissingBean
//  @ConditionalOnBean(IdempotentStampManager.class)
//  public IdempotentInterceptor idempotentInterceptor(IdempotentStampManager idempotentStampManager) {
//    IdempotentInterceptor idempotentInterceptor = new IdempotentInterceptor();
//    idempotentInterceptor.setIdempotentStampManager(idempotentStampManager);
//    log.trace("[GstDev Cloud] |- Bean [Idempotent Interceptor] Auto Configure.");
//    return idempotentInterceptor;
//  }

//  @Bean
//  @ConditionalOnMissingBean
//  @ConditionalOnBean(AccessLimitedStampManager.class)
//  public AccessLimitedInterceptor accessLimitedInterceptor(AccessLimitedStampManager accessLimitedStampManager) {
//    AccessLimitedInterceptor accessLimitedInterceptor = new AccessLimitedInterceptor();
//    accessLimitedInterceptor.setAccessLimitedStampManager(accessLimitedStampManager);
//    log.trace("[GstDev Cloud] |- Bean [Access Limited Interceptor] Auto Configure.");
//    return accessLimitedInterceptor;
//  }

//  @Bean
//  @ConditionalOnMissingBean
//  public XssHttpServletFilter xssHttpServletFilter() {
//    XssHttpServletFilter xssHttpServletFilter = new XssHttpServletFilter();
//    log.trace("[GstDev Cloud] |- Bean [Xss Http Servlet Filter] Auto Configure.");
//    return xssHttpServletFilter;
//  }
}
