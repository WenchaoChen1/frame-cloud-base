package com.gstdev.cloud.cache.redisson.annotation;

import com.gstdev.cloud.cache.redisson.autoconfigure.CacheRedissonAutoConfiguration;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;

import java.lang.annotation.*;

/**
 * <p>Description: 手动开启Redisson注入 </p>
 *
 * @author : cc
 * @date : 2022/1/14 23:06
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ImportAutoConfiguration(CacheRedissonAutoConfiguration.class)
public @interface EnableHerodotusRedisson {
}
