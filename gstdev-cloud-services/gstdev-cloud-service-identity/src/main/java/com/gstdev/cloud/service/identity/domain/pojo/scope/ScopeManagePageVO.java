package com.gstdev.cloud.service.identity.domain.pojo.scope;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ScopeManagePageVO {

    private String scopeId;
    private String scopeCode;
    private String scopeName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdDate;
    private String createdUser;
    private String createdAccount;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedDate;
    private String updatedUser;
    private String updatedAccount;
}
