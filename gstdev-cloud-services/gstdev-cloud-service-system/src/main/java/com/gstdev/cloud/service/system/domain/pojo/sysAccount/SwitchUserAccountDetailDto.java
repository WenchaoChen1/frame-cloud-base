package com.gstdev.cloud.service.system.domain.pojo.sysAccount;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SwitchUserAccountDetailDto {
    private String accountId;
    private String accountName;

    public void setName(String name) {
        this.accountName = name;
    }
}
