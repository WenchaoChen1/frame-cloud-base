package com.gstdev.cloud.service.system.domain.generator;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class SysRRoleBusinessPermissionEmbeddablePK implements Serializable {
    private String roleId;
    private String businessPermissionId;

}
