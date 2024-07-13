package com.gstdev.cloud.service.system.domain.pojo.sysAttribute;

import com.gstdev.cloud.data.core.annotations.Query;
import com.gstdev.cloud.data.core.enums.DataItemStatus;
import com.gstdev.cloud.data.core.utils.BasePage;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Data
public class MenuManageAttributePageQO implements Serializable {

    private static final long serialVersionUID = 3163118978801722144L;
    @Query(type = Query.Type.IN)
    private Set<String> attributeId;
    @Query(type = Query.Type.INNER_LIKE)
    private String attributeCode;
    @Query(type = Query.Type.INNER_LIKE)
    private String requestMethod;
    @Query(type = Query.Type.INNER_LIKE)
    private String url;
    @Query(type = Query.Type.INNER_LIKE)
    private String serviceId;
    @Query(type = Query.Type.INNER_LIKE)
    private String className;
    @Query(type = Query.Type.INNER_LIKE)
    private String methodName;
    @Query(type = Query.Type.INNER_LIKE)
    private String webExpression;
    @Query(type = Query.Type.INNER_LIKE)
    private String description;
    @Query(type = Query.Type.IN)
    private Set<DataItemStatus> status;

    private  BasePage page;
}
