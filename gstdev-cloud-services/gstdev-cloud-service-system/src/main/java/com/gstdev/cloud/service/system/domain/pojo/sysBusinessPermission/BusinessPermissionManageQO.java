package com.gstdev.cloud.service.system.domain.pojo.sysBusinessPermission;

import com.gstdev.cloud.data.core.annotations.Query;
import com.gstdev.cloud.data.core.enums.DataItemStatus;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Data
public class BusinessPermissionManageQO implements Serializable {

    private static final long serialVersionUID = 3163118978801722144L;
    @Query
    private String tenantId;
    @Query(type = Query.Type.INNER_LIKE)
    private String name;
    @Query(type = Query.Type.INNER_LIKE)
    private String code;    @Query(type = Query.Type.INNER_LIKE)
    private String description;
    @Query(type = Query.Type.IN)
    private Set<DataItemStatus> status;

}
