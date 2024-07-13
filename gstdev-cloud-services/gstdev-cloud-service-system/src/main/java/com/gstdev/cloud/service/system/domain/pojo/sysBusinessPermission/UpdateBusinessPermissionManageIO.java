package com.gstdev.cloud.service.system.domain.pojo.sysBusinessPermission;

import com.gstdev.cloud.data.core.enums.DataItemStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateBusinessPermissionManageIO {

    private String businessPermissionId;
//    private String tenantId;
    private String parentId;
    private String name;
    private String code;
    private Integer sort;
    private String description;
    @Enumerated(EnumType.ORDINAL)
    private DataItemStatus status = DataItemStatus.ENABLE;
}
