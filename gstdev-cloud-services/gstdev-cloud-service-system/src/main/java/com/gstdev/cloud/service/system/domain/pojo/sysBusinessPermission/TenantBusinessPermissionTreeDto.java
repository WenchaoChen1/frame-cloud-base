package com.gstdev.cloud.service.system.domain.pojo.sysBusinessPermission;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gstdev.cloud.data.core.enums.DataItemStatus;
import com.gstdev.cloud.service.system.util.TreeNode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class TenantBusinessPermissionTreeDto extends TreeNode<String, TenantBusinessPermissionTreeDto> {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdDate;
    private String createdUser;
    private String createdAccount;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedDate;
    private String updatedUser;
    private String updatedAccount;

    private String businessPermissionId;
    private String parentId;
    private String name;
    private String code;
    private DataItemStatus status;
    private Integer sort;
    private String description;
    private String tenantId;

    public void setBusinessPermissionId(String businessPermissionId) {
        this.businessPermissionId = businessPermissionId;
        super.setId(businessPermissionId);
    }
}
