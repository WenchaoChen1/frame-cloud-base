package com.gstdev.cloud.service.identity.domain.scope;

import com.gstdev.cloud.data.core.annotations.Query;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScopeManageQO {
    @Query
    private String scopeCode;
    @Query
    private String scopeName;
}
