package com.gstdev.cloud.service.system.domain.pojo.sysBusinessPermission;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UpdateBusinessPermissionAssignedTenantMenuIO {
    @NotBlank
    private String businessPermissionId;
    private List<String> tenantMenuIds;
}
