package com.gstdev.cloud.cache.redis.annotation;

import com.gstdev.cloud.cache.redis.configuration.CacheRedisConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * <p>Description: 开启 Herodotus Redis  </p>
 *
 * @author : cc
 * @date : 2022/12/29 21:25
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(CacheRedisConfiguration.class)
public @interface EnableHerodotusRedis {
}
