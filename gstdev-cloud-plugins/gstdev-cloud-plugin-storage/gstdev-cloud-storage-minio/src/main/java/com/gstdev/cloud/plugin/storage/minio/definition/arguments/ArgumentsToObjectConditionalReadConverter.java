// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.minio.definition.arguments;

import com.gstdev.cloud.base.core.utils.type.DateTimeUtils;
import com.gstdev.cloud.base.definition.constants.SymbolConstants;
import com.gstdev.cloud.plugin.storage.specification.arguments.base.ObjectConditionalReadArguments;
import io.minio.ObjectConditionalReadArgs;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * <p>Description: 统一定义对象条件请求参数转换为 Minio 参数转换器 </p>
 *
 * @author : cc
 * @date : 2023/8/15 21:40
 */
public abstract class ArgumentsToObjectConditionalReadConverter<S extends ObjectConditionalReadArguments, T extends ObjectConditionalReadArgs, B extends ObjectConditionalReadArgs.Builder<B, T>> extends ArgumentsToObjectReadConverter<S, T, B> {

    @Override
    public void prepare(S arguments, B builder) {

        if (ObjectUtils.isNotEmpty(arguments.getLength()) && arguments.getLength() >= 0) {
            builder.length(arguments.getLength());
        }

        if (ObjectUtils.isNotEmpty(arguments.getOffset()) && arguments.getOffset() >= 0) {
            builder.offset(arguments.getOffset());
        }

        if (CollectionUtils.isNotEmpty(arguments.getMatchETag())) {
            builder.matchETag(StringUtils.join(arguments.getMatchETag(), SymbolConstants.COMMA));
        }

        if (CollectionUtils.isNotEmpty(arguments.getNotMatchEtag())) {
            builder.notMatchETag(StringUtils.join(arguments.getNotMatchEtag(), SymbolConstants.COMMA));
        }

        if (ObjectUtils.isNotEmpty(arguments.getModifiedSince())) {
            builder.modifiedSince(DateTimeUtils.dateToZonedDateTime(arguments.getModifiedSince()));
        }

        if (ObjectUtils.isNotEmpty(arguments.getUnmodifiedSince())) {
            builder.unmodifiedSince(DateTimeUtils.dateToZonedDateTime(arguments.getUnmodifiedSince()));
        }

        super.prepare(arguments, builder);
    }
}
