package com.gstdev.cloud.service.system.repository;


import com.gstdev.cloud.data.core.repository.BaseTreeRepository;
import com.gstdev.cloud.service.system.domain.entity.SysDict;


public interface SysDictRepository extends BaseTreeRepository<SysDict, String> {
//    /**
//     * 父级主键查询
//     *
//     * @param parentId
//     * @return
//     */
//    List<SysDict> findAllByParentIdOrderBySort(String parentId);
//
//    /**
//     * 通过parentid 和小于指定的排序查询
//     *
//     * @param parentId
//     * @param sort
//     * @return
//     */
//    List<SysDict> findAllByParentIdAndSortLessThanOrderBySort(String parentId, int sort);
//
//    /**
//     * 通过parentid 和大于指定的排序查询
//     *
//     * @param parentId
//     * @param sort
//     * @return
//     */
//    List<SysDict> findAllByParentIdAndSortGreaterThanOrderBySort(String parentId, int sort);
//
//    /**
//     * 通过code查询
//     *
//     * @param code
//     * @return
//     */
//    List<SysDict> findByCode(String code);
}
