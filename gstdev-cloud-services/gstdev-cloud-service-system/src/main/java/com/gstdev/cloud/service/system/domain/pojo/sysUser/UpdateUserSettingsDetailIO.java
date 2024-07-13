package com.gstdev.cloud.service.system.domain.pojo.sysUser;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserSettingsDetailIO {
    private Integer gender;
    private String nickname;
    private String avatar;
    private String firstName;
    private String lastName;
    private String description;

    private String accountId;
    private String accountName;
}
