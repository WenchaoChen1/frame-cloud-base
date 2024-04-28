package com.gstdev.cloud.cache.caffeine.configuration;

import com.gstdev.cloud.cache.caffeine.enhance.FrameCaffeineCacheManager;
import com.gstdev.cloud.cache.core.properties.CacheProperties;
import com.github.benmanes.caffeine.cache.Caffeine;
import jakarta.annotation.PostConstruct;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>Description: Caffeine 配置 </p>
 *
 * @author : cc
 * @date : 2022/5/23 17:56
 */
@Configuration(proxyBeanMethods = false)
public class CacheCaffeineConfiguration {

  private static final Logger log = LoggerFactory.getLogger(CacheCaffeineConfiguration.class);

  private final CacheProperties cacheProperties;

  public CacheCaffeineConfiguration(CacheProperties cacheProperties) {
    this.cacheProperties = cacheProperties;
  }

  @PostConstruct
  public void postConstruct() {
    log.debug("[GstDev Cloud] |- SDK [Cache Caffeine] Auto Configure.");
  }

  @Bean
  public Caffeine<Object, Object> caffeine() {
    Caffeine<Object, Object> caffeine = Caffeine
      .newBuilder()
      .expireAfterWrite(ObjectUtils.isNotEmpty(cacheProperties.getLocalExpire()) ? cacheProperties.getLocalExpire() : cacheProperties.getExpire());

    log.trace("[GstDev Cloud] |- Bean [Caffeine] Auto Configure.");

    return caffeine;
  }

  @Bean
  @ConditionalOnMissingBean(CaffeineCacheManager.class)
  public CaffeineCacheManager caffeineCacheManager(Caffeine<Object, Object> caffeine) {
    FrameCaffeineCacheManager herodotusCaffeineCacheManager = new FrameCaffeineCacheManager(cacheProperties);
    herodotusCaffeineCacheManager.setCaffeine(caffeine);
    log.trace("[GstDev Cloud] |- Bean [Caffeine Cache Manager] Auto Configure.");
    return herodotusCaffeineCacheManager;
  }
}
