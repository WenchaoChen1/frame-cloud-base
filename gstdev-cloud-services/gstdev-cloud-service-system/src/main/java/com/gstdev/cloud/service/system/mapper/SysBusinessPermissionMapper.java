package com.gstdev.cloud.service.system.mapper;

import com.gstdev.cloud.service.system.domain.entity.SysBusinessPermission;
import com.gstdev.cloud.service.system.domain.pojo.rTenantMenu.TenantMenuMenuTreeDto;
import com.gstdev.cloud.service.system.domain.pojo.sysBusinessPermission.*;
import com.gstdev.cloud.service.system.util.TreeUtils;
import org.mapstruct.*;

import java.util.Comparator;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
public interface SysBusinessPermissionMapper {

    SysBusinessPermission toEntity(InsertBusinessPermissionManageIO insertBusinessPermissionManageIO);

    BusinessPermissionManageDetailVo toBusinessPermissionManageDetailVo(SysBusinessPermission sysBusinessPermission);


    void copy(UpdateBusinessPermissionManageIO updateBusinessPermissionManageIO, @MappingTarget SysBusinessPermission sysBusinessPermission);



    List<BusinessPermissionManageTreeVo> toBusinessPermissionManageTreeVo(List<SysBusinessPermission> byPage);


    default List<BusinessPermissionManageTreeVo> toBusinessPermissionManageTreeVoToTree(List<SysBusinessPermission> byPage) {
        List<BusinessPermissionManageTreeVo> businessPermissionManageTreeVo = toBusinessPermissionManageTreeVo(byPage);
        return TreeUtils.buildTree(
                businessPermissionManageTreeVo,
                BusinessPermissionManageTreeVo::getBusinessPermissionId,
                BusinessPermissionManageTreeVo::getParentId,
                Comparator.comparingInt((BusinessPermissionManageTreeVo item) ->
                        item.getSort() != null ? item.getSort() : Integer.MAX_VALUE)
        );
    }

    List<BusinessPermissionManageTenantMenuTreeVo> toBusinessPermissionManageMenuTreeVo(List<TenantMenuMenuTreeDto> allTenantMenuMenuTree);

    List<TenantBusinessPermissionTreeDto> toTenantBusinessPermissionTreeDto(List<SysBusinessPermission> byPage);


    default List<TenantBusinessPermissionTreeDto> toTenantBusinessPermissionTreeDtoToTree(List<SysBusinessPermission> byPage) {
        List<TenantBusinessPermissionTreeDto> businessPermissionTreeDto = toTenantBusinessPermissionTreeDto(byPage);
        return TreeUtils.buildTree(
                businessPermissionTreeDto,
                TenantBusinessPermissionTreeDto::getBusinessPermissionId,
                TenantBusinessPermissionTreeDto::getParentId,
                Comparator.comparingInt((TenantBusinessPermissionTreeDto item) ->
                        item.getSort() != null ? item.getSort() : Integer.MAX_VALUE)
        );
    }

}
