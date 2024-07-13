package com.gstdev.cloud.service.system.domain.pojo.sysAccount;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gstdev.cloud.data.core.enums.DataItemStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class AccountManageBusinessPermissionTreeVo {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdDate;
    private String createdUser;
    private String createdAccount;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedDate;
    private String updatedUser;
    private String updatedAccount;

    private String businessPermissionId;
    private String parentId;
    private String name;
    private String code;
    private DataItemStatus status;
    private Integer sort;
    private String description;
    private String tenantId;
    private List<AccountManageBusinessPermissionTreeVo> children;
}
