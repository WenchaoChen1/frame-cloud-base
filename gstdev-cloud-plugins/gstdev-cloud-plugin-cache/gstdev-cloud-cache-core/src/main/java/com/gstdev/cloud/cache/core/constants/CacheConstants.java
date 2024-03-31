package com.gstdev.cloud.cache.core.constants;


import com.gstdev.cloud.commons.ass.definition.constants.BaseConstants;

/**
 * <p>Description: Cache Property值常量 </p>
 *
 * @author : cc
 * @date : 2022/1/13 21:22
 */
public interface CacheConstants extends BaseConstants {

    String PROPERTY_REDIS_REDISSON = PROPERTY_SPRING_DATA + ".redisson";
    String ITEM_REDISSON_ENABLED = PROPERTY_REDIS_REDISSON + PROPERTY_ENABLED;
}
