// ====================================================
//
// This file is part of the Riching Cloud Platform.
//
// Create by Riching Tech <support@richingtech.com>
// Copyright (c) 2020-2025 richingtech.com
//
// ====================================================

package com.gstdev.cloud.service.identity.mapper;


import com.gstdev.cloud.service.identity.domain.application.InsertApplicationManageIO;
import com.gstdev.cloud.service.identity.domain.application.UpdateApplicationManageIO;
import com.gstdev.cloud.service.identity.domain.base.Oauth2ApplicationVo;
import com.gstdev.cloud.service.identity.entity.OAuth2Application;
import org.mapstruct.*;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
public interface Oauth2ApplicationMapper  {
    Oauth2ApplicationVo toVo(OAuth2Application insertUserManageInitializationIO);
    OAuth2Application toEntity(InsertApplicationManageIO insertUserManageInitializationIO);

    void copy(UpdateApplicationManageIO updateUserManageIO, @MappingTarget OAuth2Application sysUser);
}

