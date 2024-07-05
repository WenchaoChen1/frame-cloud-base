package com.gstdev.cloud.service.identity.domain.pojo.authorization;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AuthorizationManagePageVO {
    private String id;
    private String principalName;
    private String authorizationGrantType;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date accessTokenIssuedAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date accessTokenExpiresAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date refreshTokenIssuedAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date refreshTokenExpiresAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdDate;
    private String createdUser;
    private String createdAccount;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedDate;
    private String updatedUser;
    private String updatedAccount;
}
