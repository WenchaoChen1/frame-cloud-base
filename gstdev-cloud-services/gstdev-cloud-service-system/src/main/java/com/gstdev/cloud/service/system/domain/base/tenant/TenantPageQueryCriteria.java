// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.service.system.domain.base.tenant;

import com.gstdev.cloud.data.core.annotations.Query;
import com.gstdev.cloud.data.core.pojo.BaseTreePageQueryCriteria;
import lombok.Data;


@Data
public class TenantPageQueryCriteria extends BaseTreePageQueryCriteria {

    private static final long serialVersionUID = 3163118978801722144L;

    @Query(blurry = "tenantName", type = Query.Type.IN)
    private String tenantName;

}

