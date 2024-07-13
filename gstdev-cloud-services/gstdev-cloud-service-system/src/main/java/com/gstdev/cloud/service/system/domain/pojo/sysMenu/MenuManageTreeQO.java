package com.gstdev.cloud.service.system.domain.pojo.sysMenu;

import com.gstdev.cloud.data.core.annotations.Query;
import com.gstdev.cloud.data.core.enums.DataItemStatus;
import com.gstdev.cloud.service.system.domain.enums.SysMenuType;
import lombok.Data;

import java.util.Set;

/**
 * @author WenchaoChen
 */
@Data
public class MenuManageTreeQO {
    @Query(type = Query.Type.INNER_LIKE)
    private String menuName;
    @Query(type = Query.Type.INNER_LIKE)
    private String path;
    @Query(type = Query.Type.IN)
    private Set<SysMenuType> type;
    @Query(type = Query.Type.IN)
    private Set<DataItemStatus> status;

}
