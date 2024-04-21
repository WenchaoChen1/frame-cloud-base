// ====================================================
//
// This file is part of the Riching Cloud Platform.
//
// Create by Riching Tech <support@richingtech.com>
// Copyright (c) 2020-2025 richingtech.com
//
// ====================================================

package com.gstdev.cloud.oauth2.management.mapper.vo;

import com.gstdev.cloud.data.core.mapper.AbstractMapper;
import com.gstdev.cloud.oauth2.management.domain.base.Oauth2AuthorizationDto;
import com.gstdev.cloud.oauth2.management.domain.base.Oauth2AuthorizationVo;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
public interface Oauth2AuthorizationVoMapper extends AbstractMapper<Oauth2AuthorizationVo, Oauth2AuthorizationDto> {

}

