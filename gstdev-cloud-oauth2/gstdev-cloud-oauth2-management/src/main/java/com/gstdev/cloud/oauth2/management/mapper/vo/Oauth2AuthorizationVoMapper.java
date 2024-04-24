// ====================================================
//
// This file is part of the Riching Cloud Platform.
//
// Create by Riching Tech <support@richingtech.com>
// Copyright (c) 2020-2025 richingtech.com
//
// ====================================================

package com.gstdev.cloud.oauth2.management.mapper.vo;

import com.gstdev.cloud.data.core.mapper.BaseVoMapper;
import com.gstdev.cloud.oauth2.data.jpa.entity.DefaultOauth2Authorization;
import com.gstdev.cloud.oauth2.management.domain.base.Oauth2AuthorizationDto;
import com.gstdev.cloud.oauth2.management.domain.base.Oauth2AuthorizationVo;
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
public interface Oauth2AuthorizationVoMapper extends BaseVoMapper<Oauth2AuthorizationVo, Oauth2AuthorizationDto> {
    Oauth2AuthorizationVo entityToVo(DefaultOauth2Authorization entity);
    List<Oauth2AuthorizationVo> entityToVo(List<DefaultOauth2Authorization> entity);
    default Page<Oauth2AuthorizationVo> entityToVo(Page<DefaultOauth2Authorization> page) {
        List<Oauth2AuthorizationVo> responses = this.entityToVo(page.getContent());
        return new PageImpl(responses, page.getPageable(), page.getTotalElements());
    }
}

