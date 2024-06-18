package com.gstdev.cloud.service.identity.domain.pojo.application;

import com.gstdev.cloud.data.core.annotations.Query;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicationManageQO {
    @Query(type = Query.Type.INNER_LIKE)
    private String applicationName;
    @Query(type = Query.Type.INNER_LIKE)
    private String abbreviation;
}
