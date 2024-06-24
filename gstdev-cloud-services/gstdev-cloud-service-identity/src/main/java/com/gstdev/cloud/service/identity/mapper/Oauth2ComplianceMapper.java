// ====================================================
//
// This file is part of the Riching Cloud Platform.
//
// Create by Riching Tech <support@richingtech.com>
// Copyright (c) 2020-2025 richingtech.com
//
// ====================================================

package com.gstdev.cloud.service.identity.mapper;


import com.gstdev.cloud.service.identity.domain.entity.OAuth2Compliance;
import com.gstdev.cloud.service.identity.domain.pojo.compliance.ComplianceManagePageVO;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
public interface Oauth2ComplianceMapper {
    List<ComplianceManagePageVO> toComplianceManagePageVO(List<OAuth2Compliance> oAuth2Compliance);

    default Page<ComplianceManagePageVO> toComplianceManagePageVO(Page<OAuth2Compliance> page) {
        List<ComplianceManagePageVO> responses = this.toComplianceManagePageVO(page.getContent());
        return new PageImpl(responses, page.getPageable(), page.getTotalElements());
    }

}

