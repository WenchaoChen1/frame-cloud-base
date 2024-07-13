package com.gstdev.cloud.service.system.domain.pojo.sysMenu;

import com.gstdev.cloud.data.core.annotations.Query;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountMenuPermissionsQO {

    @Query(propName = "tenantId", joinName = "rTenantMenus", join = Query.Join.LEFT)
    String tenantId;
}
