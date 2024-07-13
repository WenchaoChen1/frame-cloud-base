package com.gstdev.cloud.service.system.domain.vo.TenantDict;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class TenantDictModifyInput {
    String id;
    String name;
    String parentId;
    String tenantId;
    String code;
    Integer status;
    Integer sort;
    String description;
}
