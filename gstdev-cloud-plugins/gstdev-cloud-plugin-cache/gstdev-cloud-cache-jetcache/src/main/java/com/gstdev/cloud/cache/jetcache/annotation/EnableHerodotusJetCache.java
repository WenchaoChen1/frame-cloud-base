package com.gstdev.cloud.cache.jetcache.annotation;

import com.gstdev.cloud.cache.jetcache.autoconfigure.CacheJetCacheAutoConfiguration;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;

import java.lang.annotation.*;

/**
 * <p>Description: 手动开启JetCache注入 </p>
 *
 * @author : cc
 * @date : 2022/1/14 22:51
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ImportAutoConfiguration(CacheJetCacheAutoConfiguration.class)
public @interface EnableHerodotusJetCache {
}
