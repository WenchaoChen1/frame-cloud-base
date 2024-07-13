// ====================================================
//
// This file is part of the CSCEC81 Cloud Platform.
//
// Create by CSCEC81 Technology <support@cscec81.com>
// Copyright (c) 2020-2021 cscec81.com
//
// ====================================================

package com.gstdev.cloud.service.system.domain.base.user;

import com.gstdev.cloud.data.core.enums.DataItemStatus;
import com.gstdev.cloud.data.core.pojo.BasePageQueryCriteria;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserPageQueryCriteria extends BasePageQueryCriteria implements Serializable {

    private static final long serialVersionUID = 3163118978801722144L;
    private DataItemStatus status;
}

