package com.gstdev.cloud.base.definition.enums;

/**
 * <p>Description: 枚举值定义 </p>
 *
 * @author : cc
 * @date : 2024/3/26 16:49
 */
public interface EnumSort<T> {

    /**
     * 获取枚举自定义顺序
     *
     * @return 自定义枚举值
     */
    T getSort();
}
