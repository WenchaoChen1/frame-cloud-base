// ====================================================
//
// This file is part of the CSCEC81 Cloud Platform.
//
// Create by CSCEC81 Technology <support@cscec81.com>
// Copyright (c) 2020-2021 cscec81.com
//
// ====================================================

package com.gstdev.cloud.service.system.domain.base.rRoleRTenantMenu;

import com.gstdev.cloud.data.core.annotations.Query;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Deprecated
@Getter
@Setter
public class RRoleRTenantMenuFindAllByQueryCriteria implements Serializable {

    private static final long serialVersionUID = 3163118978801722144L;
    //  @Query(type = Query.Type.IN)
// private Set<String> nailedType;
    @Query
    private String tenantId;
}

