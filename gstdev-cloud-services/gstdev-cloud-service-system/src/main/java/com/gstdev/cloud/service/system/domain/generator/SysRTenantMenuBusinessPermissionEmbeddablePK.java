package com.gstdev.cloud.service.system.domain.generator;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class SysRTenantMenuBusinessPermissionEmbeddablePK implements Serializable {
    private String tenantMenuId;
    private String businessPermissionId;
}
