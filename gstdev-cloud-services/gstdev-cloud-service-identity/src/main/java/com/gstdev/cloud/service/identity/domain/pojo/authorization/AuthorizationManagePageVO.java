package com.gstdev.cloud.service.identity.domain.pojo.authorization;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Date;

@Getter
@Setter
public class AuthorizationManagePageVO {
    private String id;
    private String principalName;
    private String authorizationGrantType;
    private Instant accessTokenIssuedAt;
    private Instant accessTokenExpiresAt;
    private Instant refreshTokenIssuedAt;
    private Instant refreshTokenExpiresAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdDate;
    private String createdUser;
    private String createdAccount;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedDate;
    private String updatedUser;
    private String updatedAccount;
}
