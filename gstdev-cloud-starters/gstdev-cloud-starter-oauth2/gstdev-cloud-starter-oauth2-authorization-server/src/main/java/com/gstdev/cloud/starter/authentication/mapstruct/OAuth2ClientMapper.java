// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.oauth2.authentication.mapstruct;

import com.gstdev.cloud.oauth2.authentication.domain.OAuth2RegisteredClient;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface OAuth2ClientMapper {

    /**
     * 合并内容
     *
     * @param source 源
     * @param target 目标
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void merge(OAuth2RegisteredClient source, @MappingTarget OAuth2RegisteredClient target);
}
