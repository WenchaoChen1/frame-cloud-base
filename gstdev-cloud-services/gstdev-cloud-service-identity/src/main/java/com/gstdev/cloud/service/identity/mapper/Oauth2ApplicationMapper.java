// ====================================================
//
// This file is part of the Riching Cloud Platform.
//
// Create by Riching Tech <support@richingtech.com>
// Copyright (c) 2020-2025 richingtech.com
//
// ====================================================

package com.gstdev.cloud.service.identity.mapper;


import com.gstdev.cloud.service.identity.domain.application.ApplicationManagePageVO;
import com.gstdev.cloud.service.identity.domain.application.InsertApplicationManageIO;
import com.gstdev.cloud.service.identity.domain.application.UpdateApplicationManageIO;
import com.gstdev.cloud.service.identity.domain.base.Oauth2ApplicationVo;
import com.gstdev.cloud.service.identity.entity.OAuth2Application;
import org.mapstruct.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
public interface Oauth2ApplicationMapper  {

    List<ApplicationManagePageVO> toApplicationManagePageVO(List<OAuth2Application> oAuth2Application);

    default Page<ApplicationManagePageVO> toApplicationManagePageVO(Page<OAuth2Application> page) {
        List<ApplicationManagePageVO> responses = this.toApplicationManagePageVO(page.getContent());
        return new PageImpl(responses, page.getPageable(), page.getTotalElements());
    }

    Oauth2ApplicationVo toVo(OAuth2Application oAuth2Application);

    OAuth2Application toEntity(InsertApplicationManageIO insertApplicationManageIO);

    void copy(UpdateApplicationManageIO updateUserManageIO, @MappingTarget OAuth2Application sysUser);
}

