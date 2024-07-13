package com.gstdev.cloud.service.system.domain.pojo.sysTenant;

import com.gstdev.cloud.data.core.annotations.Query;
import lombok.Data;

/**
 * @author WenchaoChen
 */
@Data
public class AccountManageTenantDetaiToListQO {

    @Query
    private String tenantId;
    @Query(type = Query.Type.INNER_LIKE)
    private String tenantName;
}
