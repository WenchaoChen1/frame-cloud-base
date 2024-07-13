// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.service.system.service;

import com.gstdev.cloud.data.core.service.BaseService;
import com.gstdev.cloud.service.system.domain.entity.SysTenant;

import java.util.List;
import java.util.Set;


public interface SysTenantService extends BaseService<SysTenant, String> {
    List<SysTenant> findAllByIds(Set<String> tenantIds);
//
}

