// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.minio.domain.base;

import com.gstdev.cloud.base.definition.domain.base.Entity;
import com.gstdev.cloud.plugin.storage.minio.enums.RetentionModeEnums;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * <p>Description: Retention 相关共用属性抽象类 </p>
 *
 * @author : cc
 * @date : 2023/7/4 21:58
 */
public abstract class BaseRetentionDomain implements Entity {

    @Schema(name = "保留模式")
    private RetentionModeEnums mode;

    public RetentionModeEnums getMode() {
        return mode;
    }

    public void setMode(RetentionModeEnums mode) {
        this.mode = mode;
    }
}
