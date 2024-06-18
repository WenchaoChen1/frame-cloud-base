package com.gstdev.cloud.service.identity.domain.pojo.scope;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gstdev.cloud.data.core.enums.DataItemStatus;
import com.gstdev.cloud.oauth2.core.enums.AllJwsAlgorithm;
import com.gstdev.cloud.oauth2.core.enums.ApplicationType;
import com.gstdev.cloud.oauth2.core.enums.SignatureJwsAlgorithm;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

@Getter
@Setter
public class InsertScopeManageIO {
    private String scopeCode;
    private String scopeName;
}
