// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.service.system.service;


import cn.hutool.core.lang.UUID;
import com.gstdev.cloud.data.core.service.BaseServiceImpl;
import com.gstdev.cloud.service.system.domain.entity.SysTenant;
import com.gstdev.cloud.service.system.mapper.SysTenantMapper;
import com.gstdev.cloud.service.system.repository.SysTenantRepository;
import jakarta.annotation.Resource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Transactional(readOnly = true)
public class SysTenantServiceImpl extends BaseServiceImpl<SysTenant, String, SysTenantRepository> implements SysTenantService {

    @Resource
    private SysAccountService accountService;
    @Resource
    private SysTenantRepository tenantRepository;
    @Resource
    private SysTenantMapper tenantMapper;

    public SysTenantServiceImpl(SysTenantRepository tenantRepository, SysTenantMapper tenantMapper) {
        super(tenantRepository);
        this.tenantRepository = tenantRepository;
        this.tenantMapper = tenantMapper;
    }


    @Override
    public SysTenantRepository getRepository() {
        return tenantRepository;
    }


    @Override
    @Transactional
    public SysTenant insert(SysTenant tenant) {
        if (tenant.getTenantCode() == null) {
            tenant.setTenantCode(UUID.fastUUID().toString());
        }
        SysTenant save = super.insert(tenant);
        //新增租户的时候默认向组织里插入一条数据
//    DepartSaveInput departSaveInput = new DepartSaveInput();
//    departSaveInput.setName(save.getTenantName());
//    departSaveInput.setCode(save.getTenantCode());
//    departSaveInput.setParentId("1");
//    departSaveInput.setSort(1);
//    departSaveInput.setTenantId(save.getId());
//    DepartDto insert = systemDepartService.insert(departSaveInput);
//
        return save;
    }

    @Override
    public void deleteById(String id) {
        super.deleteById(id);
        accountService.deleteByTenantId(id);
    }


    @Override
    public List<SysTenant> findAllByIds(Set<String> tenantIds) {
        return getRepository().findAllById(tenantIds);
    }
}

