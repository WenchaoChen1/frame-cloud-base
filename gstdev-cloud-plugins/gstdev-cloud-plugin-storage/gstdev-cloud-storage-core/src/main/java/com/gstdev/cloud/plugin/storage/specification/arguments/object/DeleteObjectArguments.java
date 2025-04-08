// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.specification.arguments.object;

import com.gstdev.cloud.plugin.storage.specification.arguments.base.ObjectVersionArguments;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * <p>Description: 删除对象请求参数实体 </p>
 *
 * @author : cc
 * @date : 2023/8/12 10:49
 */
@Schema(name = "删除对象请求参数实体", title = "删除对象请求参数实体")
public class DeleteObjectArguments extends ObjectVersionArguments {

    @Schema(name = "使用治理模式进行删除", description = "治理模式用户不能覆盖或删除对象版本或更改其锁定设置，可通过设置该参数进行强制操作")
    private Boolean bypassGovernanceMode;

    public Boolean getBypassGovernanceMode() {
        return bypassGovernanceMode;
    }

    public void setBypassGovernanceMode(Boolean bypassGovernanceMode) {
        this.bypassGovernanceMode = bypassGovernanceMode;
    }

}
