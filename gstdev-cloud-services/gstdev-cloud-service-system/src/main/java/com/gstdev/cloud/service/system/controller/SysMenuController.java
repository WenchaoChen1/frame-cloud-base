// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.service.system.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.gstdev.cloud.base.core.json.jackson2.utils.Jackson2Utils;
import com.gstdev.cloud.base.core.utils.SecureUtil;
import com.gstdev.cloud.base.definition.domain.Result;
import com.gstdev.cloud.base.definition.exception.PlatformRuntimeException;
import com.gstdev.cloud.data.core.enums.DataItemStatus;
import com.gstdev.cloud.data.core.utils.QueryUtils;
import com.gstdev.cloud.rest.core.controller.ResultController;
import com.gstdev.cloud.service.system.domain.entity.SysAttribute;
import com.gstdev.cloud.service.system.domain.entity.SysMenu;
import com.gstdev.cloud.service.system.domain.entity.SysRAttributeMenu;
import com.gstdev.cloud.service.system.domain.pojo.sysMenu.*;
import com.gstdev.cloud.service.system.mapper.SysMenuMapper;
import com.gstdev.cloud.service.system.service.SysAttributeService;
import com.gstdev.cloud.service.system.service.SysMenuService;
import com.gstdev.cloud.service.system.service.SysRAttributeMenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/v1/menu")
public class SysMenuController implements ResultController {
    @Resource
    @Lazy
    private SysMenuService menuService;
    @Resource
    @Lazy
    private SysRAttributeMenuService sysRAttributeMenuService;
    @Resource
    private SysMenuMapper menuMapper;
    @Autowired
    private SysMenuService sysMenuService;
    @Autowired
    private SysAttributeService sysAttributeService;

    public SysMenuService getService() {
        return menuService;
    }

    public SysMenuMapper getMapper() {
        return menuMapper;
    }

    // ********************************* menu Manage *****************************************
    @Tag(name = "Tenant Manage")
    @GetMapping("/get-menu-manage-tree")
    @Operation(summary = "get-menu-manage-tree")
    public Result<List<MenuManageTreeVo>> getMenuManageMageTree(MenuManageTreeQO queryCriteria) {
        List<SysMenu> byPage = this.getService().findAll((root, criteriaQuery, criteriaBuilder) -> QueryUtils.getPredicate(root, queryCriteria, criteriaBuilder));
        return this.result(this.getMapper().toMenuManageTreeVoToTree(byPage));
    }

    @Tag(name = "Tenant Manage")
    @GetMapping("/get-menu-manage-detail/{id}")
    @Operation(summary = "get-menu-manage-detail")
    public Result<MenuManageDetailVo> getMenuManageDetail(@PathVariable String id) {
        return result(this.getMapper().toMenuManageDetailVo(getService().findById(id)));
    }

    @Tag(name = "Tenant Manage")
    @PostMapping("/insert-menu-manage")
    @Operation(summary = "insert-menu-manage")
    public Result<String> insertMenuManage(@RequestBody @Validated InsertMenuManageIO insertMenuManageIO) {
        this.getService().insertMenuManage(insertMenuManageIO);
        return Result.success();
    }

    @Tag(name = "Tenant Manage")
    @PutMapping("/update-menu-manage")
    @Operation(summary = "update-menu-manage")
    public Result<String> updateMenuManage(@RequestBody @Validated UpdateMenuManageIO updateMenuManageIO) {
        this.getService().updateMenuManage(updateMenuManageIO);
        return Result.success();
    }

    @Tag(name = "Tenant Manage")
    @Operation(summary = "delete-menu-manage")
    @DeleteMapping("/delete-menu-manage/{id}")
    public Result<String> deleteMenuManage(@PathVariable String id) {
        getService().deleteById(id);
        return Result.success();
    }

    @Tag(name = "Tenant Manage")
    @Operation(summary = "delete-all-menu-manage")
    @DeleteMapping("/delete-all-menu-manage")
    public Result<String> deleteAllMenuManage(List<String> id) {
        getService().deleteAllById(id);
        return Result.success();
    }

