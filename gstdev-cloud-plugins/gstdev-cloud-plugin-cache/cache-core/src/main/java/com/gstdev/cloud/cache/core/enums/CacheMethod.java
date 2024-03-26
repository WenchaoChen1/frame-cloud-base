package com.gstdev.cloud.cache.core.enums;

/**
 * <p>Description: 缓存方式 </p>
 * <p>
 * 额外增加一个枚举类，避免直接引用 JetCache CacheType 带来过多不必要的引用。
 *
 * @author : cc
 * @date : 2023/5/19 20:25
 */
public enum CacheMethod {
    REMOTE,
    LOCAL,
    BOTH;
}
