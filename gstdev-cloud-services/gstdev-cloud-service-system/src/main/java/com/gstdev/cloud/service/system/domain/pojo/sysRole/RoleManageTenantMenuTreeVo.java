package com.gstdev.cloud.service.system.domain.pojo.sysRole;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RoleManageTenantMenuTreeVo {
    private String menuId;
    private String tenantMenuId;
    private String parentId;
    private Integer sort;

    private String menuName;
    private String name;
    private String path;
    private List<RoleManageTenantMenuTreeVo> children;
}
