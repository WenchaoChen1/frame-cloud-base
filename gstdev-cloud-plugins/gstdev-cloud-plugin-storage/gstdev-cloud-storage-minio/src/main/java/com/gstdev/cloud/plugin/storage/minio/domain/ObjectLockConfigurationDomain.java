// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.minio.domain;

import com.gstdev.cloud.plugin.storage.minio.domain.base.BaseRetentionDomain;
import com.gstdev.cloud.plugin.storage.minio.enums.RetentionUnitEnums;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * <p>Description: Minio ObjectLockConfiguration 对应 Domain Object </p>
 *
 * @author : cc
 * @date : 2023/6/5 20:55
 */
@Schema(name = "存储桶保留设置域对象")
public class ObjectLockConfigurationDomain extends BaseRetentionDomain {

    @Schema(name = "保留周期")
    private RetentionUnitEnums unit;

    @Schema(name = "保留有效期")
    private Integer validity;

    public RetentionUnitEnums getUnit() {
        return unit;
    }

    public void setUnit(RetentionUnitEnums unit) {
        this.unit = unit;
    }

    public Integer getValidity() {
        return validity;
    }

    public void setValidity(Integer validity) {
        this.validity = validity;
    }
}
