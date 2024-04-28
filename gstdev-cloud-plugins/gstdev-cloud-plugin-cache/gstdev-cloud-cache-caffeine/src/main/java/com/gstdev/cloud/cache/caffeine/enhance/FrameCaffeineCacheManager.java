package com.gstdev.cloud.cache.caffeine.enhance;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.gstdev.cloud.cache.core.properties.CacheProperties;
import com.gstdev.cloud.cache.core.properties.CacheSetting;
import com.gstdev.cloud.base.definition.constants.SymbolConstants;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.caffeine.CaffeineCacheManager;

import java.util.Map;

/**
 * <p>Description: 扩展的 CaffeineCacheManager </p>
 * <p>
 * 用于支持 Caffeine 缓存可以针对实体进行单独的过期时间设定
 *
 * @author : cc
 * @date : 2021/10/25 18:12
 */
public class FrameCaffeineCacheManager extends CaffeineCacheManager {

    private static final Logger log = LoggerFactory.getLogger(FrameCaffeineCacheManager.class);

    private final CacheProperties cacheProperties;

    public FrameCaffeineCacheManager(CacheProperties cacheProperties) {
        this.cacheProperties = cacheProperties;
        this.setAllowNullValues(cacheProperties.getAllowNullValues());
    }

    public FrameCaffeineCacheManager(CacheProperties cacheProperties, String... cacheNames) {
        super(cacheNames);
        this.cacheProperties = cacheProperties;
        this.setAllowNullValues(cacheProperties.getAllowNullValues());
    }

    @Override
    protected Cache<Object, Object> createNativeCaffeineCache(String name) {
        Map<String, CacheSetting> instances = cacheProperties.getInstances();
        if (MapUtils.isNotEmpty(instances)) {
            String key = StringUtils.replace(name, SymbolConstants.COLON, cacheProperties.getSeparator());
            if (instances.containsKey(key)) {
                CacheSetting cacheSetting = instances.get(key);
                log.debug("[GstDev Cloud] |- CACHE - Caffeine cache [{}] is set to use INSTANCE config.", name);
                return Caffeine.newBuilder().expireAfterWrite(cacheSetting.getExpire()).build();
            }
        }

        return super.createNativeCaffeineCache(name);
    }
}
