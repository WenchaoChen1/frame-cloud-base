package com.gstdev.cloud.service.system.domain.pojo.sysAccount;

import com.gstdev.cloud.data.core.annotations.Query;
import com.gstdev.cloud.data.core.enums.DataItemStatus;
import lombok.Data;

import java.util.Set;

@Data
public class AccountManageQO {

    private static final long serialVersionUID = 3163118978801722144L;

    @Query
    private String tenantId;
    @Query(type = Query.Type.INNER_LIKE)
    private String name;
    @Query(type = Query.Type.INNER_LIKE)
    private String identity;

    @Query(type = Query.Type.IN)
    private Set<DataItemStatus> status;
}
