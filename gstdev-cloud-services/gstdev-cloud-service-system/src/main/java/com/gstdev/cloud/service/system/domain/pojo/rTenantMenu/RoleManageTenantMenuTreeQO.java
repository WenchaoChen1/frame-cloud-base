package com.gstdev.cloud.service.system.domain.pojo.rTenantMenu;

import com.gstdev.cloud.data.core.annotations.Query;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleManageTenantMenuTreeQO {
    @Query()
    private String tenantId;
}
