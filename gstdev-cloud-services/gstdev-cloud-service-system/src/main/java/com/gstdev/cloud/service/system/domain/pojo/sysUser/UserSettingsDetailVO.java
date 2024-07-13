package com.gstdev.cloud.service.system.domain.pojo.sysUser;

import com.gstdev.cloud.data.core.enums.DataItemStatus;
import com.gstdev.cloud.service.system.domain.enums.SysAccountType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSettingsDetailVO {
    private String userId;
    private String username;
    private String phoneNumber;
    private String email;
    private Integer gender;
    private String nickname;
    private String avatar;
    private DataItemStatus status;

    private String firstName;
    private String lastName;
    private String description;
    private String accountId;
    private String accountName;
    private String tenantName;
    private SysAccountType accountType;


}
