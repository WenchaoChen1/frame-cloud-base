package com.gstdev.cloud.service.system.domain.pojo.sysPermission;

import com.gstdev.cloud.data.core.enums.DataItemStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InsertPermissionManageIO {

    private String permissionCode;
    private String permissionName;
    private String permissionType;
    @Enumerated(EnumType.ORDINAL)
    private DataItemStatus status = DataItemStatus.ENABLE;
}
