package com.gstdev.cloud.service.identity.domain.pojo.application;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gstdev.cloud.data.core.enums.DataItemStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.util.Date;

@Getter
@Setter
public class ApplicationManagePageVO {

    private String applicationId;
    private String applicationName;
    private String abbreviation;
    private String authorizationGrantTypes;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Duration accessTokenValidity;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Duration refreshTokenValidity;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Duration authorizationCodeValidity;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Duration deviceCodeValidity;
    private DataItemStatus status;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdDate;
    private String createdUser;
    private String createdAccount;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedDate;
    private String updatedUser;
    private String updatedAccount;

}
