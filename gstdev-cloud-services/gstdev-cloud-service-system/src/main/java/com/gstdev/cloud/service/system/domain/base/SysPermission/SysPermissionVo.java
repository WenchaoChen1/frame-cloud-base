package com.gstdev.cloud.service.system.domain.base.SysPermission;

import com.gstdev.cloud.data.core.enums.DataItemStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SysPermissionVo {

    private String permissionId;

    @Schema(title = "权限代码")
    private String permissionCode;

    @Schema(title = "权限名称")
    private String permissionName;
    private String permissionType;
    //    @Enumerated(EnumType.ORDINAL)
    private DataItemStatus status;
}
