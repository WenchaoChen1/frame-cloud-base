package com.gstdev.cloud.service.identity.domain.application;

import com.gstdev.cloud.data.core.annotations.Query;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicationManageQO {
    @Query
    private String applicationName;
}