    @Tag(name = "Menu Manage")
    @Operation(summary = "upload-menu-manage")
    @PostMapping("/upload-menu-manage")
    public Result<String> uploadMenuManageJsonFile(@RequestParam("file") MultipartFile file) throws IOException {
        // 将 MultipartFile 转换为 User 对象
        List<SysMenu> sysMenus = Jackson2Utils.getObjectMapper().readValue(file.getInputStream(), new TypeReference<List<SysMenu>>() {
        });
        Map<String, SysMenu> collect = sysMenus.stream()
            .collect(Collectors.toMap(SysMenu::getId, sysMenu -> sysMenu));
        // 根据code分组并将结果映射为Map，key为id，value为实体
        List<SysMenu> all = getService().findAll();
        List<SysMenu> sysMenuLists = new ArrayList<>();
        Map<String, SysMenu> groupedById = all.stream()
            .collect(Collectors.toMap(SysMenu::getId, sysMenu -> sysMenu));
        Map<String, SysMenu> groupedByCode = all.stream()
            .collect(Collectors.toMap(SysMenu::getCode, sysMenu -> sysMenu));
        for (SysMenu sysMenu : sysMenus) {
            sysMenu.setId(SecureUtil.md5(sysMenu.getCode()));

            System.out.println(collect.get(sysMenu.getParentId()));
            if (!ObjectUtils.isEmpty(collect.get(sysMenu.getParentId()))) {
                System.out.println(collect.get(sysMenu.getParentId()).getCode()+":1111111:"+SecureUtil.md5(collect.get(sysMenu.getParentId()).getCode()));
                sysMenu.setParentId(SecureUtil.md5(collect.get(sysMenu.getParentId()).getCode()));
            }

            if (groupedById.containsKey(sysMenu.getId())) {
                if (!sysMenu.getId().equals(SecureUtil.md5(groupedById.get(sysMenu.getId()).getCode()))) {
                    throw new PlatformRuntimeException("Menu data already exists, please delete the data first.:" + sysMenu.toString() + "new:" + groupedById.get(sysMenu.getId()));
                }
                if (sysMenu.getCode().equals(groupedById.get(sysMenu.getId()).getCode())) {
                    if (!groupedById.get(sysMenu.getId()).getName().equals(sysMenu.getName())
                        || !groupedById.get(sysMenu.getId()).getPath().equals(sysMenu.getPath())
                        || !groupedById.get(sysMenu.getId()).getType().equals(sysMenu.getType())
                        || !groupedById.get(sysMenu.getId()).getLocation().equals(sysMenu.getLocation())) {
                        sysMenu.setStatus(DataItemStatus.LOCKING);
                    }
                    sysMenuLists.add(sysMenu);
                    continue;
                }
                throw new PlatformRuntimeException("Menu data already exists, please delete the data first.:" + sysMenu.toString() + "new:" + groupedById.get(sysMenu.getId()));
            }
            if (groupedByCode.containsKey(sysMenu.getCode())) {
                throw new PlatformRuntimeException("Menu data already exists, please delete the data first:" + sysMenu.toString() + "new:" + groupedById.get(sysMenu.getId()));
            }
            sysMenu.setStatus(DataItemStatus.FORBIDDEN);
            sysMenuLists.add(sysMenu);

        }
        getService().saveAllAndFlush(sysMenuLists);
        return Result.success();
    }

    @Tag(name = "Menu Manage")
    @Operation(summary = "download-menu-manage")
    @GetMapping("/download-menu-manage")
    public ResponseEntity<byte[]> downloadMenuManageJsonFile() throws JsonProcessingException {
        List<SysMenu> all = getService().findAll();
        // 将实体对象转换为JSON字符串
        byte[] jsonBytes = Jackson2Utils.getObjectMapper().writeValueAsBytes(all);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setContentDispositionFormData("attachment", "menu.json");

        return new ResponseEntity<>(jsonBytes, headers, HttpStatus.OK);
    }

