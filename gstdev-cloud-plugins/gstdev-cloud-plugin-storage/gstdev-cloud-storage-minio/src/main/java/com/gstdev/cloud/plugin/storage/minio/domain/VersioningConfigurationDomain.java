// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.minio.domain;


import com.gstdev.cloud.base.definition.domain.base.Entity;

/**
 * <p>Description: Minio VersioningConfiguration 对应 Domain Object </p>
 *
 * @author : cc
 * @date : 2023/6/28 16:59
 */
public class VersioningConfigurationDomain implements Entity {

    private String status;

    private Boolean mfaDelete;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getMfaDelete() {
        return mfaDelete;
    }

    public void setMfaDelete(Boolean mfaDelete) {
        this.mfaDelete = mfaDelete;
    }
}
