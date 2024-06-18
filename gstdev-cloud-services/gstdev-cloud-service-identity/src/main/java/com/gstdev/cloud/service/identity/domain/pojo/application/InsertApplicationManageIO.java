package com.gstdev.cloud.service.identity.domain.pojo.application;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gstdev.cloud.base.core.json.jackson2.deserializer.CommaDelimitedStringToSetSerializer;
import com.gstdev.cloud.base.core.json.jackson2.deserializer.SetToCommaDelimitedStringDeserializer;
import com.gstdev.cloud.data.core.enums.DataItemStatus;
import com.gstdev.cloud.oauth2.core.enums.AllJwsAlgorithm;
import com.gstdev.cloud.oauth2.core.enums.ApplicationType;
import com.gstdev.cloud.oauth2.core.enums.SignatureJwsAlgorithm;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

@Getter
@Setter
public class InsertApplicationManageIO {
    private String applicationName;
    private String abbreviation;
    private String logo;
    private String homepage;
    private ApplicationType applicationType;
    private String authorizationGrantTypes;
    private String clientAuthenticationMethods;
    private Instant clientSecretExpiresAt;
    private String redirectUris;
    private String postLogoutRedirectUris;
    private Boolean requireProofKey = Boolean.FALSE;
    private Boolean requireAuthorizationConsent = Boolean.TRUE;
    private String jwkSetUrl;
    private AllJwsAlgorithm authenticationSigningAlgorithm;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Duration accessTokenValidity = Duration.ofMinutes(5);
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Duration refreshTokenValidity = Duration.ofMinutes(60);
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Duration authorizationCodeValidity = Duration.ofMinutes(5);
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Duration deviceCodeValidity = Duration.ofMinutes(5);
    private Boolean reuseRefreshTokens = Boolean.TRUE;
    private SignatureJwsAlgorithm idTokenSignatureAlgorithmJwsAlgorithm = SignatureJwsAlgorithm.RS256;


    private DataItemStatus status = DataItemStatus.ENABLE;
    private Boolean reserved = Boolean.FALSE;
    private Integer reversion = 0;
    private String description;

    public void setAuthorizationGrantTypes(List<String> authorizationGrantTypes) {
        this.authorizationGrantTypes = StringUtils.collectionToCommaDelimitedString(authorizationGrantTypes);
    }

    public void setClientAuthenticationMethods(List<String> clientAuthenticationMethods) {
        this.clientAuthenticationMethods = StringUtils.collectionToCommaDelimitedString(clientAuthenticationMethods);
    }
}
