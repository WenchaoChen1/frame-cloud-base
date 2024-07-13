// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by gstdev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.service.system.mapper;

import com.gstdev.cloud.service.system.domain.entity.SysAttribute;
import com.gstdev.cloud.service.system.domain.pojo.sysAttribute.AttributeManageDetailVo;
import com.gstdev.cloud.service.system.domain.pojo.sysAttribute.AttributeManagePageVo;
import com.gstdev.cloud.service.system.domain.pojo.sysAttribute.MenuManageAttributePageVo;
import com.gstdev.cloud.service.system.domain.pojo.sysAttribute.UpdateAttributeManageIO;
import org.mapstruct.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
public interface SysAttributeMapper {


    AttributeManageDetailVo toAttributeManageDetailVo(SysAttribute sysAttribute);

    List<AttributeManagePageVo> toAttributeManagePageVo(List<SysAttribute> sysAttribute);

    default Page<AttributeManagePageVo> toAttributeManagePageVo(Page<SysAttribute> page) {
        List<AttributeManagePageVo> responses = this.toAttributeManagePageVo(page.getContent());
        return new PageImpl(responses, page.getPageable(), page.getTotalElements());
    }
    List<MenuManageAttributePageVo> toMenuManageAttributePageVo(List<SysAttribute> sysAttribute);

    default Page<MenuManageAttributePageVo> toMenuManageAttributePageVo(Page<SysAttribute> page) {
        List<MenuManageAttributePageVo> responses = this.toMenuManageAttributePageVo(page.getContent());
        return new PageImpl(responses, page.getPageable(), page.getTotalElements());
    }

    void copy(UpdateAttributeManageIO updateAttributeManageIO, @MappingTarget SysAttribute sysAttribute);

}

