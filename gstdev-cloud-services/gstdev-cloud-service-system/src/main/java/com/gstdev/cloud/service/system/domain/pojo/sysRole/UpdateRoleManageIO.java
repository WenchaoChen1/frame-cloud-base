package com.gstdev.cloud.service.system.domain.pojo.sysRole;

import com.gstdev.cloud.data.core.enums.DataItemStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateRoleManageIO {

    private String roleId;
    private String code;
    private String description;
    private String parentId;
    private String roleName;
    private Integer sort;
    private DataItemStatus status;
//    private String tenantId;
}
