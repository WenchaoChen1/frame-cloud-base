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
import com.gstdev.cloud.data.core.pojo.BaseTreeFindAllByQueryCriteria;
import lombok.Data;

import java.util.List;


@Data
public class TenantFindAllByQueryCriteria extends BaseTreeFindAllByQueryCriteria {

    private static final long serialVersionUID = 3163118978801722144L;

    @Query(blurry = "tenantName", type = Query.Type.IN)
    private String tenantName;
    //  @Query
    private String tenantId;
    //  @Query(blurry = "tenantId", type = Query.Type.IN)
    @Query(type = Query.Type.IN)
//    @Query(blurry = "id",type = Query.Type.EQUAL)
    private List<String> id;


    public List<String> getTenantIds() {
        return id;
    }

    public void setTenantIds(List<String> tenantIds) {
        this.id = tenantIds;
    }
}

