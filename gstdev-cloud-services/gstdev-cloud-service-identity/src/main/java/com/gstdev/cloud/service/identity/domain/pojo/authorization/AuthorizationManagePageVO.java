package com.gstdev.cloud.service.identity.domain.pojo.authorization;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

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
}
