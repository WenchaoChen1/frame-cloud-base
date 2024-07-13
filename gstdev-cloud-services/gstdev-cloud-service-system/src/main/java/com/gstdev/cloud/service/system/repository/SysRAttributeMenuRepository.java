package com.gstdev.cloud.service.system.repository;

import com.gstdev.cloud.data.core.repository.BaseRepository;
import com.gstdev.cloud.service.system.domain.entity.SysRAttributeMenu;
import com.gstdev.cloud.service.system.domain.generator.SysRAttributeMenuEmbeddablePK;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Set;

@Transactional
public interface SysRAttributeMenuRepository extends BaseRepository<SysRAttributeMenu, SysRAttributeMenuEmbeddablePK> {

    List<SysRAttributeMenu> findAllByMenuId(String menu);

    List<SysRAttributeMenu> findAllByAttributeId(String attributeId);

    void deleteAllByMenuId(String menuId);

    void deleteAllByAttributeId(String attributeId);

    void deleteAllByMenuIdAndAttributeId(String menuId, String attributeId);

    void deleteAllByMenuIdIn(List<String> menuIds);

    void deleteAllByAttributeIdIn(List<String> attributeIds);

    void deleteAllByMenuIdAndAttributeIdIn(String menuId, List<String> attributeIds);

    void deleteAllByMenuIdInAndAttributeIdIn(List<String> menuIds, List<String> attributeIds);

}


