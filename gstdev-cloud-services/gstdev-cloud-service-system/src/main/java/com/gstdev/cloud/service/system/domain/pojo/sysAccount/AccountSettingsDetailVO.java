package com.gstdev.cloud.service.system.domain.pojo.sysAccount;

import com.gstdev.cloud.service.system.domain.enums.SysAccountType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountSettingsDetailVO {
    private String accountId;
    private String name;
    private String tenantName;
    private SysAccountType type;
}
