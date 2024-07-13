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
import com.gstdev.cloud.service.system.domain.base.user.UserDto;
import com.gstdev.cloud.service.system.domain.base.user.UserVo;
import com.gstdev.cloud.service.system.domain.entity.SysAccount;
import com.gstdev.cloud.service.system.domain.entity.SysUser;
import com.gstdev.cloud.service.system.domain.pojo.sysUser.*;
import com.gstdev.cloud.service.system.domain.vo.user.AccountListDto;
import com.gstdev.cloud.service.system.domain.vo.user.UserInsertInput;
import com.gstdev.cloud.service.system.domain.vo.user.UserUpdateInput;
import org.mapstruct.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
public interface SysUserMapper extends BasePOJOMapper<SysUser, UserDto, UserVo, UserInsertInput, UserUpdateInput> {

    SysUser toEntity(InsertUserManageIO insertUserManageInput);
    UserSettingsDetailVO toUserSettingsDetailVo(SysUser sysUser);

    SysUser toEntity(InsertUserManageInitializationIO insertUserManageInitializationIO);

    UserManageDetailVo toUserManageDetailVo(SysUser sysUser);

    List<UserManagePageVo> toUserManagePageVo(List<SysUser> sysUser);

    default Page<UserManagePageVo> toUserManagePageVo(Page<SysUser> page) {
        List<UserManagePageVo> responses = this.toUserManagePageVo(page.getContent());
        return new PageImpl(responses, page.getPageable(), page.getTotalElements());
    }

    void copy(UpdateUserManageIO updateUserManageIO, @MappingTarget SysUser sysUser);
    void copy(UpdateUserSettingsDetailIO updateUserSettingsDetailIO, @MappingTarget SysUser sysUser);

    List<AccountListDto> accountListToDto(List<SysAccount> accountList);
}

