// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.specification.domain.base;

import com.google.common.base.MoreObjects;
import com.gstdev.cloud.base.definition.domain.base.Entity;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * <p>Description: 所有者基础属性实体 </p>
 *
 * @author : cc
 * @date : 2023/7/27 15:43
 */
@Schema(title = "所有者")
public class OwnerDomain implements Entity {

    /**
     * 所有者 ID
     */
    @Schema(name = "所有者 ID")
    private String id;

    /**
     * 所有者显示名称
     */
    @Schema(name = "所有者显示名称")
    private String displayName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("displayName", displayName)
                .toString();
    }
}
