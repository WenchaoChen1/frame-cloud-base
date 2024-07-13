package com.gstdev.cloud.service.system.domain.pojo.sysTenant;

import com.gstdev.cloud.data.core.annotations.Query;
import com.gstdev.cloud.data.core.enums.DataItemStatus;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Data
public class TenantManageTreeQO implements Serializable {
    private static final long serialVersionUID = 3163118978801722144L;

    @Query(type = Query.Type.EQUAL)
    private Integer type;
    @Query(type = Query.Type.INNER_LIKE)
    private String tenantCode;
    @Query(type = Query.Type.INNER_LIKE)
    private String description;
    @Query(type = Query.Type.IN)
    private Set<DataItemStatus> status;
    @Query(blurry = "tenantName", type = Query.Type.INNER_LIKE)
    private String tenantName;
    //  @Query
    private String tenantId;
    //  @Query(blurry = "tenantId", type = Query.Type.IN)
    @Query(type = Query.Type.IN)
//    @Query(blurry = "id",type = Query.Type.EQUAL)
    private List<String> id;


    public List<String> getTenantIds() {
        return id;
    }

    public void setTenantIds(List<String> tenantIds) {
        this.id = tenantIds;
    }
}
