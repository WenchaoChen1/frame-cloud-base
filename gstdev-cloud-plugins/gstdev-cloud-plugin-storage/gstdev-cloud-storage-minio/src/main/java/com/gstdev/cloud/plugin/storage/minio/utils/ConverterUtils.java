// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.minio.utils;

import io.minio.Result;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.collections4.MapUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>Description: Minio Iterable 类型返回值处理工具类 </p>
 *
 * @author : cc
 * @date : 2023/6/11 17:35
 */
public class ConverterUtils {

    public static <T, R> List<R> toDomains(List<T> items, Converter<T, R> toDomain) {
        if (CollectionUtils.isNotEmpty(items)) {
            return items.stream().map(toDomain::convert).toList();
        }
        return new ArrayList<>();
    }

    public static <T, R> List<R> toDomains(Iterable<Result<T>> iterable, Converter<Result<T>, R> toDomain) {
        List<R> domains = new ArrayList<>();
        if (!IterableUtils.isEmpty(iterable)) {
            for (Result<T> result : iterable) {
                R response = toDomain.convert(result);
                domains.add(response);
            }
        }
        return domains;
    }

    public static Map<String, String> toMap(Map<String, List<String>> multimap) {
        if (MapUtils.isNotEmpty(multimap)) {
            return multimap.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, entry -> StringUtils.collectionToCommaDelimitedString(entry.getValue())));
        }
        return new HashMap<>();
    }
}
