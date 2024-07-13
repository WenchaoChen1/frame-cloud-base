// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by gstdev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.service.system.domain.pojo.sysRole;

import com.gstdev.cloud.service.system.util.TreeNode;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class RoleManageRoleDetaiToListVo extends TreeNode<String, RoleManageRoleDetaiToListVo> {

    private String roleId;
    private String roleName;
    private String parentId;
    private Integer sort;
//    List<RoleManageRoleDetaiToListVo> children;
    public void setRoleId(String roleId) {
        this.roleId = roleId;
        super.setId(roleId);
    }
}

