// ====================================================
//
// This file is part of the CSCEC81 Cloud Platform.
//
// Create by CSCEC81 Technology <support@cscec81.com>
// Copyright (c) 2020-2021 cscec81.com
//
// ====================================================

package com.gstdev.cloud.oauth2.management.domain.base;

import lombok.Data;

import java.io.Serializable;
import java.util.Set;


@Data
public class Oauth2AuthorizationPageQueryCriteria implements Serializable {

    private static final long serialVersionUID = 3163118978801722144L;
//  @Query(type = Query.Type.IN)
// private Set<String> nailedType;
}

