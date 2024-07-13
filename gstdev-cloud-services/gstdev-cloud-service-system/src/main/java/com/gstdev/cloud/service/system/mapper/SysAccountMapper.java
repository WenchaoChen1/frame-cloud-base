// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by gstdev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.service.system.mapper;

import com.gstdev.cloud.data.core.mapper.BasePOJOMapper;
import com.gstdev.cloud.service.system.domain.base.account.AccountDto;
import com.gstdev.cloud.service.system.domain.base.account.AccountInsertInput;
import com.gstdev.cloud.service.system.domain.base.account.AccountUpdateInput;
import com.gstdev.cloud.service.system.domain.base.account.AccountVo;
import com.gstdev.cloud.service.system.domain.entity.SysAccount;
import com.gstdev.cloud.service.system.domain.pojo.rTenantMenu.TenantMenuMenuTreeDto;
import com.gstdev.cloud.service.system.domain.pojo.sysAccount.*;
import com.gstdev.cloud.service.system.domain.pojo.sysBusinessPermission.TenantBusinessPermissionTreeDto;
import com.gstdev.cloud.service.system.domain.pojo.sysAccount.AccountManageRoleTreeVo;
import com.gstdev.cloud.service.system.domain.pojo.sysRole.TenantRoleTreeDto;
import org.mapstruct.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
public interface SysAccountMapper extends BasePOJOMapper<SysAccount, AccountDto, AccountVo, AccountInsertInput, AccountUpdateInput> {
    SysAccount toEntity(InsertAccountManageInitializationIO insertAccountManageInitializationIO);

    SysAccount toEntity(InsertAccountManageIO insertAccountManageInput);

    AccountManageDetailVo toAccountManageDetailVo(SysAccount sysAccount);

    List<AccountManagePageVo> toAccountManagePageVo(List<SysAccount> sysAccount);

    default Page<AccountManagePageVo> toAccountManagePageVo(Page<SysAccount> page) {
        List<AccountManagePageVo> responses = this.toAccountManagePageVo(page.getContent());
        return new PageImpl(responses, page.getPageable(), page.getTotalElements());
    }

    void copy(UpdateAccountManageIO updateAccountManageIO, @MappingTarget SysAccount sysAccount);
    void copy(UpdateAccountSettingsDetailIO updateAccountSettingsDetailIO, @MappingTarget SysAccount sysAccount);

    List<SwitchUserAccountDetailVo> toSwitchUserAccountDetailVo(List<SwitchUserAccountDetailDto> byId);

    List<SwitchUserAccountDetailDto> toSwitchUserAccountDetailDto(List<SysAccount> allByUserId);

    List<AccountManageBusinessPermissionTreeVo>  toAccountManageBusinessPermissionTreeVo(List<TenantBusinessPermissionTreeDto> allTenantMenuMenuTree);

    List<AccountManageTenantMenuTreeVo> toAccountManageTenantMenuTreeVo(List<TenantMenuMenuTreeDto> allTenantMenuMenuTree);

    List<AccountManageRoleTreeVo> toAccountManageRoleTreeVo(List<TenantRoleTreeDto> allRoleMenuTree);

    List<AccountSettingsDetailVO> toAccountSettingsDetailVO(List<SysAccount> allByUserId);
}

