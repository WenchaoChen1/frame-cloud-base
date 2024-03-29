package com.gstdev.cloud.data.core.enums;

/**
 * <p>Description: 多租户模式 </p>
 *
 * @author : cc
 * @date : 2023/3/28 23:32
 */
public enum MultiTenantApproach {
    /**
     * 共享数据库，独立Schema，共享数据表
     */
    DISCRIMINATOR,
    /**
     * 共享数据库，独立Schema
     */
    SCHEMA,
    /**
     * 独立数据库
     */
    DATABASE;
}
