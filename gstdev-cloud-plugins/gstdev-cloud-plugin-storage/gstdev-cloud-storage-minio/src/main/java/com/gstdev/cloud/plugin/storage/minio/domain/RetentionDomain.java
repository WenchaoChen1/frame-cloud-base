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
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * <p>Description: 对象保留域对象 </p>
 *
 * @author : cc
 * @date : 2022/7/2 22:22
 */
@Schema(name = "对象保留设置域对象")
public class RetentionDomain extends BaseRetentionDomain {

    @Schema(name = "保留截止日期")
    private String retainUntilDate;

    public String getRetainUntilDate() {
        return retainUntilDate;
    }

    public void setRetainUntilDate(String retainUntilDate) {
        this.retainUntilDate = retainUntilDate;
    }
}
