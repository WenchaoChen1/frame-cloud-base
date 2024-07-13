package com.gstdev.cloud.service.system.mapper;

import com.gstdev.cloud.data.core.mapper.BaseDtoMapper;
import com.gstdev.cloud.service.system.domain.base.SysPermission.SysPermissionDto;
import com.gstdev.cloud.service.system.domain.base.SysPermission.SysPermissionVo;
import com.gstdev.cloud.service.system.domain.entity.SysPermission;
import com.gstdev.cloud.service.system.domain.pojo.sysPermission.InsertPermissionManageIO;
import com.gstdev.cloud.service.system.domain.pojo.sysPermission.PermissionManageDetailVo;
import com.gstdev.cloud.service.system.domain.pojo.sysPermission.PermissionManagePageVo;
import com.gstdev.cloud.service.system.domain.pojo.sysPermission.UpdatePermissionManageIO;
import org.mapstruct.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
public interface SysPermissionMapper extends BaseDtoMapper<SysPermission, SysPermissionDto> {
    List<SysPermissionVo> entityToVo(List<SysPermission> entity);

    SysPermission toEntity(InsertPermissionManageIO insertPermissionManageIO);

    SysPermissionVo toVo(SysPermission entity);


    PermissionManageDetailVo toPermissionManageDetailVo(SysPermission sysPermission);

    List<PermissionManagePageVo> toPermissionManagePageVo(List<SysPermission> sysPermission);

    default Page<PermissionManagePageVo> toPermissionManagePageVo(Page<SysPermission> page) {
        List<PermissionManagePageVo> responses = this.toPermissionManagePageVo(page.getContent());
        return new PageImpl(responses, page.getPageable(), page.getTotalElements());
    }

    void copy(UpdatePermissionManageIO updatePermissionManageIO, @MappingTarget SysPermission sysPermission);

}
