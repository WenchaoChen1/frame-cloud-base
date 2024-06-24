package com.gstdev.cloud.service.identity.domain.pojo.application;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class ApplicationManageAssignedScopeIO {
    private String applicationId;
    private Set<String> scopeIds;
}
