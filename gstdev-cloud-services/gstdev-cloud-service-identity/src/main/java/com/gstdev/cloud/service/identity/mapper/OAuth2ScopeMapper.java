// ====================================================
//
// This file is part of the Riching Cloud Platform.
//
// Create by Riching Tech <support@richingtech.com>
// Copyright (c) 2020-2025 richingtech.com
//
// ====================================================

package com.gstdev.cloud.service.identity.mapper;


import com.gstdev.cloud.service.identity.domain.entity.OAuth2Scope;
import com.gstdev.cloud.service.identity.domain.pojo.scope.*;
import org.mapstruct.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
public interface OAuth2ScopeMapper {

    List<ScopeManagePageVO> toScopeManagePageVO(List<OAuth2Scope> oAuth2Scope);

    default Page<ScopeManagePageVO> toScopeManagePageVO(Page<OAuth2Scope> page) {
        List<ScopeManagePageVO> responses = this.toScopeManagePageVO(page.getContent());
        return new PageImpl(responses, page.getPageable(), page.getTotalElements());
    }

    ScopeManageDetailVO toScopeManageDetailVO(OAuth2Scope oAuth2Scope);

    OAuth2Scope toEntity(InsertScopeManageIO insertScopeManageIO);

    void copy(UpdateScopeManageIO updateUserManageIO, @MappingTarget OAuth2Scope sysUser);
    void copy(ScopeManageAssignedPermissionIO scopeManageAssignedPermissionIO, @MappingTarget OAuth2Scope sysUser);
}

