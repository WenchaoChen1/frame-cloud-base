package com.gstdev.cloud.service.system.domain.pojo.sysPermission;

import com.gstdev.cloud.data.core.annotations.Query;
import com.gstdev.cloud.data.core.enums.DataItemStatus;
import com.gstdev.cloud.data.core.utils.BasePage;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Data
public class PermissionManageQO implements Serializable {

    private static final long serialVersionUID = 3163118978801722144L;
    @Query(type = Query.Type.IN)
    private Set<String> permissionId;
    @Query(type = Query.Type.INNER_LIKE)
    private String permissionName;
    @Query(type = Query.Type.INNER_LIKE)
    private String permissionCode;
    @Query(type = Query.Type.IN)
    private Set<DataItemStatus> status;
    @Query(type = Query.Type.IN)
    private Set<String> permissionType;

    private BasePage page;

}
