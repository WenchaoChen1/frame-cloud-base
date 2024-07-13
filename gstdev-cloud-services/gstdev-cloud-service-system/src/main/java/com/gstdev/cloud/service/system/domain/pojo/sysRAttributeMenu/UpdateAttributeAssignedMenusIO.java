package com.gstdev.cloud.service.system.domain.pojo.sysRAttributeMenu;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UpdateAttributeAssignedMenusIO {
    private String attributeId;
    private Set<String> menuIds;
}
