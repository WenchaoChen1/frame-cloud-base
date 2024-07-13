package com.gstdev.cloud.service.system.service;

import com.gstdev.cloud.data.core.service.BaseServiceImpl;
import com.gstdev.cloud.service.system.domain.entity.SysRAttributeMenu;
import com.gstdev.cloud.service.system.domain.generator.SysRAttributeMenuEmbeddablePK;
import com.gstdev.cloud.service.system.repository.SysRAttributeMenuRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Set;

@Transactional(readOnly = true)
public class SysRAttributeMenuServiceImpl extends BaseServiceImpl<SysRAttributeMenu, SysRAttributeMenuEmbeddablePK, SysRAttributeMenuRepository> implements SysRAttributeMenuService {


    public SysRAttributeMenuServiceImpl(SysRAttributeMenuRepository repository) {
        super(repository);
    }

    @Override
    @Transactional
    public void updateAttributeAssignedMenus(String attributeId, Set<String> menuIds) {
        getRepository().deleteAllByAttributeId(attributeId);
        getRepository().saveAllAndFlush(toEntityList(attributeId, menuIds));
    }

    @Override
    @Transactional
    public void updateMenuAssignedAttributes(String menuId, Set<String> attributeIds) {
        getRepository().deleteAllByMenuId(menuId);
        getRepository().saveAllAndFlush(toEntityList(attributeIds, menuId));
    }

    @Override
    public Set<String> getAllAttributeIdByMenuId(String menuId){
        return Set.of(getRepository().findAllByMenuId(menuId).stream().map(SysRAttributeMenu::getAttributeId).toArray(String[]::new));
    }

    @Override
    public Set<String> getAllMenuIdByAttributeId(String attributeId) {
        return Set.of(getRepository().findAllByAttributeId(attributeId).stream().map(SysRAttributeMenu::getMenuId).toArray(String[]::new));
    }
    List<SysRAttributeMenu> toEntityList(Set<String> attributeIds, String menuId) {
        return attributeIds.stream().map(attributeId -> {
            SysRAttributeMenu sysRAttributeMenu = new SysRAttributeMenu();
            sysRAttributeMenu.setMenuId(menuId);
            sysRAttributeMenu.setAttributeId(attributeId);
            return sysRAttributeMenu;
        }).toList();
    }
    List<SysRAttributeMenu> toEntityList(String attributeId, Set<String> menuIds) {
        return menuIds.stream().map(menuId -> {
            SysRAttributeMenu sysRAttributeMenu = new SysRAttributeMenu();
            sysRAttributeMenu.setMenuId(menuId);
            sysRAttributeMenu.setAttributeId(attributeId);
            return sysRAttributeMenu;
        }).toList();
    }
}
