package com.gstdev.cloud.service.system.domain.pojo.sysBusinessPermission;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BusinessPermissionManageTenantMenuTreeVo {
    private String menuId;
    private String tenantMenuId;
    private String parentId;
    private Integer sort;

    private String menuName;
    private String name;
    private String path;
    private List<BusinessPermissionManageTenantMenuTreeVo> children;
}
