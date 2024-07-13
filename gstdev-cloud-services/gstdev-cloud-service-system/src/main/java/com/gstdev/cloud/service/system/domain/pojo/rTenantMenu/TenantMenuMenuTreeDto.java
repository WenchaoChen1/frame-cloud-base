package com.gstdev.cloud.service.system.domain.pojo.rTenantMenu;

import com.gstdev.cloud.data.core.enums.DataItemStatus;
import com.gstdev.cloud.service.system.domain.enums.SysMenuLocation;
import com.gstdev.cloud.service.system.domain.enums.SysMenuType;
import com.gstdev.cloud.service.system.util.TreeNode;
import lombok.Getter;
import lombok.Setter;

@Getter
public class TenantMenuMenuTreeDto extends TreeNode<String, TenantMenuMenuTreeDto> {
    private String tenantMenuId;
    private String menuId;
    private String parentId;
    private Integer sort;
    private String menuName;
    private String name;
    private String path;
    private SysMenuDto menu;


    public void setTenantMenuId(String tenantMenuId) {
        this.tenantMenuId = tenantMenuId;
    }

    public void setMenu(SysMenuDto menu) {
        this.menu = menu;
        this.menuId = menu.getId();
        this.parentId = menu.getParentId();
        this.menuName = menu.getMenuName();
        this.name = menu.getName();
        this.path = menu.getPath();
        this.sort = menu.getSort();
        super.setId(menu.getId());
    }

    @Setter
    @Getter
    public static class SysMenuDto {
        private String id;
        private String parentId;
        private String menuName;
        private String code;
        private String name;
        private String path;
        private String icon;
        private Integer sort;
        private String description;
        private SysMenuType type;
        private SysMenuLocation location;
        private DataItemStatus status;
    }

}
