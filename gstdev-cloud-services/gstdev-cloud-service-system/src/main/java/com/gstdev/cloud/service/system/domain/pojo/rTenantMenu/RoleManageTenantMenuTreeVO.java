package com.gstdev.cloud.service.system.domain.pojo.rTenantMenu;

import com.gstdev.cloud.service.system.util.TreeNode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleManageTenantMenuTreeVO extends TreeNode<String, RoleManageTenantMenuTreeVO> {

    private String id;
    private String menuName;
    private String name;
    private String parentId;
    private String path;
    private Integer sort;
}
