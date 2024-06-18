package com.gstdev.cloud.service.identity.domain.scope;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gstdev.cloud.data.core.enums.DataItemStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;

@Getter
@Setter
public class ScopeManagePageVO {

    private String scopeId;
    private String scopeCode;
    private String scopeName;

}
