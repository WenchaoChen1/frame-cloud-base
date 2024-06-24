package com.gstdev.cloud.cache.jetcache.autoconfigure;

import com.alicp.jetcache.CacheManager;
import com.alicp.jetcache.SimpleCacheManager;
import com.alicp.jetcache.anno.support.SpringConfigProvider;
import com.alicp.jetcache.autoconfigure.JetCacheAutoConfiguration;
import com.gstdev.cloud.cache.caffeine.configuration.CacheCaffeineConfiguration;
import com.gstdev.cloud.cache.core.properties.CacheProperties;
import com.gstdev.cloud.cache.jetcache.enhance.FrameCacheManager;
import com.gstdev.cloud.cache.jetcache.enhance.JetCacheCreateCacheFactory;
import com.gstdev.cloud.cache.jetcache.utils.JetCacheUtils;
import com.gstdev.cloud.cache.redis.configuration.CacheRedisConfiguration;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;

/**
 * <p>Description: JetCacheConfiguration </p>
 * <p>
 * 新增JetCache配置，解决JetCache依赖循环问题
 * <p>
 * 注解 @AutoConfiguration 它是 @Configuration、@AutoConfigureBefore、@AutoConfigureAfter三个注解结合体，以一顶三。
 * 标注 @AutoConfiguration 注解的类也必须放进
 * META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports
 * 文件里才算自动配置类，否则也只是普通配置类而已
 *
 * @author : cc
 * @date : 2021/12/4 10:44
 */
@AutoConfiguration(after = JetCacheAutoConfiguration.class)
//@ConditionalOnClass({CacheManager.class})
@EnableConfigurationProperties(CacheProperties.class)
@Import({CacheCaffeineConfiguration.class, CacheRedisConfiguration.class})
public class CacheJetCacheAutoConfiguration {

    private static final Logger log = LoggerFactory.getLogger(CacheJetCacheAutoConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.debug("[GstDev Cloud] |- SDK [Cache JetCache] Auto Configure.");
    }

    @Bean(
            name = {"jcCacheManager"},
            destroyMethod = "close"
    )
    @ConditionalOnMissingBean
    public SimpleCacheManager cacheManager(@Autowired SpringConfigProvider springConfigProvider) {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCacheBuilderTemplate(springConfigProvider.getCacheBuilderTemplate());
        return cacheManager;
    }

    @Bean
    public JetCacheCreateCacheFactory jetCacheCreateCacheFactory(@Qualifier("jcCacheManager") CacheManager cacheManager, CacheProperties cacheProperties) {
        JetCacheCreateCacheFactory factory = new JetCacheCreateCacheFactory(cacheManager, cacheProperties);
        JetCacheUtils.setJetCacheCreateCacheFactory(factory);
        log.trace("[GstDev Cloud] |- Bean [Jet Cache Create Cache Factory] Auto Configure.");
        return factory;
    }

    @Bean
    @Primary
    @ConditionalOnMissingBean
    public FrameCacheManager frameCacheManager(JetCacheCreateCacheFactory jetCacheCreateCacheFactory, CacheProperties cacheProperties) {
        FrameCacheManager frameCacheManager = new FrameCacheManager(jetCacheCreateCacheFactory, cacheProperties);
        frameCacheManager.setAllowNullValues(cacheProperties.getAllowNullValues());
        log.trace("[GstDev Cloud] |- Bean [Jet Cache GstDev Cloud Cache Manager] Auto Configure.");
        return frameCacheManager;
    }
}
