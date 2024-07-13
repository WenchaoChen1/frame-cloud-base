package com.gstdev.cloud.service.system.service;

import com.gstdev.cloud.data.core.service.BaseService;
import com.gstdev.cloud.service.system.domain.entity.SysRAttributeMenu;
import com.gstdev.cloud.service.system.domain.generator.SysRAttributeMenuEmbeddablePK;

import java.util.Set;

public interface SysRAttributeMenuService extends BaseService<SysRAttributeMenu, SysRAttributeMenuEmbeddablePK> {


    void updateAttributeAssignedMenus(String attributeId, Set<String> menuIds);

    void updateMenuAssignedAttributes(String menuId, Set<String> attributeIds);

    Set<String> getAllAttributeIdByMenuId(String attributeId);

    Set<String> getAllMenuIdByAttributeId(String attributeId);
}
