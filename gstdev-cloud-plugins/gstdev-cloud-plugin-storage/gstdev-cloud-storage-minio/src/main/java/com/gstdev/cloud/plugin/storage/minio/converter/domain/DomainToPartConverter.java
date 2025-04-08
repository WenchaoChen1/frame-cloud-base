// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.minio.converter.domain;

import com.gstdev.cloud.plugin.storage.specification.domain.multipart.PartSummaryDomain;
import io.minio.messages.Part;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.core.convert.converter.Converter;

import java.util.List;

/**
 * <p>Description: 基础的统一定义请求属性转换为 Minio Parts 参数转换器 </p>
 *
 * @author : cc
 * @date : 2023/8/13 22:45
 */
public class DomainToPartConverter implements Converter<List<PartSummaryDomain>, Part[]> {
    @Override
    public Part[] convert(List<PartSummaryDomain> source) {
        if (CollectionUtils.isNotEmpty(source)) {
            List<Part> parts = source.stream().map(item -> new Part(item.getPartNumber(), item.getEtag())).toList();
            Part[] result = new Part[parts.size()];
            return parts.toArray(result);
        }
        return new Part[]{};
    }
}
