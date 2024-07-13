package com.gstdev.cloud.service.system.repository;


import com.gstdev.cloud.data.core.repository.BaseRepository;
import com.gstdev.cloud.data.core.repository.BaseTreeRepository;
import com.gstdev.cloud.service.system.domain.entity.SysMenu;

import java.util.List;

public interface SysMenuRepository extends BaseRepository<SysMenu, String> {
    /**
     * 父级主键查询
     *
     * @param parentId
     * @return
     */
    List<SysMenu> findAllByParentIdOrderBySort(String parentId);

    /**
     * 通过parentid 和小于指定的排序查询
     *
     * @param parentId
     * @param sort
     * @return
     */
    List<SysMenu> findAllByParentIdAndSortLessThanOrderBySort(String parentId, int sort);

    /**
     * 通过parentid 和大于指定的排序查询
     *
     * @param parentId
     * @param sort
     * @return
     */
    List<SysMenu> findAllByParentIdAndSortGreaterThanOrderBySort(String parentId, int sort);


    List<SysMenu> findByStatusAndParentIdAndType(Integer status, String parentId, Integer type);
}
