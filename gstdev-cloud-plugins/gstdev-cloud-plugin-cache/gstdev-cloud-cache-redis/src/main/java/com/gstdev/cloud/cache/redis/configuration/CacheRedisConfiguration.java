package com.gstdev.cloud.cache.redis.configuration;

import com.gstdev.cloud.cache.core.properties.CacheProperties;
import com.gstdev.cloud.cache.redis.enhance.HerodotusRedisCacheManager;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * <p>Description: Redis 配置 </p>
 *
 * @author : cc
 * @date : 2022/5/23 17:00
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(CacheProperties.class)
public class CacheRedisConfiguration {

    private static final Logger log = LoggerFactory.getLogger(CacheRedisConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.debug("[GstDev Cloud] |- SDK [Cache Redis] Auto Configure.");
    }

    private RedisSerializer<String> keySerializer() {
        return new StringRedisSerializer();
    }

    /**
     * 命名为 springSessionDefaultRedisSerializer 是因为 Spring Session 会用到。
     * {@link org.springframework.session.data.redis.config.annotation.web.http.AbstractRedisHttpSessionConfiguration}
     * {@link org.springframework.session.data.redis.config.annotation.web.server.RedisWebSessionConfiguration}
     *
     * @return {@link RedisSerializer}
     */
//    @Bean(name = "springSessionDefaultRedisSerializer")
    private RedisSerializer<Object> valueSerializer() {
        RedisSerializer<Object> redisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        log.trace("[GstDev Cloud] |- Bean [Jackson2Json Redis Serializer] Auto Configure.");
        return redisSerializer;
    }

    /**
     * 重新配置一个RedisTemplate
     *
     * @return {@link RedisTemplate}
     */
    @Bean(name = "redisTemplate")
    @ConditionalOnMissingBean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(keySerializer());
        redisTemplate.setHashKeySerializer(keySerializer());
        redisTemplate.setValueSerializer(valueSerializer());
        redisTemplate.setHashValueSerializer(valueSerializer());
        redisTemplate.setDefaultSerializer(valueSerializer());
        redisTemplate.afterPropertiesSet();

        log.trace("[GstDev Cloud] |- Bean [Redis Template] Auto Configure.");

        return redisTemplate;
    }

    @Bean(name = "stringRedisTemplate")
    @ConditionalOnMissingBean
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(redisConnectionFactory);
        stringRedisTemplate.afterPropertiesSet();
        log.trace("[GstDev Cloud] |- Bean [String Redis Template] Auto Configure.");
        return stringRedisTemplate;
    }

    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer(RedisConnectionFactory connectionFactory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        log.trace("[GstDev Cloud] |- Bean [Redis Message Listener Container] Auto Configure.");
        return container;
    }

    @Bean
    public RedisCacheManager redisCacheManager(RedisConnectionFactory redisConnectionFactory, CacheProperties cacheProperties) {
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory);

        // 注意：这里 RedisCacheConfiguration 每一个方法调用之后，都会返回一个新的 RedisCacheConfiguration 对象，所以要注意对象的引用关系。
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig().entryTtl(cacheProperties.getExpire());

        boolean allowNullValues = cacheProperties.getAllowNullValues();
        if (!allowNullValues) {
            // 注意：这里 RedisCacheConfiguration 每一个方法调用之后，都会返回一个新的 RedisCacheConfiguration 对象，所以要注意对象的引用关系。
            redisCacheConfiguration = redisCacheConfiguration.disableCachingNullValues();
        }

        HerodotusRedisCacheManager herodotusRedisCacheManager = new HerodotusRedisCacheManager(redisCacheWriter, redisCacheConfiguration, cacheProperties);
        herodotusRedisCacheManager.setTransactionAware(false);
        herodotusRedisCacheManager.afterPropertiesSet();
        log.trace("[GstDev Cloud] |- Bean [Redis Cache Manager] Auto Configure.");
        return herodotusRedisCacheManager;
    }

    @Configuration(proxyBeanMethods = false)
    @ComponentScan({
            "com.gstdev.cloud.cache.redis.utils"
    })
    static class RedisUtilsConfiguration {

        @PostConstruct
        public void postConstruct() {
            log.debug("[GstDev Cloud] |- SDK [Cache Redis Utils] Auto Configure.");
        }
    }
}