    @Tag(name = "Menu Manage")
    @Operation(summary = "upload-menu-manage-assigned-attribute")
    @PostMapping("/upload-menu-manage-assigned-attribute")
    public Result<String> uploadMenuManageAssignedAttributeJsonFile(@RequestParam("file") MultipartFile file) throws IOException {

        // 将 MultipartFile 转换为 User 对象
        List<SysRAttributeMenu> sysRAttributeMenus = Jackson2Utils.getObjectMapper().readValue(file.getInputStream(), new TypeReference<List<SysRAttributeMenu>>() {
        });

//        List<SysRAttributeMenu> sysRAttributeMenuList = sysRAttributeMenuService.findAll();
        List<String> sysMenuIdList = sysMenuService.findAll().stream().map(SysMenu::getId).toList();
        List<String> sysAttributeIdList = sysAttributeService.findAll().stream().map(SysAttribute::getAttributeId).toList();
        List<SysRAttributeMenu> sysRAttributeMenuLists = new ArrayList<>();
        for (SysRAttributeMenu sysRAttributeMenu : sysRAttributeMenus) {
            if (!sysMenuIdList.contains(sysRAttributeMenu.getMenuId()) || !sysAttributeIdList.contains(sysRAttributeMenu.getAttributeId())) {
                throw new PlatformRuntimeException("Menu data already exists, please delete the data first");
            }
            sysRAttributeMenuLists.add(sysRAttributeMenu);
        }

//        if (sysRAttributeMenuList.size() > 1) {
//            throw new PlatformRuntimeException("Menu data already exists, please delete the data first");
//        }

        sysRAttributeMenuService.saveAllAndFlush(sysRAttributeMenuLists);
        return Result.success();
    }

    @Tag(name = "Menu Manage")
    @Operation(summary = "download-menu-manage-assigned-attribute")
    @GetMapping("/download-menu-manage-assigned-attribute")
    public ResponseEntity<byte[]> downloadMenuManageAssignedAttributeJsonFile() throws JsonProcessingException {
        List<SysRAttributeMenu> all = sysRAttributeMenuService.findAll();
        // 将实体对象转换为JSON字符串
        byte[] jsonBytes = Jackson2Utils.getObjectMapper().writeValueAsBytes(all);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setContentDispositionFormData("attachment", "menu.json");

        return new ResponseEntity<>(jsonBytes, headers, HttpStatus.OK);
    }
    /*------------------------------------------以上是系统访问控制自定义代码--------------------------------------------*/

    @Tag(name = "Tenant Manage")
    @GetMapping("/get-tenant-manage-menu-tree")
    @Operation(summary = "get-tenant-manage-menu-tree获取所有菜单，返回树状结构")
    public Result<List<MenuManageTreeVo>> getTenantManageMenuTree(MenuManageTreeQO queryCriteria) {
        List<SysMenu> byPage = this.getService().findAll((root, criteriaQuery, criteriaBuilder) -> QueryUtils.getPredicate(root, queryCriteria, criteriaBuilder));
        return this.result(this.getMapper().toMenuManageTreeVoToTree(byPage));
    }

//    /**
//     * 获取指定租户的所有菜单，返回树状结构
//     * @param tenantId
//     * @return
//     */
//    @Tag(name = "Role Manage")
//    @GetMapping("/get-all-by-tenant-menu-to-tree")
//    @Operation(summary = "get-all-by-tenant-menu-to-tree")
//    public Result<List<MenuVo>> getAllByTenantMenuToTree(@NotBlank @RequestParam("tenantId") String tenantId) {
//        MenuFindAllByQueryCriteria menuFindAllByQueryCriteria = new MenuFindAllByQueryCriteria();
//        menuFindAllByQueryCriteria.setTenantId(tenantId);
//        return findAllByQueryCriteriaToResultToTree(menuFindAllByQueryCriteria);
//    }
//
//    @GetMapping("/get-all-by-role-menu-to-tree")
//    @Operation(summary = "/get-all-by-role-menu-to-tree")
//    public Result<List<MenuVo>> getAllByRoleMenuToTree(@NotBlank @RequestParam("roleId") String roleId) {
//        return getMapper().toAllVo(getService().getAllByRoleMenuToTree(roleId));
//    }
//    @GetMapping("/get-all-tenant-menu-id/{tenantId}")
//    @Operation(summary = "获取指定租户的所有菜单")
//    public Result<MenuVo> getAllTenantMenuIds(@NotBlank @RequestParam("tenantId") String tenantId) {
//        return getMapper().toVo(getService().getAllTenantMenuIds(tenantId));
//    }

}
