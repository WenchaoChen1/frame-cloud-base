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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>Description: 基础的统一定义请求属性转换为 Minio Parts 参数转换器 </p>
 *
 * @author : cc
 * @date : 2023/8/13 22:45
 */
public class PartToDomainConverter implements Converter<List<Part>, List<PartSummaryDomain>> {

    @Override
    public List<PartSummaryDomain> convert(List<Part> source) {
        if (CollectionUtils.isNotEmpty(source)) {
            return source.stream().map(this::convert).toList();
        }
        return new ArrayList<>();
    }

    private PartSummaryDomain convert(Part source) {

        PartSummaryDomain domain = new PartSummaryDomain();
        domain.setPartSize(source.partSize());
        domain.setLastModifiedDate(Date.from(source.lastModified().toInstant()));
        domain.setPartNumber(source.partNumber());
        domain.setEtag(source.etag());
        return domain;
    }
}
