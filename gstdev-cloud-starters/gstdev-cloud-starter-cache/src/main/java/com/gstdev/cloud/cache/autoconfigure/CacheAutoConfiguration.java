package com.gstdev.cloud.cache.autoconfigure;

import com.gstdev.cloud.cache.autoconfigure.customizer.CacheErrorCodeMapperBuilderCustomizer;
import com.gstdev.cloud.base.definition.function.ErrorCodeMapperBuilderCustomizer;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * <p>Description: Cache 配置 </p>
 *
 * @author : cc
 * @date : 2022/1/13 22:29
 */
@AutoConfiguration
public class CacheAutoConfiguration {

  private static final Logger log = LoggerFactory.getLogger(CacheAutoConfiguration.class);

  @PostConstruct
  public void postConstruct() {
    log.info("[GstDev Cloud] |- Module [Cache Starter] Auto Configure.");
  }

  @Bean
  public ErrorCodeMapperBuilderCustomizer cacheErrorCodeMapperBuilderCustomizer() {
    CacheErrorCodeMapperBuilderCustomizer customizer = new CacheErrorCodeMapperBuilderCustomizer();
    log.debug("[GstDev Cloud] |- Strategy [Cache ErrorCodeMapper Builder Customizer] Auto Configure.");
    return customizer;
  }
}
