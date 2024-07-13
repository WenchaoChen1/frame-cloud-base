package com.gstdev.cloud.service.system.domain.pojo.sysAttribute;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class AttributeManageAssignedPermissionIO {
    private String attributeId;
    private Set<String> permissionIds;
}
