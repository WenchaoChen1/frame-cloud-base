package com.gstdev.cloud.service.identity.domain.application;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gstdev.cloud.data.core.annotations.Query;
import com.gstdev.cloud.data.core.enums.DataItemStatus;
import com.gstdev.cloud.oauth2.core.enums.ApplicationType;
import com.gstdev.cloud.oauth2.core.enums.TokenFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.time.Instant;

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

}
