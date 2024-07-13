package com.gstdev.cloud.service.system.controller;

import com.gstdev.cloud.base.definition.domain.Result;
import com.gstdev.cloud.data.core.utils.BasePage;
import com.gstdev.cloud.data.core.utils.QueryUtils;
import com.gstdev.cloud.rest.core.controller.ResultController;
import com.gstdev.cloud.service.system.domain.entity.SysAttribute;
import com.gstdev.cloud.service.system.domain.pojo.sysAttribute.*;
import com.gstdev.cloud.service.system.mapper.SysAttributeMapper;
import com.gstdev.cloud.service.system.service.SysAttributeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

/**
 * <p>Description: SysAttributeController </p>
 */

@RestController
@RequestMapping("/v1/attribute")
public class SysAttributeController implements ResultController {

    @Resource
    private SysAttributeService sysAttributeService;

    @Resource
    private SysAttributeMapper sysAttributeMapper;

    public SysAttributeService getService() {
        return sysAttributeService;
    }

    public SysAttributeMapper getMapper() {
        return sysAttributeMapper;
    }

    // ********************************* Attribute Manage *****************************************

    @Tags({
        @Tag(name = "Menu Manage Assigned Attribute"),
    })
    @PostMapping("/get-menu-manage-attribute-page")
    @Operation(summary = "get-menu-manage-attribute-page")
    public Result<Map<String, Object>> getMenuManageAttributePage(@RequestBody MenuManageAttributePageQO attributePageQO) {
        return this.result(this.getMapper().toMenuManageAttributePageVo(this.getService().findByPage((root, criteriaQuery, criteriaBuilder) -> QueryUtils.getPredicate(root, attributePageQO, criteriaBuilder), attributePageQO.getPage())));
    }
//    @Tags({
//        @Tag(name = "attributeInit"),
//    })
//    @GetMapping("/attributeInit")
//    @Operation(summary = "attributeInit")
//    public Result<Map<String, Object>> attributeInit() {
//        this.getService().attributeInit();
//        return this.result();
//    }

    @Tags({@Tag(name = "Attribute Manage"),
    })
    @GetMapping("/get-attribute-manage-page")
    @Operation(summary = "get-attribute-manage-page")
    public Result<Map<String, Object>> getAttributeManagePage(AttributeManagePageQO sysAttributeAttributeManageQO, BasePage pageable) {
        return this.result(this.getMapper().toAttributeManagePageVo(this.getService().findByPage((root, criteriaQuery, criteriaBuilder) -> QueryUtils.getPredicate(root, sysAttributeAttributeManageQO, criteriaBuilder), pageable)));
    }

    @Tag(name = "Attribute Manage")
    @GetMapping("/get-attribute-manage-detail/{id}")
    @Operation(summary = "get-attribute-manage-detail")
    public Result<AttributeManageDetailVo> getAttributeManageDetail(@NotBlank @PathVariable String id) {
        return result(getMapper().toAttributeManageDetailVo(getService().findById(id)));
    }

//    @Tag(name = "Attribute Manage")
//    @PostMapping("/insert-attribute-manage")
//    @Operation(summary = "insert-attribute-manage")
//    public Result<String> insertAttributeManage(@RequestBody @Validated InsertAttributeManageIO insertAttributeManageIO) {
//        this.getService().insertAndUpdate(attributeVoMapper.toEntity(insertAttributeManageIO));
//        return Result.success();
//    }

    @Tag(name = "Attribute Manage")
    @PutMapping("/update-attribute-manage")
    @Operation(summary = "update-attribute-manage")
    public Result<String> updateAttributeManage(@RequestBody @Validated UpdateAttributeManageIO updateAttributeManageIO) {
        SysAttribute sysAttribute = this.getService().findById(updateAttributeManageIO.getAttributeId());
        getMapper().copy(updateAttributeManageIO, sysAttribute);
        this.getService().insertAndUpdate(sysAttribute);
        return Result.success();
    }

    @Tag(name = "Attribute Manage")
    @PostMapping("/update-attribute-manage-assigned-permission")
    @Operation(summary = "update-attribute-manage-assigned-permission")
    public Result<String> updateAttributeManageAssignedPermission(@RequestBody AttributeManageAssignedPermissionIO attributeManageAssignedPermissionIO) {
        this.getService().updateAttributeManageAssignedPermission(attributeManageAssignedPermissionIO);
        return Result.success();
    }

    @Tag(name = "Attribute Manage")
    @GetMapping("/get-attribute-permission-id-by-attribute-id/{id}")
    @Operation(summary = "get-attribute-permission-id-by-attribute-id")
    public Result<Set<String>> getAttributePermissionIdByAttributeId(@PathVariable String id) {
        return result(this.getService().getAttributePermissionIdByAttributeId(id));
    }

//    @Tag(name = "Attribute Manage")
//    @Operation(summary = "delete-attribute-manage")
//    @DeleteMapping("/delete-attribute-manage/{id}")
//    public Result<String> deleteAttributeManage(@PathVariable String id) {
//        getService().deleteById(id);
//        return Result.success();
//    }
//
//    @Tag(name = "Attribute Manage")
//    @Operation(summary = "/delete-all-attribute-manage")
//    @DeleteMapping("/delete-all-attribute-manage")
//    public Result<String> deleteAllAttributeManage(List<String> id) {
//        getService().deleteAllById(id);
//        return Result.success();
//    }
}
