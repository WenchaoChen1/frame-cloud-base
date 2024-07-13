// ====================================================
//
// This file is part of the CSCEC81 Cloud Platform.
//
// Create by CSCEC81 Technology <support@cscec81.com>
// Copyright (c) 2020-2021 cscec81.com
//
// ====================================================

package com.gstdev.cloud.service.system.domain.base.rTenantMenu;

import com.gstdev.cloud.data.core.annotations.Query;
import com.gstdev.cloud.data.core.pojo.BaseFindAllByQueryCriteria;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class RTenantMenuFindAllByQueryCriteria extends BaseFindAllByQueryCriteria implements Serializable {

    private static final long serialVersionUID = 3163118978801722144L;

    @Query
    private String tenantId;
}

