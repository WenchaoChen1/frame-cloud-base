package com.gstdev.cloud.service.system.domain.pojo.sysRole;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UpdateRoleAssignedBusinessPermissionIO {
    @NotBlank
    private String roleId;
    private List<String> businessPermissionIds;
}
