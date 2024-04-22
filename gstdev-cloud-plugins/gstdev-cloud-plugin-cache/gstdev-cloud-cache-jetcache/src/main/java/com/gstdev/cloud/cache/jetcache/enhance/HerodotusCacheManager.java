package com.gstdev.cloud.cache.jetcache.enhance;

import com.gstdev.cloud.cache.core.properties.CacheProperties;
import com.gstdev.cloud.cache.core.properties.CacheSetting;
import com.gstdev.cloud.base.definition.constants.SymbolConstants;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;

import java.util.Map;

/**
 * <p>Description: 自定义 缓存管理器 </p>
 *
 * @author : cc
 * @date : 2022/7/23 17:02
 */
public class HerodotusCacheManager extends JetCacheSpringCacheManager {

  private static final Logger log = LoggerFactory.getLogger(HerodotusCacheManager.class);

  private final CacheProperties cacheProperties;

  public HerodotusCacheManager(JetCacheCreateCacheFactory jetCacheCreateCacheFactory, CacheProperties cacheProperties) {
    super(jetCacheCreateCacheFactory);
    this.cacheProperties = cacheProperties;
    this.setAllowNullValues(cacheProperties.getAllowNullValues());
  }

  public HerodotusCacheManager(JetCacheCreateCacheFactory jetCacheCreateCacheFactory, CacheProperties cacheProperties, String... cacheNames) {
    super(jetCacheCreateCacheFactory, cacheNames);
    this.cacheProperties = cacheProperties;
  }

  @Override
  protected Cache createJetCache(String name) {
    Map<String, CacheSetting> instances = cacheProperties.getInstances();
    if (MapUtils.isNotEmpty(instances)) {
      String key = StringUtils.replace(name, SymbolConstants.COLON, cacheProperties.getSeparator());
      if (instances.containsKey(key)) {
        CacheSetting cacheSetting = instances.get(key);
        log.debug("[GstDev Cloud] |- CACHE - Cache [{}] is set to use INSTANCE cache.", name);
        return super.createJetCache(name, cacheSetting);
      }
    }
    return super.createJetCache(name);
  }
}
