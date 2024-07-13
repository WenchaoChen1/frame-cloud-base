package com.gstdev.cloud.service.system.domain.pojo.sysRAttributeMenu;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;
@Getter
@Setter
public class UpdateMenuAssignedAttributesIO {
    private String menuId;
    private Set<String> attributeIds;
}
