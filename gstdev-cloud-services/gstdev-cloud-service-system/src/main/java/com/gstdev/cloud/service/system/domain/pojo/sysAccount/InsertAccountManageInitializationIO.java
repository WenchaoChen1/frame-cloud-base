package com.gstdev.cloud.service.system.domain.pojo.sysAccount;

import com.gstdev.cloud.data.core.enums.DataItemStatus;
import com.gstdev.cloud.service.system.domain.enums.SysAccountType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class InsertAccountManageInitializationIO {

    List<String> departIds;
    List<String> roleIds;
    private String tenantId;
    private String userId;
    private String name;
    private DataItemStatus status;
    private SysAccountType type;
}
