// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by gstdev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.service.system.mapper;

import com.gstdev.cloud.data.core.mapper.BaseTreeMapper;
import com.gstdev.cloud.service.system.domain.base.depart.DepartDto;
import com.gstdev.cloud.service.system.domain.base.depart.DepartInsertInput;
import com.gstdev.cloud.service.system.domain.base.depart.DepartUpdateInput;
import com.gstdev.cloud.service.system.domain.base.depart.DepartVo;
import com.gstdev.cloud.service.system.domain.entity.SysDepart;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
public interface DepartVoMapper extends BaseTreeMapper<SysDepart, DepartDto, DepartVo, DepartInsertInput, DepartUpdateInput> {

}

