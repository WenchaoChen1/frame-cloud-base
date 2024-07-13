package com.gstdev.cloud.service.system.domain.pojo.sysUser;

import com.gstdev.cloud.data.core.annotations.Query;
import com.gstdev.cloud.data.core.enums.DataItemStatus;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Data
public class UserManageQO implements Serializable {

    private static final long serialVersionUID = 3163118978801722144L;
    @Query(type = Query.Type.INNER_LIKE)
    private String username;
    @Query(type = Query.Type.INNER_LIKE)
    private String email;
    @Query(type = Query.Type.INNER_LIKE)
    private String phoneNumber;

    @Query(type = Query.Type.IN)
    private Set<DataItemStatus> status;
}
