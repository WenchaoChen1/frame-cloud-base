package com.gstdev.cloud.service.system.controller;

import com.gstdev.cloud.base.definition.domain.Result;
import com.gstdev.cloud.rest.core.controller.ResultController;
import com.gstdev.cloud.service.system.domain.pojo.sysRAttributeMenu.UpdateAttributeAssignedMenusIO;
import com.gstdev.cloud.service.system.domain.pojo.sysRAttributeMenu.UpdateMenuAssignedAttributesIO;
import com.gstdev.cloud.service.system.service.SysRAttributeMenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/v1/attribute-menu")
public class SysRAttributeMenuController implements ResultController {

    @Resource
    private SysRAttributeMenuService accountService;

    public SysRAttributeMenuService getService() {
        return accountService;
    }

    @Tag(name = "Attribute Menu Manage")
    @PostMapping("/update-attribute-assigned-menus")
    @Operation(summary = "update-attribute-assigned-menus")
    public Result<String> updateAttributeAssignedMenus(@RequestBody UpdateAttributeAssignedMenusIO updateAttributeAssignedMenusIO) {
        this.getService().updateAttributeAssignedMenus(updateAttributeAssignedMenusIO.getAttributeId(), updateAttributeAssignedMenusIO.getMenuIds());
        return Result.success();
    }

    @Tags({
        @Tag(name = "Menu Manage Assigned Attribute"),
    })
    @PostMapping("/update-menu-assigned-attribute")
    @Operation(summary = "update-menu-assigned-attribute")
    public Result<String> updateMenuAssignedAttributes(@RequestBody UpdateMenuAssignedAttributesIO updateMenuAssignedAttributesIO) {
        this.getService().updateMenuAssignedAttributes(updateMenuAssignedAttributesIO.getMenuId(), updateMenuAssignedAttributesIO.getAttributeIds());
        return Result.success();
    }

    @GetMapping("/get-all-menu-id-by-attribute-id/{attributeId}")
    @Operation(summary = "get-all-menu-id-by-attribute-id")
    public Result<Set<String>> getAllMenuIdByAttributeId(@PathVariable String attributeId) {
        return result(getService().getAllMenuIdByAttributeId(attributeId));
    }

    @Tags({
        @Tag(name = "Menu Manage Assigned Attribute"),
    })
    @GetMapping("/get-all-attribute-id-by-menu-id/{attributeId}")
    @Operation(summary = "get-all-attribute-id-by-menu-id")
    public Result<Set<String>> getAllAttributeIdByMenuId(@PathVariable String attributeId) {
        return result(getService().getAllAttributeIdByMenuId(attributeId));
    }


    /*------------------------------------------ 以上是系统访问控制 --------------------------------------------*/

}
