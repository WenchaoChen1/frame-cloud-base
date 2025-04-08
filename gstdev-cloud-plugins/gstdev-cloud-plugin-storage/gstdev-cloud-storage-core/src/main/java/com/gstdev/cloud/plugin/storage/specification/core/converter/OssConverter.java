// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.specification.core.converter;

import org.springframework.core.convert.converter.Converter;

/**
 * <p>Description: 统一转换器定义 </p>
 *
 * @author : cc
 * @date : 2023/8/16 13:00
 */
public interface OssConverter<S, T> extends Converter<S, T> {

    /**
     * 参数准备
     *
     * @param source   源对象
     * @param instance 转换后的对象
     */
    void prepare(S source, T instance);

    /**
     * 获取最终生成对象实例
     *
     * @param source 源对象。传递源对象，方便参数设置
     * @return 转换后的对象实例
     */
    T getInstance(S source);

    /**
     * 实体转换
     *
     * @param source 统一定义请求参数
     * @return 转换后的对象实例
     */
    @Override
    default T convert(S source) {
        T instance = getInstance(source);
        prepare(source, instance);
        return instance;
    }
}
