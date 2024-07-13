package com.gstdev.cloud.service.system.domain.pojo.sysAccount;

import com.gstdev.cloud.data.core.enums.DataItemStatus;
import com.gstdev.cloud.service.system.domain.enums.SysAccountType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InsertAccountManageIO {

    private String name;
    private String identity;
    private String tenantId;
    private String userId;
    private DataItemStatus status;
    private SysAccountType type;

}
