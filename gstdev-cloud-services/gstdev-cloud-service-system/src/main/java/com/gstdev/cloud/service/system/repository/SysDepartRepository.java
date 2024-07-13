package com.gstdev.cloud.service.system.repository;


import com.gstdev.cloud.data.core.repository.BaseTreeRepository;
import com.gstdev.cloud.service.system.domain.entity.SysDepart;

public interface SysDepartRepository extends BaseTreeRepository<SysDepart, String> {
//    /**
//     * 父级主键查询
//     *
//     * @param parentId
//     * @return
//     */
//    List<SysDepart> findAllByParentIdOrderBySort(String parentId);
//
//    /**
//     * 通过parentid 和小于指定的排序查询
//     *
//     * @param parentId
//     * @param sort
//     * @return
//     */
//    List<SysDepart> findAllByParentIdAndSortLessThanOrderBySort(String parentId, int sort);
//
//    /**
//     * 通过parentid 和大于指定的排序查询
//     *
//     * @param parentId
//     * @param sort
//     * @return
//     */
//    List<SysDepart> findAllByParentIdAndSortGreaterThanOrderBySort(String parentId, int sort);
//
//    List<SysDepart> findByTenantId(String tenantId);
}
