// ====================================================
//
// This file is part of the CSCEC81 Cloud Platform.
//
// Create by CSCEC81 Technology <support@cscec81.com>
// Copyright (c) 2020-2021 cscec81.com
//
// ====================================================

package com.gstdev.cloud.service.system.domain.base.account;

import com.gstdev.cloud.data.core.annotations.Query;
import com.gstdev.cloud.data.core.pojo.BasePageQueryCriteria;
import lombok.Data;

import java.io.Serializable;

@Data
public class AccountPageQueryCriteria extends BasePageQueryCriteria implements Serializable {

    private static final long serialVersionUID = 3163118978801722144L;
    @Query
    private String tenantId;
}

