package com.gstdev.cloud.service.system.domain.pojo.sysRole;

import com.gstdev.cloud.data.core.annotations.Query;
import lombok.Data;

/**
 * @author WenchaoChen
 */
@Data
public class RoleManageRoleDetaiToListQO {

    @Query
    private String tenantId;
    @Query(type = Query.Type.INNER_LIKE)
    private String roleName;
}
