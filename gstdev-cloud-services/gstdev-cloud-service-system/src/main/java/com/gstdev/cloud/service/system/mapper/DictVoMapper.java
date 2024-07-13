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
import com.gstdev.cloud.service.system.domain.base.dict.DictDto;
import com.gstdev.cloud.service.system.domain.base.dict.DictInsertInput;
import com.gstdev.cloud.service.system.domain.base.dict.DictUpdateInput;
import com.gstdev.cloud.service.system.domain.base.dict.DictVo;
import com.gstdev.cloud.service.system.domain.entity.SysDict;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
public interface DictVoMapper extends BaseTreeMapper<SysDict, DictDto, DictVo, DictInsertInput, DictUpdateInput> {

}

