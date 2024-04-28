package com.gstdev.cloud.cache.redis.definition;

import org.springframework.beans.factory.InitializingBean;

/**
 * <p>Description: 简单数据的Redis存储 </p>
 * <p>
 * 很多使用Redis进行缓存的数据都比较简单，通常就是单一的键值对存储。例如，手机验证码、JustAuth的State数据、第三方集成的Token等。
 * 因此，抽象出SimpleRedisCache，对此类操作进行定义。
 *
 * @author : cc
 * @date : 2021/5/21 23:37
 */
public interface StringRedisCache extends InitializingBean {

    /**
     * 存入缓存
     *
     * @param key   缓存key
     * @param value 缓存内容
     */
    void cache(String key, String value);

    /**
     * 存入缓存
     *
     * @param key     缓存key
     * @param value   缓存内容
     * @param timeout 指定缓存过期时间（毫秒）
     */
    void cache(String key, String value, long timeout);

    /**
     * 获取缓存内容
     *
     * @param key 缓存key
     * @return 缓存内容
     */
    String get(String key);

    /**
     * 是否存在key，如果对应key的value值已过期，也返回false
     *
     * @param key 缓存key
     * @return true：存在key，并且value没过期；false：key不存在或者已过期
     */
    boolean containsKey(String key);

    boolean delete(String key);
}
