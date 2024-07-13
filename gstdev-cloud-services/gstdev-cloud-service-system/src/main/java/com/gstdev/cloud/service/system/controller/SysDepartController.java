package com.gstdev.cloud.service.system.controller;//// ====================================================
////
//// This file is part of the GstDev Cloud Platform.
////
//// Create by GstDev Cloud <support@gstdev.com>
//// Copyright (c) 2022-2025 gstdev.com
////
//// ====================================================
//
//package com.gstdev.cloud.service.system.controller;
//
//import com.gstdev.cloud.base.definition.domain.Result;
//import com.gstdev.cloud.rest.core.controller.TreeController;
//import com.gstdev.cloud.service.system.domain.base.depart.*;
//import com.gstdev.cloud.service.system.domain.entity.SysDepart;
//import com.gstdev.cloud.service.system.mapper.DepartVoMapper;
//import com.gstdev.cloud.service.system.service.SysDepartService;
//import io.swagger.v3.oas.annotations.Operation;
//import jakarta.annotation.Resource;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//
//@RestController
//@RequestMapping("/v1/depart")
//public class SysDepartController implements TreeController<SysDepart, String, DepartVo, DepartDto, DepartInsertInput, DepartUpdateInput, DepartPageQueryCriteria, DepartFindAllByQueryCriteria> {
//
//    @Resource
//    private SysDepartService departService;
//
//    @Resource
//    private DepartVoMapper departVoMapper;
//
////    public SysDepartController(SysDepartService departService, DepartVoMapper departVoMapper) {
////        this.departService = departService;
////        this.departVoMapper = departVoMapper;
////    }
//
//    @Override
//    public SysDepartService getService() {
//        return departService;
//    }
//
//    @Override
//    public DepartVoMapper getMapper() {
//        return departVoMapper;
//    }
//
//
//    @GetMapping("/get-all-depart-to-tree")
//    @Operation(summary = "获取指定租户下的depart所有数据，返回树状结构")
//    public Result<List<DepartVo>> findAllByQueryCriteriaToTree(@RequestParam("tenantId") String tenantId) {
//        DepartFindAllByQueryCriteria departFindAllByQueryCriteria = new DepartFindAllByQueryCriteria();
//        departFindAllByQueryCriteria.setTenantId(tenantId);
//        return findAllByQueryCriteriaToResultToTree(departFindAllByQueryCriteria);
//    }
//
//    @GetMapping("/get-by-id")
//    @Operation(summary = "根据id获取实体数据")
//    public Result<DepartVo> getById(String id) {
//        return findByIdToResult(id);
//    }
//
//    @PostMapping
//    @Operation(summary = "新增一条数据")
//    public Result<DepartVo> insert(@RequestBody DepartInsertInput departInsertInput) {
//        return insertToResult(departInsertInput);
//    }
//
//    @PutMapping
//    @Operation(summary = "修改一条数据")
//    public Result<DepartVo> update(@RequestBody DepartUpdateInput departUpdateInput) {
//        return updateToResult(departUpdateInput);
//    }
//
//    @Operation(summary = "")
//    @DeleteMapping
//    public Result<DepartVo> deleteById(String id) {
//        return deleteByIdToResult(id);
//    }
//
//    /*------------------------------------------以上是系统访问控制代码--------------------------------------------*/
//
//}
