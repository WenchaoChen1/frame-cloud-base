package com.gstdev.cloud.service.system.domain.generator;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class SysRAccountTenantMenuEmbeddablePK implements Serializable {
    private String accountId;
    private String tenantMenuId;

}
