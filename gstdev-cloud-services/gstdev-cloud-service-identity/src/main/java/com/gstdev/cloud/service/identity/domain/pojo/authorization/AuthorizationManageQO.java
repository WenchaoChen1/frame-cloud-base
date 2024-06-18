package com.gstdev.cloud.service.identity.domain.pojo.authorization;

import com.gstdev.cloud.data.core.annotations.Query;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorizationManageQO {
    @Query
    private String applicationName;
}
