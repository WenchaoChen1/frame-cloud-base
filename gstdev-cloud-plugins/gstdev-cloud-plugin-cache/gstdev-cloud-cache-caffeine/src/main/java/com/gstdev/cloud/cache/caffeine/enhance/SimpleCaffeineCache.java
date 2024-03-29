package com.gstdev.cloud.cache.caffeine.enhance;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * <p>Description: Caffeine 简单封装 </p>
 *
 * @author : cc
 * @date : 2023/4/12 18:41
 */
public class SimpleCaffeineCache {

    private final Cache<String, String> cache;

    public SimpleCaffeineCache(Duration duration) {
        this.cache = Caffeine.newBuilder().expireAfterWrite(duration).build();
    }

    public SimpleCaffeineCache(long duration, TimeUnit unit) {
        this.cache = Caffeine.newBuilder().expireAfterWrite(duration, unit).build();
    }

    public void put(String key, String value) {
        cache.put(key, value);
    }

    public String get(String key) {
        return cache.getIfPresent(key);
    }
}
