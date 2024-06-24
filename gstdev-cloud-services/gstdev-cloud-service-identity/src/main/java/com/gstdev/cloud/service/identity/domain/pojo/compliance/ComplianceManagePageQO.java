package com.gstdev.cloud.service.identity.domain.pojo.compliance;

import com.gstdev.cloud.data.core.annotations.Query;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComplianceManagePageQO {
    @Query(type = Query.Type.INNER_LIKE)
    private String principalName;
    @Query(type = Query.Type.INNER_LIKE)
    private String clientId;
    @Query(type = Query.Type.INNER_LIKE)
    private String ip;
    @Query(type = Query.Type.INNER_LIKE)
    private String osName;
    @Query(type = Query.Type.INNER_LIKE)
    private String browserName;
    private Boolean mobileBrowser;
    @Query(type = Query.Type.INNER_LIKE)
    private String engineName;
    @Query(type = Query.Type.INNER_LIKE)
    private String operation;
}
